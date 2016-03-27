 $(document).ready(function(){
 var canvas = document.getElementById("theCanvas");
      var ct = canvas.getContext("2d");
      var x = canvas.width/2;
      var y = canvas.height/2 +200;
      var dy= 0;
      var randomX;

      var SPEED_OF_ANIMATION = 2000;

      var TIME_OF_CALL = 2000;

      var img = document.getElementById("cartoonImg");
      var ball = document.getElementById("cartoonBallImg");


      
      var counter = 0;

      var FRAMES_PER_SECOND = 50;
      var RATIO_CANVAS_TO_LEAP = 2.45;
      var OFFSET = 350;
    

      function moveObj(){
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

        ct.drawImage(img, x, y);
        
      }


      /*function spawnBall(){

        ct.drawImage(ball,randomX, dy);

      }*/



      function draw(){
        ct.clearRect(0,0,canvas.width, canvas.height); 
        moveObj();

          Leap.loop({enableGestures: true}, function(frame) {

              frame.pointables.forEach(function(pointable) {
              var position = pointable.stabilizedTipPosition;
              normalized = frame.interactionBox.normalizePoint(position);
              hand = frame.hands.length;

               var dataX = (normalized[0]* 900);

               x=dataX;

           });
          });
      }


      setInterval(draw, FRAMES_PER_SECOND);




randomX = Math.floor((Math.random() * canvas.width) + 1);

// var tid2 = setTimeout(larger,1000);

var noballs = 1;



if(noballs == 1){
        $("#cartoonBallImg").css("top", "0px");
        $("#cartoonBallImg").css("left", randomX + "px");
        $("#cartoonBallImg").animate({top: canvas.height/2 +400}, SPEED_OF_ANIMATION);

      }




var time = setInterval(checkPos, 10);

function checkPos(){

        var leftBall = randomX;
        var topBall = $("#cartoonBallImg").offset().top;

        var leftHoop = x;
        var topHoop = $("#cartoonImg").offset().top;

        
        console.log(leftBall + "     "+leftHoop);
        
        if (Math.abs((leftHoop)-(leftBall))<50 && Math.abs(topHoop-topBall) < 250){
            counter++;

            if(counter == 1){
            alert("success" + leftHoop + " "+ leftBall);
          }
        }


}      





/*function larger(){

    var tid = setTimeout(mycode,5);

    function mycode(){



      if(nooballs == 1){
        $("#cartoonBallImg").css("top", "0px");
        $("#cartoonBallImg").css("left",randomX);
      }
        
      /*spawnBall(dy += 1, randomX);
                  
                  if (Math.abs(randomX-x)<60 && Math.abs(dy-y) < 60){

                    console.log("pass");
                    }

    tid = setTimeout(mycode, 5); 


        if(dy > 1000){
          dy = 0;
        }

    }


    tid2 = setTimeout(larger, 1000);


}*/
});



  





