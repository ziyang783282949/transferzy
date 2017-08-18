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
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSON;
import com.spirit.porker.dao.OrderIdGenerator;
import com.spirit.porker.dao.UserDao;
import com.spirit.porker.dao.pagination.PaginationList;
import com.spirit.porker.enums.ResultType;
import com.spirit.porker.model.UserModel;
import com.spirit.porker.vo.request.RegistRequest;
import com.spirit.porker.vo.request.SelectNameRequest;
import com.spirit.porker.vo.response.BaseResponse;
import com.spirit.porker.vo.response.RegistResponse;
import com.spirit.porker.vo.response.SelectNameResponse;

@Service
public class SettingService {

	@Resource
	UserDao userDao;

	@Resource
	OrderIdGenerator orderIdGenerator;

	public BaseResponse<SelectNameResponse> selectName(@RequestBody UserModel pojo) {
		SelectNameResponse data = new SelectNameResponse();
		BaseResponse<SelectNameResponse> result = new BaseResponse<>(ResultType.succes);
		result.setData(data);

		// UserModel userMo=JSON.parseObject(pojo.getInfo(), UserModel.class);
		Map<String, Object> cond = new HashMap<>();

		cond.put("id", "");
		PaginationList<UserModel> userModel = userDao.findEntityListByCond(cond, null);
		if (userModel == null || userModel.size() == 0) {
			result.setCode(ResultType.fail.getCode());
			result.setDesc("查询不到该用户，请重新输入");
			return result;
		}
		UserModel user = userModel.get(0);

		data.setId(user.getId());
		data.setUsername(user.getUsername());
		data.setPassword(user.getPassword());
		data.setSex(user.getSex());
		data.setUrlUserIcon(user.getUrlUserIcon());

		return result;

	}

	public BaseResponse<RegistResponse> userRegist(@RequestBody UserModel pojo) {
		RegistResponse data = new RegistResponse();
		BaseResponse<RegistResponse> result = new BaseResponse<>(ResultType.succes);
		result.setData(data);

		Map<String, Object> cond = new HashMap<>();
		cond.put("username", pojo.getUsername());
		PaginationList<UserModel> userExist = userDao.findEntityListByCond(cond, null);
		if (userExist != null) {
			result.setCode(ResultType.fail.getCode());
			result.setDesc("该用户已存在，请更换用户名");
			return result;
		}
		data.setUsername(pojo.getUsername());
		data.setPassword(pojo.getPassword());
		
		UserModel user=new UserModel();
		user.setUsername(pojo.getUsername());
		user.setPassword(pojo.getPassword());
		userDao.addEntity(user);
		return result;

	}
	/*
	 * public BaseResponse<Object> verifyBuyCredits(VerifyBuyCreditsRequest pojo) {
	 * BaseResponse<Object> result = new BaseResponse<>(ResultType.succes); if
	 * (pojo.getVerifyMessage().equals("成功")) {
	 * 
	 * Map<String, Object> cond = new HashMap<>(); cond.put("id", pojo.getId());
	 * PaginationList<OrderModel> order = orderDao.findEntityListByCond(cond, null);
	 * if (order == null || order.size() == 0) {
	 * result.setCode(ResultType.fail.getCode()); result.setDesc("查询不到该订单，请重新输入");
	 * return result; }
	 * 
	 * // 在订单表中修改为交易成功 OrderModel orderModel = new OrderModel();
	 * orderModel.setId(pojo.getId());
	 * orderModel.setOrderStatus(OrderStatus.payed.getCode());
	 * orderDao.updateEntity(orderModel);
	 * 
	 * // 在用户表中更新积分{查询用户} cond.clear(); cond.put("cardNo", orderModel.getCardNo());
	 * PaginationList<UserModel> user = userDao.findEntityListByCond(cond, null); if
	 * (user == null || user.size() == 0) {
	 * result.setCode(ResultType.fail.getCode()); result.setDesc("查询不到该用户"); return
	 * result; }
	 * 
	 * 
	 * 
	 * // 在用户表中更新积分{更新用户积分} UserModel updataUser=new UserModel();
	 * updataUser.setCardNo(user.get(0).getCardNo()); CreditsMisc
	 * creditsMisc=JSON.parseObject(order.get(0).getEventMisc(), CreditsMisc.class);
	 * updataUser.setScore(user.get(0).getScore()+creditsMisc.getCredits().intValue(
	 * )); userDao.updateEntity(updataUser); return result; }
	 * result.setCode(ResultType.fail.getCode()); result.setDesc("微信回调确认信息错误");
	 * return result;
	 * 
	 * }
	 */

}
