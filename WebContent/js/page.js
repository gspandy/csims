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