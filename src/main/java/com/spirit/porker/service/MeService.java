package com.spirit.porker.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.VoiceStatus;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.spirit.porker.dao.ShopDao;
import com.spirit.porker.dao.TicketDao;
import com.spirit.porker.dao.UserDao;
import com.spirit.porker.dao.pagination.PaginationList;
import com.spirit.porker.enums.ResultType;
import com.spirit.porker.enums.TicketState;
import com.spirit.porker.enums.UserLevel;
import com.spirit.porker.model.ShopModel;
import com.spirit.porker.model.TicketModel;
import com.spirit.porker.model.UserModel;
import com.spirit.porker.util.PropertyUtil;
import com.spirit.porker.vo.TicketMisc;
import com.spirit.porker.vo.request.MeRequest;
import com.spirit.porker.vo.request.ShowShopRequest;
import com.spirit.porker.vo.request.UserMiscRequest;
import com.spirit.porker.vo.request.UserTicketsRequest;
import com.spirit.porker.vo.response.BaseResponse;
import com.spirit.porker.vo.response.Exchange;
import com.spirit.porker.vo.response.MeResponse;
import com.spirit.porker.vo.response.ShowShopResponse;
import com.spirit.porker.vo.response.TicketResponse;
import com.spirit.porker.vo.response.UserMiscResponse;
import com.spirit.porker.vo.response.UserTicketResponse;

@Service
public class MeService {
	
	@Resource 
	UserDao userDao;
	
	@Resource
	TicketDao ticketDao;
	
	@Resource
	ShopDao shopDao;
	
	
	public BaseResponse<MeResponse> myInfo(MeRequest pojo,HttpServletRequest request,HttpServletResponse response){
		
		BaseResponse<MeResponse> result = new BaseResponse<MeResponse>(ResultType.succes);
		MeResponse data = new MeResponse();
		result.setData(data);
		
		Map<String, Object> cond = new HashMap<String,Object>();
		cond.put("openId", pojo.getOpenId());
		
		PaginationList<UserModel> users = userDao.findEntityListByCond(cond, null);
		
		if(users == null || users.size() == 0){
			result.setCode(ResultType.unKnowUser.getCode());
			result.setDesc(ResultType.unKnowUser.getDesc());
			return result;
		}
		
		MeResponse vo = new MeResponse();
		vo.setCardNo(users.get(0).getCardNo());
		vo.setCertNo(users.get(0).getCertNo());
		vo.setMasterScore(users.get(0).getCurMasterScore());
		vo.setMttScore(users.get(0).getCurMttScore());
		vo.setNickName(users.get(0).getNickName());
		vo.setPhone(users.get(0).getPhone());
		vo.setScore(users.get(0).getScore());
		vo.setUserLevel(UserLevel.getTypeByCode(users.get(0).getUserLevel()).getDesc());
		vo.setImgUrl(users.get(0).getImgUrl());
		//计算排名，排名以mtt积分为基准
		vo.setBuyIn(users.get(0).getBuyIn());
		vo.setBuyOut(users.get(0).getBuyOut());
		
		result.setData(vo);
		
		return result;
		
	}
	
	/**
	 * @param 更新用户身份证件等信息
	 * @param request
	 * @param response
	 * @return
	 */
	public BaseResponse<MeResponse> updateMe(MeRequest pojo,HttpServletRequest request,HttpServletResponse response){
		
		BaseResponse<MeResponse> result = new BaseResponse<MeResponse>(ResultType.succes);
		MeResponse data = new MeResponse();
		result.setData(data);
		
		Map<String, Object> cond = new HashMap<String,Object>();
		cond.put("openId", pojo.getOpenId());
		
		PaginationList<UserModel> users = userDao.findEntityListByCond(cond, null);
		
		if(users == null || users.size() == 0){
			result.setCode(ResultType.unKnowUser.getCode());
			result.setDesc(ResultType.unKnowUser.getDesc());
			return result;
		}
		
		UserModel model = new UserModel();
		model.setCardNo(pojo.getCardNo());
		model.setPhone(pojo.getPhone());
		model.setCertNo(pojo.getCertNo());
		model.setRealName(pojo.getRealName());
		userDao.updateEntity(model);
		
		return result;
	}

