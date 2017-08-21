package com.spirit.porker.model;

import java.io.Serializable;

public class SimpleSessionEntity {
	private Long id;
    private String cookie;
    private Serializable session;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Serializable entity() {
        return session;
    }
    public void setSession(Serializable session) {
        this.session = session;
    }
    public String getCookie() {
        return cookie;
    }
    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
    public Serializable getSession() {
        return session;
    }
}
