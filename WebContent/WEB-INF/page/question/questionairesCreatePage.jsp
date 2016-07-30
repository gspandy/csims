
	function createQuestionaire(){
		var qtitle = document.getElementById("qtitle").value;
		var qenddatetime = document.getElementById("qenddatetime").value;
		var area = document.getElementById("area").value;
		var org = document.getElementById("org").value;
		if(qtitle.length <=0){
			alert("请输入问卷调查标题!");
			return false;
		}
		if(qenddatetime.length <=0){
			alert("请输入问卷调查结束时间!");
			return false;
		}
		if(area.length <=0 && org.length <=0){
			alert("请选择问卷调查参与地区或机构!");
			return false;
		}
		if(confirm("操作将保存问卷调查信息,确认?")){
			document.forms[0].action="QuestionAction.do?method=createQuestionaire";
			document.forms[0].submit();
		}
	}
	
	