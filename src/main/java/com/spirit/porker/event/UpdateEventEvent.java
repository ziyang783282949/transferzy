package com.spirit.porker.event;

import org.springframework.context.ApplicationEvent;

public class UpdateEventEvent extends ApplicationEvent{

	private static final long serialVersionUID = -5508753604474170798L;

	public UpdateEventEvent(Object source) {
		super(source);
	}

}


