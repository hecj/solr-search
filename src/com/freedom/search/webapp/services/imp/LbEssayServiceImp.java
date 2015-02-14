package com.freedom.search.webapp.services.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freedom.search.util.Log4jUtil;
import com.freedom.search.util.Pagination;
import com.freedom.search.util.Result;
import com.freedom.search.util.ResultSupport;
import com.freedom.search.util.StringUtil;
import com.freedom.search.webapp.dao.LbCommentDAO;
import com.freedom.search.webapp.dao.LbEssayDAO;
import com.freedom.search.webapp.entity.LbComment;
import com.freedom.search.webapp.entity.LbEssay;
import com.freedom.search.webapp.services.LbEssayService;

@Transactional
@Service("lbEssayService")
public class LbEssayServiceImp implements LbEssayService {
	
	@Resource
	private LbEssayDAO lbEssayDAO ;
	
	@Resource
	private LbCommentDAO lbCommentDAO ;

	public void setLbEssayDAO(LbEssayDAO lbEssayDAO) {
		this.lbEssayDAO = lbEssayDAO;
	}
	
	public void setLbCommentDAO(LbCommentDAO lbCommentDAO) {
		this.lbCommentDAO = lbCommentDAO;
	}



	@Override
	public boolean addLbEssay(LbEssay lbEssay) {
		if(!StringUtil.isObjectNull(lbEssayDAO.save(lbEssay))){
			return true;
		}
		return false;
	}

	@Override
	public boolean delLbEssay(String id) {
		return lbEssayDAO.delete(lbEssayDAO.findById(id));
	}

	@Override
	public boolean editLbEssay(LbEssay lbEssay) {
		lbEssayDAO.merge(lbEssay);
		return true;
	}

	@Override
	public Result searchEssayList(Map<String, Object> map) {
		
		Result result = new ResultSupport();
		try{
			
			Pagination p = (Pagination) map.get("pagination");
			String query = "select e from LbEssay e where 1=1 order by e.updateDate desc";
			String count = "select count(e) from LbEssay e where 1=1 ";
			
			List<LbEssay> list = lbEssayDAO.queryListByParamsAndPagination(query, p.startCursor().intValue(), p.getPageSize(),new Object[]{});
			long total = Long.parseLong(lbEssayDAO.queryUniqueResultByHQL(count).toString());
			p.setCountSize(total);
			
			result.setData(list);
			result.setPagination(p);
			result.setResult(true);
		}catch(Exception ex){
			result.setResult(false);
			Log4jUtil.log(ex.getMessage());
			ex.printStackTrace();
		}
		return result;
	}
	
	@Override
	public Result searchCommentList(Map<String, Object> map) {
		
		Result result = new ResultSupport();
		try{
			String essayId = (String) map.get("essayId");
			Pagination p = (Pagination) map.get("pagination");
			String query = "select e from LbComment e where essayId=? order by e.createDate desc";
			String count = "select count(e) from LbComment e where essayId='"+essayId+"'";
			
			List<LbComment> list = lbCommentDAO.queryListByParamsAndPagination(query, p.startCursor().intValue(), p.getPageSize(),new Object[]{essayId});
			long total = Long.parseLong(lbCommentDAO.queryUniqueResultByHQL(count).toString());
			p.setCountSize(total);
			
			result.setData(list);
			result.setPagination(p);
			result.setResult(true);
		}catch(Exception ex){
			result.setResult(false);
			Log4jUtil.log(ex.getMessage());
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public LbEssay searchEssay(String id) {

		return lbEssayDAO.findById(id);
	}

	@Override
	public boolean addLbComment(LbComment lbComment) {
		if(!StringUtil.isObjectNull(lbCommentDAO.save(lbComment))){
			return true;
		}
		return false;
	}
	
	
}
