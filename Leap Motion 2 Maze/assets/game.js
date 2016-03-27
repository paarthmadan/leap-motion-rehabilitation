	  var canvas = document.getElementById("theCanvas");
      var ct = canvas.getContext("2d");
      var x = canvas.width/2;
      var y = canvas.height/2;
      ballRadius = 10;
      var dx = 5
      var dy = -5


      function drawObj(){
      	ct.beginPath();
      	ct.arc(x,y, ballRadius, 0 , Math.PI*2)
      	ct.fillStyle= "#FFF"
      	ct.fill()
      	ct.closePath();
      }

      function draw(){
      	drawObj()
         Leap.loop({enableGestures: true}, function(frame) {

          frame.pointables.forEach(function(pointable) {
          var position = pointable.stabilizedTipPosition;
          normalized = frame.interactionBox.normalizePoint(position);
          hand = frame.hands.length
         // console.log("hand " + hand)
          //console.log("normalized " + normalized)
         // if (normalized[2]/100 > 355){
          //  x += normalized[2]/-200
            //console.log("x1 "+ x)
          //}else{
            //x+= normalized[2]/200
            //console.log("x2 "+ x)
          y = normalized[1]*-100;
          //x = normalized[1]*100;

          console.log( " \t" + y); 
       });
    
  })
    }
      setInterval(draw, 10)