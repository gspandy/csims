    
TreePanelFil = function(conf, url,params) {      
         
    var scope = this;      
          
    this['tpStack'] = -1 ;     
    this['maskLock'] = false ;   
    this['syncAimTree'] = false ;      
   
    var src = url == null ? '#' : url; 
   
    var treeData = new Ext.tree.TreeLoader({      
                  url : src,      
                  requestMethod : 'POST'
                    
            });      
  
    var config = {      
        height:450,
		width:220,
        line:true,     
        border : true,      
        folderSort : true,      
        autoScroll : true,      
        loader : treeData,      
        rootVisible : false,  
        animate:true,    
        autoScroll:true,
        enableDD:false,
        containerScroll:true,
        root : new Ext.tree.AsyncTreeNode({      
                    text : '1',      
                     url : src,      
                     requestMethod : 'POST',   
                     expanded : true ,
                     id:'0'
                       
               })      
    }      
     
    Ext.apply(config, conf);      
     
    TreePanelFil.superclass.constructor.call(this, config);      
          
          
          
}      
     
Ext.extend(TreePanelFil, Ext.tree.TreePanel, {      
    
            reloadData : function(params) {      
                        var scope = this;      
                        var root = this.getRootNode();      
                        var treeLoad = this.getLoader();      
                                  
                        if( params!=null )      
                        treeLoad.baseParams = params;      
                              
                        treeLoad.load(root, function(){});      
                             
                        this.expandAll();  
                                     
						},      

            startupListenMask : function( maskCfg){      
                           
                     var scope = this;      
                     this['tpStack'] = 0 ;      
                           
                    scope.openMask(maskCfg);      
                           
                     var interval = setInterval(function() {      
                        if (scope['tpStack'] == -1 ) {      
                                  
                                scope.closeMask(maskCfg);      
                                scope['maskLock'] = false;      
                                clearInterval(interval);      
                        }      
                     }, 200);      
            },      
  
            openMask : function(maskCfg){      
                      
                this['maskLock'] = true;      
                if( this['syncAimTree']!=true ){      
                    maskCfg.scope.el.mask(maskCfg.msg);      
                }      
            },      
 
            closeMask : function(maskCfg){      
                      
                var scope = this ;       
                    if( scope['syncMaskTree']!=null ){        
                        var interval = setInterval(function() {      
                                if ( scope['syncMaskTree'].isRescindLockMask() ) {      
                                       if( this['syncAimTree']!=true ){      
                                        maskCfg.scope.el.unmask(true);      
                                       }      
                                        clearInterval(interval);      
                                }      
                            }, 200);      
                    }else{      
                        if( this['syncAimTree']!=true ){      
                            maskCfg.scope.el.unmask(true);      
                        }      
                        this['maskLock'] = false;      
                    }      
            },      
                     
            isRescindLockMask : function(){      
                      
                if( this['tpStack']==-1 && this['maskLock'] == false ){      
                    return true ;      
                }else{      
                    return false ;      
                }      
                      
            },      
                   
            syncListenMask : function(tree){      
                       
                 this['syncMaskTree'] = tree;      
                 tree.setAmiSynListenMask();      
           },      
                     
            setAmiSynListenMask : function(){      
                     
                this['syncAimTree'] = true ;      
            },         
            expandNodeAll : function(rootNode,index) {      
                          
                    this['tpStack'] =  this.setTpStack(index);      
                          
                    var scope = this;      
                    rootNode.expand(true);      
                    var childs = rootNode.childNodes;      
                    for (var i = 0; i < childs.length; i++) {      
                        this['tpStack'] = scope.expandNodeAll(childs[i], (index+1));      
                    }      
                          
                    this['tpStack'] =  this.setTpStack(index);      
                    if( this['tpStack'] ==  0 )      
                        this['tpStack'] = -1;      
            },      
                  
            setTpStack : function(inx){      
                     
                var tmp = this['tpStack'];      
                this['tpStack'] = inx ;      
                return this['tpStack'];      
            },      
       
            filterNodeByText : function(text) {      
     
                var scope = this;      
                if (this[this.id + 'filterNode'] == null) {      
                    this[this.id + 'filterNode'] = new Ext.tree.TreeFilter(      
                            this, {      
                                clearBlank : true,      
                                autoClear : true     
                            });      
                }      
     
                var filter = this[this.id + 'filterNode'];      
     
                this.expandAll();      
     
                filter.filterBy(function(n) {      
     
                            if (text == null)      
                                return true;      
                            if (text == "")      
                                return true;      
     
                            var str = n.attributes.text;      
                            var newStr = str.substring(0, text.length);      
                            if (newStr == text)      
                                return n;      
     
                            if (n.childNodes != null && n.childNodes.length > 0) {      
     
                                return scope.filterParse(n, text);      
                            } else     
                                return false;      
     
                        });      
     
            },          
            filterParse : function(node, text) {      
     
                var str = node.attributes.text;      
                var newStr = str.substring(0, text.length);      
                if (newStr == text)      
                    return node;      
     
                var nodes = node.childNodes;      
                if (nodes == null)      
                    return false;      
     
                var tNode = null;      
                for (var i = 0; i < nodes.length; i++) {      
    
                    if ((tNode = this.filterParse(nodes[i], text))) {      
                        return tNode;      
                    }      
                }      
     
                return false;      
            }      
});    