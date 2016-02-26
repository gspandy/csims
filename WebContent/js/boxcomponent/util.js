
var js = new Object();
js.util = {
	
	removeChilds:function(node){
		
		while(node.firstChild){
    		node.removeChild(node.firstChild);
    	}
	},
	/* find parentNode with tagName */
	findParentNode : function(node, tagName) {
		if (node == null || typeof node == 'undefined') {
			alert('node is null! | findParentNode:function');
			throw new Error('node is null ! | findParentNode:function')
		}
		while (node && node.nodeName.toLowerCase() != tagName) {
			node = node.parentNode;
		}
		return node;
	},
	/* transform json{'id',"10001"}to"id:100001;" CSS use it */
	JSONtoString : function(obj) {
		var item = "";
		for (var ix in obj) {
			item += ix + ":" + obj[ix];
			if (obj[ix].indexOf(";") == -1)
				item += ";"
		}
		return item;
	},
	newNode : function(tag,attr,text){
		try {
			var node = this.Builder.node(tag,attr,text);
			//事件
			if(attr){
				
				if(attr.onclick){
					node.onclick = attr.onclick; 
				}
				
				if(attr.onchange){
					node.onchange = attr.onchange; 
				}
			
				if(attr.onpropertychange){
					node.onpropertychange = attr.onpropertychange; 
				}
			}
			
			return node;
		} catch (e) {
			alert("can't create node,tag=" + tag + ",attr="+attr+"|js.util.newNode");
			throw e;
		}
		
	},
	appendChild : function(parent, child) {

		if (child != null && typeof child != 'undefined') {
			try {
				parent.appendChild($(child));
			} catch (e) {
				alert("cant'appendChild parent=" + parent + "child=" + child + "| js.util.newNode");
				throw e;
			}
		}
		return parent;
	},
	/**
	 * hidden or show select node,except this node 
	 * @param {Element} node
	 * @param {Boolean} show true:显示,false:隐藏
	 */
	controlSelectNode : function(node,show) {
		var allSelects = $A(document.getElementsByTagName('select'));
		allSelects.each(function(v, i) {
			if (show) {
				v.style.display = "";
			} else {
				v.style.display = "none";
			}

		});
		var innerSelects = $A(node.getElementsByTagName('select'));
		innerSelects.each(function(v, i) {
			if (v.style.display == "none")
				v.style.display = "";

		});

	},

	/*
	 * desc:show node 
	 * note:it does't response if pass null node to it!
	 */
	show : function(node) {
		if (node != null || $('node') != null)
			node.style.display = "block";
	},
	/*
	 * desc:hidden node 
	 * note:it does't response if pass null node to it!
	 */
	hidden : function(node) {
		if (node != null || $('node') != null)
			node.style.display = "none";
	},
	/* return file suffix */
	getSuffix : function(fileTitle) {
		if (fileTitle == null) {
			alert("fileTitle is null! | getSuffix:function");
			throw new Error("fileTitle is null! | getSuffix:function");
		}
		return fileTitle.substring(fileTitle.lastIndexOf(".") + 1,
				fileTitle.length);
	},
	/* copy object property */
	copyProperty : function(dest, src) {
		for (var property in src) {
			dest[property] = src[property];
		}
	},
	/* class A extend B */
	extend : function(dest, src) {
		var source = src.prototype;
		for (var property in source) {
			dest.prototype[property] = source[property];
		}
	},
	/* innner Class*/
	Builder : {

		NODEMAP : {
			AREA : 'map',
			CAPTION : 'table',
			COL : 'table',
			COLGROUP : 'table',
			LEGEND : 'fieldset',
			OPTGROUP : 'select',
			OPTION : 'select',
			PARAM : 'object',
			TBODY : 'table',
			TD : 'table',
			TFOOT : 'table',
			TH : 'table',
			THEAD : 'table',
			TR : 'table'
		},
		ATTR_MAP : {
			'className' : 'class',
			'htmlFor' : 'for'
		},
		_attributes : function(attributes) {
			var attrs = [];
			for (attribute in attributes){
				attrs.push((attribute in this.ATTR_MAP
						? this.ATTR_MAP[attribute]
						: attribute)
						+ '="'
						+ attributes[attribute].toString().escapeHTML()
						+ '"');
			}
			return attrs.join(" ");
		},
		node : function(elementName) {
			
			elementName = elementName.toUpperCase();
			// try innerHTML approach
			var parentTag = js.util.Builder.NODEMAP[elementName] || 'div';
			var parentElement = document.createElement(parentTag);
			try {
				// prevent IE "feature":http://dev.rubyonrails.org/ticket/2707
				var innerHTML =  "<" + elementName + "></"+ elementName + ">";
				parentElement.innerHTML =innerHTML;
			} catch (e) {
			}
			var element = parentElement.firstChild || null;
			// see if browser added wrapping tags 
			if (element && (element.tagName.toUpperCase() != elementName))
				element = element.getElementsByTagName(elementName)[0];

			// fallback to createElement approach
			if (!element)
				element = document.createElement(elementName);

			// abort if nothing could be created
			if (!element)
				return;
			
			if (arguments[1]) {
				
				var attrs = this._attributes(arguments[1]);
				if (attrs.length) {
					try {
						// prevent IE "feature":http://dev.rubyonrails.org/ticket/2707
						var innerHTML = "<" + elementName + " "+ attrs + "></" + elementName + ">";
						parentElement.innerHTML = innerHTML;
					} catch (e) {
					}
					element = parentElement.firstChild || null;
					// work firefox
					if (!element) {
						element = document.createElement(elementName);
						for (attr in arguments[1])
							element[attr == 'class' ? 'className' : attr] = arguments[1][attr];
					}
					// see if browser added wrapping tags 
					if (element && (element.tagName.toUpperCase() != elementName))
						element = element.getElementsByTagName(elementName)[0];
				}
			}
			
			if(typeof arguments[2] == 'string'){
				element.innerHTML = arguments[2]; 
			}
			else if(typeof arguments[2] == 'object'){
				element.appendChild(arguments[2]); 
			}
			return element;
		}
	}
}