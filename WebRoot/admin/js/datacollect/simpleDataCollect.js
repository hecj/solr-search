
var SimpleDataCollect = {
    /*
     * 添加字段
     */
	addField:function(){
		var defaultField = jQuery("#ID_fieldContent #ID_defaultField").html();
		jQuery("#ID_fieldContent ul").append("<li>"+defaultField+"</li>")
	},
	/*
	 * 提交数据爬取
	 */
	submitDataCollect:function(){
		var baseURL = jQuery(".ID_mainContent .CLS_mainContentDivHegiht input[name=baseURL]").val();
		var pageParams = jQuery(".ID_mainContent .CLS_mainContentDivHegiht input[name=pageParams]").val();
		var start = jQuery(".ID_mainContent .CLS_mainContentDivHegiht input[name=start]").val();
		var end = jQuery(".ID_mainContent .CLS_mainContentDivHegiht input[name=end]").val();
		var step = jQuery(".ID_mainContent .CLS_mainContentDivHegiht input[name=step]").val();
		var baseSelect = jQuery(".ID_mainContent .CLS_mainContentDivHegiht input[name=baseSelect]").val();
		var encode = jQuery(".ID_mainContent .CLS_mainContentDivHegiht select[name=encode]").val();
		var tableName = jQuery(".ID_mainContent .CLS_mainContentDivHegiht input[name=tableName]").val();
		var dataBaseType = jQuery(".ID_mainContent .CLS_mainContentDivHegiht select[name=dataBaseType]").val();
		var fieldList = new Array();
		jQuery("#ID_fieldContent ul li").each(function(index,obj){
			var fieldSelect = jQuery(obj).find("input[name=fieldSelect]").val();
			var pattern = jQuery(obj).find("input[name=pattern]").val();
			var oldPlace = jQuery(obj).find("input[name=oldPlace]").val();
			var newPlace = jQuery(obj).find("input[name=newPlace]").val();
			var fieldName = jQuery(obj).find("input[name=fieldName]").val();
			var fieldType = jQuery(obj).find("select[name=fieldType]").val();
			var fieldLenth = jQuery(obj).find("input[name=fieldLenth]").val();
			fieldList.push(new DataField(fieldSelect,pattern,oldPlace,newPlace,fieldName,fieldType,fieldLenth));
		});
		var objData = new DataCollectParams(baseURL,pageParams,start,end,step,baseSelect,encode,dataBaseType,tableName,fieldList);
		var jsonData = jQuery.toJSON(objData);
		jQuery.get("admin/data/dataCollect.htm?operator=submitDataCollect",{data:encodeURI(jsonData)}, function(data){
			alert(data.message);
		},"json");
	}
};

/*
 * 封装传送后天的JSON对象
 */
function DataCollectParams(baseURL,pageParams,start,end,step,baseSelect,encode,dataBaseType,tableName,fieldList){
	this.baseURL=baseURL;
	this.pageParams=pageParams;
	this.start=start;
	this.end=end;
	this.step=step;
	this.baseSelect=baseSelect;
	this.encode=encode;
	this.dataBaseType=dataBaseType;
	this.tableName=tableName;
	this.fieldList = fieldList;
	
}
/*
 * 字段
 */
function DataField(fieldSelect,pattern,oldPlace,newPlace,fieldName,fieldType,fieldLenth){
	this.fieldSelect = fieldSelect;
	this.pattern = pattern;
	this.oldPlace = oldPlace;
	this.newPlace = newPlace;
	this.fieldName = fieldName;
	this.fieldType = fieldType;
	this.fieldLenth = fieldLenth;
}

(function(){
	jQuery(document).ready(function(){
		
	});
})();
