<%@ taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/tld/struts-logic.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<style type="text/css">
</style>
		<title><bean:message key="PROJECT_NAME" /></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="template report">
		<link href="css/css.css" rel="stylesheet" type="text/css" />
		<link href="css/manus.css" rel="stylesheet" type="text/css" />
		<link href="css/home.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="<%=basePath%>css/css2.css"
			type="text/css"></link>
		<link rel="stylesheet"
			href="<%=basePath%>ext-3.2.0/resources/css/ext-all.css"
			type="text/css"></link>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/home.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/home03.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/manu.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common.js"></script>
		<script type="text/javascript"
            src="<%=request.getContextPath()%>/js/validate.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/WdatePicker.js" defer="defer"></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>ext-3.2.0/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="<%=basePath%>ext-3.2.0/ext-all.js"></script>
    	<script type="text/javascript" src="<%=basePath%>ext-3.2.0/build/locale/ext-lang-zh_CN.js"></script>  
		<script type="text/javascript"
			src="<%=basePath%>ext-3.2.0/epandsTree.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>ext-3.2.0/epandsTreeOne.js"></script>
<script type="text/javascript">	
String.prototype.trim = function() {
    return this.replace(/(^\s+)|\s+$/g, "");
}
</script>
<script type="text/javascript">
//=======================工作底稿==================================
Ext.MessageBox.minWidth = 400;
function inputBasis() {
    if($("input[name='isSaveAeedorgSelect']").val() == 'false') {
        Ext.Msg.alert('提示', '请选择被检查机构，然后点击选定按钮');
        var errorGif = '<img src="<%=basePath%>ext-3.2.0//resources//images//default//form//exclamation.gif"></img>';
        $('#aeedorgno_span').html(errorGif + '&nbsp;&nbsp;保存前请选择被检查机构，然后点击选定按钮');
    } else if($("input[name='isSaveAeheadmanSelect']").val() == 'false') {
        Ext.Msg.alert('提示', '请选择检查组组长，然后点击选定按钮');
        var errorGif = '<img src="<%=basePath%>ext-3.2.0//resources//images//default//form//exclamation.gif"></img>';
        $('#aeheadman_span').html(errorGif + '&nbsp;&nbsp;保存前请选择检查组组长，然后点击选定按钮');
    } else {
        var row1 = {
            layout : 'column', //从左往右布局
            items : [{
                columnWidth : .5, //该列有整行中所占百分比
                layout : 'form', //从上往下布局
                items : [{
                    xtype : 'textfield',
                    fieldLabel : '被检查人',
                    name : 'field1',
                    id : 'field1',
                    value : $("select[name='aeedorgno']").find("option:selected").text(),
                    width : 250,
                    allowBlank : false
                }]
            }, {
                columnWidth : .5,
                layout : 'form',
                items : [{
                    xtype : 'textfield',
                    fieldLabel : '项目名称',
                    name : 'field2',
                    id : 'field2',
                    value : $("input[name='prjnm']").val(),
                    width : 250,
                    allowBlank : false
                }]
            }]
        };
        var row2 = {
            layout : 'column', //从左往右布局
            items : [{
                columnWidth : .5, //该列有整行中所占百分比
                layout : 'form', //从上往下布局
                items : [{
                    xtype : 'textfield',
                    fieldLabel : '检查人',
                    name : 'field3',
                    id : 'field3',
                    value : $("input[name='aeHeadmanToServer']").val(),
                    width : 250,
                    allowBlank : false
                }]
            }, {
                columnWidth : .5,
                layout : 'form',
                items : [{
                    xtype : 'textfield',
                    fieldLabel : '复核人',
                    name : 'field4',
                    id : 'field4',
                    width : 250,
                    allowBlank : false
                }]
            }]
        };
        var row3 = {
            layout : 'form',
            items : [{
                xtype : 'htmleditor',
                fieldLabel : '检查情况',
                name : 'field5',
                id : 'field5',
                height : 260,
                width : 629,
                // createLinkText: "创建超链接",
                // defaultLinkValue: "http://",
                enableAlignments : false,
                enableColors : false,
                enableFont : false,
                enableFontSize : false,
                enableFormat : false,
                enableLinks : false,
                enableLists : false,
                enableSourceEdit : true,
                fontFamilies : ["宋体", "隶书", "黑体"]
            }]
        };
        var row4 = {
            layout : 'column', //从左往右布局
            items : [{
                columnWidth : .5, //该列有整行中所占百分比
                layout : 'form', //从上往下布局
                items : [{
                    xtype : 'datefield',
                    columnWidth : .5, //该列有整行中所占百分比
                    fieldLabel : '日期',
                    format : "Y-m-d",
                    name : 'field6',
                    id : 'field6',
                    width : 250,
                    allowBlank : false,
                    editable : false,
                    value : $("input[name='incomingDate']").val()
                }]
            }, {
                columnWidth : .5,
                layout : 'form',
                items : [{
                    xtype : 'datefield',
                    columnWidth : .5, //该列有整行中所占百分比
                    fieldLabel : '到',
                    format : "Y-m-d",
                    name : 'field12',
                    id : 'field12',
                    width : 250,
                    allowBlank : false,
                    editable : false,
                    value : $("input[name='goawayDate']").val()
                }]
            }]
        };
        var row5 = {
            layout : 'form',
            items : [{
                xtype : 'textfield',
                name : 'field7',
                id : 'field7',
                hidden : true,
                hideLabel : true,
                value : $("input[name='aeno']").val() + '第【' + $("input[name='maxino']").val() + '】号'
            }, {
                xtype : 'textfield',
                name : 'field8',
                id : 'field8',
                hidden : true,
                hideLabel : true,
                value : $("input[name='aeno']").val()
            }, {
                xtype : 'textfield',
                name : 'aeorgno',
                id : 'aeorgno',
                hidden : true,
                hideLabel : true,
                value : $("input[name='aeorgno']").val()
            }, {
                xtype : 'textfield',
                name : 'aeorgnm',
                id : 'aeorgnm',
                hidden : true,
                hideLabel : true,
                value : $("input[name='aeorgnm']").val()
            }, {
                xtype : 'textfield',
                name : 'aeedorgno',
                id : 'aeedorgno',
                hidden : true,
                hideLabel : true,
                value : $("select[name='aeedorgno']").val()
            }, {
                xtype : 'textfield',
                name : 'aeedorgnm',
                id : 'aeedorgnm',
                hidden : true,
                hideLabel : true,
                value : $("select[name='aeedorgno']").find("option:selected").text()
            }, {
                xtype : 'textfield',
                name : 'inspect_token',
                id : 'inspect_token',
                hidden : true,
                hideLabel : true,
                value : $("input[name='inspect_token']").val()
            }]
        };

        var fp = new Ext.form.FormPanel({
            title : '<%= request.getAttribute("inputBasis_title")%>',
            width : 800,
            //height:250,
            autoHeight : true,
            bodyStyle : 'padding:5px 5px 0',
            renderTo : Ext.getBody(),
            frame : true,
            layout : 'form',
            url : '', //提交地址
            method : 'post',
            labelWidth : 65,
            labelAlign : 'right',
            items : [row1, row2, row3, row4, row5],
            buttonAlign : 'center',
            style : 'padding:10px',
            buttons : [{
                text : '确定',
                handler : function() {
                    Ext.Msg.confirm('提示', '是否确认新增工作底稿？', function(btn) {
                        if(btn == 'yes') {
                            fp.getForm().submit({
                                url : '<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=saveBasis',
                                waitTitle : '请等待',
                                waitMsg : '正在添加工作底稿',
                                success : function(form, action) {
                                    //url后台返回的数据{success:true,msg:'成功'}
                                    Ext.Msg.alert('提示', action.result.msg);
                                    addBasisWin.close();
                                    fp.destroy();
                                    addBasisWin.destroy();
                                },
                                failure : function(form, action) {
                                    Ext.Msg.alert('提示', '保存工作底稿失败！请重新填写');
                                }
                            });
                        }
                    });
                }
            }, {
                text : '关闭',
                handler : function() {
                    addBasisWin.close();
                    fp.destroy();
                    addBasisWin.destroy();
                }
            }]
        });

        var addBasisWin = new Ext.Window({
            title : '添加工作底稿',
            width : 800,
            height : 510,
            resizable : false,
            closeAction : 'close',
            //autoHeight : true,
            constrainHeader : true,
            modal : true,
            plain : true,
            items : [fp]
        });
        addBasisWin.show();
    }
}

function viewBasisGrid() {
	var sm = new Ext.grid.CheckboxSelectionModel({ dataIndex: "id" ,singleSelect: true  } ); 
    var cm = new Ext.grid.ColumnModel([sm,{
        header : 'id',
        dataIndex : 'id',
        hidden : true
    }, {
        header : '被检查人',
        dataIndex : 'filed1'
    }, {
        header : '项目名称',
        dataIndex : 'filed2'
    }, {
        header : '检查人',
        dataIndex : 'filed3'
    }, {
        header : '复核人',
        dataIndex : 'filed4'
    }, {
        header : '开始时间',
        dataIndex : 'filed6'
    }, {
        header : '结束时间',
        dataIndex : 'filed12'
    }, {
        header : '创建时间',
        dataIndex : 'createdate'
    }]);
    var ds = new Ext.data.Store({
        proxy : new Ext.data.HttpProxy({
            url : '<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=pageBasises',
            method : 'POST',
            baseParams : {
                aeinspectionNo : $("input[name='aeno']").val() + '第【' + $("input[name='maxino']").val() + '】号',
                inspect_token : $("input[name='inspect_token']").val()
            }
        }),
        //jsonReader({constructor args},[create fields]);
        reader : new Ext.data.JsonReader({
            totalProperty : 'totalProperty',
            root : 'root'
        }, ['id', 'filed1', 'filed2', 'filed3', 'filed4', 'filed6', 'filed12', 'createdate'])
    });
    ds.load({
        params : {
            aeinspectionNo : $("input[name='aeno']").val() + '第【' + $("input[name='maxino']").val() + '】号',
            inspect_token : $("input[name='inspect_token']").val(),
            start : 0,
            limit : 5
        }
    });
    ds.on("beforeload", function(thiz, options) {
        thiz.baseParams["aeinspectionNo"] = $("input[name='aeno']").val() + '第【' + $("input[name='maxino']").val() + '】号';
        thiz.baseParams["inspect_token"] = $("input[name='inspect_token']").val();
    });
    var grid = new Ext.grid.GridPanel({
        id : 'workBasises-grid',
        title : '工作底稿', // 标题
        //renderTo : 'workBasises',
        // sm : sm,
        cm : cm,
        ds : ds,
        region : 'center',
        // layout:'form'
        stripeRows : true,
        loadMask : true,
        border : true,
        frame : true,
        autoHeight : true,
        width : 790,
        x : 1,
        y : 1,
        //floating : true,
        viewConfig : {
            forceFit : true
        },
        bbar : new Ext.PagingToolbar({
            pageSize : 5,
            store : ds,
            displayInfo : true,
            displayMsg : '显示第{0}条到{1}条  记录共{2}条',
            emptyMsg : '没有记录'
        })
    });
    grid.addListener('rowdblclick', onrowdoubleclick);
    var row1 = {
        layout : 'form',
        items : [{
            id : 'textField5',
            xtype : 'displayfield',
            value : '--双击下面列表中记录查看检查详情',
            fieldLabel : '',
            height : 180,
            autoScroll: true
        }]
    };
    var viewFiled5From = new Ext.form.FormPanel({//注意：Ext.form.FormPanel=Ext.FormPanel
        title : '<%= request.getAttribute("inputBasis_title")%> 检查详情    ' + $("input[name='aeno']").val() + '第【' + $("input[name='maxino']").val() + '】号',
        width : 800,
        height : 250,
        bodyStyle : 'padding:5px 5px 0',
        frame : true,
        region : 'north',
        url : '',
        method : '',
        labelWidth : 1,
        labelAlign : 'right',
        items : [row1],
        buttonAlign : 'center',
        style : 'padding:10px'
    });

    //grid.render();
    var viewBasisWin = new Ext.Window({
        title : '查看工作底稿',
        width : 800,
        height : 520,
        resizable : false,
        closeAction : 'close',
        //autoHeight : true,
        constrainHeader : true,
        modal : true,
        plain : true,
        layout : 'border',
        items : [viewFiled5From, grid],
        buttons : [{
            text : '关闭',
            handler : function() {
                viewBasisWin.close();
                viewFiled5From.destroy();
                viewBasisWin.destroy();
            }
        }, {
			text : '删除',
			handler : function() {
				Ext.Msg.confirm('提示', '是否确认新增工作底稿？', function(btn) {
					if(btn == 'yes') {
						var selectionModel = grid.getSelectionModel();
						var record = selectionModel.getSelected();
						Ext.Ajax.request({
							url : '<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=deleteBasis',
							params : {
								id : record.get('id')
							},
							method : 'POST',
							callback : function(options, success, response) {
								if(success) {
									Ext.Msg.alert("提示","删除工作底稿成功！");
									ds.reload();
								} else {
									Ext.Msg.confirm('失败', '请求超时或网络故障,错误编号：[' + response.status + ']是否要重新发送？', function(btn) {
										if(btn == 'yes') {
											Ext.Ajax.request(options);
										}
									});
								}
							}
						});
					}
				});
			}
		}],
        buttonAlign : 'center'
    });
    viewBasisWin.show();
}

