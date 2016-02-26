// JavaScript Document
var PositionManager = {

	currentEventPosition : function(event) {
	   var e = window.event || event;
		var x = e.clientX + parseInt(document.body.scrollLeft);
		var y = e.clientY + parseInt(document.body.scrollTop);
		return {
			'left' : x,
			'top' : y
		};
	},

	currentNodePosition : function(node) {
		var x=0,y=0;
		while(node.parentNode){
			x += node.offsetLeft;
			y += node.offsetTop;
			node = node.parentNode;
		}
		return{'left':x,'top':y};
	},
	/*
	desc:Full screen display
	note:it does't response if pass null node to it! 
	*/
	fullDisplay : function(node) {
	    if(node==null||$(node)==null)return;
		var scrollHeight = document.body.scrollHeight;
		var clientHeight = document.body.clientHeight;
		var scrollWidth = document.body.scrollWidth;
		var clientWidth = document.body.clientWidth;
		node.style.height = (scrollHeight > clientHeight)
				? scrollHeight
				: clientHeight;
		node.style.width = (scrollWidth > clientWidth)
				? scrollWidth
				: clientWidth;
	},
    
	/**
	 * desc:center display
	 * note:it does't response if pass null node to it! 
	 * @param {Element} node
	 */
	centerDisplay : function(node,win) {
		//alert(node.innerHTML)
	    if(node==null||$(node)==null)return;
	    /*
		var clientHeight = window.screenTop;
		*/
		//document.body.clientHeight;

	    var scrollTop = document.body.scrollTop;
	    if(scrollTop == 0){
	    	scrollTop = document.documentElement.scrollTop;
	    }
	    //alert(scrollTop);
	    /*
		var clientWidth = document.body.clientWidth;
		*/
	    var scrollLeft = document.body.scrollLeft;
	    if(scrollLeft == 0){
	    	scrollLeft = document.documentElement.scrollLeft;
	    }
	    
		var top = scrollTop + (win.top||30);
		var left = scrollLeft + (win.left||70);
		node.style.top = top;
		node.style.left = left;

	},
		
	/*
        描述:将节点的offsetWidth和offsetHeight分别设置为它的width和height
        参数:src源节点,dest新节点(可选)
        注意:如果传递一个空节点不会有任何响应
    */
	
	 /*
		desc:copy node size
		note:copy node self 's offsetWidth if src is null! 
		 	 it does't response if pass null node to it! 
	*/
	copySize:function(dest,src,offset){
	    if(dest==null||$(dest)==null)return;
	    if(offset==null)offset={width:0,height:0};
	     if(src==null||$(src)==null){
	     	try{
            	dest.style.width = dest.offsetWidth+offset.width;
            	dest.style.height = dest.offsetHeight+offset.height;
	     	}catch(e){
	     		alert('dest is not node dest='+dest+'|copyOffsetPositon:function')
	     		throw new Error('dest is not node dest='+dest+'|copyOffsetPositon:function');
	     	}
         }else{
         	try{
            	dest.style.width = src.offsetWidth+offset.width;
            	dest.style.height = src.offsetHeight+offset.height;
         	}catch(e){
         		alert('dest or src is not node dest='+dest+',src='+src+'|copyOffsetPositon:function')
	     		throw new Error('dest or src is not node dest='+dest+',src='+src+'|copyOffsetPositon:function');
         	}
         }
        
    }
}
