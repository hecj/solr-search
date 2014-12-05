package com.hecj.search.hibernate.dao.imp;

import java.io.Serializable;
import java.util.ArrayList;
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
		T t = null ;
		System.out.println(entityClass);
		try{
			t = (T) getSession().get(entityClass, id);
		} catch (Exception ex){
			ex.printStackTrace();
		} 
		return  t ;
	}
	
	@Override
	public Serializable save(Object t) {
		getSession().beginTransaction();
		Serializable s = getSession().save(t);
		getSession().getTransaction().commit();
		return s;
	}
	
	@Override
	public boolean persist(Object t) {
		
		try{
			getSession().persist(t);
			return true;
		} catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
	
	@Override
	public void merge(Object t) {
		
		getSession().merge(t);
	}

	@Override
	public boolean delete(T t) {
		try{
			getSession().beginTransaction();
			getSession().delete(t);
			getSession().getTransaction().commit();
			return true;
		} catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public List<T> queryListByPagination(String pHQL,int... pPagination){
		List<T> rList = new ArrayList<T>();
		try{
			rList = getSession().createQuery(pHQL).setFirstResult(pPagination[0]).setMaxResults(pPagination[1]).list();
			
		} catch(Exception mException){
			mException.printStackTrace();
		}
		return rList;
	}
	
	@Override
	public Object queryUniqueResultByHQL(String pHQL){
		Object mObject = new Object();
		try{

			mObject = getSession().createQuery(pHQL).uniqueResult();
		} catch(Exception mException){
			mException.printStackTrace();
		}
		return mObject;
	}
}
