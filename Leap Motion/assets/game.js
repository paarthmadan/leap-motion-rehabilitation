	  var canvas = document.getElementById("theCanvas");
      var ct = canvas.getContext("2d");
      var x = canvas.width/2;
      var y = canvas.height/2;
      ballRadius = 10;
    

      function drawObj(){
      	ct.beginPath();
      	ct.arc(x,y, ballRadius, 0 , Math.PI*2)
      	ct.fillStyle= "#FFF"
      	ct.fill()
      	ct.closePath();
      }

      function draw(){
      	
        ct.clearRect(0,0,canvas.width, canvas.height);
        drawObj();



      Leap.loop({enableGestures: true}, function(frame) {

          frame.pointables.forEach(function(pointable) {
          var position = pointable.stabilizedTipPosition;
          normalized = frame.interactionBox.normalizePoint(position);
          hand = frame.hands.length


           var dataY = (normalized[1] * 1000);
          // document.getElementById("text").innerHTML = dataY;

           y = (dataY / 2.45) - 350;

           y *= -1;
           var dataX = (normalized[0]* 900)
           // document.getElementById("text2").innerHTML = dataX;

           x=dataX

       });
      })
  }
      setInterval(draw, 50)