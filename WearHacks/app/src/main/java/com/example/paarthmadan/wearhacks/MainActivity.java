package com.example.paarthmadan.wearhacks;

/**
 * Example of using libmuse library on android.
 * Interaxon, Inc. 2015
 */

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;



import com.interaxon.libmuse.Accelerometer;
import com.interaxon.libmuse.AnnotationData;
import com.interaxon.libmuse.ConnectionState;
import com.interaxon.libmuse.Eeg;
import com.interaxon.libmuse.LibMuseVersion;
import com.interaxon.libmuse.MessageType;
import com.interaxon.libmuse.Muse;
import com.interaxon.libmuse.MuseArtifactPacket;
import com.interaxon.libmuse.MuseConfiguration;
import com.interaxon.libmuse.MuseConnectionListener;
import com.interaxon.libmuse.MuseConnectionPacket;
import com.interaxon.libmuse.MuseDataListener;
import com.interaxon.libmuse.MuseDataPacket;
import com.interaxon.libmuse.MuseDataPacketType;
import com.interaxon.libmuse.MuseFileFactory;
import com.interaxon.libmuse.MuseFileReader;
import com.interaxon.libmuse.MuseFileWriter;
import com.interaxon.libmuse.MuseManager;
import com.interaxon.libmuse.MusePreset;
import com.interaxon.libmuse.MuseVersion;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;





public class MainActivity extends Activity implements View.OnClickListener {

    final int LIMIT_STRESS_RANGE = 1000;
    int counter = 0;
    int calibrationCounter = 0;
    double totalFirst = 0;
    double totalSecond = 0;
    double LOW_POINT = 0;
    double HIGH_POINT = 0;

    final int HIGH_NUMBER = 5000;
    final int LOW_NUMBER = HIGH_NUMBER/2;

    int lowpointInt = 0;
    int highpointInt = 0;


    public void main (View view){



        System.out.println(averageegg);

    }

    public static double accelforward;
    public static double accelup;
    public static double accelleft;

    public static  double averageaccel;


    public  static double eggtp9;
    public static double eggfp1;
    public static double eggfp2;
    public static  double eggtp10;

    public static   double averageegg;





    /**
     * Connection listener updates UI with new connection status and logs it.
     */
    class ConnectionListener extends MuseConnectionListener {

        final WeakReference<Activity> activityRef;

        ConnectionListener(final WeakReference<Activity> activityRef) {
            this.activityRef = activityRef;
        }

