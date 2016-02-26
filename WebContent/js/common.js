//数字,包括整数和浮点数验证函数和例子
function judgeDecimalFormat(decimal,message){
	if(/^[0-9]{0,12}((\.[0-9]{0,12})|)$/.test(decimal))
		return true;
	else{
	    alert(message+"请输入数字!");
		return false;
		}
}

//判断是否为整数字符串 要提示  if(!judgeIntegerFormat2('123',''))return false;
function judgeIntegerFormat2(srcStr,message){
    srcStr = quanjiaoToBanjiao(srcStr);
    if(/^(\d+)$/.test(srcStr)){
    	return true;
    }
    else{
	    alert(message+"请输入数字!");
	    return false;
    }
        
}	

//判断是否为整数字符串 同时全角转为半角 要提示  if(!judgeIntegerFormat2('123',''))return false;
function judgeIntegerFormat3(srcNode,message){
    srcNode.value = quanjiaoToBanjiao(srcNode.value);
    if(/^(\d+)$/.test(srcNode.value.trim2())){
    	return true;
    }
    else{
	    alert(message+"请输入数字!");
	    return false;
    }
        
}	

//判断是否为整数字符串 不提示
function judgeIntegerFormat(srcStr,message){
    if(/^(\d+)$/.test(srcStr)){
    	return true;
    }
    else{
	    return false;
    }
        
}

//判断是否为空 参数为 节点id 提示消息 列子 if(judgeNull('columnName','请填写版面名称!'))return false;
function judgeNull(id,message){
    var srcNode = document.getElementById(id);
    if(srcNode.value.trim()==''){
        alert(message);
        return true;
    }else{
        return false;
    }
}	

//判断是否为空 参数为 节点 提示消息
function judgeNodeNull(node,message){
    if(node.value.trim()==''){
        alert(message);
        return true;
    }else{
        return false;
    }
}	

//限定字符长度,不截断字符
function limitValue(id,charLength,message){
    var srcNode = document.getElementById(id);
    if(srcNode.value.length>charLength){//字符长度不能超过限定个数
        alert(message+'输入太长,最多'+charLength+'个字符!');
        return true;
    }else{
        return false;
    }
}		

//限定字符长度,截断字符
function limitValueCut(id,charLength,message){
    var srcNode = document.getElementById(id);
    if(srcNode.value.length>charLength){//字符长度不能超过限定个数
        alert(message+'输入太长,最多'+charLength+'个字');
        srcNode.value = srcNode.value.substring(0,charLength);
        return true;
    }else{
        return false;
    }
}	

    //去掉字符前后的空格
    String.prototype.trim = function(){   
        var str = ToDBC(this);//全角空格转半角空格
        return   str.replace(/(^\s*)|(\s*$)/g,""); //用正则表达式将前后空格  用空字符串替代。   
    }
    //去掉字符全部的空格
    String.prototype.trim2 = function(){   
        var str = delBlank(this);//全角空格转半角空格
        return   str.replace(/(^\s*)|(\s*$)/g,""); //用正则表达式将前后空格  用空字符串替代。   
    }
  	//全角空格转半角空格
	function ToDBC(str) {  
	  	var i;
		var result = '';
		for (i = 0; i < str.length; i++) {
			code = str.charCodeAt(i);
			if (code == 12288) {
			    result += String.fromCharCode(32);
			}
			else {
			    result += str.charAt(i);
			}
		}
    	return result; 	
	}
  	//去掉字符串中全部空格（全角和半角）
	function delBlank(str){
		str = ToDBC(str);
		var i;
		var result = '';
			for (i = 0; i < str.length; i++) {
			        code = str.charCodeAt(i);
			        if (code != 32) 
			            result += str.charAt(i);
			    }
    	return result; 	
	}
	//全角数字转半角数字
	function quanjiaoToBanjiao(str) {
    		str = delBlank(str);
    		var i;
			    var result = '';
			    for (i = 0; i < str.length; i++) {
			        code = str.charCodeAt(i);
			        if (code >= 65281 && code < 65373) {
			            result += String.fromCharCode(str.charCodeAt(i) - 65248);
			        }
			        else {
			            result += str.charAt(i);
			        }
			    }
			    
    	return result;
	}
	
	//全角数字转半角数字并去掉前后的空格
	function quanjiaoToBanjiao2(str) {
    		str = delBlank(str);
    		var i;
			    var result = '';
			    for (i = 0; i < str.length; i++) {
			        code = str.charCodeAt(i);
			        if (code >= 65281 && code < 65373) {
			            result += String.fromCharCode(str.charCodeAt(i) - 65248);
			        }
			        else {
			            result += str.charAt(i);
			        }
			    }
			    
    	return result;
	}
		//同去掉字符串前后的全角和半角空格（全角转为半角）
	function removeTrim(str){
		str = ToDBC(str);
        str = trim(str);
		return str; 
	}
	
  function openWindow(titleName,para,width,height){ //弹出窗口有滚动条�����
       var l=(window.screen.width-width)/2;
	   var t=(window.screen.Height-height)/2;
	   window.open(para, titleName, "Top="+t+",Left="+l+",Width="+width+",Height="+height+",resizable=no,toolbar=no,location=no,derectories=no,status=no,menubar=no,scrollbars=yes");
  }
  
  function openWindow2(titleName,para,width,height){ //弹出窗口无滚动条�����
       var l=(window.screen.width-width)/2;
	   var t=(window.screen.Height-height)/2;
	   window.open(para, titleName, "Top="+t+",Left="+l+",Width="+width+",Height="+height+",resizable=no,toolbar=no,location=no,derectories=no,status=no,menubar=no,scrollbars=no");
  }
  //打开一个模式窗口
  function openShowModelWindow(url,arguments,width,height){
       var l=(window.screen.width-width)/2;
	   var t=(window.screen.Height-height)/2;
	   window.showModalDialog(url,window,'dialogWidth:'+width+'px;dialogHeight:'+height+'px;dialogLeft:'+l+'px;dialogTop:'+t+'px;center:no;help:no;resizable:yes;status:no');
  }
  
  //获取元素的绝对位置
