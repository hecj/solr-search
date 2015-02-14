package com.freedom.search.webapp.dao.imp;

import org.springframework.stereotype.Repository;

import com.freedom.search.webapp.dao.LbCommentDAO;
import com.freedom.search.webapp.entity.LbComment;
@Repository("lbCommentDAO")
public class LbCommentDAOImp extends BaseDAOImp<LbComment> implements LbCommentDAO {

	private static final long serialVersionUID = 1L;

}
