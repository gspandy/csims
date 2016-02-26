function isNullOrEmpty(strVal) {
	if(strVal == '' || strVal == null || strVal == undefined) {
		return true;
	}else{
		return false;
	}
}

function validateFileType(fileNm) {
	var filepath = $("input[name='"+fileNm+"']").val();
	var extStart = filepath.lastIndexOf(".");
	var ext = filepath.substring(extStart, filepath.length).toUpperCase();
	if(ext == '.EXE' || ext == '.BAT' || ext == '.JS') {
		// alert("图片限于bmp,png,gif,jpeg,jpg格式");
		// 检测允许的上传文件类型
		return false;
	}
	return true;
}