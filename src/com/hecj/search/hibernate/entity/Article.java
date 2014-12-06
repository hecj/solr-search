package com.hecj.search.hibernate.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @类功能说明：文章实体类
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-1 下午05:28:41
 * @版本：V1.0
 */

@Entity
@Table(name = "tb_article")
public class Article implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 主键由com.hecj.search.hibernate.util.UUIDUtil.autoUUID()生成
	 */
	private String articleNo;
    /**
     * 文章标题
     */
	private String title;
	/**
	 * 文章内容
	 */
	private String content;
	/**
	 * 文章作者
	 */
	private String auther;
	/**
	 * 创建日期
	 */
	private Date createDate;
	/**
	 * 修改日期
	 */
	private Date updateDate;
	/**
	 * 文章附件
	 */
	private Set<Attachment> attachments;

	public Article() {
		super();
	}

	@Id
	@Column(name="articleNo",length=30)
	public String getArticleNo() {
		return articleNo;
	}
	
	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}
	@Column(name="title",length=300,nullable=false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name="content",length=5000)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	@Column(name="auther",length=100)
	public String getAuther() {
		return auther;
	}

	public void setAuther(String auther) {
		this.auther = auther;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "article")
	public Set<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<Attachment> attachments) {
		this.attachments = attachments;
	}

}
