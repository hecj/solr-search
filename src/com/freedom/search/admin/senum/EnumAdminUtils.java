package com.freedom.search.admin.senum;

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
	/**
	 * 0:SUCCESS
	 * 1:FAIL
	 */	
	public enum MessageCode {
		SUCCESS("0"), FAIL("1");
		public String code;

		MessageCode(String code) {
			this.code = code;
		}
		
	}
	/**
	 * 是否叶子
	 * 0:否
	 * 1:是
	 */	
	public enum Leaf {
		TRUE("1"), FALSE("0");
		public String code;
		Leaf(String code) {
			this.code = code ;
		}
	}
	
	/**
	 * 菜单树
	 */	
	public enum Tree {
		ROOT("0");
		public String code;
		Tree(String code) {
			this.code = code ;
		}
	}
	
}
