package test.hecj.search.hibernate.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="tb_attachment")
public class Attachment implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String title;
	private String content;
	private Date createDate;
	private Date updateDate;
	private Long articleId;
	
	
	public Attachment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Attachment(Long id, String title, String content, Date createDate,
			Date updateDate, Long articleId) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.articleId = articleId;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="articleId")
	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}
	@Column(name="title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name="content")
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
	
	
}
