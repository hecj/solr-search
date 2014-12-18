package com.hecj.search.admin.dao.imp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.hecj.search.admin.dao.BaseDAO;
import com.hecj.search.hibernate.HibernateSessionFactory;
import com.hecj.search.hibernate.util.GenericsUtil;
import com.hecj.search.util.Log4jUtil;
/**
 * @类功能说明：DAO base实现类
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-4 上午09:41:59
 * @版本：V1.0
 */
@SuppressWarnings({ "unchecked", "serial" })
public abstract class BaseDAOImp<T> extends HibernateSessionFactory implements BaseDAO<T> {
	
	protected Class<T> entityClass = GenericsUtil.getSuperClassGenricType(this.getClass());
	
	@Override
	public T findById(Serializable id) {
		return (T)getSessionFactory().getCurrentSession().get(entityClass, id);
	}
	
	@Override
	public Serializable save(Object t) {
		
		Log4jUtil.log("begin");
		Serializable mSerializable = null;
		try {
			mSerializable = getSessionFactory().getCurrentSession().save(t);
			Log4jUtil.log("end");
		} catch (RuntimeException ex) {
			Log4jUtil.error("error");
			ex.printStackTrace();
			throw ex;
		}
		return mSerializable;
	}
	
	@Override
	public boolean persist(Object t) {
		
		Log4jUtil.log("begin");
		try {
			getSessionFactory().getCurrentSession().persist(t);
			Log4jUtil.log("end");
		} catch (RuntimeException ex) {
			Log4jUtil.error("error");
			ex.printStackTrace();
			throw ex;
		}
		return true;
	}
	
	@Override
	public void merge(Object t) {
		
		Log4jUtil.log("begin");
		try {
			getSessionFactory().getCurrentSession().merge(t);
			Log4jUtil.log("end");
		} catch (RuntimeException ex) {
			Log4jUtil.error("error");
			ex.printStackTrace();
			throw ex;
		}
	}

	@Override
	public boolean delete(T t) {
		
		Log4jUtil.log("begin");
		try {
			getSessionFactory().getCurrentSession().delete(t);
			Log4jUtil.log("end");
		} catch (RuntimeException ex) {
			Log4jUtil.error("error");
			ex.printStackTrace();
			throw ex;
		}
		return true;
	}

	@Override
	public List<T> queryListByPagination(String pHQL,int... pPagination){
		java.net.ConnectException d;
		List<T> mList = new ArrayList<T>();
		Log4jUtil.log("begin");
		try {
			if(pPagination.length == 0 ){
				Log4jUtil.showSQL(pHQL);
				mList = getSessionFactory().getCurrentSession().createQuery(pHQL).list();
			}else if(pPagination.length == 1) {
				Log4jUtil.showSQL(pHQL+"~"+pPagination[0]);
				mList = getSessionFactory().getCurrentSession().createQuery(pHQL).setMaxResults(pPagination[0]).list();
			}else{
				Log4jUtil.showSQL(pHQL+"~"+pPagination[0]+","+pPagination[1]);
				mList = getSessionFactory().getCurrentSession().createQuery(pHQL).setFirstResult(pPagination[0]).setMaxResults(pPagination[1]).list();
			}
			Log4jUtil.log("end");
		} catch (RuntimeException ex) {
			Log4jUtil.error("error");
			ex.printStackTrace();
			throw ex;
		}
		return mList;
	}

	@Override
	public List<T> queryListByParams(String pHQL, Object... pParams) {

		List<T> mList = new ArrayList<T>();
		Log4jUtil.log("begin");
		try {
			Query query = getSessionFactory().getCurrentSession().createQuery(pHQL);
			Log4jUtil.showSQL(pHQL);
			if (pParams.length > 0) {
				for (int i = 0; i < pParams.length; i++) {
					query.setParameter(i, pParams[i]);
					Log4jUtil.showSQL(pParams[i].toString());
				}
			}
			mList = query.list();
			Log4jUtil.log("end");
		} catch (RuntimeException ex) {
			Log4jUtil.error("error");
			ex.printStackTrace();
			throw ex;
		}
		return mList;
	}

	@Override
	public List<T> queryListByParamsAndPagination(String pHQL,int start,int rows,Object... pParams){
		
		List<T> mList = new ArrayList<T>();
		Log4jUtil.log("begin");
		try {
			Query query = getSessionFactory().getCurrentSession().createQuery(pHQL);
			Log4jUtil.showSQL(pHQL+"~"+start+","+rows);
			if(pParams.length>0){
				for(int i = 0 ;i<pParams.length;i++){
					query.setParameter(i, pParams[i]);
					Log4jUtil.showSQL(pParams[i].toString());
				}
			}
			mList = query.setFirstResult(start).setMaxResults(rows).list();
			Log4jUtil.log("end");
		} catch (RuntimeException ex) {
			Log4jUtil.error("error");
			ex.printStackTrace();
			throw ex;
		}
		return mList;
		
		
		
	}
	
	@Override
	public Object queryUniqueResultByHQL(String pHQL){
		
		Log4jUtil.log("begin");
		Object obj = null ;
		try {
			obj = getSessionFactory().getCurrentSession().createQuery(pHQL).uniqueResult();
			Log4jUtil.log("end");
		} catch (RuntimeException ex) {
			Log4jUtil.error("error");
			ex.printStackTrace();
			throw ex;
		}
		return obj;
	}
	
	@Override
	public int executeHQL(String pHQL){
		
		Log4jUtil.log("begin");
		int n = 0 ;
		try {
			n = getSessionFactory().getCurrentSession().createQuery(pHQL).executeUpdate();
			Log4jUtil.log("end");
		} catch (RuntimeException ex) {
			Log4jUtil.error("error");
			ex.printStackTrace();
			throw ex;
		}
		return n;
	}
	
}
