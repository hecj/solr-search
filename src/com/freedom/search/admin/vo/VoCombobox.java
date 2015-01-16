package com.freedom.search.admin.vo;

import java.io.Serializable;

public class VoCombobox implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	
	private static final long serialVersionUID = 1L;
	
	private String value;
	private String text;

	public VoCombobox() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VoCombobox(String value, String text) {
		super();
		this.value = value;
		this.text = text;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