var onrowdoubleclick = function(grid, index, e) {
    var selectionModel = grid.getSelectionModel();
    var record = selectionModel.getSelected();
    Ext.Ajax.request({
        url : '<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=getBasisContent',
        params : {
            id : record.get('id')
        },
        method : 'POST',
        callback : function(options, success, response) {
            if(success) {
                // alert(response.responseText);
                var responseJson = Ext.util.JSON.decode(response.responseText);
                // Ext.Msg.alert("成功" + responseJson.filed5);
                // alert(Ext.getCmp('textField5'));
                Ext.getCmp('textField5').setValue(responseJson.filed5);
            } else {
                Ext.Msg.confirm('失败', '请求超时或网络故障,错误编号：[' + response.status + ']是否要重新发送？', function(btn) {
                    if(btn == 'yes') {
                        Ext.Ajax.request(options);
                    }
                });
            }
        }
    });
}
//=======================进场记录==================================
function inputComing() {
    if($("input[name='isSaveAeedorgSelect']").val() == 'false') {
        Ext.Msg.alert('提示', '请选择被检查机构，然后点击选定按钮');
        var errorGif = '<img src="<%=basePath%>ext-3.2.0//resources//images//default//form//exclamation.gif"></img>';
        $('#aeedorgno_span').html(errorGif + '&nbsp;&nbsp;请先选择被检查机构并点击选定按钮');
    } else if($("input[name='isSaveAeheadmanSelect']").val() == 'false') {
        Ext.Msg.alert('提示', '请选择检查组组长，然后点击选定按钮');
        var errorGif = '<img src="<%=basePath%>ext-3.2.0//resources//images//default//form//exclamation.gif"></img>';
        $('#aeheadman_span').html(errorGif + '&nbsp;&nbsp;保存前请选择检查组组长，然后点击选定按钮');
    } else {
        var row1 = {
            layout : 'column', //从左往右布局
            items : [{
                columnWidth : .5, //该列有整行中所占百分比
                layout : 'form', //从上往下布局
                items : [{
                    xtype : 'textfield',
                    fieldLabel : '被检查人',
                    name : 'field1',
                    id : 'field1',
                    value : $("select[name='aeedorgno']").find("option:selected").text(),
                    width : 250,
                    allowBlank : false
                }]
            }, {
                columnWidth : .5,
                layout : 'form',
                items : [{
                    xtype : 'datefield',
                    fieldLabel : '进场时间',
                    format : "Y-m-d",
                    name : 'field2',
                    id : 'field2',
                    width : 250,
                    allowBlank : false,
                    editable : false,
                    minValue : $("input[name='aeplanstdate']").val(),
                    maxValue : $("input[name='aeplantmdate']").val()
                }]
            }]
        };
        var row2 = {
            layout : 'column', //从左往右布局
            items : [{
                columnWidth : 1, //该列有整行中所占百分比
                layout : 'form', //从上往下布局
                items : [{
                    xtype : 'textfield',
                    fieldLabel : '进场地点',
                    name : 'field3',
                    id : 'field3',
                    width : 629,
                    allowBlank : false
                }]
            }]
        };

        var row4 = {
            layout : 'form',
            items : [{
                xtype : 'htmleditor',
                fieldLabel : '进场情况',
                name : 'field5',
                id : 'field5',
                height : 230,
                width : 629,
                // createLinkText: "创建超链接",
                // defaultLinkValue: "http://",
                enableAlignments : false,
                enableColors : false,
                enableFont : false,
                enableFontSize : false,
                enableFormat : false,
                enableLinks : false,
                enableLists : false,
                enableSourceEdit : true,
                fontFamilies : ["宋体", "隶书", "黑体"]
            }]
        };
        var row5 = {
            layout : 'column', //从左往右布局
            items : [{
                columnWidth : .5, //该列有整行中所占百分比
                layout : 'form', //从上往下布局
                items : [{
                    xtype : 'textfield',
                    fieldLabel : '被检查负责人',
                    name : 'field11',
                    id : 'field11',
                    width : 250,
                    allowBlank : false
                }]
            }, {
                columnWidth : .5,
                layout : 'form',
                items : [{
                    xtype : 'textfield',
                    fieldLabel : '检查组组长',
                    name : 'field12',
                    id : 'field12',
                    value : $("input[name='aeHeadmanToServer']").val(),
                    width : 250,
                    allowBlank : false,
                    readOnly:true,
                    editable : false
                }]
            }]
        };
        var row6 = {
            layout : 'column',
            items : [{
                columnWidth : .5,
                layout : 'form', //从上往下布局
                items : [{
                    xtype : 'textfield',
                    fieldLabel : '记录人',
                    name : 'field13',
                    id : 'field13',
                    width : 250,
                    allowBlank : false
                }]
            }]
        };
        var row7 = {
            layout : 'form',
            items : [{
                xtype : 'textfield',
                name : 'field7',
                id : 'field7',
                hidden : true,
                hideLabel : true,
                value : $("input[name='aeno']").val() + '第【' + $("input[name='maxino']").val() + '】号'
            }, {
                xtype : 'textfield',
                name : 'field8',
                id : 'field8',
                hidden : true,
                hideLabel : true,
                value : $("input[name='aeno']").val()
            }, {
                xtype : 'textfield',
                name : 'aeorgno',
                id : 'aeorgno',
                hidden : true,
                hideLabel : true,
                value : $("input[name='aeorgno']").val()
            }, {
                xtype : 'textfield',
                name : 'aeorgnm',
                id : 'aeorgnm',
                hidden : true,
                hideLabel : true,
                value : $("input[name='aeorgnm']").val()
            }, {
                xtype : 'textfield',
                name : 'aeedorgno',
                id : 'aeedorgno',
                hidden : true,
                hideLabel : true,
                value : $("select[name='aeedorgno']").val()
            }, {
                xtype : 'textfield',
                name : 'aeedorgnm',
                id : 'aeedorgnm',
                hidden : true,
                hideLabel : true,
                value : $("select[name='aeedorgno']").find("option:selected").text()
            }, {
                xtype : 'textfield',
                name : 'inspect_token',
                id : 'inspect_token',
                hidden : true,
                hideLabel : true,
                value : $("input[name='inspect_token']").val()
            }]
        };
        var fp = new Ext.form.FormPanel({//注意：Ext.form.FormPanel=Ext.FormPanel
            title : '<%= request.getAttribute("inputComing_title")%>',
            width : 800,
            //height:250,
            autoHeight : true,
            bodyStyle : 'padding:5px 5px 0',
            renderTo : Ext.getBody(),
            frame : true, //在此，这个属性就很重要了，如果不为true，你会看到下面的button和panel看起来是分开的
            layout : 'form',
            url : '', //提交地址
            method : 'post', //提交方法
            labelWidth : 90,
            labelAlign : 'right',
            items : [row1, row2, row4, row5, row6, row7],
            buttonAlign : 'center', //按钮对其方式
            style : 'padding:10px',
            buttons : [{
                text : '确定',
                handler : function() {
                    Ext.Msg.confirm('提示', '是否确认新增进场记录？', function(btn) {
                        if(btn == 'yes') {
                            fp.getForm().submit({
                                url : '<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=saveComing',
                                waitTitle : '请等待',
                                waitMsg : '正在添加进场记录',
                                success : function(form, action) {
                                    //url后台返回的数据{success:true,msg:'成功'}
                                    Ext.Msg.alert('提示', action.result.msg);
                                    addBasisWin.close();
                                    fp.destroy();
                                    addBasisWin.destroy();
                                },
                                failure : function(form, action) {
                                    Ext.Msg.alert('提示', '保存进场记录失败！请重新填写');
                                }
                            });
                            $("#incomingDate").val(Ext.util.Format.date(Ext.getCmp('field2').getValue(), "Y-m-d"));
                            $("#recorder").val(Ext.getCmp('field13').getValue());
                            $("#reposiblitor").val(Ext.getCmp('field11').getValue());
                        }
                    });
                }
            }, {
                text : '关闭',
                handler : function() {//点击取消按钮的操作事件
                    addBasisWin.close();
                    fp.destroy();
                    addBasisWin.destroy();
                }
            }]
        });

        var addBasisWin = new Ext.Window({
            title : '添加进场记录',
            width : 800,
            height : 500,
            resizable : false,
            closeAction : 'close',
            constrainHeader : true,
            modal : true,
            plain : true,
            items : [fp]
        });
        addBasisWin.show();
    }
}

function viewComingGrid() {
    var cm = new Ext.grid.ColumnModel([{
        header : 'id',
        dataIndex : 'id',
        hidden : true
    }, {
        header : '被检查人',
        dataIndex : 'filed1'
    }, {
        header : '进场时间',
        dataIndex : 'filed2'
    }, {
        header : '进场地点',
        dataIndex : 'filed3'
    }, {
        header : '被检查负责人',
        dataIndex : 'filed11'
    }, {
        header : '检查组组长',
        dataIndex : 'filed12'
    }, {
        header : '记录人',
        dataIndex : 'filed13'
    }]);
    var ds = new Ext.data.Store({
        proxy : new Ext.data.HttpProxy({
            url : '<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=pageComings',
            method : 'POST',
            baseParams : {
                aeinspectionNo : $("input[name='aeno']").val() + '第【' + $("input[name='maxino']").val() + '】号',
                inspect_token : $("input[name='inspect_token']").val()
            }
        }),
        //jsonReader({constructor args},[create fields]);
        reader : new Ext.data.JsonReader({
            totalProperty : 'totalProperty',
            root : 'root'
        }, ['id', 'filed1', 'filed2', 'filed3', 'filed11', 'filed12', 'filed13'])
    });
    ds.load({
        params : {
            aeinspectionNo : $("input[name='aeno']").val() + '第【' + $("input[name='maxino']").val() + '】号',
            inspect_token : $("input[name='inspect_token']").val(),
            start : 0,
            limit : 5
        }
    });
    ds.on("beforeload", function(thiz, options) {
        thiz.baseParams["aeinspectionNo"] = $("input[name='aeno']").val() + '第【' + $("input[name='maxino']").val() + '】号';
        thiz.baseParams["inspect_token"] = $("input[name='inspect_token']").val();
    });
    var comingGrid = new Ext.grid.GridPanel({
        id : 'workComings-grid', // 设置标识ID，方便以后引用！
        title : '进场记录', // 标题
        //renderTo : 'workBasises',// 显示表格的地方
        // sm : sm,// 复选框
        cm : cm, // 列模型
        ds : ds, // 数据源
        region : 'center',
        // layout:'form'  //从上往下布局
        stripeRows : true, // 加上行条纹
        loadMask : true, // 加载数据时遮蔽表格
        border : true, // 加上边框
        frame : true, // 显示天蓝色圆角框
        autoHeight : true, // 自动设置高度，这个配置很重要
        width : 790,
        x : 1, // 设置初始位置横坐标
        y : 1, // 设置初始位置纵坐标
        // enableDragDrop : true,//容许行的拖曳
        // collapsible : true, // 面板可以折叠
        // titleCollapse : true,// 单击表头任何地方都可以折叠
        //floating : true,
        viewConfig : {
            //True表示为自动展开/缩小列的宽度以适应grid的宽度，这样就不会出现水平的滚动条
            forceFit : true
        },
        bbar : new Ext.PagingToolbar({
            pageSize : 5,
            store : ds,
            displayInfo : true,
            displayMsg : '显示第{0}条到{1}条  记录共{2}条',
            emptyMsg : '没有记录'
        })
    });
    comingGrid.addListener('rowdblclick', onComingdoubleclick);
    var row1 = {
        layout : 'form',
        items : [{
            id : 'textField5',
            xtype : 'displayfield',
            value : '--双击下面列表中记录查看--进场情况',
            fieldLabel : '',
            height : 310
        }]
    };
    var viewFiled5From = new Ext.form.FormPanel({//注意：Ext.form.FormPanel=Ext.FormPanel
        title : '<%= request.getAttribute("inputComing_title")%> 向被检查人告知的事项    ' + $("input[name='aeno']").val() + '第【' + $("input[name='maxino']").val() + '】号',
        width : 800,
        height : 250,
        // autoHeight:true,
        bodyStyle : 'padding:5px 5px 0',
        // renderTo:Ext.getBody(),
        frame : true, //在此，这个属性就很重要了，如果不为true，你会看到下面的button和panel看起来是分开的
        // layout : 'form',
        region : 'north',
        url : '', //提交地址
        method : '', //提交方法
        labelWidth : 1,
        labelAlign : 'right',
        items : [row1],
        buttonAlign : 'center', //按钮对其方式
        style : 'padding:10px'
    });

    //grid.render();
    var viewBasisWin = new Ext.Window({
        title : '查看进场记录',
        width : 800,
        height : 520,
        resizable : false,
        closeAction : 'close',
        //autoHeight : true,
        constrainHeader : true,
        modal : true,
        plain : true,
        layout : 'border',
        items : [viewFiled5From, comingGrid],
        buttons : [{
            text : '关闭',
            handler : function() {//点击取消按钮的操作事件
                viewBasisWin.close();
                viewFiled5From.destroy();
                viewBasisWin.destroy();
            }
        }],
        buttonAlign : 'center' //按钮对其方式
    });
    viewBasisWin.show();
}

