/***
 * 实体对象工具类
 */
var AppEntity={
		/**
		 * DataCollect数据字段
		 */
		DataCollect:function(id,ip,port,baseURL,pageParams,start,end,step,baseSelect,code,dataBaseType,tableName,dataFields){
			this.id = id;
			this.ip = ip;
			this.port = port;
			this.baseURL = baseURL;
			this.pageParams = pageParams;
			this.start =start ;
			this.end = end;
			this.step = step;
			this.baseSelect =baseSelect;
			this.code = code;
			this.dataBaseType =dataBaseType;
			this.tableName =tableName;
			this.dataFields=dataFields;
		},
		/**
		 * DataField数据字段类
		 */
		DataField : function(id,fieldSelect, selectMethod,targetAttr, pattern,newPlace, oldPlace,fieldName, fieldType, fieldLenth) {
			this.id = id;
			this.fieldSelect = fieldSelect;
			this.selectMethod = selectMethod;
			this.targetAttr = targetAttr;
			this.pattern = pattern;
			this.newPlace = newPlace;
			this.oldPlace = oldPlace, 
			this.fieldName = fieldName;
			this.fieldType = fieldType;
			this.fieldLenth = fieldLenth;
	}
}

