package com.spirit.porker.model;

public class MessageModel {
	
	/**
	 * 消息正文
	 */
	private String content;
	
	/**
	 * 消息标题
	 */
	private String title;
	
	/**
	 * 摘要
	 */
	private String description;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
