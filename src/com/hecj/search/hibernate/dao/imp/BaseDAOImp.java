package com.hecj.search.hibernate.dao.imp;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;

import com.hecj.search.hibernate.HibernateSessionFactory;
import com.hecj.search.hibernate.dao.BaseDAO;
import com.hecj.search.hibernate.util.GenericsUtil;
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
		
		return getSessionFactory().getCurrentSession().save(t);
	}
	
	@Override
	public boolean persist(Object t) {
		getSessionFactory().getCurrentSession().persist(t);
		return true;
	}
	
	@Override
	public void merge(Object t) {
		
		getSessionFactory().getCurrentSession().merge(t);
	}

	@Override
	public boolean delete(T t) {
		
		getSessionFactory().getCurrentSession().delete(t);
		return true;
	}

	@Override
	public List<T> queryListByPagination(String pHQL,int... pPagination){
		
		if(pPagination.length == 0 ){
			return getSessionFactory().getCurrentSession().createQuery(pHQL).list();
		}else if(pPagination.length == 1) {
			return getSessionFactory().getCurrentSession().createQuery(pHQL).setMaxResults(pPagination[0]).list();
		}else{
			return getSessionFactory().getCurrentSession().createQuery(pHQL).setFirstResult(pPagination[0]).setMaxResults(pPagination[1]).list();
		}
	}
	
	@Override
	public List<T> queryListByParams(String pHQL,Object... pParams){
		
		Query query = getSessionFactory().getCurrentSession().createQuery(pHQL);
		if(pParams.length>0){
			for(int i = 0 ;i<pParams.length;i++){
				query.setParameter(i, pParams[i]);
			}
		}
		return query.list();
	}
	
	@Override
	public List<T> queryListByParamsAndPagination(String pHQL,int start,int rows,Object... pParams){
		Query query = getSessionFactory().getCurrentSession().createQuery(pHQL);
		if(pParams.length>0){
			for(int i = 0 ;i<pParams.length;i++){
				query.setParameter(i, pParams[i]);
			}
		}
		return query.setFirstResult(start).setMaxResults(rows).list();
	}
	
	@Override
	public Object queryUniqueResultByHQL(String pHQL){
		return getSessionFactory().getCurrentSession().createQuery(pHQL).uniqueResult();
	}
	
	@Override
	public int executeHQL(String pHQL){
		
		return getSessionFactory().getCurrentSession().createQuery(pHQL).executeUpdate();
	}
	
}