var onComingdoubleclick = function(grid, index, e) {
    var selectionModel = grid.getSelectionModel();
    var record = selectionModel.getSelected();
    // alert(record.get('id'));
    Ext.Ajax.request({
        url : '<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=getComingContent',
        params : {
            id : record.get('id')
        },
        method : 'POST',
        callback : function(options, success, response) {
            if(success) {
                // alert(response.responseText);
                var responseJson = Ext.util.JSON.decode(response.responseText);
                // Ext.Msg.alert("成功" + responseJson.filed5);
                // alert(Ext.getCmp('textField5'));
                Ext.getCmp('textField5').setValue(responseJson.filed5);
            } else {
                Ext.Msg.confirm('失败', '请求超时或网络故障,错误编号：[' + response.status + ']是否要重新发送？', function(btn) {
                    if(btn == 'yes') {
                        Ext.Ajax.request(options);
                    }
                });
            }
        }
    });
}
//=======================离场记录==================================
function inputGoaway() {
    if($("input[name='isSaveAeedorgSelect']").val() == 'false') {
        Ext.Msg.alert('提示', '请选择被检查机构，然后点击选定按钮');
        var errorGif = '<img src="<%=basePath%>ext-3.2.0//resources//images//default//form//exclamation.gif"></img>';
        $('#aeedorgno_span').html(errorGif + '&nbsp;&nbsp;请先选择被检查机构并点击选定按钮');
    } else if($("input[name='isSaveAeheadmanSelect']").val() == 'false') {
        Ext.Msg.alert('提示', '请选择检查组组长，然后点击选定按钮');
        var errorGif = '<img src="<%=basePath%>ext-3.2.0//resources//images//default//form//exclamation.gif"></img>';
        $('#aeheadman_span').html(errorGif + '&nbsp;&nbsp;保存前请选择检查组组长，然后点击选定按钮');
    } else {
        var row1 = {
            layout : 'column', //从左往右布局
            items : [{
                columnWidth : .5, //该列有整行中所占百分比
                layout : 'form', //从上往下布局
                items : [{
                    xtype : 'textfield',
                    fieldLabel : '被检查单位',
                    name : 'field1',
                    id : 'field1',
                    value : $("select[name='aeedorgno']").find("option:selected").text(),
                    width : 250,
                    allowBlank : false
                }]
            }, {
                columnWidth : .5,
                layout : 'form',
                items : [{
                    xtype : 'textfield',
                    fieldLabel : '检查项目名称',
                    name : 'field2',
                    id : 'field2',
                    value : $("input[name='prjnm']").val(),
                    width : 250,
                    allowBlank : false
                }]
            }]
        };
        var row2 = {
            layout : 'column', //从左往右布局
            items : [{
                columnWidth : .5,
                layout : 'form', //从上往下布局
                items : [{
                    xtype : 'datefield',
                    fieldLabel : '会谈时间',
                    format : "Y-m-d",
                    name : 'field3',
                    id : 'field3',
                    width : 250,
                    allowBlank : false,
                    editable : false
                }]
            }, {
                columnWidth : .5,
                layout : 'form',
                items : [{
                    xtype : 'textfield',
                    fieldLabel : '会谈地点',
                    name : 'field4',
                    id : 'field4',
                    width : 250,
                    allowBlank : false
                }]
            }]
        };
        var row3 = {
            layout : 'column', //从左往右布局
            items : [{
                columnWidth : .5, //该列有整行中所占百分比
                layout : 'form', //从上往下布局
                items : [{
                    xtype : 'textfield',
                    fieldLabel : '会谈主持人',
                    name : 'field5',
                    id : 'field5',
                    width : 250,
                    allowBlank : false
                }]
            }, {
                columnWidth : .5,
                layout : 'form',
                items : [{
                    xtype : 'textfield',
                    fieldLabel : '会谈记录人',
                    name : 'field6',
                    id : 'field6',
                    width : 250,
                    allowBlank : false
                }]
            }]
        };
        var row4 = {
            layout : 'column', //从左往右布局
            items : [{
                columnWidth : 1, //该列有整行中所占百分比
                layout : 'form', //从上往下布局
                items : [{
                    xtype : 'textfield',
                    fieldLabel : '双方参与人',
                    name : 'field7',
                    id : 'field7',
                    width : 629,
                    allowBlank : false
                }]
            }]
        };
        var row5 = {
            layout : 'form',
            items : [{
                xtype : 'htmleditor',
                fieldLabel : '会谈内容',
                name : 'field8',
                id : 'field8',
                height : 190,
                width : 629,
                // createLinkText: "创建超链接",
                // defaultLinkValue: "http://",
                enableAlignments : false,
                enableColors : false,
                enableFont : false,
                enableFontSize : false,
                enableFormat : false,
                enableLinks : false,
                enableLists : false,
                enableSourceEdit : true,
                fontFamilies : ["宋体", "隶书", "黑体"]
            }]
        };
        var row6 = {
            layout : 'column', //从左往右布局
            items : [{
                columnWidth : .5, //该列有整行中所占百分比
                layout : 'form', //从上往下布局
                items : [{
                    xtype : 'textfield',
                    fieldLabel : '检查组组长',
                    name : 'field12',
                    id : 'field12',
                    value : $("input[name='aeHeadmanToServer']").val(),
                    width : 250,
                    allowBlank : false,
                    readOnly:true,
                    editable : false
                }]
            }, {
                columnWidth : .5,
                layout : 'form',
                items : [{
                    xtype : 'textfield',
                    fieldLabel : '被检查负责人',
                    name : 'field11',
                    id : 'field11',
                    width : 250,
                    allowBlank : false
                }]
            }]
        };
        var row8 = {
            layout : 'form',
            items : [{
                xtype : 'textfield',
                name : 'field14',
                id : 'field14',
                hidden : true,
                hideLabel : true,
                value : $("input[name='aeno']").val() + '第【' + $("input[name='maxino']").val() + '】号'
            }, {
                xtype : 'textfield',
                name : 'field15',
                id : 'field15',
                hidden : true,
                hideLabel : true,
                value : $("input[name='aeno']").val()
            }, {
                xtype : 'textfield',
                name : 'field10',
                id : 'field10',
                hidden : true,
                hideLabel : true,
                value : $("input[name='awaynotext']").val() + '【' + $("input[name='awaynoyear']").val() + '】第【' + $("input[name='awaynoindex']").val() + '】号'
            }, {
                xtype : 'textfield',
                name : 'aeorgno',
                id : 'aeorgno',
                hidden : true,
                hideLabel : true,
                value : $("input[name='aeorgno']").val()
            }, {
                xtype : 'textfield',
                name : 'aeorgnm',
                id : 'aeorgnm',
                hidden : true,
                hideLabel : true,
                value : $("input[name='aeorgnm']").val()
            }, {
                xtype : 'textfield',
                name : 'aeedorgno',
                id : 'aeedorgno',
                hidden : true,
                hideLabel : true,
                value : $("select[name='aeedorgno']").val()
            }, {
                xtype : 'textfield',
                name : 'aeedorgnm',
                id : 'aeedorgnm',
                hidden : true,
                hideLabel : true,
                value : $("select[name='aeedorgno']").find("option:selected").text()
            }, {
                xtype : 'textfield',
                name : 'inspect_token',
                id : 'inspect_token',
                hidden : true,
                hideLabel : true,
                value : $("input[name='inspect_token']").val()
            }]
        };
        var fp = new Ext.form.FormPanel({//注意：Ext.form.FormPanel=Ext.FormPanel
            title : '<%= request.getAttribute("inputGoaway_title")%>',
            width : 800,
            //height:250,
            autoHeight : true,
            bodyStyle : 'padding:5px 5px 0',
            renderTo : Ext.getBody(),
            frame : true,
            layout : 'form',
            url : '',
            method : 'post',
            labelWidth : 90,
            labelAlign : 'right',
            items : [row1, row2, row3, row4, row5, row6, row8],
            buttonAlign : 'center',
            style : 'padding:10px',
            buttons : [{
                text : '确定',
                handler : function() {
                    Ext.Msg.confirm('提示', '是否确认新增离场会谈纪要？', function(btn) {
                        if(btn == 'yes') {
                            fp.getForm().submit({
                                url : '<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=saveGoaway',
                                waitTitle : '请等待',
                                waitMsg : '正在添加离场会谈纪要',
                                success : function(form, action) {
                                    //url后台返回的数据{success:true,msg:'成功'}
                                    Ext.Msg.alert('提示', action.result.msg);
                                    addBasisWin.close();
                                    fp.destroy();
                                    addBasisWin.destroy();
                                },
                                failure : function(form, action) {
                                    Ext.Msg.alert('提示', '保存离场会谈纪要失败！请重新填写');
                                }
                            });
                            $("#goawayDate").val(Ext.util.Format.date(Ext.getCmp('field3').getValue(), "Y-m-d"));
                        }
                    });
                }
            }, {
                text : '关闭',
                handler : function() {
                    addBasisWin.close();
                    fp.destroy();
                    addBasisWin.destroy();
                }
            }]
        });

        var addBasisWin = new Ext.Window({
            title : '添加离场会谈纪要',
            width : 800,
            height : 500,
            resizable : false,
            closeAction : 'close',
            //autoHeight : true,
            constrainHeader : true,
            modal : true,
            plain : true,
            items : [fp]
        });
        addBasisWin.show();
    }
}

