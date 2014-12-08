package com.hecj.search.senum;

import com.hecj.search.hibernate.entity.Article;
import com.hecj.search.hibernate.entity.Attachment;

/**
 * @类功能说明：枚举类型工具类
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-8 下午12:26:17
 * @版本：V1.0
 */
public class EnumUtils {
	
	/**
	 * @类功能说明：类实体枚举类型
	 * @类修改者：
	 * @修改日期：
	 * @修改说明：
	 * @作者：HECJ
	 * @创建时间：2014-12-8 下午02:36:41
	 * @版本：V1.0
	 */
	public enum ObjectType {

		Article("tb_article"), 
		Attachment("tb_attachment");

		private String mType;

		private ObjectType(String pType) {
			this.mType = pType;
		}

		@Override
		public String toString() {
			return String.valueOf(this.mType);
		}
	}

	/**
	 * @类功能说明： 操作类型枚举类<br>
	 *         对应字段:com.hecj.search.hibernate.entity.TempIndex.operatorType<br>
	 *         ADD:添加,DELETE:删除,UPDATE:更新
	 * @类修改者：
	 * @修改日期：
	 * @修改说明：
	 * @作者：HECJ
	 * @创建时间：2014-12-8 下午12:35:48
	 * @版本：V1.0
	 */
	public enum OperatorType {

		ADD("A"), DELETE("D"), UPDATE("U");

		private String TYPE;

		private OperatorType(String pTYPE) {
			this.TYPE = pTYPE;
		}

		@Override
		public String toString() {
			return String.valueOf(this.TYPE);
		}
	}

}
