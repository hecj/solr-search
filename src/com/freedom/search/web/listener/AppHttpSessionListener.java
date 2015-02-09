package com.freedom.search.web.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.freedom.search.admin.entity.LzUser;
import com.freedom.search.admin.vo.UserContext;
/**
 * @类功能说明：监听Session创建/销毁
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2015-2-10 上午01:23:00
 * @版本：V1.0
 */
public class AppHttpSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent enent) {
		
		UserContext context = (UserContext) enent.getSession().getAttribute(UserContext.SESSION_KEY);
		if(context != null){
			LzUser user = context.getUser();
			System.out.println("session创建："+user.getUsercode()+"("+user.getUsername()+")");
		}
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent enent) {
		UserContext context = (UserContext) enent.getSession().getAttribute(UserContext.SESSION_KEY);
		if(context != null){
			LzUser user = context.getUser();
			System.out.println("session销毁："+user.getUsercode()+"("+user.getUsername()+")");
		}
	}

}
