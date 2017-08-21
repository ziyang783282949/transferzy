package com.spirit.porker.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;

import com.spirit.porker.dao.pagination.PaginationList;
import com.spirit.porker.model.SimpleSessionEntity;

public class SessionEntityDao extends EnterpriseCacheSessionDAO{
	
	@Resource
	SimpleSessionDao sessionDao;
	
	@Override
    public Serializable create(Session session) {
        // 先保存到缓存中
        Serializable cookie = super.create(session);
        // 新建一个SimpleSessionEntity，然后保存到数据库
        SimpleSessionEntity entity = new SimpleSessionEntity();
        entity.setSession((SimpleSession)session);
        entity.setCookie(cookie.toString());
        sessionDao.addEntity(entity);
        return cookie;
    }
	
	@Override
    public void update(Session session) throws UnknownSessionException {
        super.update(session);
        SimpleSessionEntity entity = getEntity(session.getId());
        if(entity != null){
            entity.setSession((SimpleSession)session);    
            sessionDao.updateEntity(entity);
        }        
    }
	
	
	
	private SimpleSessionEntity getEntity(Serializable sessionId){
        String hql = "from SimpleSessionEntity entity where entity.cookie ='" + sessionId + "'";
        Map<String,Object> cond=new HashMap<>();
        cond.put("session", arg1)
        PaginationList<SimpleSessionEntity> session=sessionDao.findEntityListByCond(cond, null);
        return sessionDao.findUniqueByHQL(hql);
    }
}