function viewGoawayGrid() {
    var cm = new Ext.grid.ColumnModel([{
        header : 'id',
        dataIndex : 'id',
        hidden : true
    }, {
        header : '被检查单位',
        dataIndex : 'filed1'
    }, {
        header : '检查项目',
        dataIndex : 'filed2'
    }, {
        header : '会谈时间',
        dataIndex : 'filed3'
    }, {
        header : '会谈地点',
        dataIndex : 'filed4'
    }, {
        header : '会谈主持人',
        dataIndex : 'filed5'
    }, {
        header : '会谈记录人',
        dataIndex : 'filed6'
    }, {
        header : '检查组组长',
        dataIndex : 'filed12'
    }, {
        header : '被检查单位负责人',
        dataIndex : 'filed11'
    }]);
    var ds = new Ext.data.Store({
        proxy : new Ext.data.HttpProxy({
            url : '<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=pageGoaways',
            method : 'POST',
            baseParams : {
                aeinspectionNo : $("input[name='aeno']").val() + '第【' + $("input[name='maxino']").val() + '】号',
                inspect_token : $("input[name='inspect_token']").val()
            }
        }),
        //jsonReader({constructor args},[create fields]);
        reader : new Ext.data.JsonReader({
            totalProperty : 'totalProperty',
            root : 'root'
        }, ['id', 'filed1', 'filed2', 'filed3', 'filed4', 'filed5', 'filed6', 'filed12', 'filed11'])
    });
    ds.load({
        params : {
            aeinspectionNo : $("input[name='aeno']").val() + '第【' + $("input[name='maxino']").val() + '】号',
            inspect_token : $("input[name='inspect_token']").val(),
            start : 0,
            limit : 5
        }
    });
    ds.on("beforeload", function(thiz, options) {
        thiz.baseParams["aeinspectionNo"] = $("input[name='aeno']").val() + '第【' + $("input[name='maxino']").val() + '】号';
        thiz.baseParams["inspect_token"] = $("input[name='inspect_token']").val();
    });
    var goawayGrid = new Ext.grid.GridPanel({
        id : 'workGoaways-grid', // 设置标识ID，方便以后引用！
        title : '离场会谈纪要', // 标题
        //renderTo : 'workBasises',// 显示表格的地方
        // sm : sm,// 复选框
        cm : cm, // 列模型
        ds : ds, // 数据源
        region : 'center',
        // layout:'form'  //从上往下布局
        stripeRows : true, // 加上行条纹
        loadMask : true, // 加载数据时遮蔽表格
        border : true, // 加上边框
        frame : true, // 显示天蓝色圆角框
        autoHeight : true, // 自动设置高度，这个配置很重要
        width : 790,
        x : 1, // 设置初始位置横坐标
        y : 1, // 设置初始位置纵坐标
        // enableDragDrop : true,//容许行的拖曳
        // collapsible : true, // 面板可以折叠
        // titleCollapse : true,// 单击表头任何地方都可以折叠
        //floating : true,
        viewConfig : {
            //True表示为自动展开/缩小列的宽度以适应grid的宽度，这样就不会出现水平的滚动条
            forceFit : true
        },
        bbar : new Ext.PagingToolbar({
            pageSize : 5,
            store : ds,
            displayInfo : true,
            displayMsg : '显示第{0}条到{1}条  记录共{2}条',
            emptyMsg : '没有记录'
        })
    });
    goawayGrid.addListener('rowdblclick', onGoawaydoubleclick);
    var row1 = {
        layout : 'form',
        items : [{
            id : 'textField5',
            xtype : 'displayfield',
            value : '--双击下面列表中记录查看--离场会谈纪要会谈内容',
            fieldLabel : '',
            height : 310
        }]
    };
    var viewFiled5From = new Ext.form.FormPanel({//注意：Ext.form.FormPanel=Ext.FormPanel
        title : '<%= request.getAttribute("inputGoaway_title")%> 离场会谈纪要会谈内容    ' + $("input[name='aeno']").val() + '第【' + $("input[name='maxino']").val() + '】号',
        width : 800,
        height : 250,
        // autoHeight:true,
        bodyStyle : 'padding:5px 5px 0',
        // renderTo:Ext.getBody(),
        frame : true, //在此，这个属性就很重要了，如果不为true，你会看到下面的button和panel看起来是分开的
        // layout : 'form',
        region : 'north',
        url : '', //提交地址
        method : '', //提交方法
        labelWidth : 1,
        labelAlign : 'right',
        items : [row1],
        buttonAlign : 'center', //按钮对其方式
        style : 'padding:10px'
    });

    //grid.render();
    var viewBasisWin = new Ext.Window({
        title : '查看离场会谈纪要',
        width : 800,
        height : 520,
        resizable : false,
        closeAction : 'close',
        //autoHeight : true,
        constrainHeader : true,
        modal : true,
        plain : true,
        layout : 'border',
        items : [viewFiled5From, goawayGrid],
        buttons : [{
            text : '关闭',
            handler : function() {//点击取消按钮的操作事件
                viewBasisWin.close();
                viewFiled5From.destroy();
                viewBasisWin.destroy();
            }
        }],
        buttonAlign : 'center' //按钮对其方式
    });
    viewBasisWin.show();
}

var onGoawaydoubleclick = function(grid, index, e) {
    var selectionModel = grid.getSelectionModel();
    var record = selectionModel.getSelected();
    // alert(record.get('id'));
    Ext.Ajax.request({
        url : '<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=getGoawayContent',
        params : {
            id : record.get('id')
        },
        method : 'POST',
        callback : function(options, success, response) {
            if(success) {
                // alert(response.responseText);
                var responseJson = Ext.util.JSON.decode(response.responseText);
                // Ext.Msg.alert("成功" + responseJson.filed5);
                // alert(Ext.getCmp('textField5'));
                Ext.getCmp('textField5').setValue(responseJson.filed8);
            } else {
                Ext.Msg.confirm('失败', '请求超时或网络故障,错误编号：[' + response.status + ']是否要重新发送？', function(btn) {
                    if(btn == 'yes') {
                        Ext.Ajax.request(options);
                    }
                });
            }
        }
    });
}
//=======================会谈纪要==================================
function inputTalkSummary() {
    if($("input[name='isSaveAeedorgSelect']").val() == 'false') {
        Ext.Msg.alert('提示', '请选择被检查机构，然后点击选定按钮');
        var errorGif = '<img src="<%=basePath%>ext-3.2.0//resources//images//default//form//exclamation.gif"></img>';
        $('#aeedorgno_span').html(errorGif + '&nbsp;&nbsp;请先选择被检查机构并点击选定按钮');
    } else if($("input[name='isSaveAeheadmanSelect']").val() == 'false') {
        Ext.Msg.alert('提示', '请选择检查组组长，然后点击选定按钮');
        var errorGif = '<img src="<%=basePath%>ext-3.2.0//resources//images//default//form//exclamation.gif"></img>';
        $('#aeheadman_span').html(errorGif + '&nbsp;&nbsp;保存前请选择检查组组长，然后点击选定按钮');
    } else {
        var row1 = {
            layout : 'column', //从左往右布局
            items : [{
                columnWidth : 1, //该列有整行中所占百分比
                layout : 'form', //从上往下布局
                items : [{
                    xtype : 'textfield',
                    fieldLabel : '被检查人',
                    name : 'field1',
                    id : 'field1',
                    value : $("select[name='aeedorgno']").find("option:selected").text(),
                    width : 629,
                    allowBlank : false
                }]
            }]
        };
        var row2 = {
            layout : 'column', //从左往右布局
            items : [{
                columnWidth : .5, //该列有整行中所占百分比
                layout : 'form', //从上往下布局
                items : [{
                    xtype : 'textfield',
                    fieldLabel : '被检查联系人',
                    name : 'field3',
                    id : 'field3',
                    width : 250,
                    allowBlank : false
                }]
            }, {
                columnWidth : .5,
                layout : 'form',
                items : [{
                    xtype : 'textfield',
                    fieldLabel : '联系电话',
                    name : 'field4',
                    id : 'field4',
                    width : 250,
                    allowBlank : false
                }]
            }]
        };
        var row3 = {
            layout : 'column', //从左往右布局
            items : [{
                columnWidth : 1, //该列有整行中所占百分比
                layout : 'form', //从上往下布局
                items : [{
                    xtype : 'textfield',
                    fieldLabel : '被检查参与人',
                    name : 'field2',
                    id : 'field2',
                    width : 629,
                    allowBlank : false
                }]
            }]
        };
        var row4 = {
            layout : 'column', //从左往右布局
            items : [{
                columnWidth : 1, //该列有整行中所占百分比
                layout : 'form', //从上往下布局
                items : [{
                    xtype : 'textfield',
                    fieldLabel : '检查组参与人',
                    name : 'field5',
                    id : 'field5',
                    width : 629,
                    allowBlank : false
                }]
            }]
        };
        var row5 = {
            layout : 'form',
            items : [{
                xtype : 'htmleditor',
                fieldLabel : '会谈内容',
                name : 'field10',
                id : 'field10',
                height : 170,
                width : 629,
                // createLinkText: "创建超链接",
                // defaultLinkValue: "http://",
                enableAlignments : false,
                enableColors : false,
                enableFont : false,
                enableFontSize : false,
                enableFormat : false,
                enableLinks : false,
                enableLists : false,
                enableSourceEdit : true,
                fontFamilies : ["宋体", "隶书", "黑体"]
            }]
        };
        var row6 = {
            layout : 'column', //从左往右布局
            items : [{
                columnWidth : .5, //该列有整行中所占百分比
                layout : 'form', //从上往下布局
                items : [{
                    xtype : 'textfield',
                    fieldLabel : '被检查负责人',
                    name : 'field13',
                    id : 'field13',
                    value : $("input[name='reposiblitor']").val(),
                    width : 250,
                    allowBlank : false
                }]
            }, {
                columnWidth : .5,
                layout : 'form',
                items : [{
                    xtype : 'textfield',
                    fieldLabel : '检查组组长',
                    name : 'field14',
                    id : 'field14',
                    value : $("input[name='aeHeadmanToServer']").val(),
                    width : 250,
                    allowBlank : false,
                    readOnly:true,
                    editable : false
                }]
            }]
        };
        var row7 = {
            layout : 'column', //从左往右布局
            items : [{
                columnWidth : .5,
                layout : 'form',
                items : [{
                    xtype : 'datefield',
                    fieldLabel : '会谈时间',
                    format : "Y-m-d",
                    name : 'field6',
                    id : 'field6',
                    width : 250,
                    allowBlank : false,
                    editable : false,
                    value : $("input[name='incomingDate']").val()
                }]
            }, {
                columnWidth : .5,
                layout : 'form',
                items : [{
                    xtype : 'textfield',
                    fieldLabel : '记录人',
                    name : 'field15',
                    id : 'field15',
                    width : 250,
                    allowBlank : false,
                    value : $("input[name='recorder']").val()
                }]
            }]
        };
        var row8 = {
            layout : 'form',
            items : [{
                xtype : 'textfield',
                name : 'field16',
                id : 'field16',
                hidden : true,
                hideLabel : true,
                value : $("input[name='aeno']").val() + '第【' + $("input[name='maxino']").val() + '】号'
            }, {
                xtype : 'textfield',
                name : 'field17',
                id : 'field17',
                hidden : true,
                hideLabel : true,
                value : $("input[name='aeno']").val()
            }, {
                xtype : 'textfield',
                name : 'aeorgno',
                id : 'aeorgno',
                hidden : true,
                hideLabel : true,
                value : $("input[name='aeorgno']").val()
            }, {
                xtype : 'textfield',
                name : 'aeorgnm',
                id : 'aeorgnm',
                hidden : true,
                hideLabel : true,
                value : $("input[name='aeorgnm']").val()
            }, {
                xtype : 'textfield',
                name : 'aeedorgno',
                id : 'aeedorgno',
                hidden : true,
                hideLabel : true,
                value : $("select[name='aeedorgno']").val()
            }, {
                xtype : 'textfield',
                name : 'aeedorgnm',
                id : 'aeedorgnm',
                hidden : true,
                hideLabel : true,
                value : $("select[name='aeedorgno']").find("option:selected").text()
            }, {
                xtype : 'textfield',
                name : 'inspect_token',
                id : 'inspect_token',
                hidden : true,
                hideLabel : true,
                value : $("input[name='inspect_token']").val()
            }]
        };
        var fp = new Ext.form.FormPanel({//注意：Ext.form.FormPanel=Ext.FormPanel
            title : '<%= request.getAttribute("inputTalkSummary_title")%>',
            width : 800,
            //height:250,
            autoHeight : true,
            bodyStyle : 'padding:5px 5px 0',
            renderTo : Ext.getBody(),
            frame : true, //在此，这个属性就很重要了，如果不为true，你会看到下面的button和panel看起来是分开的
            layout : 'form',
            url : '', //提交地址
            method : 'post', //提交方法
            labelWidth : 90,
            labelAlign : 'right',
            items : [row1, row2, row3, row4, row5, row6, row7, row8],
            buttonAlign : 'center', //按钮对其方式
            style : 'padding:10px',
            buttons : [{
                text : '确定',
                handler : function() {
                    Ext.Msg.confirm('提示', '是否确认新增执法检查会谈纪要？', function(btn) {
                        if(btn == 'yes') {
                            //FormPanel自身带异步提交方式
                            fp.getForm().submit({
                                url : '<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=saveTalkSummary',
                                waitTitle : '请等待',
                                waitMsg : '正在添加执法检查会谈纪要',
                                success : function(form, action) {
                                    //url后台返回的数据{success:true,msg:'成功'}
                                    Ext.Msg.alert('提示', action.result.msg);
                                    addBasisWin.close();
                                    fp.destroy();
                                    addBasisWin.destroy();
                                },
                                failure : function(form, action) {
                                    Ext.Msg.alert('提示', '保存执法检查会谈纪要失败！请重新填写');
                                }
                            });
                        }
                    });
                }
            }, {
                text : '关闭',
                handler : function() {//点击取消按钮的操作事件
                    addBasisWin.close();
                    fp.destroy();
                    addBasisWin.destroy();
                }
            }]
        });

        var addBasisWin = new Ext.Window({
            title : '添加执法检查会谈纪要',
            width : 800,
            height : 500,
            resizable : false,
            closeAction : 'close',
            //autoHeight : true,
            constrainHeader : true,
            modal : true,
            plain : true,
            items : [fp]
        });
        addBasisWin.show();
    }
}

