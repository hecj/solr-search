package com.freedom.search.admin.dao.imp;

import org.springframework.stereotype.Repository;

import com.freedom.search.admin.dao.UserDAO;
import com.freedom.search.admin.entity.LzUser;
@Repository("userDAO")
public class UserDAOImp extends BaseDAOImp<LzUser> implements UserDAO {

	private static final long serialVersionUID = 1L;


}
