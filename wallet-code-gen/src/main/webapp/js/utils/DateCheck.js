/**
 * 日期处理函数
 * haoshuai
 */
var DateCheck = function() {	
	this.EqualDate=function (createDate,endDate){
		var createDateStr=createDate.replace(/-/g, "/");
		var createdate=new Date(createDateStr);
		var createdateSeconds=createdate.getTime();
		
		var endDateStr=endDate.replace(/-/g, "/");
		var enddate=new Date(endDateStr);
		var enddateSeconds=enddate.getTime();
		if(createdateSeconds>enddateSeconds){
			return false;
		}return true;
	};
	this.checkEqualDate=function(start,end){
    	if(start!=""&&end!=""){
    		var flag=EqualDate(start,end);
    		if(!flag){
    			$.messager.alert("消息","开始日期必须小于结束日期!");
    			return false;
    		}	
    	}
    	if(start==""&&end!=""||start!=""&&end==""){
    		$.messager.alert("消息","开始日期和结束日期必须同时录入!");
    		return false;
    	}
    	return true;
    };
	return this;
}();