function viewTalkSummaryGrid() {
    var cm = new Ext.grid.ColumnModel([{
        header : 'id',
        dataIndex : 'id',
        hidden : true
    }, {
        header : '被检查人',
        dataIndex : 'filed1'
    }, {
        header : '会谈时间',
        dataIndex : 'filed6'
    }, {
        header : '会谈记录人',
        dataIndex : 'filed9'
    }, {
        header : '检查组组长',
        dataIndex : 'filed14'
    }, {
        header : '被检查单位负责人',
        dataIndex : 'filed13'
    }]);
    var ds = new Ext.data.Store({
        proxy : new Ext.data.HttpProxy({
            url : '<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=pageTalkSummarys',
            method : 'POST',
            baseParams : {
                aeinspectionNo : $("input[name='aeno']").val() + '第【' + $("input[name='maxino']").val() + '】号',
                inspect_token : $("input[name='inspect_token']").val()
            }
        }),
        //jsonReader({constructor args},[create fields]);
        reader : new Ext.data.JsonReader({
            totalProperty : 'totalProperty',
            root : 'root'
        }, ['id', 'filed1', 'filed6', 'filed9', 'filed14', 'filed13'])
    });
    ds.load({
        params : {
            aeinspectionNo : $("input[name='aeno']").val() + '第【' + $("input[name='maxino']").val() + '】号',
            inspect_token : $("input[name='inspect_token']").val(),
            start : 0,
            limit : 5
        }
    });
    ds.on("beforeload", function(thiz, options) {
        thiz.baseParams["aeinspectionNo"] = $("input[name='aeno']").val() + '第【' + $("input[name='maxino']").val() + '】号';
        thiz.baseParams["inspect_token"] = $("input[name='inspect_token']").val();
    });
    var talkSummaryGrid = new Ext.grid.GridPanel({
        id : 'workTalkSummarys-grid', // 设置标识ID，方便以后引用！
        title : '执法检查会谈纪要', // 标题
        //renderTo : 'workBasises',// 显示表格的地方
        // sm : sm,// 复选框
        cm : cm, // 列模型
        ds : ds, // 数据源
        region : 'center',
        // layout:'form'  //从上往下布局
        stripeRows : true, // 加上行条纹
        loadMask : true, // 加载数据时遮蔽表格
        border : true, // 加上边框
        frame : true, // 显示天蓝色圆角框
        autoHeight : true, // 自动设置高度，这个配置很重要
        width : 790,
        x : 1, // 设置初始位置横坐标
        y : 1, // 设置初始位置纵坐标
        // enableDragDrop : true,//容许行的拖曳
        // collapsible : true, // 面板可以折叠
        // titleCollapse : true,// 单击表头任何地方都可以折叠
        floating : true,
        viewConfig : {
            //True表示为自动展开/缩小列的宽度以适应grid的宽度，这样就不会出现水平的滚动条
            forceFit : true
        },
        bbar : new Ext.PagingToolbar({
            pageSize : 5,
            store : ds,
            displayInfo : true,
            displayMsg : '显示第{0}条到{1}条  记录共{2}条',
            emptyMsg : '没有记录'
        })
    });
    talkSummaryGrid.addListener('rowdblclick', onTalkSummarydoubleclick);
    var row1 = {
        layout : 'form',
        items : [{
            id : 'textField5',
            xtype : 'displayfield',
            value : '--双击下面列表中记录查看--执法检查会谈纪要会谈内容',
            fieldLabel : '',
            height : 310
        }]
    };
    var viewFiled5From = new Ext.form.FormPanel({//注意：Ext.form.FormPanel=Ext.FormPanel
        title : '<%= request.getAttribute("inputTalkSummary_title")%> 执法检查会谈纪要会谈内容    ' + $("input[name='aeno']").val() + '第【' + $("input[name='maxino']").val() + '】号',
        width : 800,
        height : 250,
        // autoHeight:true,
        bodyStyle : 'padding:5px 5px 0',
        // renderTo:Ext.getBody(),
        frame : true, //在此，这个属性就很重要了，如果不为true，你会看到下面的button和panel看起来是分开的
        // layout : 'form',
        region : 'north',
        url : '', //提交地址
        method : '', //提交方法
        labelWidth : 1,
        labelAlign : 'right',
        items : [row1],
        buttonAlign : 'center', //按钮对其方式
        style : 'padding:10px'
    });

    //grid.render();
    var viewBasisWin = new Ext.Window({
        title : '查看执法检查会谈纪要',
        width : 800,
        height : 520,
        resizable : false,
        closeAction : 'close',
        //autoHeight : true,
        constrainHeader : true,
        modal : true,
        plain : true,
        layout : 'border',
        items : [viewFiled5From, talkSummaryGrid],
        buttons : [{
            text : '关闭',
            handler : function() {//点击取消按钮的操作事件
                viewBasisWin.close();
                viewFiled5From.destroy();
                viewBasisWin.destroy();
            }
        }],
        buttonAlign : 'center' //按钮对其方式
    });
    viewBasisWin.show();
}

var onTalkSummarydoubleclick = function(grid, index, e) {
    var selectionModel = grid.getSelectionModel();
    var record = selectionModel.getSelected();
    Ext.Ajax.request({
        url : '<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=getTalkSummaryContent',
        params : {
            id : record.get('id')
        },
        method : 'POST',
        callback : function(options, success, response) {
            if(success) {
                // alert(response.responseText);
                var responseJson = Ext.util.JSON.decode(response.responseText);
                // Ext.Msg.alert("成功" + responseJson.filed5);
                // alert(Ext.getCmp('textField5'));
                Ext.getCmp('textField5').setValue(responseJson.filed10);
            } else {
                Ext.Msg.confirm('失败', '请求超时或网络故障,错误编号：[' + response.status + ']是否要重新发送？', function(btn) {
                    if(btn == 'yes') {
                        Ext.Ajax.request(options);
                    }
                });
            }
        }
    });
}
//=====================================事实认定书==============================
function addFactBook() {
    if($("input[name='isSaveAeedorgSelect']").val() == 'false') {
        Ext.Msg.alert('提示', '请选择被检查机构，然后点击选定按钮');
        var errorGif = '<img src="<%=basePath%>ext-3.2.0//resources//images//default//form//exclamation.gif"></img>';
        $('#aeedorgno_span').html(errorGif + '&nbsp;&nbsp;请先选择被检查机构并点击选定按钮');
    } else if($("input[name='isSaveAeheadmanSelect']").val() == 'false') {
        Ext.Msg.alert('提示', '请选择检查组组长，然后点击选定按钮');
        var errorGif = '<img src="<%=basePath%>ext-3.2.0//resources//images//default//form//exclamation.gif"></img>';
        $('#aeheadman_span').html(errorGif + '&nbsp;&nbsp;保存前请选择检查组组长，然后点击选定按钮');
    } else {
        var row1 = {
            layout : 'column', //从左往右布局
            items : [{
                columnWidth : .5,
                layout : 'form',
                items : [{
                    xtype : 'datefield',
                    fieldLabel : '检查时间',
                    format : "Y-m-d",
                    name : 'filed6',
                    id : 'filed6',
                    width : 250,
                    allowBlank : false,
                    editable : false,
                    value : $("input[name='incomingDate']").val()
                }]
            }, {
                columnWidth : .5,
                layout : 'form',
                items : [{
                    xtype : 'datefield',
                    fieldLabel : '到',
                    format : "Y-m-d",
                    name : 'filed12',
                    id : 'filed12',
                    width : 250,
                    allowBlank : false,
                    editable : false,
                    value : $("input[name='goawayDate']").val()
                }]
            }]
        };
        var row2 = {
            layout : 'column', //从左往右布局
            items : [{
                columnWidth : 1, //该列有整行中所占百分比
                layout : 'form', //从上往下布局
                items : [{
                    xtype : 'textfield',
                    fieldLabel : '检查内容',
                    name : 'filed9',
                    id : 'filed9',
                    width : 596,
                    allowBlank : false,
                    value : '<%= request.getAttribute("filed9")%>'
                }]
            }]
        };
        var row3 = {
            layout : 'form',
            items : [{
                xtype : 'htmleditor',
                fieldLabel : '',
                name : 'factBookContent',
                id : 'factBookContent',
                height : 300,
                width : 596,
                // createLinkText: "创建超链接",
                // defaultLinkValue: "http://",
                enableAlignments : false,
                enableColors : false,
                enableFont : false,
                enableFontSize : false,
                enableFormat : false,
                enableLinks : false,
                enableLists : false,
                enableSourceEdit : true,
                fontFamilies : ["宋体", "隶书", "黑体"]
            }]
        };
        var row4 = {
            layout : 'form',
            items : [{
                xtype : 'textfield',
                name : 'selectedwtgk',
                id : 'selectedwtgk',
                hidden : true,
                hideLabel : true,
                value : ''
            }]
        };
        var form = new Ext.form.FormPanel({
            title : '<%= request.getAttribute("inputFactBook_title")%>',
            width : 600,
            //height:250,
            autoHeight : true,
            region : 'center',
            bodyStyle : 'padding:5px 5px 0',
            //renderTo : Ext.getBody(),
            frame : true,
            layout : 'form',
            url : '',
            method : 'post',
            labelWidth : 79,
            labelAlign : 'right',
            items : [row1, row2, row3, row4],
            buttonAlign : 'center',
            style : 'padding:10px',
            buttons : [{
                text : '确定',
                handler : function() {
                    if(Ext.getCmp('factBookContent').getValue() == '') {
                        Ext.Msg.alert('提示', '请选择问题概况');
                    } else {
                        if(Ext.getCmp('filed9').getValue() == '' || Ext.getCmp('filed6').getValue() == '' || Ext.getCmp('filed12').getValue() == '') {
                            Ext.Msg.alert('提示', '检查时间和检查内容为必填信息');
                        } else {
                            Ext.Msg.confirm('提示', '是否确认新增事实认定书？', function(btn) {
                                if(btn == 'yes') {
                                    Ext.Ajax.request({
                                        url : '<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=saveFactBook',
                                        method : 'post',
                                        params : {
                                            factBookNo : $("input[name='factnotext']").val() + '【' + $("input[name='factnoyear']").val() + '】第【' + $("input[name='factnoindex']").val() + '】',
                                            aeedOrg : $("input[name='aeedorgnm']").val(),
                                            aeinspectionNo : $("input[name='aeno']").val() + '第【' + $("input[name='maxino']").val() + '】号',
                                            aeno : $("input[name='aeno']").val(),
                                            factBookContent : Ext.getCmp('factBookContent').getValue(),
                                            filed6 : Ext.util.Format.date(Ext.getCmp('filed6').getValue(), "Y-m-d"),
                                            filed12 : Ext.util.Format.date(Ext.getCmp('filed12').getValue(), "Y-m-d"),
                                            filed9 : Ext.getCmp('filed9').getValue(),
                                            selectedwtgk : Ext.getCmp('selectedwtgk').getValue(),
                                            aeorgno : $("input[name='aeorgno']").val(),
                                            aeorgnm : $("input[name='aeorgnm']").val(),
                                            aeedorgno : $("select[name='aeedorgno']").val(),
                                            aeedorgnm : $("select[name='aeedorgno']").find("option:selected").text(),
                                            inspect_token : $("input[name='inspect_token']").val()
                                        },
                                        success : function(response, options) {
                                            var o = Ext.util.JSON.decode(response.responseText);
                                            Ext.Msg.alert('提示', o.msg);
                                            addBasisWin.close();
                                            form.destroy();
                                            addBasisWin.destroy();
                                        },
                                        failure : function() {
                                            Ext.Msg.alert('提示', '保存事实认定书发生错误');
                                        }
                                    });
                                }
                            });
                        }
                    }

                }
            }, {
                text : '关闭',
                handler : function() {
                    addBasisWin.close();
                    form.destroy();
                    addBasisWin.destroy();
                }
            }]
        });
        var trNode;
        var loader = new Ext.tree.TreeLoader({
            dataUrl : '<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=getProblemSummay',
            listeners : {
                "beforeload" : function(treeLoader, node) {
                    treeLoader.baseParams.id = (node.id != "0" ? node.id : "");
                }
            }
        });
        Ext.BLANK_IMAGE_URL = "<%=request.getContextPath()%>/ext-3.2.0/resources/images/default/tree/s.gif";
        var tree = new Ext.tree.TreePanel({
            title : '选择问题概况',
            useArrows : true,
            autoScroll : true,
            containerScroll : true,
            region : 'west',
            animate : true,
            height : 200,
            width : 210,
            loader : loader,
            listeners : {
                "click" : function(node) {
                    trNode = node;
                },
                "checkchange" : function(node, state) {
                    if(node.hasChildNodes()) {
                        for( i = 0; i < node.childNodes.length; i++) {
                            node.childNodes[i].getUI().checkbox.checked = state;
                        }
                    }
                }
            },
            buttons : [{
                text : '选择',
                handler : function() {
                    //var nodes = tree.getChecked();
                    var nodes = tree.getRootNode().childNodes;
                    var selectedIds = '';
                    for(var j = 0; j < nodes.length; j++) {
                        var node = tree.getRootNode().childNodes[j];
                        if(node.hasChildNodes()) {
                            for(var i = 0; i < node.childNodes.length; i++) {
                                if(node.childNodes[i].getUI().checkbox.checked) {
                                    // alert(node.childNodes[i].id);
                                    selectedIds = selectedIds + node.childNodes[i].id + ',';
                                }
                            }
                        }
                    }
                    Ext.getCmp('selectedwtgk').setValue(selectedIds);
                    Ext.Ajax.request({
                        url : '<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=generateFactBook',
                        method : 'post',
                        params : {
                            selectIds : selectedIds
                        },
                        success : function(response, options) {
                            // alert('a');
                            var o = Ext.util.JSON.decode(response.responseText);
                            // alert(o.content);
                            Ext.getCmp('factBookContent').setValue(o.content);
                        },
                        failure : function() {
                            Ext.Msg.alert('提示', '生成事实认定书发生错误');
                        }
                    });

                }
            }],
            buttonAlign : 'center'
        });

        var root = new Ext.tree.AsyncTreeNode({
            text : '问题概况',
            draggable : false,
            id : '000011'
        });
        tree.setRootNode(root);
        //        tree.render();
        root.expand();

        var addBasisWin = new Ext.Window({
            title : '编辑事实认定书',
            layout : 'border',
            width : 960,
            height : 520,
            resizable : false,
            closeAction : 'close',
            //autoHeight : true,
            constrainHeader : true,
            modal : true,
            plain : true,
            items : [tree, form]
        });
        addBasisWin.show();
    }
}

