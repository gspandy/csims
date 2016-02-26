var Assert = {
	/**
	 * @param {Object} obj
	 * @param {String} str
	 */
	notNull:function(obj,str){
		if(obj==null){
			alert(str);
			throw new Error(str);
		}
	}
}


function WindowBox(body, options) {
	
	
	this.window_root = null;
	this.window_title = null;
	this.window_close_on = null;
	this.window_body = null;
	
	Assert.notNull(body,    'body '+body+' is null |WindowBox : construct function' );
	Assert.notNull($(body), 'body '+body+' is null |WindowBox : construct function' );
	
	this.body = $(body);
	this.setOptions(options);
}

WindowBox.destoryNode = function(){
	
	if(this.window_title)this.window_title.remove();
	if(this.window_close_on)this.window_close_on.remove();
	if(this.window_root)this.window_root.remove();
	
}
WindowBox.observe = function(){
	
	//监听大小改变自动恢复默认位置
	if(this.resize){
		Event.observe(window, "resize", this.resize.bindAsEventListener(this));
	}
	//监听关闭window
	if (this.window_close_on != null) {
		Event.observe(this.window_close_on, "click", this.close.bindAsEventListener(this));
	}
	
} 
WindowBox.initNode = function(){
		// Inner Class
	 
	var init_Node = {

		initWindow_title : function() {
			 
			if(this.title){
				this.window_title = js.util.newNode('div', {'class' : 'window_title'});
				var text = js.util.newNode('span',{'class' : 'window_text'});
				text.innerHTML =this.title;
				this.window_close_on = js.util.newNode('div', {'class' : 'window_close_on'});
				js.util.appendChild(this.window_title,text),
				js.util.appendChild(this.window_title, this.window_close_on);
				
			}
		},
		initwindow_body : function() {

			this.window_body = js.util.newNode('div', {
				'class' : 'window_body'
			});
			//是否有滚动条
			this.window_body.style.overflow = this.overflow;
			js.util.appendChild(this.window_body, this.body);

		},
		initWindow_root : function() {

			this.window_root = js.util.newNode('div', {
				'class' : 'window_root'
			});
			js.util.appendChild(this.window_root, this.window_title);
			js.util.appendChild(this.window_title, this.window_close_on);
			js.util.appendChild(this.window_root, this.window_body);
			js.util.appendChild(this.parentNode, this.window_root);
			
		},
		initWindow_shadow : function() {
			if (this.shadow) {
				this.window_shadow = js.util.newNode('div', {
					'class' : 'window_shadow'
				});
				js.util.appendChild(document.body, this.window_shadow);
			}
		}  
	};
	// init node
	for (var ix in init_Node) {
		init_Node[ix].call(this);
	}
	if(this.drag){
		new DragWrapper(this.window_root, this.window_title, this.window_body);	
	}
}

/**
 * 控制大小
 */
WindowBox.setSize = function(){
	
	// copy body size
	PositionManager.copySize(this.window_body, this.body);
	// copy window_body size
	PositionManager.copySize(this.window_root, this.window_body, {width : 2,height : 0});

	if (this.height != null
			&& parseInt(this.window_body.style.height) > this.height) {
		this.window_body.style.height = this.height;
		this.window_root.style.height = this.height;
		this.window_body.style.width = 15
				+ parseInt(this.window_root.style.width);
	}

	if (this.width != null
			&& parseInt(this.window_body.style.width) > this.width) {
		this.window_body.style.width = this.width;
		this.window_root.style.width = this.width;
		this.window_body.style.width = 15
				+ parseInt(this.window_root.style.width);
	}
	//加上标题高度
	PositionManager.copySize(this.window_root, this.window_body, {width : 2,height :21});
	
}
WindowBox.show = function(){
	
	js.util.show(this.window_shadow);
	js.util.show(this.body);
	js.util.show(this.window_root);
	
}
/**
 * 控制位置
 */
