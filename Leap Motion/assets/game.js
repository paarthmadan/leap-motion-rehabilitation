 $(document).ready(function(){
 var canvas = document.getElementById("theCanvas");
      var ct = canvas.getContext("2d");
      var x = canvas.width/2;
      var y = canvas.height/2 +200;
      var dy= 0;
      var randomX;
      var isScoredChecker = false;

      var SPEED_OF_ANIMATION = 2000;

      var TIME_OF_CALL = 2000;

      var img = document.getElementById("cartoonImg");
      var ball = document.getElementById("cartoonBallImg");


      // var background = new Image();
      // background.src = "assets/BG.png";

      
      var counter = 0;

      var FRAMES_PER_SECOND = 50;
      var RATIO_CANVAS_TO_LEAP = 2.45;
      var OFFSET = 350;
    

      function moveObj(){
        if(x < 0){
          x = 0;
        }else if(x > canvas.width){
          x = canvas.width;
        }

        if(y < 20){
          y = 20;
        }else if(y > 1200){
          y = 1200;
        }
        // ct.drawImage(background,0,0);  
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






// var tid2 = setTimeout(larger,1000);

createBall();





function createBall(){


    isScoredChecker = false;

  randomX = Math.floor((Math.random() * (canvas.width-300)) + 1);

        $("#cartoonBallImg").css("top", 0);
        $("#cartoonBallImg").css("left", randomX + "px");
        $("#cartoonBallImg").animate({top: canvas.height/2 +400}, SPEED_OF_ANIMATION, function complete(){
          createBall();
        });



}





var time = setInterval(checkPos, 10);

function checkPos(){

        var leftBall = randomX;
        var topBall = $("#cartoonBallImg").offset().top;

        var leftHoop = x;
        var topHoop = $("#cartoonImg").offset().top;

        
        console.log(leftBall + "     "+leftHoop);
        
        if (Math.abs((leftHoop)-(leftBall))<50 && Math.abs(topHoop-topBall) < 250){

          


          if(isScoredChecker == false){
              counter+=1;
              isScoredChecker = true;
          }


            $("#score").html("Score: "+ counter);


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



  





