/**
 * @class 机构选择
 * @partment id=""
 */
Ext.BLANK_IMAGE_URL = "js/ext/resources/images/default/s.gif";
function showOrgSelect(id,text){ 	  
	var myWindow = new Ext.Window({
        title: '组织机构选择',
        width: 350,
        height:470,
        minWidth: 300,
        minHeight: 200,
        layout: 'fit',
        plain:true,
        bodyStyle:'padding:5px;',
        buttonAlign:'center',
        closable:false,
        items: [{
            xtype: 'treepanel',
        	id: 'tree-panel',
  			width:'100%',  
  			height:300,  
  			checkModel: 'multiple',   //对树的级联多选  
  			onlyLeafCheckable: false,//对树所有结点都可选  
  			animate: true,    
  			autoScroll:true,
  			rootVisible:true,
  			loader: new Ext.tree.TreeLoader({
    		dataUrl:'orgTree.do?method=loadCurrentOrgAndChildOrgTreeData',
    		baseAttrs: {uiProvider: Ext.ux.TreeCheckNodeUI },
    		baseParams :{parentOrgId:'id'}
		}), 
  		root: new Ext.tree.AsyncTreeNode({id:id,text:text,checked:false}),//checked:false属性代表根节点是否有选择框
  		listeners: {
  			'render': function(tp){
  				this.loader.on("beforeload", function(treeLoader, node) {
					treeLoader.baseParams.parentOrgId = node.id;
				});
				this.render(); 					
				//this.getRootNode().expand();默认是否展开	根节点				
            }
        }
        }],
        buttons: [{
            text: '确定',
            handler:function(){
        		var orgTree = Ext.getCmp("tree-panel");
        		var chk = orgTree.getChecked();
        		if(chk.length == 0){
        		    Ext.Msg.alert('提示',"请选择组织机构");
        		    return false;
        		}else if(chk.length > 1){
        		    Ext.Msg.alert('提示',"只能选择一个组织机构");
        		    return false;
        		}
        		
        		var objectString = "{id:'" + chk[0].id + "',name:'" + chk[0].text + "'}";
        		eval("object = "+objectString);
        		window.opener.openCallback(object);
        		myWindow.close();
        		window.close();
            }
        },{
            text: '关闭',
           	handler:function(){
            	myWindow.close();
            	window.close();
            }
        }]
    });
    myWindow.show();  
}