WindowBox.setPosition = function(){
	
	if (this.top != null || this.left != null) {
		
		this.window_root.style.top  = this.top!=null?parseInt(this.top):0;
		this.window_root.style.left = this.left != null?parseInt(this.left):0;
		
	} else {
		
		PositionManager.centerDisplay(this.window_root,this);
	}
	//控制覆盖层
	PositionManager.fullDisplay(this.window_shadow);

}
/**
 * 
 */
WindowBox.render = function(){
 	 
	js.util.controlSelectNode(this.window_root,false);	
	WindowBox.show.call(this);
	WindowBox.setSize.call(this);
	WindowBox.setPosition.call(this);
 	var type = typeof this.onRender;
	switch(type) {
      	case 'undefined':break;
      	case 'function':this.onRender.call();
      	case 'string':eval(this.onRender);
    }
}

WindowBox.prototype = {
	
	setOptions : function(options) {
		
 		if(options==null)return null;
		
		this.options = {
			parentNode : document.body,
			resize:false,
			overflow:'auto',
			shadow : true
		}
		js.util.copyProperty(this.options, options || {});
		js.util.copyProperty(this, this.options);
		this.parentNode = $(this.parentNode);
		WindowBox.destoryNode.call(this);
		WindowBox.initNode.call(this);
		WindowBox.observe.call(this);	
	},
	resize : function() {
		PositionManager.centerDisplay(this.window_root);
		PositionManager.fullDisplay(this.window_shadow);

	},
	render : function() {
		
		var context = this;
	 	var url = arguments[0];
	 	var fun= arguments[1];
	 	 
		if(url){	
		
			var options = {
				encoding:'UTF-8',
				method: 'post',
				onSuccess: function(transport){
					
					context.body.update(transport.responseText);
					WindowBox.render.call(context);		
					 	 
				  	if(fun && (typeof fun =='function')){
					 
						fun.call();
					}
				}, 
				onLoading: function(){
					context.body.update('<div class="window_load"></div>');
					WindowBox.show.call(context);
					WindowBox.setSize.call(context);
					WindowBox.setPosition.call(context);
					
				},			
				onFailure: function(){
					context.body.setStyle({height:100}).update('网络故障,请稍候再试...');
					WindowBox.render.call(context);
				} 
			};
 			//new Ajax.Request(arguments[0],options);
			new WindowBox.Ajax(arguments[0],options).send();
		}else{
			WindowBox.render.call(this);
		}

	},
	close : function() {
		
		var type = typeof this.onClose;
		switch(type) {
      		case 'undefined':break;
      		case 'function':this.onClose.call();
      		case 'string':eval(this.onClose);
    	}
 		js.util.controlSelectNode(this.window_root,true);
		js.util.hidden(this.window_root);
		js.util.hidden(this.window_shadow);
 
	},
	isRender:function(){
		 
		return this.window_root.style.display == 'block';
	}
}





WindowBox.Ajax = function(url, options) {
	this.url = url;
    this.onSuccess = options.onSuccess;
	this.onLoading = options.onLoading;
	this.loadhttp();
}
WindowBox.Ajax.prototype.send = function () {
	var req = this.req;
	ax = this;
	req.onreadystatechange = function () {
		ax.receive.call(ax);
	};
	var url = this.url.split('?');
	req.open("POST", url[0], true);
	req.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8"); 
	req.send(url.length>1?url[1]:null);
};
WindowBox.Ajax.prototype.receive = function () {
	var req = this.req;
    this.onLoading.call(this); 
	if (req.readyState == 4) {
		if (req.status == 200 || req.status == 0) {
			this.onSuccess.call(this, req);
		}
	}
};
WindowBox.Ajax.prototype.loadhttp = function () {
	if (window.XMLHttpRequest) {
		this.req = new XMLHttpRequest();
	} else {
		if (window.ActiveXObject) {
			this.req = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
}; 

