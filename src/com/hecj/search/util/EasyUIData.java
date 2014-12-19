package com.hecj.search.util;

import java.io.Serializable;
import java.util.List;

/**
 * @类功能说明：简单封装EasyUI数据
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-19 下午02:30:52
 * @版本：V1.0
 */
public class EasyUIData implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long total;

	private List<?> rows;

	public EasyUIData() {
	}

	public EasyUIData(List<?> rows,Long total) {
		this.rows = rows;
		this.total = total;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public String toJSON() {
		return ObjectToJson.object2json(this);
	}
	@Override
	public String toString() {
		return ObjectToJson.object2json(this);
	}
}