//=======================执法情况统计==================================
function inputinspectionAnl() {
    if($("input[name='isSaveAeedorgSelect']").val() == 'false') {
        Ext.Msg.alert('提示', '请选择被检查机构，然后点击选定按钮');
        var errorGif = '<img src="<%=basePath%>ext-3.2.0//resources//images//default//form//exclamation.gif"></img>';
        $('#aeedorgno_span').html(errorGif + '&nbsp;&nbsp;请先选择被检查机构并点击选定按钮');
    } else if($("input[name='isSaveAeheadmanSelect']").val() == 'false') {
        Ext.Msg.alert('提示', '请选择检查组组长，然后点击选定按钮');
        var errorGif = '<img src="<%=basePath%>ext-3.2.0//resources//images//default//form//exclamation.gif"></img>';
        $('#aeheadman_span').html(errorGif + '&nbsp;&nbsp;保存前请选择检查组组长，然后点击选定按钮');
    } else {
        // reference local blank image
        Ext.BLANK_IMAGE_URL = "<%=request.getContextPath()%>/ext-3.2.0/resources/images/default/tree/s.gif";
        var row1 = {
            layout : 'column', //从左往右布局
            items : [{
                columnWidth : .5, //该列有整行中所占百分比
                layout : 'form', //从上往下布局
                items : [{
                    xtype : 'textfield',
                    fieldLabel : '检查派出行',
                    name : 'a1',
                    id : 'a1',
                    value : $("input[name='aeorgnm']").val(),
                    width : 250,
                    allowBlank : false
                }]
            }, {
                columnWidth : .5,
                layout : 'form',
                items : [{
                    xtype : 'textfield',
                    fieldLabel : '被检查机构',
                    name : 'b1',
                    id : 'b1',
                    value : $("select[name='aeedorgno']").find("option:selected").text(),
                    width : 250,
                    allowBlank : false
                }]
            }]
        };
        var row2 = {
            layout : 'column', //从左往右布局
            items : [{
                columnWidth : .5, //该列有整行中所占百分比
                layout : 'form', //从上往下布局
                items : [{
                    xtype : 'textfield',
                    fieldLabel : '派出检查人员',
                    emptyText : '（人天）',
                    name : 'c1',
                    id : 'c1',
                    width : 250,
                    regex : /^\d+$/,
                    regexText : '请输入正确的数据类型',
                    allowBlank : false
                }]
            }, {
                columnWidth : .5,
                layout : 'form',
                items : [{
                    xtype : 'textfield',
                    fieldLabel : '现场检查',
                    emptyText : '（天）',
                    name : 'd1',
                    id : 'd1',
                    width : 250,
                    regex : /^\d+$/,
                    regexText : '请输入正确的数据类型',
                    allowBlank : false
                }]
            }]
        };
        var row3 = {
            layout : 'column', //从左往右布局
            items : [{
                columnWidth : .5, //该列有整行中所占百分比
                layout : 'form', //从上往下布局
                items : [{
                    xtype : 'textfield',
                    fieldLabel : '调阅企业档案',
                    emptyText : '（户）',
                    name : 'e1',
                    id : 'e1',
                    width : 250,
                    regex : /^\d+$/,
                    regexText : '请输入正确的数据类型',
                    allowBlank : false
                }]
            }, {
                columnWidth : .5,
                layout : 'form',
                items : [{
                    xtype : 'textfield',
                    fieldLabel : '调阅个人档案',
                    emptyText : '（户）',
                    name : 'f1',
                    id : 'f1',
                    width : 250,
                    regex : /^\d+$/,
                    regexText : '请输入正确的数据类型',
                    allowBlank : false
                }]
            }]
        };
        var row4 = {
            layout : 'column', //从左往右布局
            items : [{
                columnWidth : .5, //该列有整行中所占百分比
                layout : 'form', //从上往下布局
                items : [{
                    xtype : 'textfield',
                    fieldLabel : '抽查总数',
                    emptyText : '企业信贷业务（笔）',
                    name : 'g1',
                    id : 'g1',
                    width : 250,
                    regex : /^\d+$/,
                    regexText : '请输入正确的数据类型',
                    allowBlank : false
                }]
            }, {
                columnWidth : .5,
                layout : 'form',
                items : [{
                    xtype : 'textfield',
                    fieldLabel : '错误笔数',
                    emptyText : '企业信贷业务（笔）',
                    name : 'h1',
                    id : 'h1',
                    width : 250,
                    regex : /^\d+$/,
                    regexText : '请输入正确的数据类型',
                    allowBlank : false
                }]
            }]
        };
        var row5 = {
            layout : 'column', //从左往右布局
            items : [{
                columnWidth : .5, //该列有整行中所占百分比
                layout : 'form', //从上往下布局
                items : [{
                    xtype : 'textfield',
                    fieldLabel : '抽查总数',
                    emptyText : '个人信贷业务（笔）',
                    name : 'i1',
                    id : 'i1',
                    width : 250,
                    regex : /^\d+$/,
                    regexText : '请输入正确的数据类型',
                    allowBlank : false
                }]
            }, {
                columnWidth : .5,
                layout : 'form',
                items : [{
                    xtype : 'textfield',
                    fieldLabel : '错误笔数',
                    emptyText : '个人信贷业务（笔）',
                    name : 'j1',
                    id : 'j1',
                    width : 250,
                    regex : /^\d+$/,
                    regexText : '请输入正确的数据类型',
                    allowBlank : false
                }]
            }]
        };
        var row6 = {
            layout : 'column', //从左往右布局
            items : [{
                columnWidth : .5, //该列有整行中所占百分比
                layout : 'form', //从上往下布局
                items : [{
                    xtype : 'textfield',
                    fieldLabel : '调阅工作文件资料',
                    emptyText : '（份）',
                    name : 'k1',
                    id : 'k1',
                    width : 250,
                    regex : /^\d+$/,
                    regexText : '请输入正确的数据类型',
                    allowBlank : false
                }]
            }, {
                columnWidth : .5,
                layout : 'form',
                items : [{
                    xtype : 'textfield',
                    fieldLabel : '制作询问笔录',
                    emptyText : '（份）',
                    name : 'l1',
                    id : 'l1',
                    width : 250,
                    regex : /^\d+$/,
                    regexText : '请输入正确的数据类型',
                    allowBlank : false
                }]
            }]
        };
        var row7 = {
            layout : 'column', //从左往右布局
            items : [{
                columnWidth : .5, //该列有整行中所占百分比
                layout : 'form', //从上往下布局
                items : [{
                    xtype : 'textfield',
                    fieldLabel : '制作工作底稿',
                    emptyText : '（份）',
                    name : 'm1',
                    id : 'm1',
                    width : 250,
                    allowBlank : false,
                    regex : /^\d+$/,
                    regexText : '请输入正确的数据类型'
                }]
            }, {
                columnWidth : .5,
                layout : 'form',
                items : [{
                    id : 'n1',
                    xtype : 'combo',
                    fieldLabel : '检查方式',
                    displayField : 'jcfs',
                    name : 'n1',
                    triggerAction : 'all',
                    emptyText : '请选择数据项',
                    mode : 'local',
                    // readOnly:true,
                    width : 250,
                    allowBlank : false,
                    // anchor : '96%',
                    editable : false,
                    forceSelection : true,
                    // blankText : '必须选择检查方式',
                    store : new Ext.data.SimpleStore({
                        fields : ['jcfs'],
                        data : [['专项执法'], ['综合执法']]
                    })
                }]
            }]
        };
        var row8 = {
            layout : 'column',
            items : [{
                columnWidth : .5,
                layout : 'form',
                items : [{
                    xtype : 'textfield',
                    fieldLabel : '已实施行政处罚金额',
                    emptyText : '（元）',
                    name : 'p1',
                    id : 'p1',
                    width : 250,
                    // regex : /^\d+(\.\d{1,2})?$/,
                    // regexText : '请输入正确的数据类型',
                    allowBlank : true,
                    readOnly : true,
                    value : 0
                }]
            }]
        };
        var row9 = {
            layout : 'form',
            items : [{
                xtype : 'textfield',
                name : 'aeinspectionno',
                id : 'aeinspectionno',
                hidden : true,
                hideLabel : true,
                value : $("input[name='aeno']").val() + '第【' + $("input[name='maxino']").val() + '】号'
            }, {
                xtype : 'textfield',
                name : 'aeno',
                id : 'aeno',
                hidden : true,
                hideLabel : true,
                value : $("input[name='aeno']").val()
            }, {
                xtype : 'textfield',
                name : 'aeorgno',
                id : 'aeorgno',
                hidden : true,
                hideLabel : true,
                value : $("input[name='aeorgno']").val()
            }, {
                xtype : 'textfield',
                name : 'aeorgnm',
                id : 'aeorgnm',
                hidden : true,
                hideLabel : true,
                value : $("input[name='aeorgnm']").val()
            }, {
                xtype : 'textfield',
                name : 'aeedorgno',
                id : 'aeedorgno',
                hidden : true,
                hideLabel : true,
                value : $("select[name='aeedorgno']").val()
            }, {
                xtype : 'textfield',
                name : 'aeedorgnm',
                id : 'aeedorgnm',
                hidden : true,
                hideLabel : true,
                value : $("select[name='aeedorgno']").find("option:selected").text()
            }, {
                xtype : 'textfield',
                name : 'inspect_token',
                id : 'inspect_token',
                hidden : true,
                hideLabel : true,
                value : $("input[name='inspect_token']").val()
            }]
        };
        var fp = new Ext.form.FormPanel({
            title : '<%= request.getAttribute("inputInspectionAnl_title")%>',
            width : 800,
            //height:250,
            autoHeight : true,
            bodyStyle : 'padding:5px 5px 0',
            renderTo : Ext.getBody(),
            frame : true, //在此，这个属性就很重要了，如果不为true，你会看到下面的button和panel看起来是分开的
            layout : 'form',
            url : '', //提交地址
            method : 'post', //提交方法
            labelWidth : 120,
            labelAlign : 'right',
            items : [row1, row2, row3, row4, row5, row6, row7, row8, row9],
            buttonAlign : 'center', //按钮对其方式
            style : 'padding:10px',
            buttons : [{
                text : '确定',
                handler : function() {
                    Ext.Msg.confirm('提示', '是否确认新增执法检查情况？', function(btn) {
                        if(btn == 'yes') {
                            fp.getForm().submit({
                                url : '<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=saveInspectionAnl',
                                waitTitle : '请等待',
                                waitMsg : '正在添加执法检查情况',
                                success : function(form, action) {
                                    //url后台返回的数据{success:true,msg:'成功'}
                                    Ext.Msg.alert('提示', action.result.msg);
                                    addBasisWin.close();
                                    fp.destroy();
                                    addBasisWin.destroy();
                                },
                                failure : function(form, action) {
                                    Ext.Msg.alert('提示', '保存执法检查情况失败！请重新填写');
                                }
                            });
                        }
                    });
                }
            }, {
                text : '关闭',
                handler : function() {
                    addBasisWin.close();
                    fp.destroy();
                    addBasisWin.destroy();
                }
            }]
        });
        var ds = new Ext.data.Store({
            proxy : new Ext.data.HttpProxy({
                url : '<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=pageInspectionAnl',
                method : 'POST',
                baseParams : {
                    aeinspectionNo : $("input[name='aeno']").val() + '第【' + $("input[name='maxino']").val() + '】号',
                    inspect_token : $("input[name='inspect_token']").val()
                }
            }),
            //jsonReader({constructor args},[create fields]);
            reader : new Ext.data.JsonReader({
                totalProperty : 'totalProperty',
                root : 'root'
            }, ['id', 'a1', 'b1', 'c1', 'd1', 'e1', 'f1', 'g1', 'h1', 'i1', 'j1', 'k1', 'l1', 'm1', 'n1', 'p1'])
        });
        ds.load({
            params : {
                aeinspectionNo : $("input[name='aeno']").val() + '第【' + $("input[name='maxino']").val() + '】号',
                inspect_token : $("input[name='inspect_token']").val(),
                start : 0,
                limit : 1
            },
            callback : function(records, options, success) {
                // Ext.Msg.alert('info', records.length);
                if(records.length > 0) {
                    Ext.each(records, function(rec) {
                        fp.getForm().loadRecord(rec);
                        return false;
                    });
                }
            }
        });

        var addBasisWin = new Ext.Window({
            title : '添加执法检查情况',
            width : 810,
            height : 380,
            resizable : false,
            closeAction : 'close',
            //autoHeight : true,
            constrainHeader : true,
            modal : true,
            plain : true,
            items : [fp]
        });
        addBasisWin.show();
    }
}