        @Override
        public void receiveMuseConnectionPacket(MuseConnectionPacket p) {
            final ConnectionState current = p.getCurrentConnectionState();
            final String status = p.getPreviousConnectionState().toString() +
                    " -> " + current;
            final String full = "Muse " + p.getSource().getMacAddress() +
                    " " + status;
            Log.i("Muse Headband", full);
            Activity activity = activityRef.get();
            // UI thread is used here only because we need to update
            // TextView values. You don't have to use another thread, unless
            // you want to run disconnect() or connect() from connection packet
            // handler. In this case creating another thread is required.
            if (activity != null) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView statusText =
                                (TextView) findViewById(R.id.con_status);
                        statusText.setText(status);


                        if (current == ConnectionState.CONNECTED) {
                            MuseVersion museVersion = muse.getMuseVersion();
                            String version = museVersion.getFirmwareType() +
                                    " - " + museVersion.getFirmwareVersion() +
                                    " - " + Integer.toString(
                                    museVersion.getProtocolVersion());

                        } else {

                        }
                    }
                });
            }
        }
    }

    /**
     * Data listener will be registered to listen for: Accelerometer,
     * Eeg and Relative Alpha bandpower packets. In all cases we will
     * update UI with new values.
     * We also will log message if Artifact packets contains "blink" flag.
     * DataListener methods will be called from execution thread. If you are
     * implementing "serious" processing algorithms inside those listeners,
     * consider to create another thread.
     */
    class DataListener extends MuseDataListener {

        final WeakReference<Activity> activityRef;
        private MuseFileWriter fileWriter;

        DataListener(final WeakReference<Activity> activityRef) {
            this.activityRef = activityRef;
        }

        @Override
        public void receiveMuseDataPacket(MuseDataPacket p) {
            switch (p.getPacketType()) {
                case EEG:
                    updateEeg(p.getValues());
                    break;
                case ACCELEROMETER:
                    updateAccelerometer(p.getValues());
                    break;
                case ALPHA_RELATIVE:
                    updateAlphaRelative(p.getValues());
                    break;
                case BATTERY:
                    fileWriter.addDataPacket(1, p);
                    // It's library client responsibility to flush the buffer,
                    // otherwise you may get memory overflow.
                    if (fileWriter.getBufferedMessagesSize() > 8096)
                        fileWriter.flush();
                    break;
                default:
                    break;
            }
        }

        @Override
        public void receiveMuseArtifactPacket(MuseArtifactPacket p) {
            /*if (p.getHeadbandOn() && p.getBlink()) {
                Log.i("Artifacts", "blink");
            }*/
        }

        private void updateAccelerometer(final ArrayList<Double> data) {
            Activity activity = activityRef.get();
            if (activity != null) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        accelforward = data.get(Accelerometer.FORWARD_BACKWARD.ordinal());
                        accelup = data.get(Accelerometer.UP_DOWN.ordinal());
                        accelleft = data.get(Accelerometer.LEFT_RIGHT.ordinal());

                        averageaccel = (accelforward + accelleft + accelup) / 3.0;

                    }
                });
            }
        }

        private void updateEeg(final ArrayList<Double> data) {


            Activity activity = activityRef.get();

            if (activity != null) {

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        eggtp9 = data.get(Eeg.TP9.ordinal());
                        eggfp1 = data.get(Eeg.FP1.ordinal());
                        eggfp2 = data.get(Eeg.FP2.ordinal());
                        eggtp10 = data.get(Eeg.TP10.ordinal());

                        averageegg = (eggtp9+eggfp1+eggfp2+eggtp10)/4.0;


                        if(calibrationCounter <= LOW_NUMBER){
                            totalFirst += averageegg;
                        }

                        if(calibrationCounter == LOW_NUMBER){
                            LOW_POINT = totalFirst/LOW_NUMBER;
                            lowpointInt = (int)LOW_POINT;
                            System.out.println("LOWPOINT \t" + lowpointInt);
                        }

                        if(calibrationCounter == HIGH_NUMBER){
                            HIGH_POINT = LOW_POINT + (int)(Math.random()*20) + 300;
                            highpointInt = (int)HIGH_POINT;
                            System.out.println("HIGHPOINT \t" + highpointInt);
                        }


                        if(calibrationCounter > HIGH_NUMBER) {
                            if (averageegg > highpointInt) {
                                counter++;
                                System.out.println("VALUE OF COUNTER:\t" + counter);
//                                System.out.println(averageegg);
                            }
                        }
//
//

//                        System.out.println(averageegg);

                        calibrationCounter++;

                    }
                });
            }
        }

        private void updateAlphaRelative(final ArrayList<Double> data) {
            Activity activity = activityRef.get();
            if (activity != null) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        }

        public void setFileWriter(MuseFileWriter fileWriter) {
            this.fileWriter = fileWriter;
        }
    }

    private Muse muse = null;
    private ConnectionListener connectionListener = null;
    private DataListener dataListener = null;
    private boolean dataTransmission = true;
    private MuseFileWriter fileWriter = null;

    public MainActivity() {
        // Create listeners and pass reference to activity to them
        WeakReference<Activity> weakActivity =
                new WeakReference<Activity>(this);

        connectionListener = new ConnectionListener(weakActivity);
        dataListener = new DataListener(weakActivity);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button refreshButton = (Button) findViewById(R.id.refresh);
        refreshButton.setOnClickListener(this);
        Button connectButton = (Button) findViewById(R.id.connect);
        connectButton.setOnClickListener(this);
        Button disconnectButton = (Button) findViewById(R.id.disconnect);
        disconnectButton.setOnClickListener(this);
        Button pauseButton = (Button) findViewById(R.id.pause);
        pauseButton.setOnClickListener(this);
        // // Uncommet to test Muse File Reader
        //
        // // file can be big, read it in a separate thread
        // Thread thread = new Thread(new Runnable() {
        //     public void run() {
        //         playMuseFile("testfile.muse");
        //     }
        // });
        // thread.start();

        File dir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        fileWriter = MuseFileFactory.getMuseFileWriter(
                new File(dir, "new_muse_file.muse"));
        Log.i("Muse Headband", "libmuse version=" + LibMuseVersion.SDK_VERSION);
        fileWriter.addAnnotationString(1, "MainActivity onCreate");
        dataListener.setFileWriter(fileWriter);
    }

    @Override
    public void onClick(View v) {
        Spinner musesSpinner = (Spinner) findViewById(R.id.muses_spinner);
        if (v.getId() == R.id.refresh) {
            MuseManager.refreshPairedMuses();
            List<Muse> pairedMuses = MuseManager.getPairedMuses();
            List<String> spinnerItems = new ArrayList<String>();
            for (Muse m : pairedMuses) {
                String dev_id = m.getName() + "-" + m.getMacAddress();
                Log.i("Muse Headband", dev_id);
                spinnerItems.add(dev_id);
            }
            ArrayAdapter<String> adapterArray = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, spinnerItems);
            musesSpinner.setAdapter(adapterArray);
        } else if (v.getId() == R.id.connect) {
            List<Muse> pairedMuses = MuseManager.getPairedMuses();
            if (pairedMuses.size() < 1 ||
                    musesSpinner.getAdapter().getCount() < 1) {
                Log.w("Muse Headband", "There is nothing to connect to");
            } else {
                muse = pairedMuses.get(musesSpinner.getSelectedItemPosition());
                ConnectionState state = muse.getConnectionState();
                if (state == ConnectionState.CONNECTED ||
                        state == ConnectionState.CONNECTING) {
                    Log.w("Muse Headband",
                            "doesn't make sense to connect second time to the same muse");
                    return;
                }
                configureLibrary();
                fileWriter.open();
                fileWriter.addAnnotationString(1, "Connect clicked");
                /**
                 * In most cases libmuse native library takes care about
                 * exceptions and recovery mechanism, but native code still
                 * may throw in some unexpected situations (like bad bluetooth
                 * connection). Print all exceptions here.
                 */
                try {
                    muse.runAsynchronously();
                } catch (Exception e) {
                    Log.e("Muse Headband", e.toString());
                }
            }
        } else if (v.getId() == R.id.disconnect) {
            if (muse != null) {
                /**
                 * true flag will force libmuse to unregister all listeners,
                 * BUT AFTER disconnecting and sending disconnection event.
                 * If you don't want to receive disconnection event (for ex.
                 * you call disconnect when application is closed), then
                 * unregister listeners first and then call disconnect:
                 * muse.unregisterAllListeners();
                 * muse.disconnect(false);
                 */
                muse.disconnect(true);
                fileWriter.addAnnotationString(1, "Disconnect clicked");
                fileWriter.flush();
                fileWriter.close();
            }
        } else if (v.getId() == R.id.pause) {
            dataTransmission = !dataTransmission;
            if (muse != null) {
                muse.enableDataTransmission(dataTransmission);
            }
        }
    }

    /*
     * Simple example of getting data from the "*.muse" file
     */
    private void playMuseFile(String name) {
        File dir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(dir, name);
        final String tag = "Muse File Reader";
        if (!file.exists()) {
            Log.w(tag, "file doesn't exist");
            return;
        }
        MuseFileReader fileReader = MuseFileFactory.getMuseFileReader(file);
        while (fileReader.gotoNextMessage()) {
            MessageType type = fileReader.getMessageType();
            int id = fileReader.getMessageId();
            long timestamp = fileReader.getMessageTimestamp();
            Log.i(tag, "type: " + type.toString() +
                    " id: " + Integer.toString(id) +
                    " timestamp: " + String.valueOf(timestamp));
            switch (type) {
                case EEG:
                case BATTERY:
                case ACCELEROMETER:
                case QUANTIZATION:
                    MuseDataPacket packet = fileReader.getDataPacket();
                    Log.i(tag, "data packet: " + packet.getPacketType().toString());
                    break;
                case VERSION:
                    MuseVersion version = fileReader.getVersion();
                    Log.i(tag, "version" + version.getFirmwareType());
                    break;
                case CONFIGURATION:
                    MuseConfiguration config = fileReader.getConfiguration();
                    Log.i(tag, "config" + config.getBluetoothMac());
                    break;
                case ANNOTATION:
                    AnnotationData annotation = fileReader.getAnnotation();
                    Log.i(tag, "annotation" + annotation.getData());
                    break;
                default:
                    break;
            }
        }
    }


    private void configureLibrary() {
        muse.registerConnectionListener(connectionListener);
        muse.registerDataListener(dataListener,
                MuseDataPacketType.ACCELEROMETER);
        muse.registerDataListener(dataListener,
                MuseDataPacketType.EEG);
        muse.registerDataListener(dataListener,
                MuseDataPacketType.ALPHA_RELATIVE);
        muse.registerDataListener(dataListener,
                MuseDataPacketType.ARTIFACTS);
        muse.registerDataListener(dataListener,
                MuseDataPacketType.BATTERY);
        muse.setPreset(MusePreset.PRESET_14);
        muse.enableDataTransmission(dataTransmission);
    }

}
