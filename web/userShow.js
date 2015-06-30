var SPEED = 5;
function nunfzaza(){
  //alert(1);
 document.getElementById("athlete-images").innerHTML+= '<li><a href="" alt=""><img alt="" src="http://i.imgur.com/WT1ty1F.jpg"><span>Guri</span></a></li><li><a href="" alt=""><img alt="" src="http://i.imgur.com/WT1ty1F.jpg"><span>John Person</span></a></li>';
}
(function($athList,context){
	
	context.scroll = function(e) {
		//alert(1);
		if (e.target == context.leftArrow[0]) {
			//left
			$athList.scrollLeft($athList.scrollLeft() - SPEED);
		} else {
			//right
			$athList.scrollLeft($athList.scrollLeft() + SPEED);
		}
		
		if (context.keepScrolling) {
			setTimeout(function() {
				context.scroll(e);
			},(1000 / 60)); //60 frames per second
		}
	};
	
	context.stopScroll = function() {
		context.keepScrolling = false;
	};
		
		context.leftArrow = $('.nav-block.left',context.wrapper);
		context.rightArrow = $('.nav-block.right',context.wrapper);
		
		context.leftArrow.add(context.rightArrow)
			.on('mouseover',function(e) {
				context.keepScrolling = true;
				context.scroll(e);
			})
			.on('mouseout',context.stopScroll);	
			
})($('.athlete-list'),{});