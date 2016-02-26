var application = new Object();

//弹出一个非模态窗口
/*
 */
application.OpenWindowFn = function(url, width, height, left, top) {
	var temp = "channelmode=no,location=no,menubar=no,toolbar=no,directories=no,scrollbars=yes,resizable=no";
	if (width) {
		temp += ',width=' + width;
	} else {
		width = 0;
	}
	if (height) {
		temp += ',height=' + height;
	} else {
		height = 0;
	}
	if (left) {
		temp += ',left=' + left;
	} else {
		temp += ',left='
				+ Math.round((window.screen.width - parseInt(width)) / 2);
	}
	if (top) {
		temp += ',top=' + top;
	} else {
		temp += ',top='
				+ Math.round((window.screen.height - parseInt(height)) / 2);
	}
	window.open(url, '_blank', temp);
}