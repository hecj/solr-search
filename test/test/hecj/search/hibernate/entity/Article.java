package test.hecj.search.hibernate.entity;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;


/**
 * @类功能说明：文章实体类
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-1 下午05:28:41
 * @版本：V1.0
 */

public class Article implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String title;
	private Blob content;
//	private Attachment[] attachments;
	private String auther ;
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	private Date createDate;
//	private Date updateDate;
	
	
	public Article() {
		super();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Blob getContent() {
		return content;
	}
	public void setContent(Blob content) {
		this.content = content;
	}
	/*public Attachment[] getAttachments() {
		return attachments;
	}
	public void setAttachments(Attachment[] attachments) {
		this.attachments = attachments;
	}*/
	public String getAuther() {
		return auther;
	}
	public void setAuther(String auther) {
		this.auther = auther;
	}

}
