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
import com.spirit.porker.dao.pagination.PaginationInfo;
import com.spirit.porker.dao.pagination.PaginationList;
import com.spirit.porker.enums.EventStatus;
import com.spirit.porker.enums.OrderStatus;
import com.spirit.porker.enums.OrderTypeState;
import com.spirit.porker.enums.ResultType;
import com.spirit.porker.enums.TicketState;
import com.spirit.porker.model.BlindModel;
import com.spirit.porker.model.CreditsModel;
import com.spirit.porker.model.EventModel;
import com.spirit.porker.model.OrderModel;
import com.spirit.porker.model.RankInfo;
import com.spirit.porker.model.RewardModel;
import com.spirit.porker.model.ShopModel;
import com.spirit.porker.model.TicketModel;
import com.spirit.porker.model.UserModel;
import com.spirit.porker.util.DateUtil;
import com.spirit.porker.util.PropertyUtil;
import com.spirit.porker.vo.request.BlindListRequest;
import com.spirit.porker.vo.request.EventProductRequest;
import com.spirit.porker.vo.request.RewardUser;
import com.spirit.porker.vo.request.RewardUserListRequest;
import com.spirit.porker.vo.request.UserModelListRequest;
import com.spirit.porker.vo.request.merchant.AddEventRequest;
import com.spirit.porker.vo.request.merchant.BuyCreditsRequest;
import com.spirit.porker.vo.request.merchant.BuyTicketRequest;
import com.spirit.porker.vo.request.merchant.DeleteEventRequest;
import com.spirit.porker.vo.request.merchant.EventQueryRequest;
import com.spirit.porker.vo.request.merchant.ServerCheckTicketRequest;
import com.spirit.porker.vo.request.merchant.ShowTicketLeftRequest;
import com.spirit.porker.vo.request.merchant.UserOrderListRequest;
import com.spirit.porker.vo.request.merchant.UserSellTicketRequest;
import com.spirit.porker.vo.request.merchant.VerifyBuyCreditsRequest;
import com.spirit.porker.vo.request.merchant.VerifyBuyTicketRequest;
import com.spirit.porker.vo.request.merchant.VerifyTicketRequest;
import com.spirit.porker.vo.request.merchant.VerifyTickt;
import com.spirit.porker.vo.request.merchant.WithdrawCashRequest;
import com.spirit.porker.vo.CreditsMisc;
import com.spirit.porker.vo.EventMisc;
import com.spirit.porker.vo.TicketMisc;
import com.spirit.porker.vo.request.AddBlindListRequest;
import com.spirit.porker.vo.response.BaseResponse;
import com.spirit.porker.vo.response.Blind;
import com.spirit.porker.vo.response.BlindResponse;
import com.spirit.porker.vo.response.EventProductResponse;
import com.spirit.porker.vo.response.Player;
import com.spirit.porker.vo.response.RewardInfo;
import com.spirit.porker.vo.response.RewardPlayer;
import com.spirit.porker.vo.response.RewardUserListResponse;
import com.spirit.porker.vo.response.UserModelListResponse;
import com.spirit.porker.vo.response.merchant.AddEventResponse;
import com.spirit.porker.vo.response.merchant.BuyCreditsResponse;
import com.spirit.porker.vo.response.merchant.BuyTicketResponse;
import com.spirit.porker.vo.response.merchant.DeleteEventResponse;
import com.spirit.porker.vo.response.merchant.EventQueryResponse;
import com.spirit.porker.vo.response.merchant.ServerCheckTicketResponse;
import com.spirit.porker.vo.response.merchant.ShowTicketLeftResponse;
import com.spirit.porker.vo.response.merchant.UserBasicInfo;
import com.spirit.porker.vo.response.merchant.UserOrderListResponse;
import com.spirit.porker.vo.response.merchant.UserOrderResponse;
import com.spirit.porker.vo.response.merchant.UserSellTicketResponse;
import com.spirit.porker.vo.response.merchant.WithdrawCashResponse;

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

	public BaseResponse<List<BlindResponse>> getBlindList(BlindListRequest pojo) {
		List<BlindResponse> data = new ArrayList<BlindResponse>();
		BaseResponse<List<BlindResponse>> result = new BaseResponse<List<BlindResponse>>(ResultType.succes);
		result.setData(data);

		Map<String, Object> cond = new HashMap<String, Object>();
		PaginationList<BlindModel> list = blindDao.findEntityListByCond(cond, null);

		if (list == null || list.size() == 0) {
			return result;
		}

		for (int i = 0; i < list.size(); i++) {
			BlindResponse vo = new BlindResponse();
			vo.setName(list.get(i).getName());
			vo.setBlindList(JSON.parseArray(list.get(i).getBlindList(), Blind.class));
			data.add(vo);
		}

		return result;
	}

	public BaseResponse<BlindResponse> addBlindList(AddBlindListRequest pojo) {
		BlindResponse data = new BlindResponse();
		BaseResponse<BlindResponse> result = new BaseResponse<BlindResponse>(ResultType.succes);
		result.setData(data);

		Map<String, Object> cond = new HashMap<String, Object>();
		cond.put("name", pojo.getName());
		PaginationList<BlindModel> list = blindDao.findEntityListByCond(cond, null);
		if (list != null && list.size() > 0) {
			result.setCode(ResultType.fail.getCode());
			result.setDesc("该名字已存在,请重新更换类型名");
			return result;
		}

		BlindModel blindModel = new BlindModel();
		blindModel.setBlindList(pojo.getBlindList());
		blindModel.setCreateTime(new Timestamp(System.currentTimeMillis()));
		blindModel.setName(pojo.getName());

		BlindModel model = blindDao.addEntity(blindModel);

		data.setName(model.getName());
		data.setBlindList(JSON.parseArray(model.getBlindList(), Blind.class));

		return result;
	}

	public BaseResponse<EventProductResponse> selectEventId(EventProductRequest pojo) {
		EventProductResponse data = new EventProductResponse();
		BaseResponse<EventProductResponse> result = new BaseResponse<>(ResultType.succes);
		result.setData(data);

		Map<String, Object> cond = new HashMap<>();
		cond.put("id", pojo.getEventId());
		PaginationList<EventModel> list = eventDao.findEntityListByCond(cond, null);
		if (list == null || list.size() == 0) {
			result.setCode(ResultType.fail.getCode());
			result.setDesc("查询赛事列表信息失败");
			return result;
		}

		data.setEventId(list.get(0).getId());
		data.setEventInfo(list.get(0).getRewardInfo());
		return result;
	}

	/**
	 * 赛事结束奖励用户积分
	 */
	public BaseResponse<Object> rewardUserList(RewardUserListRequest pojo) {
		BaseResponse<Object> result = new BaseResponse<>(ResultType.succes);

		// 通过id查询赛事
		Map<String, Object> cond = new HashMap<>();
		cond.put("id", pojo.getEventId());
		PaginationList<EventModel> list = eventDao.findEntityListByCond(cond, null);

		if (list == null || list.size() == 0) {
			result.setCode(ResultType.fail.getCode());
			result.setDesc("查询赛事列表信息失败");
			return result;
		}

		// 解析真正获奖用户
		List<RewardUser> userList = JSON.parseArray(pojo.getUserList(), RewardUser.class);

		Map<String, Object> condUsers = new HashMap<>();
		List<String> carddNos = new ArrayList<String>();
		for (int i = 0; i < userList.size(); i++) {
			carddNos.add(userList.get(i).getCardNo());
		}
		cond.put("carddNos", carddNos);
		// 从user表查询真正获奖用户
		PaginationList<UserModel> listUsers = userDao.findEntityListByCond(condUsers, null);
		Map<String, UserModel> mapUsers = new HashMap<>();
		// map<userCard,user>
		for (int i = 0; i < listUsers.size(); i++) {
			mapUsers.put(listUsers.get(i).getCardNo(), listUsers.get(i));
		}

		if (listUsers == null || listUsers.size() == 0) {
			result.setCode(ResultType.fail.getCode());
			result.setDesc("查询赛事获奖人员失败");
			return result;
		}

		// 获取赛事奖励信息解析成对应ranking和point
		List<RankInfo> rewardInfos = JSON.parseArray(list.get(0).getRewardInfo(), RankInfo.class);

		if (rewardInfos.size() != userList.size()) {
			result.setCode(ResultType.fail.getCode());
			result.setDesc("奖励级别人数和提交人数不一致，请核对会员卡号是否一致");
			return result;
		}

		// 使用treeMapkey降序排列,存储上次masterScore排名(降序)
		Map<String, Integer> lastMasterRanking = new HashMap<String, Integer>();
		// 写入map<cardNo,lastMasterScore(得分)>
		for (int i = 0; i < listUsers.size(); i++) {
			lastMasterRanking.put(listUsers.get(i).getCardNo(), listUsers.get(i).getCurMasterScore());
		}
		List<Entry<String, Integer>> newList = new ArrayList<Entry<String, Integer>>(lastMasterRanking.entrySet());
		// 首先对value降序排序
		Collections.sort(newList, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o2.getValue().compareTo(o1.getValue()));
			}
		});
		// 按照<cardNos,Ranking(排名)>写入到新map
		Map<String, Integer> newLastMasterRankingMap = new TreeMap<String, Integer>();
		int rank = 1;
		for (Map.Entry<String, Integer> mapping : newList) {
			newLastMasterRankingMap.put(mapping.getKey(), rank++);
		}

		// 使用treeMapkey降序排列,存储上次mttScore排名(降序)
		Map<String, Integer> lastMttScoreRanking = new HashMap<String, Integer>();
		// 写入map<cardNo,lastMttScore(得分)>
		for (int i = 0; i < listUsers.size(); i++) {
			lastMttScoreRanking.put(listUsers.get(i).getCardNo(), listUsers.get(i).getCurMttScore());
		}
		newList = new ArrayList<Entry<String, Integer>>(lastMttScoreRanking.entrySet());
		// 首先对value降序排序
		Collections.sort(newList, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o2.getValue().compareTo(o1.getValue()));
			}
		});
		// 按照<cardNos,Ranking(排名)>写入到新map
		Map<String, Integer> newLastMttRankingMap = new TreeMap<String, Integer>();
		rank = 1;
		for (Map.Entry<String, Integer> mapping : newList) {
			newLastMttRankingMap.put(mapping.getKey(), rank++);
		}

		// 更新order表scoreMisc信息
		for (int j = 0; j < userList.size(); j++) {
			Map<String, Object> condEvent = new HashMap<>();
			condEvent.put("cardNo", userList.get(j).getCardNo());
			condEvent.put("eventId", pojo.getEventId());
			PaginationList<OrderModel> order = orderDao.findEntityListByCond(condEvent, null);
			if (order == null || order.size() == 0) {
				result.setCode(ResultType.fail.getCode());
				result.setDesc("查询订单列表为空");
				return result;
			}
			OrderModel orderModel = new OrderModel();
			orderModel.setId(order.get(0).getId());
			for (int i = 0; i < rewardInfos.size(); i++) {
				if (userList.get(j).getRanking() == rewardInfos.get(i).getRanking()) {
					orderModel.setScoreMisc(JSON.toJSONString(rewardInfos.get(i)));
				}
			}
			orderDao.updateEntity(orderModel);
		}

		// 更新user表master,mtt积分
		for (int i = 0; i < userList.size(); i++) {
			for (int j = 0; j < listUsers.size(); j++) {
				if (userList.get(i).getCardNo().equals(mapUsers.get(userList.get(j).getCardNo()).getCardNo())) {
					if (userList.get(i).getRanking() == rewardInfos.get(j).getRanking()) {

						UserModel usermodel = new UserModel();
						usermodel.setCardNo(userList.get(i).getCardNo());

						usermodel.setLastMasterScore(mapUsers.get(userList.get(j).getCardNo()).getCurMasterScore());
						usermodel.setLastMttScore(mapUsers.get(userList.get(j).getCardNo()).getCurMttScore());
						usermodel
								.setCurMasterScore(rewardInfos.get(j).getMasteScore() + usermodel.getLastMasterScore());
						usermodel.setCurMttScore(rewardInfos.get(j).getMttScore() + usermodel.getLastMttScore());
						// usermodel.setMasterChange(usermodel.getCurMasterScore() -
						// usermodel.getLastMasterScore());
						// usermodel.setMttChange(usermodel.getCurMttScore() -
						// usermodel.getLastMttScore());

						userDao.updateEntity(usermodel);
					}

				}
			}

		}

		// 更新了masterScore和mttScore后重新从user表查询真正获奖用户，以作排名变动使用
		PaginationList<UserModel> newlistUsers = userDao.findEntityListByCond(condUsers, null);

		// 使用treeMapkey降序排列,存储本次mttScore排名(降序<cardNo,mttRanking>)
		Map<String, Integer> curMttScoreRanking = new HashMap<String, Integer>();
		// 写入map<cardNo,curMttScore(得分)>
		for (int i = 0; i < listUsers.size(); i++) {
			curMttScoreRanking.put(newlistUsers.get(i).getCardNo(), newlistUsers.get(i).getCurMttScore());
		}
		newList = new ArrayList<Entry<String, Integer>>(curMttScoreRanking.entrySet());
		// 首先对value降序排序
		Collections.sort(newList, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o2.getValue().compareTo(o1.getValue()));
			}
		});
		// 按照<cardNos,Ranking(排名)>写入到新map
		Map<String, Integer> newCurMttRankingMap = new TreeMap<String, Integer>();
		rank = 1;
		for (Map.Entry<String, Integer> mapping : newList) {
			newCurMttRankingMap.put(mapping.getKey(), rank++);
		}

		// 使用treeMapkey降序排列,存储本次masterScore排名(降序<cardNo,masterRanking>)
		Map<String, Integer> curMasterScoreRanking = new HashMap<String, Integer>();
		// 写入map<cardNo,curMasterScore(得分)>
		for (int i = 0; i < listUsers.size(); i++) {
			curMasterScoreRanking.put(newlistUsers.get(i).getCardNo(), newlistUsers.get(i).getCurMasterScore());
		}
		newList = new ArrayList<Entry<String, Integer>>(curMasterScoreRanking.entrySet());
		// 首先对value降序排序
		Collections.sort(newList, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o2.getValue().compareTo(o1.getValue()));
			}
		});
		// 按照<cardNos,Ranking(排名)>写入到新map
		Map<String, Integer> newCurMasterRankingMap = new TreeMap<String, Integer>();
		rank = 1;
		for (Map.Entry<String, Integer> mapping : newList) {
			newCurMasterRankingMap.put(mapping.getKey(), rank++);
		}

		for (int i = 0; i < userList.size(); i++) {
			for (int j = 0; j < listUsers.size(); j++) {
				if (userList.get(i).getCardNo().equals(mapUsers.get(userList.get(j).getCardNo()).getCardNo())) {
					if (userList.get(i).getRanking() == rewardInfos.get(j).getRanking()) {

						UserModel usermodel = new UserModel();
						usermodel.setCardNo(userList.get(i).getCardNo());
						usermodel.setMasterChange(newCurMasterRankingMap.get(userList.get(i).getCardNo())
								- newLastMasterRankingMap.get(userList.get(i).getCardNo()));
						usermodel.setMttChange(newCurMttRankingMap.get(userList.get(i).getCardNo())
								- newLastMttRankingMap.get(userList.get(i).getCardNo()));
						// if(lastMasterRanking.get(key))
						// usermodel.setMasterChange(usermodel.getCurMasterScore() -
						// usermodel.getLastMasterScore());
						// usermodel.setMttChange(usermodel.getCurMttScore() -
						// usermodel.getLastMttScore());

						userDao.updateEntity(usermodel);
					}

				}
			}

		}

		for (int i = 0; i < rewardInfos.size(); i++) {
			for (int k = 0; k < userList.size(); k++) {
				if (rewardInfos.get(i).getRanking() == userList.get(k).getRanking()) {
					// 更新user表用户数据
					UserModel userModel = new UserModel();
					userModel.setScore(
							mapUsers.get(userList.get(k).getCardNo()).getScore() + rewardInfos.get(i).getScore());
					userModel.setCardNo(mapUsers.get(userList.get(k).getCardNo()).getCardNo());
					userDao.updateEntity(userModel);
					break;
				}
			}
		}

		List<RewardPlayer> rewardInfoList = new ArrayList<>();
		for (int i = 0; i < userList.size(); i++) {
			// 设置赛事rewardPlayers
			RewardPlayer rewardInfo = new RewardPlayer();
			rewardInfo.setRank(userList.get(i).getRanking());
			rewardInfo.setNickName(mapUsers.get(userList.get(i).getCardNo()).getNickName());
			rewardInfo.setCardNos(userList.get(i).getCardNo());
			rewardInfoList.add(rewardInfo);
		}
		// 更新赛事表状态和rewardPlayers
		EventModel event = new EventModel();
		event.setId(Integer.parseInt(pojo.getEventId()));
		event.setRewardPlayers(JSON.toJSONString(rewardInfoList));
		event.setEventStatus(EventStatus.done.getCode());
		eventDao.updateEntity(event);

		return result;
	}

	public BaseResponse<UserModelListResponse> basicUsersInfomation(UserModelListRequest pojo) {
		UserModelListResponse data = new UserModelListResponse();
		BaseResponse<UserModelListResponse> result = new BaseResponse<UserModelListResponse>(ResultType.succes);
		result.setData(data);

		Map<String, Object> cond = new HashMap<>();
		PaginationList<UserModel> list = userDao.findEntityListByCond(cond, null);
		if (list == null || list.size() == 0) {
			result.setCode(ResultType.fail.getCode());
			result.setDesc("获取用户列表失败，请查看是否存在用户");
			return result;
		}

		data.setTotalNums(list.size());
		data.setTotalBuyIn(0);
		data.setTotalBuyOut(0);
		for (int i = 0; i < list.size(); i++) {
			data.setTotalBuyIn(data.getTotalBuyIn() + list.get(i).getBuyIn());
			data.setTotalBuyOut(data.getTotalBuyOut() + list.get(i).getBuyOut());
		}
		return result;
	}

	public BaseResponse<UserOrderListResponse> userOrderList(UserOrderListRequest pojo) {

		BaseResponse<UserOrderListResponse> result = new BaseResponse<>(ResultType.succes);

		UserOrderListResponse data = new UserOrderListResponse();
		result.setData(data);

		Map<String, Object> cond = new HashMap<>();
		cond.put("cardNo", pojo.getCardNo());
		PaginationList<UserModel> listUser = userDao.findEntityListByCond(cond, null);

		if (listUser == null || listUser.size() == 0) {
			result.setCode(ResultType.fail.getCode());
			result.setDesc("获取用户信息失败，请查询是否存在该会员卡号");
			return result;
		}

		PaginationList<OrderModel> orders = orderDao.findEntityListByCond(cond, null);
		if (orders == null) {
			return result;
		}

		List<UserOrderResponse> userOrderList = new ArrayList<UserOrderResponse>();
		data.setUserOrderList(userOrderList);

		UserBasicInfo userBasicInfo = new UserBasicInfo();
		data.setUserBasicInfo(userBasicInfo);

		for (int i = 0; i < orders.size(); i++) {
			UserOrderResponse order = new UserOrderResponse();
			order.setCardNo(orders.get(i).getCardNo());
			order.setEventId(orders.get(i).getEventId());
			order.setId(orders.get(i).getId());
			order.setOrderAmount(orders.get(i).getOrderAmount());
			order.setOrderStatus(orders.get(i).getOrderStatus());
			order.setOrderTime(orders.get(i).getOrderTime());
			order.setPayAmount(orders.get(i).getPayAmount());
			order.setPaySuccessTime(orders.get(i).getPaySuccessTime());
			order.setPayType(orders.get(i).getPayType());
			order.setRefundAmount(orders.get(i).getRefundAmount());
			EventMisc eventMisc = JSON.parseObject(orders.get(i).getEventMisc(), EventMisc.class);
			order.setEventName(eventMisc.getEventName());
			order.setEventTime(eventMisc.getEventTime());

			userOrderList.add(order);
		}
		userBasicInfo.setBuyIn(listUser.get(0).getBuyIn());
		userBasicInfo.setBuyOut(listUser.get(0).getBuyOut());
		userBasicInfo.setCardNo(listUser.get(0).getCardNo());
		userBasicInfo.setCertNo(listUser.get(0).getCertNo());
		userBasicInfo.setCity(listUser.get(0).getCity());
		userBasicInfo.setCreateTime(listUser.get(0).getCreateTime());
		userBasicInfo.setId(listUser.get(0).getId());
		userBasicInfo.setImgUrl(listUser.get(0).getImgUrl());
		userBasicInfo.setMasterScore(listUser.get(0).getCurMasterScore());
		userBasicInfo.setMttScore(listUser.get(0).getCurMttScore());
		userBasicInfo.setNickName(listUser.get(0).getNickName());
		userBasicInfo.setOpenId(listUser.get(0).getOpenId());
		userBasicInfo.setPhone(listUser.get(0).getPhone());
		userBasicInfo.setRealName(listUser.get(0).getRealName());
		userBasicInfo.setScore(listUser.get(0).getScore());
		userBasicInfo.setSex(listUser.get(0).getSex());
		userBasicInfo.setUserLevel(listUser.get(0).getUserLevel());
		return result;
	}

	public BaseResponse<EventQueryResponse> eventQuery(EventQueryRequest pojo) {

		BaseResponse<EventQueryResponse> result = new BaseResponse<>(ResultType.succes);
		EventQueryResponse data = new EventQueryResponse();
		result.setData(data);

		Map<String, Object> cond = new HashMap<>();
		cond.put("endTime", pojo.getEndTime());
		cond.put("beginTime", pojo.getStartTime());

		PaginationList<EventModel> eventList = eventDao.findEntityListByCond(cond, null);

		if (eventList == null || eventList.size() == 0) {
			return result;
		}

		List<EventModel> eventModelList = new ArrayList<>();
		data.setEventModelList(eventModelList);
		BigDecimal totalPot = BigDecimal.ZERO;
		BigDecimal validPot = BigDecimal.ZERO;
		int totalPlayers = 0;
		List<Player> oneEventPlayers = new ArrayList<>();
		int validEventNumbers = 0;
		for (int i = 0; i < eventList.size(); i++) {
			EventModel eventModel = new EventModel();
			eventModel = eventList.get(i);

			totalPot = totalPot.add(eventList.get(i).getBasePot());
			if (eventList.get(i).getEventStatus() != EventStatus.canceled.getCode()) {
				validPot = validPot.add(eventList.get(i).getBasePot());
				validEventNumbers++;
			}

			oneEventPlayers = JSON.parseArray(eventList.get(i).getCurPlayers(), Player.class);
			totalPlayers += oneEventPlayers.size();

			eventModelList.add(eventModel);
		}
		data.setTotalPot(totalPot);
		data.setTotalPlayers(totalPlayers);
		data.setValidEventNumbers(validEventNumbers);
		data.setValidPot(validPot);
		data.setTotalEvent(eventList.size());
		return result;

	}

	public BaseResponse<AddEventResponse> addEvent(AddEventRequest pojo) {
		AddEventResponse data = new AddEventResponse();
		BaseResponse<AddEventResponse> result = new BaseResponse<>(ResultType.succes);
		result.setData(data);

		EventModel eventModel = new EventModel();
		Map<String, Object> cond = new HashMap<>();
		cond.put("id", pojo.getBlindType());
		PaginationList<BlindModel> blind = blindDao.findEntityListByCond(cond, null);

		if (blind == null || blind.size() == 0) {
			result.setCode(ResultType.fail.getCode());
			result.setDesc("查询盲注信息失败，请检查是否存在该盲注级别");
			return result;
		}

		cond.remove("id");
		cond.put("id", pojo.getRewardType());
		PaginationList<RewardModel> reward = rewardDao.findEntityListByCond(cond, null);

		if (reward == null || reward.size() == 0) {
			result.setCode(ResultType.fail.getCode());
			result.setDesc("查询奖励信息失败，请检查是否存在该奖励积分");
			return result;
		}
		eventModel.setBasePot(pojo.getBasePot());
		eventModel.setEnrollCloseTime(Timestamp.valueOf(pojo.getEnrollCloseTime()));
		eventModel.setEventTime(pojo.getEventTime());
		eventModel.setMaxPlayers(pojo.getMaxPlayers());
		eventModel.setMinPlayers(pojo.getMinPlayers());
		eventModel.setServiceFee(pojo.getServiceFee());
		eventModel.setPrice(pojo.getPrice());
		eventModel.setName(pojo.getName());
		eventModel.setMinPlayers(pojo.getMinPlayers());
		eventModel.setMaxPlayers(pojo.getMaxPlayers());
		eventModel.setRebuyTimes(0);
		eventModel.setRewardType(pojo.getRewardType());
		eventModel.setRewardPlayers("");
		eventModel.setEventStatus(EventStatus.init.getCode());
		eventModel.setBlindInfo(blind.get(0).getBlindList());
		eventModel.setCurPlayers("");
		eventModel.setCreateTime(new Timestamp(System.currentTimeMillis()));
		eventModel.setEventEndTime(new Timestamp(System.currentTimeMillis()));
		eventModel.setRewardInfo(reward.get(0).getRewardInfo());
		eventModel.setEventType(pojo.getEventType());

		eventDao.addEntity(eventModel);

		return result;
	}

	public BaseResponse<DeleteEventResponse> deleteEvent(DeleteEventRequest pojo) {

		DeleteEventResponse data = new DeleteEventResponse();
		BaseResponse<DeleteEventResponse> result = new BaseResponse<>(ResultType.succes);
		result.setData(data);

		Map<String, Object> cond = new HashMap<>();
		cond.put("id", pojo.getEventId());
		PaginationList<EventModel> eventId = eventDao.findEntityListByCond(cond, null);
		if (eventId == null || eventId.size() == 0) {
			result.setCode(ResultType.fail.getCode());
			result.setDesc("不存在该赛事，请确认该赛事是否存在");
			return result;
		}

		EventModel eventModel = new EventModel();
		eventModel.setId(pojo.getEventId());
		eventDao.deleteEntity(eventModel);

		return result;

	}

	public BaseResponse<WithdrawCashResponse> withdrawCash(WithdrawCashRequest pojo) {
		WithdrawCashResponse data = new WithdrawCashResponse();
		BaseResponse<WithdrawCashResponse> result = new BaseResponse<>(ResultType.succes);
		result.setData(data);

		Map<String, Object> cond = new HashMap<>();
		cond.put("cardNo", pojo.getCardNo());
		PaginationList<UserModel> user = userDao.findEntityListByCond(cond, null);
		if (user == null || user.size() == 0) {
			result.setCode(ResultType.fail.getCode());
			result.setDesc("用户不存在");
			return result;
		}
		cond.remove("cardNo");
		cond.put("id", pojo.getEventId());
		PaginationList<EventModel> event = eventDao.findEntityListByCond(cond, null);
		if (event == null || event.size() == 0) {
			result.setCode(ResultType.fail.getCode());
			result.setDesc("赛事不存在");
			return result;
		}
		if (pojo.getCash() > user.get(0).getScore()) {
			result.setCode(ResultType.fail.getCode());
			result.setDesc("提现积分大于用户余额积分,用户积分余额为" + user.get(0).getScore());
			return result;
		}
		Integer preWithdraw = user.get(0).getScore();

		UserModel newUser = new UserModel();
		newUser.setCardNo(pojo.getCardNo());
		newUser.setScore(user.get(0).getScore() - pojo.getCash());
		userDao.updateEntity(newUser);

		cond.remove("id");
		cond.put("cardNo", pojo.getCardNo());
		user = userDao.findEntityListByCond(cond, null);
		if (user == null || user.size() == 0) {
			result.setCode(ResultType.fail.getCode());
			result.setDesc("更新用户积分出现异常");
			return result;
		}
		data.setMessage("提现前积分:" + preWithdraw + " 提现:" + pojo.getCash() + "积分" + " 剩余积分:" + user.get(0).getScore());
		return result;
	}

	public BaseResponse<UserSellTicketResponse> userSellTicket(UserSellTicketRequest pojo) {
		UserSellTicketResponse data = new UserSellTicketResponse();
		BaseResponse<UserSellTicketResponse> result = new BaseResponse<>(ResultType.succes);
		result.setData(data);

		Map<String, Object> cond = new HashMap<String, Object>();
		cond.put("userCardNo", pojo.getUserCardno());
		cond.put("ticketId", pojo.getTicketId());
		PaginationList<TicketModel> ticket = ticketDao.findEntityListByCond(cond, null);

		if (ticket == null || ticket.size() == 0) {
			result.setCode(ResultType.fail.getCode());
			result.setDesc("查询门票失败，请找客服核对是否有该门票");
			return result;
		}

		// 检查用户是否重复提交订单
		cond.clear();
		cond.put("cardNo", pojo.getUserCardno());
		PaginationList<OrderModel> order = orderDao.findEntityListByCond(cond, null);
		if (order != null || order.size() > 0) {
			for (int k = 0; k < order.size(); k++) {
				if (order.get(k).getType() == OrderTypeState.userSellTicket.getCode()) {
					TicketModel ticketMisc = JSON.parseObject(order.get(k).getEventMisc(), TicketModel.class);
					if (ticketMisc.getTicketId().equals(pojo.getTicketId())) {
						result.setCode(ResultType.fail.getCode());
						result.setDesc("不能重复提交订单");
						return result;
					}
				}
			}
		}

		// 更新门票状态为待审核
		TicketModel ticketModel = new TicketModel();
		ticketModel.setTicketId(ticket.get(0).getTicketId());
		ticketModel.setUserCardNo(ticket.get(0).getUserCardNo());
		ticketModel.setState(TicketState.check.getCode());
		ticketDao.updateEntity(ticketModel);

		// 在订单表中插入一条记录
		OrderModel orderModel = new OrderModel();
		orderModel.setId(orderIdGenerator.getOrderId());
		orderModel.setCardNo(pojo.getUserCardno());
		orderModel.setOrderStatus(OrderStatus.init.getCode());
		// 保留整数
		BigDecimal price = ticket.get(0).getPrice()
				.multiply(new BigDecimal(PropertyUtil.config.getProperties("user_sell_rate")));
		orderModel.setOrderAmount(price.setScale(0, BigDecimal.ROUND_HALF_UP));
		orderModel.setOrderTime(new Timestamp(System.currentTimeMillis()));
		orderModel.setEventId(0);
		TicketModel ticketMisc = new TicketModel();
		ticketMisc.setTicketId(ticket.get(0).getTicketId());
		orderModel.setEventMisc(JSON.toJSONString(ticketMisc));
		orderModel.setType(OrderTypeState.userSellTicket.getCode());
		orderDao.addEntity(orderModel);

		return result;
	}

	public BaseResponse<List<ServerCheckTicketResponse>> serverCheckTicket(ServerCheckTicketRequest pojo) {
		List<ServerCheckTicketResponse> data = new ArrayList<>();
		BaseResponse<List<ServerCheckTicketResponse>> result = new BaseResponse<>(ResultType.succes);
		result.setData(data);

		Map<String, Object> cond = new HashMap<String, Object>();
		cond.put("state", TicketState.check.getCode());
		PaginationList<TicketModel> ticketList = ticketDao.findEntityListByCond(cond, null);

		if (ticketList == null || ticketList.size() == 0) {
			return result;
		}

		for (int i = 0; i < ticketList.size(); i++) {
			ServerCheckTicketResponse serverCheckTicketResponse = new ServerCheckTicketResponse();
			serverCheckTicketResponse.setId(ticketList.get(i).getId());
			serverCheckTicketResponse.setState(ticketList.get(i).getState());
			serverCheckTicketResponse.setTicketId(ticketList.get(i).getTicketId());
			serverCheckTicketResponse
					.setTicketMisc(JSON.parseObject(ticketList.get(i).getTicketMisc(), TicketMisc.class));
			serverCheckTicketResponse.setUserCardNo(ticketList.get(i).getUserCardNo());
			data.add(serverCheckTicketResponse);
		}
		return result;
	}

	public BaseResponse<Object> verifyTickets(VerifyTicketRequest pojo) {
		BaseResponse<Object> result = new BaseResponse<>(ResultType.succes);

		List<VerifyTickt> verifyTickets = JSON.parseArray(pojo.getVerifyTickets(), VerifyTickt.class);
		if (verifyTickets == null || verifyTickets.size() == 0) {
			result.setCode(ResultType.fail.getCode());
			result.setDesc("参数异常");
			return result;
		}

		for (int i = 0; i < verifyTickets.size(); i++) {
			Map<String, Object> cond = new HashMap<>();
			cond.put("userCardNo", verifyTickets.get(i).getUserCardNo());
			cond.put("ticketId", verifyTickets.get(i).getTicketId());
			PaginationList<TicketModel> ticket = ticketDao.findEntityListByCond(cond, null);

			if (ticket == null || ticket.size() == 0) {
				result.setCode(ResultType.fail.getCode());
				result.setDesc("获取列表失败,请稍后重试(verify)");
				return result;
			}

			cond.clear();
			cond.put("cardNo", ticket.get(0).getUserCardNo());
			cond.put("type", OrderTypeState.userSellTicket.getCode());
			cond.put("orderStatus", OrderStatus.init.getCode());
			PaginationList<OrderModel> order = orderDao.findEntityListByCond(cond, null);

			if (order == null || order.size() == 0) {
				result.setCode(ResultType.fail.getCode());
				result.setDesc("获取订单列表失败");
				return result;
			}

			// 在商城中增加一条记录
			ShopModel model = new ShopModel();
			// 在订单表中修改记录为已支付
			for (int j = 0; j < order.size(); j++) {
				TicketModel ticketMisc = JSON.parseObject(order.get(j).getEventMisc(), TicketModel.class);
				if (ticketMisc.getTicketId().equals(verifyTickets.get(i).getTicketId())) {
					OrderModel ordermodel = new OrderModel();
					ordermodel.setId(order.get(j).getId());
					ordermodel.setOrderStatus(OrderStatus.payed.getCode());
					cond.clear();
					cond.put("id", order.get(j).getId());
					PaginationList<OrderModel> findOrder = orderDao.findEntityListByCond(cond, null);
					if (findOrder == null || findOrder.size() == 0) {
						result.setCode(ResultType.fail.getCode());
						result.setDesc("不存在该订单");
						return result;
					}
					model.setUserSellPrice(findOrder.get(0).getOrderAmount());
					orderDao.updateEntity(ordermodel);
				}
			}
			model.setId(ticket.get(0).getId());
			model.setState(TicketState.unlock.getCode());
			model.setTicketId(ticket.get(0).getTicketId());
			model.setTicketMisc(ticket.get(0).getTicketMisc());
			model.setUserCardNo(ticket.get(0).getUserCardNo());
			model.setNormalPrice(ticket.get(0).getPrice());

			BigDecimal price = ticket.get(0).getPrice()
					.multiply(new BigDecimal(PropertyUtil.config.getProperties("user_buy_rate")));
			model.setUserBuyPrice(price.setScale(0, BigDecimal.ROUND_HALF_UP));
			shopDao.addEntity(model);

			// 删除门票记录
			TicketModel ticketModel = new TicketModel();
			ticketModel.setTicketId(ticket.get(0).getTicketId());
			ticketDao.deleteEntity(ticketModel);
		}
		return result;
	}

	public BaseResponse<List<ShowTicketLeftResponse>> showTicketLeft(ShowTicketLeftRequest pojo) {
		List<ShowTicketLeftResponse> data = new ArrayList<>();
		BaseResponse<List<ShowTicketLeftResponse>> result = new BaseResponse<>(ResultType.succes);
		result.setData(data);

		Map<String, Object> cond = new HashMap<>();
		cond.put("state", TicketState.unlock.getCode());
		PaginationList<ShopModel> shopList = shopDao.findEntityListByCond(cond, null);
		if (shopList == null || shopList.size() == 0) {
			return result;
		}
		for (int i = 0; i < shopList.size(); i++) {
			ShowTicketLeftResponse showTicketLeftResponse = new ShowTicketLeftResponse();
			showTicketLeftResponse.setId(shopList.get(i).getId());
			showTicketLeftResponse.setState(shopList.get(i).getState());
			showTicketLeftResponse.setTicketId(shopList.get(i).getTicketId());
			TicketMisc ticketMisc = JSON.parseObject(shopList.get(i).getTicketMisc(), TicketMisc.class);
			showTicketLeftResponse.setTicketMisc(ticketMisc);
			showTicketLeftResponse.setUserCardNo(shopList.get(i).getUserCardNo());
			showTicketLeftResponse.setPrice(shopList.get(i).getUserBuyPrice());
			data.add(showTicketLeftResponse);
		}
		return result;
	}

	public BaseResponse<BuyTicketResponse> buyTicket(BuyTicketRequest pojo) {
		BuyTicketResponse data = new BuyTicketResponse();
		BaseResponse<BuyTicketResponse> result = new BaseResponse<>(ResultType.succes);
		result.setData(data);

		Map<String, Object> cond = new HashMap<>();
		cond.put("ticketId", pojo.getTicketId());
		cond.put("state", TicketState.unlock.getCode());
		PaginationList<ShopModel> shop = shopDao.findEntityListByCond(cond, null);

		if (shop == null || shop.size() == 0) {
			result.setCode(ResultType.fail.getCode());
			result.setDesc("该优惠券不存在获已被人购买，请重新刷新");
			return result;
		}

		// 将商城优惠券锁住
		ShopModel shopModel = new ShopModel();
		shopModel.setTicketId(pojo.getTicketId());
		shopModel.setState(TicketState.lock.getCode());
		shopModel.setUserCardNo(pojo.getUserCardNo());
		shopDao.updateEntity(shopModel);

		// 在订单表中插入一条记录
		OrderModel orderModel = new OrderModel();
		orderModel.setId(orderIdGenerator.getOrderId());
		orderModel.setCardNo(pojo.getUserCardNo());
		orderModel.setOrderStatus(OrderStatus.init.getCode());
		orderModel.setOrderAmount(shop.get(0).getUserBuyPrice());
		orderModel.setOrderTime(new Timestamp(System.currentTimeMillis()));
		orderModel.setEventId(0);
		TicketModel ticketMisc = new TicketModel();
		ticketMisc.setTicketId(pojo.getTicketId());
		orderModel.setEventMisc(JSON.toJSONString(ticketMisc));
		orderModel.setType(OrderTypeState.userBuyTicket.getCode());
		orderDao.addEntity(orderModel);

		data.setOrderId(orderModel.getId());
		return result;

	}

	public BaseResponse<Object> verifyBuyTicket(VerifyBuyTicketRequest pojo) {
		BaseResponse<Object> result = new BaseResponse<>(ResultType.succes);

		if (pojo.getVerifyMessage().equals("成功")) {
			Map<String, Object> cond = new HashMap<>();
			cond.put("id", pojo.getOrderId());
			PaginationList<OrderModel> order = orderDao.findEntityListByCond(cond, null);
			if (order == null || order.size() == 0) {
				result.setCode(ResultType.fail.getCode());
				result.setDesc("不存在该订单获查询订单为空");
				return result;
			}
			// 将订单状态更新为已支付
			OrderModel orderModel = new OrderModel();
			orderModel.setId(order.get(0).getId());
			orderModel.setOrderStatus(OrderStatus.payed.getCode());
			orderDao.updateEntity(orderModel);

			// 查询商城中对应ticketId优惠券
			TicketModel ticketMisc = JSON.parseObject(order.get(0).getEventMisc(), TicketModel.class);
			cond.clear();
			cond.put("ticketId", ticketMisc.getTicketId());
			PaginationList<ShopModel> shop = shopDao.findEntityListByCond(cond, null);
			if (shop == null || shop.size() == 0) {
				result.setCode(ResultType.fail.getCode());
				result.setDesc("商城中不存在该订单的优惠券");
				return result;
			}

			// ticket表中增加一条记录
			TicketModel newUserTicket = new TicketModel();
			newUserTicket.setTicketId(shop.get(0).getTicketId());
			newUserTicket.setState(TicketState.normal.getCode());
			newUserTicket.setTicketMisc(shop.get(0).getTicketMisc());
			newUserTicket.setUserCardNo(shop.get(0).getUserCardNo());
			newUserTicket.setPrice(shop.get(0).getNormalPrice());
			ticketDao.addEntity(newUserTicket);

			// 删除对应商城表的优惠券
			shopDao.deleteEntity(shop.get(0));
			return result;
		} else {
			result.setCode(ResultType.fail.getCode());
			result.setDesc("微信支付确认信息不成功，请稍后重试");
			return result;
		}
	}

	public BaseResponse<BuyCreditsResponse> buyCredits(BuyCreditsRequest pojo) {
		BuyCreditsResponse data = new BuyCreditsResponse();
		BaseResponse<BuyCreditsResponse> result = new BaseResponse<>(ResultType.succes);
		result.setData(data);

		Map<String, Object> cond = new HashMap<>();
		cond.put("id", pojo.getId());
		PaginationList<CreditsModel> creditsModel = creditsDao.findEntityListByCond(cond, null);
		if (creditsModel == null || creditsModel.size() == 0) {
			result.setCode(ResultType.fail.getCode());
			result.setDesc("查询不到该积分对应Id，请重新输入");
			return result;
		}

		// 在订单表中插入一条记录
		OrderModel orderModel = new OrderModel();
		orderModel.setId(orderIdGenerator.getOrderId());
		orderModel.setCardNo(pojo.getUserCardno());
		orderModel.setOrderStatus(OrderStatus.init.getCode());
		orderModel.setOrderAmount(creditsModel.get(0).getCash());
		orderModel.setOrderTime(new Timestamp(System.currentTimeMillis()));
		orderModel.setEventId(0);
		CreditsMisc creditsMisc=new CreditsMisc();
		creditsMisc.setCash(creditsModel.get(0).getCash());
		creditsMisc.setCredits(creditsModel.get(0).getCredits());
		orderModel.setEventMisc(JSON.toJSONString(creditsMisc));
		orderModel.setType(OrderTypeState.userBuyCredits.getCode());
		orderDao.addEntity(orderModel);

		data.setOrderId(orderModel.getId());
		return result;
	}

	public BaseResponse<Object> verifyBuyCredits(VerifyBuyCreditsRequest pojo) {
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

	}

}
