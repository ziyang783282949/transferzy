package com.spirit.porker.model;

import java.io.Serializable;

import org.apache.shiro.session.mgt.SimpleSession;

public class SessionModel {
	private int id;
    private String cookie;
    private String session;
    
   
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Serializable entity() {
        return session;
    }
    
    public String getCookie() {
        return cookie;
    }
    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	
    
}
