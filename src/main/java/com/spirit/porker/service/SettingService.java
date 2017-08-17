package com.spirit.porker.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.cert.CertPathValidatorException.BasicReason;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.spirit.porker.dao.BlindDao;
import com.spirit.porker.dao.CreditsDao;
import com.spirit.porker.dao.EventDao;
import com.spirit.porker.dao.OrderDao;
import com.spirit.porker.dao.OrderIdGenerator;
import com.spirit.porker.dao.RewardDao;
import com.spirit.porker.dao.ShopDao;
import com.spirit.porker.dao.TicketDao;
import com.spirit.porker.dao.UserDao;

@Service
public class SettingService {

	@Resource
	BlindDao blindDao;

	@Resource
	EventDao eventDao;

	@Resource
	UserDao userDao;

	@Resource
	OrderDao orderDao;

	@Resource
	RewardDao rewardDao;

	@Resource
	TicketDao ticketDao;

	@Resource
	ShopDao shopDao;

	@Resource
	OrderIdGenerator orderIdGenerator;

	@Resource
	CreditsDao creditsDao;

	

	/*public BaseResponse<Object> verifyBuyCredits(VerifyBuyCreditsRequest pojo) {
		BaseResponse<Object> result = new BaseResponse<>(ResultType.succes);
		if (pojo.getVerifyMessage().equals("成功")) {

			Map<String, Object> cond = new HashMap<>();
			cond.put("id", pojo.getId());
			PaginationList<OrderModel> order = orderDao.findEntityListByCond(cond, null);
			if (order == null || order.size() == 0) {
				result.setCode(ResultType.fail.getCode());
				result.setDesc("查询不到该订单，请重新输入");
				return result;
			}

			// 在订单表中修改为交易成功
			OrderModel orderModel = new OrderModel();
			orderModel.setId(pojo.getId());
			orderModel.setOrderStatus(OrderStatus.payed.getCode());
			orderDao.updateEntity(orderModel);

			// 在用户表中更新积分{查询用户}
			cond.clear();
			cond.put("cardNo", orderModel.getCardNo());
			PaginationList<UserModel> user = userDao.findEntityListByCond(cond, null);
			if (user == null || user.size() == 0) {
				result.setCode(ResultType.fail.getCode());
				result.setDesc("查询不到该用户");
				return result;
			}
			
			
			
			// 在用户表中更新积分{更新用户积分}
			UserModel updataUser=new UserModel();
			updataUser.setCardNo(user.get(0).getCardNo());
			CreditsMisc creditsMisc=JSON.parseObject(order.get(0).getEventMisc(), CreditsMisc.class);
			updataUser.setScore(user.get(0).getScore()+creditsMisc.getCredits().intValue());
			userDao.updateEntity(updataUser);
			return result;
		}
		result.setCode(ResultType.fail.getCode());
		result.setDesc("微信回调确认信息错误");
		return result;

	}*/

}
