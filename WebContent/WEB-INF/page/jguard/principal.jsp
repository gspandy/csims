<div align="left"><%@taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld"%> 
<%@taglib prefix="logic" uri="/WEB-INF/tld/struts-logic.tld"%> 
<%@taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%> 
<%@page contenttype="text/html;charset=UTF-8"%> 
 
</div><script type="text/javascript">
var selectedText;
var selectedValues;
var domains = new Array;
var domainNames = new Array;
<c:forEach var="domain" items="${generalForm.map.Domains}" varStatus="statusDomain">
    var <c:out value="${domain.name}"/>_ = new Array;
    domainNames[<c:out value="${statusDomain.index}"/>] = '<c:out value="${domain.name}"/>_';
	<c:forEach var="permission" items="${domain.permissions}" varStatus="statusPerm" >
            <c:out value="${domain.name}"/>_[<c:out value="${statusPerm.index}"/>]='<c:out value="${permission.name}"/>';
	</c:forEach>
    domains[<c:out value="${statusDomain.index}"/>] = <c:out value="${domain.name}"/>_;
</c:forEach>



function updatePrincipal(){
var domNames = document.getElementById('principalDomains');
var strDomainNames="";
var nametry = /^([a-z]|[A-Z]|[0-9]|[\u0391-\uFFE5])+$/ ;
	if(document.getElementById("principalName").value==""){
		alert("The principal name be required!");
		document.getElementById("principalName").focus();
		return false;
	}
	if(!nametry.test(document.getElementById("principalName").value)){
		alert("The other sign has not be used in principal name!");
		return false;
	}

for(var r=1;r<domNames.options.length;r++){
    strDomainNames += domNames.options[r].value+"#";
}

var permNames = document.getElementById('principalPermissions');
var strPermNames ="";

for(var r=1;r<permNames.options.length;r++){
    strPermNames += permNames.options[r].value+"#";
}
document.getElementById("domainNames").value=strDomainNames;
document.getElementById("permissionNames").value=strPermNames;

document.forms[0].action="./Principal.do?method=update";
document.forms[0].submit();
}

function grantDomain(var1, var2){
 moveOptions(var1, var2);
 var permissions = document.getElementById("permissions");
 childPermissions = permissions.childNodes;
 var principalPermissions = document.getElementById("principalPermissions");
 childPrincipalPermissions = principalPermissions.childNodes;

   for(j=selectedValues.length -1; j>=0; j--){
       for(a=0; a<childPermissions.length; a++){
          if(childPermissions[a].nodeName == 'OPTGROUP' && childPermissions[a].getAttribute('label')==selectedValues[j]){
             permissions.removeChild(childPermissions[a]);
          }

       }

       for(k=eval(selectedValues[j]+'_').length -1; k>=0; k--){
           for(i=permissions.length -1; i>=0; i--){
              if(eval(selectedValues[j]+'_')[k] == permissions.options[i].value){
                 deleteOption(permissions,i);
              }
           }
           for(i=principalPermissions.length -1; i>=0; i--){
              if(eval(selectedValues[j]+'_')[k] == principalPermissions.options[i].value){
                 deleteOption(principalPermissions,i);
              }
           }
       }
   }
}

function revokeDomain(var1, var2){
 moveOptions(var1, var2);
 var principalPermissions = document.getElementById("principalPermissions");
 var permissions = document.getElementById("permissions");
 childPrincipalPermissions = principalPermissions.childNodes;

      for(j=selectedValues.length -1; j>=0; j--){

          var newOptGroup = document.createElement("OPTGROUP");
          var label  = document.createAttribute("label");
          label.nodeValue =selectedValues[j];
          newOptGroup.setAttributeNode(label);
          permissions.appendChild(newOptGroup);



       //add the corresponding permissions nested into the domain
      for(k=0; k<eval(selectedValues[j]+'_').length; k++){
           var insertPermission=true;
             for(y=0;y<principalPermissions.options.length;y++){
               if(eval(selectedValues[j]+'_')[k] == principalPermissions.options[y].value){
                 insertPermission= false;
                 break;
               }
           }

           if(insertPermission==true){
             addOption(permissions,eval(selectedText[j]+'_')[k],eval(selectedValues[j]+'_')[k]);
           }
       }

       }

}

function grantPermission(var1,var2){
 moveOptions(var1, var2);
}

function revokePermission(theSelFrom,theSelTo){

 var perms = document.getElementById('permissions');
 var childs = perms.childNodes;
 var selLength = theSelFrom.length;
 selectedText = new Array();
 selectedValues = new Array();
 var selectedCount = 0;

 var i;

	 // Find the selected Options in reverse order
	 // and delete them from the 'from' Select.
	 for(i=selLength-1; i>=0; i--) {
		if(theSelFrom.options[i].selected)	{
			selectedText[selectedCount] = theSelFrom.options[i].text;
			selectedValues[selectedCount] = theSelFrom.options[i].value;
			deleteOption(theSelFrom, i);
			selectedCount++;
		}
	 }


	for(i=selectedCount-1; i>=0; i--){


		for(var x = 0;x<domains.length;x++){
		var domainName = null;
		   for(var d=0;d<domains[x].length;d++){
		      if(domains[x][d] == selectedValues[i]){
			 domainName = domainNames[x];
			 //we remove the '_' character => use to prevent domain names like reserved keywords
			 domainName = domainName.slice(0,domainName.length-1);
			 break;
		      }

		   }


		   for(var q = 0;q<childs.length;q++){
		     if(childs[q].nodeName=='OPTGROUP' && childs[q].getAttribute('label') == domainName){
		       var newOption = new Option(selectedText[i],selectedValues[i]);
		       perms.insertBefore(newOption,childs[q+1]);
                       domainName == null;
                       break;
		     }
		   }
		}

	}

}

