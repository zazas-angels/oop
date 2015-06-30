/**
 * 
 */
$( document ).ready( function () {
  
  var s = new Snap( '#svg_wrapper' );
  var stork = Snap.select( '#stork-main' );
  
  s.append( stork );
  
  var eyes = s.selectAll( '.eye' );
  
  eyeAnimation();
  setInterval( eyeAnimation, 2500 );
  function eyeAnimation() {
    s.selectAll( '.eye' ).animate({
      transform: 's1,0'
    }, 50, mina.easein, function ()  {
      eyes.animate({
        transform: 's1,1'
      }, 100, mina.elastic );
    });
  }
  
  var upperWing = s.select( '.upper-wing' );
  var lowerWing = s.select( '.lower-wing' );
  
  upperWingAnimation();
  setInterval( upperWingAnimation, 3000 );
  function upperWingAnimation () {
    upperWing.animate({
      transform: 's1,0.5, t0,-6'
    }, 1500, mina.easeinout, function () {
      upperWing.animate({
        transform: 's1,1'
      }, 1500, mina.easeinout )
    });
  }
  
  lowerWingAnimation();
  setInterval( lowerWingAnimation, 3000 );
  function lowerWingAnimation () {
    lowerWing.animate({
      transform: 's1,0.7 0 0, t0,20'
    }, 1500, mina.easeinout, function () {
      lowerWing.animate({
        transform: 's1,1'
      }, 1500, mina.easeinout );
    })
  }
});