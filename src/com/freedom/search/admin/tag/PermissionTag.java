package com.freedom.search.admin.tag;

import java.io.BufferedReader;
import java.io.Reader;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * BodyTagSupport类已经帮我们实现了 BodyTag接口 我们只要重写自己想要的方法就可以了
 */
public class PermissionTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	
	private BodyContent bodyContent = null;
	/**
	 * 用户代码
	 */
	private String usercode;
	/**
	 * 按钮代码
	 */
	private String radiocode;
	
	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getRadiocode() {
		return radiocode;
	}

	public void setRadiocode(String radiocode) {
		this.radiocode = radiocode;
	}

	@Override
	public int doStartTag() throws JspException {
		return EVAL_BODY_BUFFERED;
	}

	@Override
	public void setBodyContent(BodyContent b) {
		this.bodyContent = b;
	}

	@Override
	public int doAfterBody() throws JspException {
		System.out.println(usercode+","+radiocode);
		JspWriter jspWriter = bodyContent.getEnclosingWriter();
		try {
			Reader reader = bodyContent.getReader();
			BufferedReader br = new BufferedReader(reader);
			String content = "";
			String temp = "";
			while((temp = br.readLine())!=null){
				content += temp;
			}
			jspWriter.print(content);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return SKIP_BODY;
	}

}
