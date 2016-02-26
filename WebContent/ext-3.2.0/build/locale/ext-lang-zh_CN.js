/*
 * Simplified Chinese translation
 * By DavidHu
 * 09 April 2007
 */


if(Ext.View){
   Ext.View.prototype.emptyText = "";
}


Date.monthNames = [
   "一月",
   "二月",
   "三月",
   "四月",
   "五月",
   "六月",
   "七月",
   "八月",
   "九月",
   "十月",
   "十一",
   "十二"
];

Date.dayNames = [
   "日",
   "一",
   "二",
   "三",
   "四",
   "五",
   "六"
];

if(Ext.MessageBox){
   Ext.MessageBox.buttonText = {
      ok     : "确定",
      cancel : "取消",
      yes    : "是",
      no     : "否"
   };
}

if(Ext.util.Format){
   Ext.util.Format.date = function(v, format){
      if(!v) return "";
      if(!(v instanceof Date)) v = new Date(Date.parse(v));
      return v.dateFormat(format || "y年m月d日");
   };
}

if(Ext.DatePicker){
   Ext.apply(Ext.DatePicker.prototype, {
      todayText         : "今天",
      minText           : "日期在最小日期之前",
      maxText           : "日期在最大日期之后",
      disabledDaysText  : "",
      disabledDatesText : "",
      monthNames        : Date.monthNames,
      dayNames          : Date.dayNames,
      nextText          : '下月 (Control+Right)',
      prevText          : '上月 (Control+Left)',
      monthYearText     : '选择一个月 (Control+Up/Down 来改变年)',
      todayTip          : "{0} (空格键选择)",
      format            : "y年m月d日",
      okText            : "确定",
      cancelText        : "取消"
   });
}



if(Ext.form.DateField){
   Ext.apply(Ext.form.DateField.prototype, {
      disabledDaysText  : "禁用",
      disabledDatesText : "禁用",
      minText           : "该输入项的日期必须在 {0} 之后",
      maxText           : "该输入项的日期必须在 {0} 之前",
      invalidText       : "{0} 是无效的日期 - 必须符合格式： {1}",
      format            : "y年m月d日"
   });
}




