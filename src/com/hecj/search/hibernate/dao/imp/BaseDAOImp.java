package com.hecj.search.hibernate.dao.imp;

import java.io.Serializable;
import java.util.List;

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
		
		return getSessionFactory().getCurrentSession().createQuery(pHQL).setFirstResult(pPagination[0]).setMaxResults(pPagination[1]).list();
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
