package com.spirit.porker.listener;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.spirit.porker.event.UpdateEventEvent;
import com.spirit.porker.message.UpdateEventAfterPayMessage;
import com.spirit.porker.service.OrderService;

/**
 * @Description:支付成功后更新赛事信息(更新赛事中已报名用户信息和奖池总额)
 * @author: tony.wang
 * @time:2017年7月31日 下午2:16:47
 */
@Component
@Async
public class UpdateEventAfterPaySuccessListener implements ApplicationListener<UpdateEventEvent>{

	@Resource
	OrderService orderService;
	
	@Override
	public void onApplicationEvent(UpdateEventEvent event) {
		UpdateEventAfterPayMessage message = (UpdateEventAfterPayMessage)event.getSource();
		orderService.updateEventAfterPay(message);
	}
	
}


