/***
 * 实体对象工具类
 */
var AppEntity={
		/**
		 * DataCollect数据字段
		 */
		DataCollect:function(id,IP,PORT,baseURL,pageParams,start,end,step,baseSelect,encode,dataBaseType,tableName,fieldList){
			this.id = id;
			this.IP = IP;
			this.PORT = PORT;
			this.baseURL = baseURL;
			this.pageParams = pageParams;
			this.start =start ;
			this.end = end;
			this.step = step;
			this.baseSelect =baseSelect;
			this.encode = encode;
			this.dataBaseType =dataBaseType;
			this.tableName =tableName;
			this.fieldList=fieldList;
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

