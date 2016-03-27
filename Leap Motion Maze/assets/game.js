	  var canvas = document.getElementById("theCanvas");
      var ct = canvas.getContext("2d");
      var x = canvas.width/2;
      var y = canvas.height/2 - 75;

      var img=document.getElementById("cartoonImg");

    var FRAMES_PER_SECOND = 50;
    var RATIO_CANVAS_TO_LEAP = 2.45;
    var OFFSET = 350;


      ballRadius = 10;
      var dx = 5
      var dy = -5


      function drawObj(){
        if(x < 0){
          x = 0;
        }else if(x > 1100){
          x = 1100;
        }

        if(y < 20){
          y = 20;
        }else if(y > 1200){
          y = 1200;
        }

        ct.drawImage(cartoonImg, x, y);
      	
      }

      function draw(){
      	drawObj()
      Leap.loop({enableGestures: true}, function(frame) {

          frame.pointables.forEach(function(pointable) {
          var position = pointable.stabilizedTipPosition;
          normalized = frame.interactionBox.normalizePoint(position);
          hand = frame.hands.length
<<<<<<< HEAD
         // console.log("hand " + hand)
          //console.log("normalized " + normalized)
          if (normalized[2]/100 > 355){
            x += normalized[2]/100
            //console.log("x1 "+ x)
          }else{
            x+= normalized[2]/-100
            //console.log("x2 "+ x)
          }
          if (normalized[1] > 275){
            y += normalized[1]/100
           // console.log("y1 "+ x)
          }else{
            y+= normalized[1]/-100
           //console.log("y2 "+ x)
          }
=======


           //var dataY = (normalized[1] * 1000);

           //y = (dataY / RATIO_CANVAS_TO_LEAP) - OFFSET;

           //y *= -1;
           var dataX = (normalized[0]* 900);

           x=dataX
>>>>>>> origin/master

       });
      })
  }
<<<<<<< HEAD
      setInterval(draw, 10)
=======
      setInterval(draw, FRAMES_PER_SECOND);
>>>>>>> origin/master
