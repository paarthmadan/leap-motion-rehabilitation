

import com.mailjet.client.MailjetClient;

import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.easy.MJEasyClient;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.resource.Contact;
import com.mailjet.client.resource.Email;
import java.util.Date;


import org.junit.Test;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import javax.swing.JOptionPane;


class Person {
    final String email, name;

    Person(String email, String name) {
        this.email = email;
        this.name = name;
    }
}

public class Main {

	static MailjetClient client = new MailjetClient("47fe6a4a6fb66b58bbbab3de82af8e69" ,"92aeaf0a0bb2d5393786105264c6ad9e");
	static MailjetRequest email;
	static JSONArray recipients;
	static MailjetResponse response;
	static Date date = new Date();
	
	final static String sb = "Rehabilitation Report Progress";
	
	
    public static void testEmailSend(String msg) throws MailjetException {

    	recipients = new JSONArray()
    	                .put(new JSONObject().put(Contact.EMAIL, "madanhockey@gmail.com"))
    					.put(new JSONObject().put(Contact.EMAIL, "ashpanr@gmail.com"))
    					.put(new JSONObject().put(Contact.EMAIL, "darshil4133@gmail.com"));
    	
    	               

    	email = new MailjetRequest(Email.resource)
    	                    .property(Email.FROMNAME, "Paarth")
    	                    .property(Email.FROMEMAIL, "saj5211@hotmail.com")
    	                    .property(Email.SUBJECT,  sb + " " + date.getTime())
    	                    .property(Email.TEXTPART, msg)
    	                    .property(Email.RECIPIENTS, recipients)
    	                    .property(Email.MJCUSTOMID, "JAVA-Email");

    	response = client.post(email);


    }
    public static void main(String[] args) throws MailjetException {
        
    	String input = JOptionPane.showInputDialog(null, "Please enter the score you obtained after 10 minutes of playing:");
    	
    	int num = Integer.parseInt(input);
    	
    	testEmailSend("Patient Id: " + date.getTime() + "\nDescription: This report is computer generated as per the Leap Motion API and the Sky Ball Activity \nScore: " + num + "\n This report is generated for the purpose of analysis and rehibilation progress \n\n Sincerely,\nThe Re-hack-ilation Team");
    }

}

