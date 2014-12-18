package com.hecj.search.hibernate.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @类功能说明：附件实体
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-1 下午05:31:05
 * @版本：V1.0
 */
@Entity
@Table(name = "tb_attachment")
public class Attachment implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 主键由com.hecj.search.hibernate.util.UUIDUtil.autoUUID()生成
	 */
	private String attachmentNo;
	/**
	 * 附件标题
	 */
	private String title;
	/**
	 * 附件内容
	 */
	private String content;
	/**
	 * 创建时间
	 */
	private Date createDate;
	/**
	 * 修改时间
	 */
	private Date updateDate;
	/**
	 * 附件对应的文章实体
	 */
	private Article article;

	public Attachment() {
		super();
	}

	@Id
	@Column(name="attachmentNo",length=32)
	public String getAttachmentNo() {
		return attachmentNo;
	}

	public void setAttachmentNo(String attachmentNo) {
		this.attachmentNo = attachmentNo;
	}
	@Column(name="title",length=300)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name="content",length=3000)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	@Column(name="createDate")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Column(name="updateDate")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	/**
	 	CascadeType.ALL
		CascadeType.DETACH
		CascadeType.REMOVE : 级联删除
		CascadeType.MERGE : 级联保存或修改，先查询，再修改
		CascadeType.PERSIST只有A类新增时，会级联B对象新增。若B对象在数据库存（跟新）在则抛异常（让B变为持久态）-->级联直接保存数据库 
		CascadeType.REFRESH
		
	 */
	@ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.DETACH})
	@JoinColumn(name = "articleNo", nullable=false)
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

}