	public BaseResponse<List<UserTicketResponse>> userTicket(UserTicketsRequest pojo,HttpServletRequest request,HttpServletResponse response){
		List<UserTicketResponse> data=new ArrayList<>();
		BaseResponse<List<UserTicketResponse>> result=new BaseResponse<>(ResultType.succes);
		result.setData(data);
		
		Map<String,Object> cond=new HashMap<>();
		cond.put("userCardNo", pojo.getCardNo());
		PaginationList<TicketModel> tickets=ticketDao.findEntityListByCond(cond, null);
		if(tickets.size()==0 || tickets==null) {
			return result;
		}
		for(int i=0;i<tickets.size();i++) {
			UserTicketResponse userTicketResponse=new UserTicketResponse();
			TicketMisc ticketMisc=JSON.parseObject(tickets.get(i).getTicketMisc(), TicketMisc.class);
			userTicketResponse.setId(ticketMisc.getId());
			userTicketResponse.setDeadLine(ticketMisc.getDeadLine());
			userTicketResponse.setLimit(ticketMisc.getLimit());
			userTicketResponse.setNote(ticketMisc.getNote());			
			userTicketResponse.setName(ticketMisc.getName());
			userTicketResponse.setType(ticketMisc.getType());
			data.add(userTicketResponse);
		}
		return result;
		
	}
	
	public BaseResponse<UserMiscResponse> userMisc(UserMiscRequest pojo,HttpServletRequest request,HttpServletResponse response){
		UserMiscResponse data=new UserMiscResponse();
		BaseResponse<UserMiscResponse> result=new BaseResponse<>(ResultType.succes);
		result.setData(data);
		
		Map<String,Object> cond=new HashMap<>();
		cond.put("cardNo", pojo.getCardNo());
		PaginationList<UserModel> usermodel=userDao.findEntityListByCond(cond, null);
		if(usermodel.size()==0 || usermodel==null) {
			result.setCode(ResultType.fail.getCode());
			result.setDesc("获取用户信息异常，请稍后重试");
			return result;
		}
		UserModel user=usermodel.get(0);
		
		data.setCardNo(user.getCardNo());
		data.setImgUrl(user.getImgUrl());
		data.setNickName(user.getNickName());
		data.setCertNo(user.getCertNo());
		data.setRealName(user.getRealName());
		data.setPhone(user.getPhone());
		data.setCurMasterScore(user.getCurMasterScore());
		data.setCurMttScore(user.getCurMttScore());
		data.setScore(user.getScore());
		if(user.getUserLevel()==UserLevel.bronze.getCode()) {
			data.setUserLevel("青铜");
		}
		if(user.getUserLevel()==UserLevel.sliver.getCode()) {
			data.setUserLevel("白银");
		}
		if(user.getUserLevel()==UserLevel.huangjin.getCode()) {
			data.setUserLevel("黄金");
		}
		if(user.getUserLevel()==UserLevel.diamond.getCode()) {
			data.setUserLevel("钻石");
		}
		if(user.getUserLevel()==UserLevel.royal.getCode()) {
			data.setUserLevel("皇家");
		}
		return result;
	}
	
	
	public BaseResponse<ShowShopResponse> shop(ShowShopRequest pojo,HttpServletRequest request,HttpServletResponse response){
		ShowShopResponse data=new ShowShopResponse();
		BaseResponse<ShowShopResponse> result=new BaseResponse<>(ResultType.succes);
		result.setData(data);
		String exchange=PropertyUtil.config.getProperties("exchange");
		List<Exchange> exchanges=JSON.parseArray(exchange, Exchange.class);
		data.setExchanges(exchanges);
		
		Map<String,Object> cond=new HashMap<>();
		cond.put("state", TicketState.unlock.getCode());
		PaginationList<ShopModel> shopTickets=shopDao.findEntityListByCond(cond, null);
		List<TicketResponse> tickets=new ArrayList<>();
		if(shopTickets==null || shopTickets.size()==0) {
			data.setTickets(tickets);
			return result;
		}
		
		for(int i=0;i<shopTickets.size();i++) {
			TicketMisc ticketMisc=JSON.parseObject(shopTickets.get(i).getTicketMisc(), TicketMisc.class);
			TicketResponse ticketResponse=new TicketResponse();
			ticketResponse.setId(ticketMisc.getId());
			ticketResponse.setName(ticketMisc.getName());
			ticketResponse.setType(ticketMisc.getType());
			ticketResponse.setDeadLine(ticketMisc.getDeadLine());
			ticketResponse.setPrice(shopTickets.get(i).getNormalPrice());
			
			tickets.add(ticketResponse);
		}
		data.setTickets(tickets);
		return result;
		
	}
	
	
	
}