function addOption(theSel, theText, theValue){
	var newOpt = new Option(theText, theValue);
	var selLength = theSel.length;
	theSel.options[selLength] = newOpt;
}

function deleteOption(theSel, theIndex){
	var selLength = theSel.length;
	if(selLength>0)	{
		theSel.options[theIndex] = null;
	}
}

function moveOptions(theSelFrom, theSelTo){

	var selLength = theSelFrom.length;
	selectedText = new Array();
	selectedValues = new Array();
	var selectedCount = 0;

	var i;

	// Find the selected Options in reverse order
	// and delete them from the 'from' Select.
	for(i=selLength-1; i>=0; i--)	{
		if(theSelFrom.options[i].selected){
			selectedText[selectedCount] = theSelFrom.options[i].text;
			selectedValues[selectedCount] = theSelFrom.options[i].value;
			deleteOption(theSelFrom, i);
			selectedCount++;
		}
	}

	// Add the selected text/values in reverse order.
	// This will add the Options to the 'to' Select
	// in the same order as they were in the 'from' Select.
	for(i=selectedCount-1; i>=0; i--){
		addOption(theSelTo, selectedText[i], selectedValues[i]);
	}

}
</script>

<title><bean:message key="PROJECT_NAME"/></title>
<div class="tiltlegerner" >
			Now：Principal Management&nbsp;
			<bean:message key="common.navigation.narrow"/>
			&nbsp;<b>Principal-Permission Relation</b>
		</div><br>
<html:form action="/Principal.do?method=update">
	<link href="css/base.css" rel="stylesheet" type="text/css" />
	<html:hidden styleId="oldPrincipalName" title="oldPrincipalName"
		property="oldPrincipalName"
		value="${generalForm.map.principal.localName}" />
	<html:hidden styleId="domainNames" title="domainNames"
		property="domainNames" value="" />
	<html:hidden styleId="permissionNames" title="permissionNames"
		property="permissionNames" value="" />
	<div id="main">
		
		<div id="content">
			<div class="query">
				<ul>
					<li>
						<label>
							Principal name:
						</label>
						<html:text property="principalName"
							value="${generalForm.map.principal.localName}" size="100"/>
					</li>
				</ul>
			</div>
			<ul>
				<li>
					<select id="principalDomains" multiple="multiple" size="10"
						class="selwt">
						<option value="----" disabled="disabled">
							- - - - - - used domain - - - - - -
						</option>
						<c:forEach var="domain"
							items="${generalForm.map.principal.domains}"
							varStatus="statusDomain">
							<option value="<c:out value="${domain.name}"/>">
								<c:out value="${domain.name}" />
							</option>
						</c:forEach>
					</select>

					<select id="domains" multiple="multiple" size="10" class="selwt">
						<option value="----" disabled="disabled">
							- - - - - - avalidable domain - - - - - -
						</option>
						<c:forEach var="domain" items="${generalForm.map.DomainsNotBound}"
							varStatus="statusDomain">
							<option value="<c:out value="${domain.name}"/>">
								<c:out value="${domain.name}" />
							</option>
						</c:forEach>
					</select>
				</li>
				<li>
					<div class="wt1">
						<input type="button" value="" class="buttong" style="border: 0px;"
							onclick="javascript:grantDomain(this.form.domains, this.form.principalDomains);" />
						<input type="button" value=" " class="buttonf"
							style="border: 0px;"
							onclick="javascript:revokeDomain(this.form.principalDomains, this.form.domains);" />
					</div>
				</li>
				<li>
					<select id="principalPermissions" multiple="multiple" size="10"
						class="selwt">
						<option value="----" disabled="disabled">
							- - - - - - used permission - - - - - -
						</option>
						<c:forEach var="permission"
							items="${generalForm.map.principal.orphanedPermissions}"
							varStatus="statusPerm">
							<option value="<c:out value="${permission.name}"/>">
								<c:out value="${permission.name}" />
							</option>
						</c:forEach>
					</select>
					<select id="permissions" multiple="multiple" size="10"
						class="selwt">
						<option value="----" disabled="disabled">
							- - - - - - avalidable permission- - - - - -
						</option>
						<c:forEach var="domain"
							items="${generalForm.map.permissionsNotBound}"
							varStatus="statusDomain">
							<optgroup label="<c:out value="${domain.name}"/>"></optgroup>
							<c:forEach var="permission" items="${domain.permissions}"
								varStatus="statusPerm">
								<option value="<c:out value="${permission.name}"/>">
									<c:out value="${permission.name}" />
								</option>
							</c:forEach>
						</c:forEach>
					</select>
				</li>
				<li>
					<div class="wt1">
						<input type="button" value="" class="buttong" style="border: 0px;"
							onClick="grantPermission(this.form.permissions, this.form.principalPermissions);" />
						<input type="button" value=" " class="buttonf"
							style="border: 0px;"
							onClick="revokePermission(this.form.principalPermissions, this.form.permissions);" />
					</div>
				</li>
			</ul>
			<div class="dkbutton">
				<input type="button" value="Save"  
					onclick="javascript:updatePrincipal();return false;" />
				<input type="button" value="Return" 
													onclick="history.go(-1)" />
			</div>
		</div>
	</div>
</html:form>