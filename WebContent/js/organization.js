function ChooseOrg(){
	    application.OpenWindowFn("orgTree.do?method=showCurrentOrgAndChildOrgTree",375,475);
	}
	
function getDeptByOrgNo(orgNo) {
	$.ajax( {
		type :"GET",
		url :"./SystemBaseInfoManagerAction.do?method=ajaxDeptByOrgNoForJson&orgNo=" + orgNo,
		ifModified :true,
		dataType :'json',
		success : function(data) {
	        var optionString = "<option value=''>-----请选择-----<\/option>";
			for(var i = 0;i < data.length;i++){
			    var dept = data[i];
			    optionString += "<option value='" + dept.id + "'>" + dept.name + "<\/option>";
			}
			optionString += "<option value='0'>无部门<\/option>";
			openCallbackBuildDept(optionString);
		},
		error : function() {
	        alert("生成使用部门列表错误");
		}
	});
}

function getAepeople(orgNo,deptNo) {
	$.ajax( {
		type :"GET",
		url :"./User.do?method=ajaxPepByOrgNoandDeptNoForJson&orgNo="+orgNo+"&deptNo="+deptNo,
		ifModified :true,
		dataType :'json',
		success : function(data) {
	        var optionString = "<option value=''>-----请选择-----<\/option>";
			for(var i = 0;i < data.length;i++){
			    var dept = data[i];
			    optionString += "<option value='" + dept.id + "'>" + dept.name + "<\/option>";
			}
			openCallbackBuildPep(optionString);
		},
		error : function() {
	        alert("生成用户列表错误");
		}
	});
}