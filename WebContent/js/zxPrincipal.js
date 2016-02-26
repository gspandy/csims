function zxPrincipalValidate(principal,str1,str2) {
	var principals = principal.split(",");
	var m=0;
	var n=0;
	var pr="";
	for(var i=0;i<principals.length;i+=1){
		pr = principals[i];
		if(pr.indexOf(str1)>=0){
			m+=1;
		}
	}
	
	for(var j=0;j<principals.length;j+=1){
		pr = principals[j];
		if(pr.indexOf(str2)>=0){
			n+=1;
		}
	}
	if(m>3){
		return "1";
	}else if(n>3){
		return "2";
	}else{
		return "0";
	}
}
