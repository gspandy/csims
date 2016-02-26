/*
<div id="confirmdiv" style="width: 300px;height: 80px;">
<table width="100%" cellpadding="5" cellspacing="0" border="0">
<tbody>
    <tr>
      <td width="20%"><img src="${path}/images/icon-info.gif"/></td>	
	  <td width="80%">点击"是"提交给领导进行审核，点击"否"不提交给领导审核!</td>
	</tr>
    <tr>
	  <td colspan="2" align="center">
	  	 <input type="button" value="是" class="buttom_table" style="color: #006699" onclick="submitAudit();" />
	  	 <input type="button" value="否" class="buttom_table" style="color: #006699" onclick="submitUnAudit();" />
	  	 <input type="button" value="取消" class="buttom_table" style="color: #006699" onclick="confirmbox.close();" />
	  </td>
	</tr>
	</tbody>
</table>
</div>
*/
function Confirmbox(config,options){
		
	this.text = config.text;
	this.root = js.util.newNode('div',{style:'width: 300px;height: 80px;'});
	this.result = null;
	this.init();
	WindowBox.call(this, this.root, options);
}
Confirmbox.prototype.init = function(){
	
	 
	 new Ajax.request();
	
}
js.util.extend(Confirmbox, WindowBox);
