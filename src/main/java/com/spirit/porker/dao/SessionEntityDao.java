package com.spirit.porker.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;

import com.alibaba.fastjson.JSON;
import com.spirit.porker.dao.pagination.PaginationList;
import com.spirit.porker.model.SessionModel;
import com.spirit.porker.model.UserModel;

public class SessionEntityDao extends EnterpriseCacheSessionDAO {

	@Resource
	SimpleSessionDao sessionDao;

	@Resource
	UserDao userDao;

	@Override
	public Serializable create(Session session) {
		// 先保存到缓存中
		Serializable cookie = super.create(session);
		// 新建一个SimpleSessionEntity，然后保存到数据库
		SessionModel entity = new SessionModel();
		entity.setSession(JSON.toJSONString((SimpleSession) session));
		entity.setCookie(cookie.toString());
		sessionDao.addEntity(entity);
		return cookie;
	}

	@Override
	public void update(Session session) throws UnknownSessionException {
		super.update(session);
		SessionModel entity = getEntity(session.getId());
		if (entity != null) {
			entity.setSession(JSON.toJSONString((SimpleSession) session));
			sessionDao.updateEntity(entity);
		}
	}

	@Override
	public Session readSession(Serializable sessionId) throws UnknownSessionException {
		Session session = null;

		try {
			session = super.readSession(sessionId);
		} catch (Exception e) {

		}

		// 如果session已经被删除，则从数据库中查询session
		if (session == null) {
			SessionModel entity = getEntity(sessionId);
			if (entity != null) {
				session = (Session) JSON.parseObject(entity.getSession(),SimpleSession.class);
			}
		}
		// 如果是APP则更新lastAccessTime
		Map<String, Object> cond = new HashMap<>();
		cond.put("cookie", sessionId);
		PaginationList<UserModel> users = userDao.findEntityListByCond(cond, null);
		//UserModel user = users.get(0);
		if (users != null && users.size()>0) {
			// 如果该用户是APP用户（user不为空说明就是），则判断session是否过期，如果过期则修改最后访问时间
			((SimpleSession) session).setLastAccessTime(new Date());
		}

		return session;
	}

	@Override
    public void delete(Session session) {
        super.delete(session);        
    }
	
	private SessionModel getEntity(Serializable sessionId) {
		Map<String, Object> cond = new HashMap<>();
		cond.put("cookie", sessionId);
		PaginationList<SessionModel> session = sessionDao.findEntityListByCond(cond, null);
		if(session.size()>0 && session!=null) {
			return session.get(0);
		}
		return null;
	}
}
