	  var canvas = document.getElementById("theCanvas");
      var ct = canvas.getContext("2d");
      var x = canvas.width/2;
      var y = canvas.height/2 - 75;

      var img=document.getElementById("cartoonImg");
      var ball = document.getElementById("cartoonBallImg");

    var FRAMES_PER_SECOND = 50;
    var RATIO_CANVAS_TO_LEAP = 2.45;
    var OFFSET = 350;


      ballRadius = 10;
    

      function drawObj(){
        if(x < 0){
          x = 0;
        }else if(x > 1100){
          x = 1100;
        }


        ct.drawImage(cartoonImg, x, y);
      	
      }

      function draw(){
      	
        ct.clearRect(0,0,canvas.width, canvas.height);
        drawObj();



      Leap.loop({enableGestures: true}, function(frame) {

          frame.pointables.forEach(function(pointable) {
          var position = pointable.stabilizedTipPosition;
          normalized = frame.interactionBox.normalizePoint(position);
          hand = frame.hands.length


           //var dataY = (normalized[1] * 1000);

           //y = (dataY / RATIO_CANVAS_TO_LEAP) - OFFSET;

           //y *= -1;
           var dataX = (normalized[0]* 900);

           x=dataX

       });
      })
  }
      setInterval(draw, FRAMES_PER_SECOND);