function GetAbsoluteLocation(element) 
{ 
    if ( arguments.length != 1 || element == null ) 
    { 
        return null; 
    } 
    var offsetTop = element.offsetTop; 
    var offsetLeft = element.offsetLeft; 
    var offsetWidth = element.offsetWidth; 
    var offsetHeight = element.offsetHeight; 
    while( element = element.offsetParent ) 
    { 
        offsetTop += element.offsetTop; 
        offsetLeft += element.offsetLeft; 
    } 
    return { absoluteTop: offsetTop, absoluteLeft: offsetLeft, 
        offsetWidth: offsetWidth, offsetHeight: offsetHeight }; 
} 
var parentMessage;
function viewBody(isView,message){
	var div1 = document.getElementById('div1');//获取遮盖层
	//获取页面body的绝对坐标
	div1.style.height = document.body.scrollHeight ;
	div1.style.width  = document.body.scrollWidth ;
	if(isView){
		div1.style.display = 'none';
	}else{
		div1.style.display = 'block';
	}
	parentMessage = message;
}

function parentWindownMessage(){
    //alert("请先在子窗口中"+parentMessage);
}

	//去掉字符串前后的半角空格
   function trim  (str)
{
         return   str.replace(/(^\s*)|(\s*$)/g,   "");
}

function separatePage(o,document,url){
		var cPage = document.getElementById('pageNo').value;
		var lPage = document.getElementById('pageCount').value;
		var iPage = Number(cPage);
		var mPage = Number(lPage);
		if(o == 'next'){
			iPage++;
		}
		if(o =='previous'){
			iPage--;
		}
		if(o == 'first'){
		    iPage = 1;
		} 
		if(o == 'last'){
		    iPage = mPage;
		} 
		if(o == 'jumpTo'){
			if(isNaN(cPage)||
			!(cPage.match(/^\d+$/))||
			cPage==0) { 
			     iPage = 1;
			}
		}
		 if(iPage > mPage){	
		    iPage = Number(lPage);
		}
		if(cPage < 1){
	  	    iPage = 1;
	 	}
		document.getElementById('pageNo').value = iPage;
		document.forms[0].action = url;
		document.forms[0].submit();

    }    
    
    function initExportPage(){
        document.getElementById('tableDate').border = 1;
        document.getElementById('tableDate').className = '';
        var trNodes = document.getElementsByTagName('tr');
        for(var i=0;i<trNodes.length;i++){
            if(trNodes[i].className=='th'){
                trNodes[i].className='th2';
            }
        }
        document.getElementById('tableViewDate').value = document.getElementById('tableDate').outerHTML;
        document.getElementById('tableDate').border = 0;
        document.getElementById('tableDate').className = 'container';
        for(var i=0;i<trNodes.length;i++){
            if(trNodes[i].className=='th2'){
                trNodes[i].className='th';
            }
        }
    }

  
  function viewBody2(isView){
		    var div1 = document.createElement('div'); //创建遮盖层加到body里面
		    div1.id = 'div1';
		    div1.className = 'hiddenDiv';
		    document.body.appendChild(div1);
	        var div1 = document.getElementById('div1');//获取遮盖层
			//获取页面body的绝对坐标
			div1.style.height = document.body.scrollHeight ;
			div1.style.width  = document.body.scrollWidth ;
		    if(isView){
			    div1.style.display = 'none';
			}else{
			    div1.style.display = 'block';
			}
	}
	
	function exportExcel(pageNo,pageCount,totalNo,url,fm,title,colspan){
	    document.getElementById('title1').className='printTitleBold';
  	    document.getElementById('tableViewDate').value = document.getElementById('tableDate').innerHTML;
  	    document.getElementById('title1').className='printTitleNormal';
  	    var url = 'RateSettingView.do?method=exportTable';
  	    var params = '&pageNo='+pageNo+'&pageCount='+pageCount+'&totalNo='+totalNo;
  	    fm.action = url + params+'&title='+title+'&colspan='+colspan;	
  	    if(totalNo==0){
  	        alert('无记录');
  	        return;
  	    }
  	    fm.submit();
	}
	
	function SeparatePage(hrefStr,formIndex){
		var cPage = document.forms[formIndex].pageNo.value;
		var lPage = document.forms[formIndex].pageCount.value;
		var iPage = Number(cPage);
		var mPage = Number(lPage);
		if(hrefStr == 'next'){
			iPage++;
		}
		if(hrefStr =='previous'){
			iPage--;
		}
		if(hrefStr == 'first'){
		    iPage = 1;
		} 
		if(hrefStr == 'last'){
		    iPage = mPage;
		} 
		if(hrefStr == 'jumpTo'){
			if(isNaN(document.forms[formIndex].pageNo.value)||
			!(document.forms[formIndex].pageNo.value.match(/^\d+$/))||
			document.forms[formIndex].pageNo.value==0) { 
			     iPage = 1;
			}
		}
		 if(iPage > mPage){	
		    iPage = Number(lPage);
		}
		if(cPage < 1){
	  	    iPage = 1;
	 	}
		document.forms[formIndex].pageNo.value = iPage;
        viewBody2(false);
		document.forms[formIndex].submit();
  }