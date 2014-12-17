package com.hecj.search.admin.senum;

/**
 * @类功能说明：枚举类型工具类
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-8 下午12:26:17
 * @版本：V1.0
 */
public class EnumAdminUtils {
	
	/**
	 * 数据库类型枚举类
	 */
	public enum DataBaseType {

		MySQL("MySQL"), Oracle("Oracle");

		private String dataBaseType;

		private DataBaseType(String dataBaseType) {
			this.dataBaseType = dataBaseType;
		}

		@Override
		public String toString() {
			return String.valueOf(this.dataBaseType);
		}
	}
	
	/**
	 * html解析方法枚举
	 */
	public enum SelectMethod{
		TEXT("text"),ATTR("attr"),HTML("html");
		private String method ;
		SelectMethod(String method){
			this.method = method;
		}
		@Override
		public String toString() {
			return String.valueOf(this.method);
		}
	}
}
