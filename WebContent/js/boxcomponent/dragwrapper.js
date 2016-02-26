// JavaScript Document
function DragWrapper(root, title, content) {
	this.node = root;
	this.title = title;
	this.content = content;
	this.canDrag = false;
	this.srcX = 0;
	this.scrY = 0;
	this.title.style.cursor = "move";
	var mousedownHander = function(event) {
		this.canDrag = true;
		this.srcX = PositionManager.currentEventPosition(event).left;
		this.scrY = PositionManager.currentEventPosition(event).top;

	}

	var mouseupHander = function() {
		this.canDrag = false;
		this.content.style.display = "";
	}

	var mousemoveHander = function(event) {
		if (this.canDrag) {
			this.content.style.display = "none";
			var node = this.node;
			var destX = PositionManager.currentEventPosition(event).left;
			var destY = PositionManager.currentEventPosition(event).top;
			var offsetX = destX - this.srcX;
			var offsetY = destY - this.scrY;
			if ((offsetX + PositionManager.currentNodePosition(node).left > 0)
					&& (offsetX
							+ PositionManager.currentNodePosition(node).left
							+ parseInt(node.style.width) < document.body.clientWidth
							+ document.body.scrollLeft)) {
				node.style.left = offsetX
						+ PositionManager.currentNodePosition(node).left;
				this.srcX = PositionManager.currentEventPosition(event).left;
			}
			if (offsetY + parseInt(node.style.top) > 0) {
				node.style.top = offsetY
						+ PositionManager.currentNodePosition(node).top;
				this.scrY = PositionManager.currentEventPosition(event).top;
			}
			//window.status = node.style.top+":"+node.style.left;
		}
	}

	Event.observe(this.title, 'mousedown', mousedownHander
			.bindAsEventListener(this));
	Event.observe(document, 'mouseup', mouseupHander.bindAsEventListener(this));
	Event.observe(document, 'mousemove', mousemoveHander
			.bindAsEventListener(this));

}
