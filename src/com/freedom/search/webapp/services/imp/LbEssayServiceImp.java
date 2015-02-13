package com.freedom.search.webapp.services.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.freedom.search.admin.entity.LzUser;
import com.freedom.search.util.Log4jUtil;
import com.freedom.search.util.Pagination;
import com.freedom.search.util.Result;
import com.freedom.search.util.ResultSupport;
import com.freedom.search.util.StringUtil;
import com.freedom.search.webapp.dao.LbEssayDAO;
import com.freedom.search.webapp.entity.LbEssay;
import com.freedom.search.webapp.services.LbEssayService;

@Service("lbEssayService")
public class LbEssayServiceImp implements LbEssayService {
	
	@Resource
	private LbEssayDAO lbEssayDAO ;

	public void setLbEssayDAO(LbEssayDAO lbEssayDAO) {
		this.lbEssayDAO = lbEssayDAO;
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
			
			String usercode = (String) map.get("usercode");
			Pagination pagination = (Pagination) map.get("pagination");
			String mQueryHQL = "select u from LzUser u where 1=1";
			String mContHQL = "select count(u) from LzUser u where 1=1";
			
			//动态拼接SQL
			if(!StringUtil.isStrEmpty(usercode)){
				mQueryHQL += " and u.usercode='"+usercode+"'";
				mContHQL += " and u.usercode='"+usercode+"'";
			}
			List<LbEssay> userList = map.queryListByParamsAndPagination(mQueryHQL, pagination.startCursor().intValue(), pagination.getPageSize(),new Object[]{});
			long count = Long.parseLong(map.queryUniqueResultByHQL(mContHQL).toString());
			pagination.setCountSize(count);
			
			result.setData(userList);
			result.setPagination(pagination);
			result.setResult(true);
		}catch(Exception ex){
			
			result.setResult(false);
			Log4jUtil.log(ex.getMessage());
			ex.printStackTrace();
		}
		return result;
	}
	
	
}