function viewinspectionAnlGrid() {
    var cm = new Ext.grid.ColumnModel([{
        header : 'id',
        dataIndex : 'id',
        hidden : true
    }, {
        header : '检查派出行',
        dataIndex : 'a1'
    }, {
        header : '被检查机构',
        dataIndex : 'b1'
    }, {
        header : '派出检查人员（人天）',
        dataIndex : 'c1'
    }, {
        header : '现场检查（天）',
        dataIndex : 'd1'
    }, {
        header : '调阅企业档案',
        dataIndex : 'e1'
    }, {
        header : '调阅个人档案',
        dataIndex : 'f1'
    }, {
        header : '抽查总数（企业）',
        dataIndex : 'g1'
    }, {
        header : '错误总数（企业）',
        dataIndex : 'h1'
    }, {
        header : '抽查总数（个人）',
        dataIndex : 'i1'
    }, {
        header : '错误总数（个人）',
        dataIndex : 'j1'
    }, {
        header : '调阅工作文件资料（份）',
        dataIndex : 'k1'
    }, {
        header : '制作询问笔录（份）',
        dataIndex : 'l1'
    }, {
        header : '制作工作底稿（份）',
        dataIndex : 'm1'
    }, {
        header : '检查方式',
        dataIndex : 'n1'
    }, {
        header : '已实施行政处罚金额（元）',
        dataIndex : 'p1'
    }]);
    var ds = new Ext.data.Store({
        proxy : new Ext.data.HttpProxy({
            url : '<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=pageInspectionAnl',
            method : 'POST',
            baseParams : {
                aeinspectionNo : $("input[name='aeno']").val() + '第【' + $("input[name='maxino']").val() + '】号',
                inspect_token : $("input[name='inspect_token']").val()
            }
        }),
        //jsonReader({constructor args},[create fields]);
        reader : new Ext.data.JsonReader({
            totalProperty : 'totalProperty',
            root : 'root'
        }, ['id', 'a1', 'b1', 'c1', 'd1', 'e1', 'f1', 'g1', 'h1', 'i1', 'j1', 'k1', 'l1', 'm1', 'n1', 'p1'])
    });
    ds.load({
        params : {
            aeinspectionNo : $("input[name='aeno']").val() + '第【' + $("input[name='maxino']").val() + '】号',
            inspect_token : $("input[name='inspect_token']").val(),
            start : 0,
            limit : 5
        }
    });
    ds.on("beforeload", function(thiz, options) {
        thiz.baseParams["aeinspectionNo"] = $("input[name='aeno']").val() + '第【' + $("input[name='maxino']").val() + '】号';
        thiz.baseParams["inspect_token"] = $("input[name='inspect_token']").val();
    });
    var inspectionanlGrid = new Ext.grid.GridPanel({
        id : 'inspectionanl-grid', // 设置标识ID，方便以后引用！
        title : '执法检查情况', // 标题
        //renderTo : 'workBasises',// 显示表格的地方
        // sm : sm,// 复选框
        cm : cm, // 列模型
        ds : ds, // 数据源
        region : 'center',
        // layout:'form'  //从上往下布局
        stripeRows : true, // 加上行条纹
        loadMask : true, // 加载数据时遮蔽表格
        border : true, // 加上边框
        frame : true, // 显示天蓝色圆角框
        autoHeight : true, // 自动设置高度，这个配置很重要
        width : 790,
        x : 1, // 设置初始位置横坐标
        y : 1, // 设置初始位置纵坐标
        // enableDragDrop : true,//容许行的拖曳
        // collapsible : true, // 面板可以折叠
        // titleCollapse : true,// 单击表头任何地方都可以折叠
        //floating : true,
        viewConfig : {
            //True表示为自动展开/缩小列的宽度以适应grid的宽度，这样就不会出现水平的滚动条
            forceFit : true
        },
        bbar : new Ext.PagingToolbar({
            pageSize : 5,
            store : ds,
            displayInfo : true,
            displayMsg : '显示第{0}条到{1}条  记录共{2}条',
            emptyMsg : '没有记录'
        })
    });

    //grid.render();
    var viewBasisWin = new Ext.Window({
        title : '查看执法检查情况',
        width : 1000,
        height : 520,
        resizable : false,
        closeAction : 'close',
        //autoHeight : true,
        constrainHeader : true,
        modal : true,
        plain : true,
        layout : 'border',
        items : [inspectionanlGrid],
        buttons : [{
            text : '关闭',
            handler : function() {//点击取消按钮的操作事件
                viewBasisWin.close();
                viewBasisWin.destroy();
            }
        }],
        buttonAlign : 'center' //按钮对其方式
    });
    viewBasisWin.show();
}

function generateWord(wordId) {
    document.forms[0].action = "AdminEnforceManagerAction.do?method=generateWord&wordTemplateId=" + wordId + '&inspect_token=' + $("input[name='inspect_token']").val();
    document.forms[0].submit();
}

function validateForm() {
    var errorGif = '<img src="<%=basePath%>ext-3.2.0//resources//images//default//form//exclamation.gif"></img>';
    var okGif = '<img src="<%=basePath%>ext-3.2.0//resources//images//default//dd//drop-yes.gif"></img>';
    var isPass = true;
    if(isNullOrEmpty($("input[name='aeplanstdate']").val()) || isNullOrEmpty($("input[name='aeplantmdate']").val())) {
        $('#aeplandate_span').html(errorGif + '&nbsp;&nbsp;必填');
        isPass = false;
    } else {
        if($("input[name='aeplantmdate']").val() < $("input[name='aeplanstdate']").val()) {
            $('#aeplandate_span').html(errorGif + '&nbsp;&nbsp;开始时间应该小于结束时间');
            isPass = false;
        } else {
            $('#aeplandate_span').html(okGif);
        }
    }
    if(isNullOrEmpty($("select[name='aeedorgno']").val())) {
        $('#aeedorgno_span').html(errorGif + '&nbsp;&nbsp;必填');
        isPass = false;
    } else {
        if($("input[name='isSaveAeedorgSelect']").val() == 'false') {
            $('#aeedorgno_span').html(errorGif + '&nbsp;&nbsp;保存前请选择被检查机构，然后点击选定按钮');
            isPass = false;
        } else {
            $('#aeedorgno_span').html(okGif);
        }
    }

    if(isNullOrEmpty($("select[name='aeheadman']").val())) {
        $('#aeheadman_span').html(errorGif + '&nbsp;&nbsp;必填');
        isPass = false;
    } else {
        if($("input[name='isSaveAeheadmanSelect']").val() == 'false') {
            $('#aeheadman_span').html(errorGif + '&nbsp;&nbsp;保存前请选择检查组组长，然后点击选定按钮');
            isPass = false;
        } else {
            $('#aeheadman_span').html(okGif);
        }
    }
    if(isNullOrEmpty($("select[name='aemaster']").val())) {
        $('#aemaster_span').html(errorGif + '&nbsp;&nbsp;必填');
        isPass = false;
    } else {
        var text = '';
        var index = 0;
        $("select[name='aemaster']").find("option:selected").each(function() {
            text = text + $(this).text() + ',';
            index++;
        });
        if(index > 2) {
            $('#aemaster_span').html(errorGif + '&nbsp;&nbsp;检查组主查人最多只能选择2个');
            isPass = false;
        } else {
            $('#aemaster_span').html(okGif);
        }
    }
    if(isNullOrEmpty($("select[name='aeother']").val())) {
        $('#aeother_span').html(errorGif + '&nbsp;&nbsp;必填');
        isPass = false;
    } else {
        $('#aeother_span').html(okGif);
    }
    return isPass;
}

function createAeinspection() {
    if(validateForm()) {
        Ext.Msg.confirm('提示', '是否确认新增行政执法检查工作记录信息?', function(btn) {
            if(btn == 'yes') {
            	//Ext.Ajax.request({
					//url : '<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=isInputAllWork',
					//method : 'post',
					//params : {
					//	aeinspectionNo : $("input[name='aeno']").val() + '第【' + $("input[name='maxino']").val() + '】号',
					//	inspect_token : $("input[name='inspect_token']").val()
					//},
					//success : function(response, options) {
					//	var returnMsg = Ext.util.JSON.decode(response.responseText);
					//	if(returnMsg.success) {
						//	var msgbox = Ext.Msg.progress("提示", "保存数据", "请稍候.....");
                            //显示等待对话框
                       //     document.forms[0].action = "AdminEnforceManagerAction.do?method=createAeinspection";
                     //       document.forms[0].submit();
					//	} else {
					//		Ext.Msg.alert('提示', returnMsg.msg);
					//	}
					//},
					//failure : function() {
//
					//}
				//});
				var msgbox = Ext.Msg.progress("提示", "保存数据", "请稍候.....");
                //显示等待对话框
                document.forms[0].action = "AdminEnforceManagerAction.do?method=createAeinspection";
                document.forms[0].submit();
            }
        });
    }
}

