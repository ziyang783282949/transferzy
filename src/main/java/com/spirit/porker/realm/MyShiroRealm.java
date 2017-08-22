package com.spirit.porker.realm;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;

import com.spirit.porker.dao.UserDao;
import com.spirit.porker.dao.admindao.AdminDao;
import com.spirit.porker.dao.pagination.PaginationList;
import com.spirit.porker.model.AdminModel;
import com.spirit.porker.model.UserModel;

public class MyShiroRealm extends AuthorizingRealm {

	@Resource
	AdminDao adminDao;
	
	@Resource
	UserDao userDao;

	/* 
	 * DELL
	 * 权限认证(该项目没有权限区分)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Map<String, Object> cond = new HashMap<String, Object>();
		// 获取登录时输入的用户名
		String loginName = (String) principals.fromRealm(getName()).iterator().next();
		cond.put("username", loginName);
		PaginationList<AdminModel> list = adminDao.findEntityListByCond(cond, null);
		if (list != null && list.size() > 0) {
			//权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）  
            SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
            return info;
		}
		return null;
	}

	
	/* 
	 * DELL
	 * 身份验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//UsernamePasswordToken对象用来存放提交的登录信息  
        UsernamePasswordToken uToken=(UsernamePasswordToken) token;  
        //查出是否有此用户  
        Map<String, Object> cond = new HashMap<String, Object>();
		// 获取登录时输入的用户名
		cond.put("username", uToken.getUsername());
		PaginationList<AdminModel> list = adminDao.findEntityListByCond(cond, null);
		if(list!=null && list.size()>0) {
			//若存在，将此用户存放到登录认证info中  
			AdminModel user=list.get(0);
			
			Session session = SecurityUtils.getSubject().getSession();
			cond.clear();
			cond.put("username", uToken.getUsername());
			PaginationList<UserModel> users = userDao.findEntityListByCond(cond, null);
			UserModel user2 = users.get(0);
			
			//将cookie写入User表
			UserModel newUser = new UserModel();
			newUser.setId(user2.getId());
			newUser.setUsername(user2.getUsername());
			newUser.setCookie(session.toString());
			userDao.updateEntity(newUser);
			
			
			
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());  			
		}
		return null;
	}

}