function confirmAeedOrg() {
	if($("select[name='aeedorgno']").val() == '') {
		Ext.Msg.alert('提示', '请先选择被检查机构');
	} else {
		Ext.Msg.confirm('提示', '是否确认选择被检查机构为-' + $("select[name='aeedorgno']").find("option:selected").text() + '？', function(btn) {
			if(btn == 'yes') {
				Ext.Ajax.request({
					url : '<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=isExsitAeInspc',
					method : 'post',
					params : {
						aeno : $("input[name='aeno']").val(),
						aeedorgno : $("select[name='aeedorgno']").val()
					},
					success : function(response, options) {
						var returnMsg = Ext.util.JSON.decode(response.responseText);
						if(returnMsg.success) {
							if(returnMsg.msg == 'false') {
								$("select[name='aeedorgno']").attr("disabled", "disabled");
								$("#isSaveAeedorgSelect").val("true");
								$("#aeedorgnoToServer").val($("select[name='aeedorgno']").val());
							} else {
								Ext.Msg.alert('提示', '该被检查机构在此立项中已经存在工作检查记录，如需修改请转到工作检查记录修改功能。');
							}
						} else {
							Ext.Msg.alert('提示', '在调用判断该检查机构是否在此立项中存在工作检查记录时发生错误，错误原因：' + returnMsg.msg);
						}
					},
					failure : function() {
						Ext.Msg.alert('提示', '在调用判断该检查机构是否在此立项中存在工作检查记录时发生错误');
					}
				});
			}
		});
	}
}

function confirmAeheanman() {
    if($("select[name='aeheadman']").find("option:selected").text() == '') {
        Ext.Msg.alert('提示', '请先选择检查组组长');
    } else {
        Ext.Msg.confirm('提示', '是否确认选择检查组组长为-' + $("select[name='aeheadman']").find("option:selected").text() + '?', function(btn) {
            if(btn == 'yes') {
                var text = '';
                var index = 0;
                $("select[name='aeheadman']").find("option:selected").each(function() {
                    text = text + $.trim($(this).text()) + ',';
                    index++;
                });
                if(index > 2) {
                    Ext.Msg.alert('提示', '检查组组长最多只能选择2个');
                    return;
                }
                if(index > 0) {
                    text = text.substr(0, text.length - 1);
                }
                $("#aeHeadmanToServer").val(text);
                $("select[name='aeheadman']").attr("disabled", "disabled");
                $("#isSaveAeheadmanSelect").val("true");
            }
        });
    }
}
</script>
	<body>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="35" class="nawzjj">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="ndwz">
								&nbsp;
							</td>
							<td class="ndwzzml">
								<bean:message key="PROJECT_NAME" />
								<img src="<%=request.getContextPath()%>/images/index11.jpg"
									width="6" height="10" hspace="5" />
								行政执法管理
								<img src="<%=request.getContextPath()%>/images/index11.jpg"
									width="6" height="10" hspace="5" />
								检查工作记录信息录入
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<html:form
			action="/AdminEnforceManagerAction.do?method=createAeinspection"
			method="post" enctype="multipart/form-data">
			<input type="hidden" id='isSaveAeedorgSelect' name="isSaveAeedorgSelect" value="false"/>
			<input type="hidden" id='isSaveAeheadmanSelect' name="isSaveAeheadmanSelect" value="false"/>
			<input type="hidden" id='incomingDate' name="incomingDate" value=""/>
            <input type="hidden" id='reposiblitor' name="reposiblitor" value=""/>
			<input type="hidden" id='goawayDate' name="goawayDate" value=""/>
			<input type="hidden" id='recorder' name="recorder" value=""/>
			<input type="hidden" id='inspect_token' name="inspect_token" value="<%= request.getAttribute("inspect_token") %>"/>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="left" valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td valign="top">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td class="tablestyle">
												<table width="100%" border="0" cellpadding="0"
													cellspacing="0">
													<tr>
														<td>
															<table width="100%" border="0" cellspacing="0"
																cellpadding="0">
																<tr>
																	<td class="tiltlegerner">
																		检查工作记录信息录入
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td height="180" valign="top">
															<table width="100%" border="0" cellpadding="0"
																cellspacing="0" class="tableline01">
																<tr>
																	<td align="right" class="tabletext02">
																		执法检查立项编号（征信）
																	</td>
																	<td>
																		<html:text property="aeno" readonly="true" size="100%"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		检查工作记录编号（征信）
																	</td>
																	<td>
																		<html:text property="aeno" readonly="true" size="40"></html:text>
																		第【<html:text property="maxino" readonly="true" size="3" ></html:text>】号
																	</td>
																</tr>
                                                                <tr>
                                                                    <td align="right" class="tabletext02">
                                                                                                                                                 检查工作记录编号（行内）
                                                                    </td>
                                                                    <td>
                                                                        <html:text property="innerno" styleClass="text11155"></html:text>
                                                                                                                                                第【<html:text property="maxino" readonly="true" size="3" ></html:text>】号
                                                                    </td>
                                                                </tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>执法检查单位
																	</td>
																	<td>
																		<html:text property="aeorgnm" readonly="true" size="100%"></html:text>
																		<html:hidden property="aeorgno"></html:hidden>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>被检查机构
																	</td>
																	<td>
																	    <select id="aeedorgno" name="aeedorgno">
																	        <option
                                                                                    value="">
                                                                                    --请选择--
                                                                            </option>
                                                                            <logic:iterate id="org" name="aeedorgs">
                                                                                <option
                                                                                    value="<bean:write name="org" property="no" />"><bean:write name="org" property="name" />
                                                                                </option>
                                                                            </logic:iterate>
                                                                        </select>
                                                                        &nbsp;&nbsp;
                                                                        <input type="button" value="选 定" class="botton01"
                                                                            onclick="return confirmAeedOrg();" />
                                                                        <input type="hidden" id="aeedorgnoToServer" name="aeedorgnoToServer"></input>
                                                                        <span id="aeedorgno_span"></span>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>检查时间
																	</td>
																	<td>
																		<html:text property="aeplanstdate" maxlength="20"
																			size="15" styleClass="text111"
																			onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
																		&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;
																		<html:text property="aeplantmdate" maxlength="20"
																			size="15" styleClass="text111"
 																			onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
																		<span id="aeplandate_span"></span>
																	</td>
																	
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		项目名称
																	</td>
																	<td>
																		<html:text property="prjnm" readonly="true" size="100%"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>检查组组长
																	</td>
																	<td>
																	    <select id="aeheadman" name="aeheadman" multiple="multiple" size='6'>
                                                                            <logic:iterate id="headman" name="aeheadmans">
                                                                                <option
                                                                                    value="<bean:write name="headman" property="pepname" />--<bean:write name="headman" property="certno" />">
                                                                                    <bean:write name="headman" property="pepname" />--<bean:write name="headman" property="certno" />
                                                                                </option>
                                                                            </logic:iterate>
                                                                        </select>
                                                                        &nbsp;&nbsp;
                                                                        <input type="button" value="选 定" class="botton01"
                                                                            onclick="return confirmAeheanman();" />
                                                                        <input type="text" readonly="readonly" id="aeHeadmanToServer" name="aeHeadmanToServer" size="100%" value=""></input>
                                                                        <br>
																		<span id="aeheadman_span"></span>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>检查组主查人
																	</td>
																	<td>
																	    <select id="aemaster" name="aemaster" multiple="multiple" size='6'>
                                                                            <logic:iterate id="master" name="aemasters">
                                                                                <option
                                                                                    value="<bean:write name="master" property="pepname" />--<bean:write name="master" property="certno" />">
                                                                                    <bean:write name="master" property="pepname" />--
                                                                                    <bean:write name="master" property="certno" />
                                                                                </option>
                                                                            </logic:iterate>
                                                                        </select>
																		<span id="aemaster_span"></span>
																	</td>
																</tr>
																<tr>
                                                                    <td align="right" class="tabletext02">
                                                                        <font color='#FF0000'>*</font>检查组其他成员
                                                                    </td>
                                                                    <td>
                                                                        <select id="aeother" name="aeother" multiple="multiple" size='10'> 
                                                                            <logic:iterate id="other" name="aeothers">
                                                                                <option
                                                                                    value="<bean:write name="other" property="pepname" />--<bean:write name="other" property="certno" />">
                                                                                    <bean:write name="other" property="pepname" />--
                                                                                    <bean:write name="other" property="certno" />
                                                                                </option>
                                                                            </logic:iterate>
                                                                        </select>
                                                                        <span id="aeother_span"></span>
                                                                    </td>
                                                                </tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>进场记录
																	</td>
																	<td>
																		<input type="button" value="添 加" class="botton01"
																			onclick="inputComing()" />&nbsp;&nbsp;
																		<input type="button" value="查 看" class="botton01"
																			onclick="viewComingGrid()" />
																	</td>
																</tr>
																<tr>
                                                                    <td align="right" class="tabletext02">
                                                                        <font color='#FF0000'>*</font>执法检查会谈纪要
                                                                    </td>
                                                                    <td>
                                                                        <input type="button" value="添 加" class="botton01"
                                                                            onclick="inputTalkSummary()" />&nbsp;&nbsp;
                                                                        <input type="button" value="查 看" class="botton01"
                                                                            onclick="viewTalkSummaryGrid()" />
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td align="right" class="tabletext02">
                                                                                                                                              上传询问笔录相关附件
                                                                    </td>
                                                                    <td align="left">
                                                                        <input type="file" name="enquirercdpath" id="aeplan"
                                                                            class="filepath01" size="70%" />
                                                                        <span id="enquirercdpath_span"></span>
                                                                        <br>
                                                                        &nbsp;&nbsp;如果需要上传多份文件可以先压缩多份文件，然后上传压缩文件
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td align="right" class="tabletext02">
                                                                        <font color='#FF0000'>*</font>离场会谈纪要
                                                                    </td>
                                                                    <td>
                                                                        <input type="button" value="添 加" class="botton01"
                                                                            onclick="inputGoaway()" />&nbsp;&nbsp;
                                                                        <input type="button" value="查 看" class="botton01"
                                                                            onclick="viewGoawayGrid()" />
                                                                    </td>
                                                                </tr>
																<tr>
                                                                    <td align="right" class="tabletext02">
                                                                        <font color='#FF0000'>*</font>事实认定书
                                                                    </td>
                                                                    <td align="left">
                                                                        <input type="button" value="添 加" class="botton01"
                                                                            onclick="addFactBook()" />&nbsp;&nbsp;
                                                                        <input type="button" value="导 出" class="botton01"
                                                                            onclick="return generateWord('factbook');" />
                                                                    </td>
                                                                </tr>
																<tr>
																	<td align="right" class="tabletext02">
																		上传事实认定书相关附件
																	</td>
																	<td align="left">
																		<input type="file" name="actualrcdpath" id="aeplan"
																			class="filepath01" size="70%" />
																		<br>
                                                                        &nbsp;&nbsp;如果需要上传多份文件可以先压缩多份文件，然后上传压缩文件
																	</td>
																</tr>
																<tr>
                                                                    <td align="right" class="tabletext02">
                                                                        <font color='#FF0000'>*</font>工作底稿
                                                                    </td>
                                                                    <td align="left">
                                                                        <input type="button" value="添 加" class="botton01"
                                                                            onclick="inputBasis()" />&nbsp;&nbsp;
                                                                        <input type="button" value="查 看" class="botton01"
                                                                            onclick="viewBasisGrid()" />
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td align="right" class="tabletext02">
                                                                                                                                               上传工作底稿相关附件
                                                                    </td>
                                                                    <td align="left">
                                                                        <input type="file" name="aebasispath" id="aeplan"
                                                                            class="filepath01" size="70%" />
                                                                        <br>
                                                                        &nbsp;&nbsp;如果需要上传多份文件可以先压缩多份文件，然后上传压缩文件
                                                                    </td>
                                                                </tr>
																<tr>
                                                                    <td align="right" class="tabletext02">
                                                                        <font color='#FF0000'>*</font>检查情况统计
                                                                    </td>
                                                                    <td>
                                                                        <input type="button" value="添 加" class="botton01"
                                                                            onclick="inputinspectionAnl()" />&nbsp;&nbsp;
                                                                        <input type="button" value="查 看" class="botton01"
                                                                            onclick="viewinspectionAnlGrid()" />
                                                                    </td>
                                                                </tr>
																
																<tr>
																	<td colspan="2" align="center">
																		<input type="button" value="保 存" class="botton01"
																			onclick="return createAeinspection();" />
																			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<input type="button" value="返 回" class="botton01"
																			onclick="javascript:history.back()" />
																	</td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			
		</html:form>
	</body>
</html>