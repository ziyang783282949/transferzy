package com.spirit.porker.merchant.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.spirit.porker.enums.ResultType;
import com.spirit.porker.service.SettingService;
import com.spirit.porker.service.WechatCallBackService;
import com.spirit.porker.util.LoggerUtil;
import com.spirit.porker.vo.request.AddBlindListRequest;
import com.spirit.porker.vo.request.BlindListRequest;
import com.spirit.porker.vo.request.EventProductRequest;
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
import com.spirit.porker.vo.request.merchant.WithdrawCashRequest;
import com.spirit.porker.vo.response.BaseResponse;
import com.spirit.porker.vo.response.BlindResponse;
import com.spirit.porker.vo.response.EventProductResponse;
import com.spirit.porker.vo.response.UserModelListResponse;
import com.spirit.porker.vo.response.merchant.AddEventResponse;
import com.spirit.porker.vo.response.merchant.BuyCreditsResponse;
import com.spirit.porker.vo.response.merchant.BuyTicketResponse;
import com.spirit.porker.vo.response.merchant.DeleteEventResponse;
import com.spirit.porker.vo.response.merchant.EventQueryResponse;
import com.spirit.porker.vo.response.merchant.ServerCheckTicketResponse;
import com.spirit.porker.vo.response.merchant.ShowTicketLeftResponse;
import com.spirit.porker.vo.response.merchant.UserOrderListResponse;
import com.spirit.porker.vo.response.merchant.UserSellTicketResponse;
import com.spirit.porker.vo.response.merchant.WithdrawCashResponse;

@Controller
public class SettingController {

	@Resource
	SettingService settingService;

	@Resource
	WechatCallBackService wechatCallBackService;

	@RequestMapping("/blindList")
	@ResponseBody
	public String blindList(BlindListRequest pojo) {
		BaseResponse<List<BlindResponse>> result = null;
		try {
			result = settingService.getBlindList(pojo);
		} catch (Exception e) {

			LoggerUtil.error("获取盲注级别异常", e);
			result = new BaseResponse<List<BlindResponse>>(ResultType.fail);

		}
		return JSON.toJSONString(result);
	}

	@RequestMapping("/addBlindList")
	@ResponseBody
	public String addBlindList(AddBlindListRequest pojo) {
		BaseResponse<BlindResponse> result = null;
		try {
			result = settingService.addBlindList(pojo);
		} catch (Exception e) {
			LoggerUtil.error("获取盲注级别异常", e);
			result = new BaseResponse<BlindResponse>(ResultType.fail);
		}
		return JSON.toJSONString(result);
	}

	@RequestMapping("/selectEventId")
	@ResponseBody
	public String SelectEventId(EventProductRequest pojo) {
		BaseResponse<EventProductResponse> result = null;
		try {
			result = settingService.selectEventId(pojo);
		} catch (Exception e) {
			LoggerUtil.error("获取赛事id失败", e);
			result = new BaseResponse<EventProductResponse>(ResultType.fail);

		}
		return JSON.toJSONString(result);

	}

	@RequestMapping("/rewardUserList")
	@ResponseBody
	public String RewardUserList(RewardUserListRequest pojo) {
		BaseResponse<Object> result = null;
		try {
			result = settingService.rewardUserList(pojo);
		} catch (Exception e) {
			LoggerUtil.error("提交赛事结果列表失败,请重新提交", e);
			result = new BaseResponse<Object>(ResultType.fail);

		}
		return JSON.toJSONString(result);
	}

	@RequestMapping("/basicUsersInfomation")
	@ResponseBody
	public String basicUsersInfomation(UserModelListRequest pojo) {
		BaseResponse<UserModelListResponse> result = null;
		try {
			result = settingService.basicUsersInfomation(pojo);
		} catch (Exception e) {
			LoggerUtil.error("获取用户信息失败", e);
			result = new BaseResponse<UserModelListResponse>(ResultType.fail);

		}
		return JSON.toJSONString(result);
	}

	@RequestMapping("/userOrderList")
	@ResponseBody
	public String UserOrderList(UserOrderListRequest pojo) {
		BaseResponse<UserOrderListResponse> result = null;
		try {
			result = settingService.userOrderList(pojo);
		} catch (Exception e) {
			LoggerUtil.error("查询个人用户信息失败，请检查该会员卡号是否存在", e);
			result = new BaseResponse<UserOrderListResponse>(ResultType.fail);

		}
		return JSON.toJSONString(result);
	}

	@RequestMapping("/eventQuery")
	@ResponseBody
	public String EventQuery(EventQueryRequest pojo) {
		BaseResponse<EventQueryResponse> result = null;
		try {
			result = settingService.eventQuery(pojo);
		} catch (Exception e) {
			LoggerUtil.error("查询赛事失败", e);
			result = new BaseResponse<EventQueryResponse>(ResultType.fail);
		}
		return JSON.toJSONString(result);
	}

	@RequestMapping("/addEvent")
	@ResponseBody
	public String AddEvent(AddEventRequest pojo) {
		BaseResponse<AddEventResponse> result = null;
		try {
			result = settingService.addEvent(pojo);
		} catch (Exception e) {
			LoggerUtil.error("增加赛事失败", e);
			result = new BaseResponse<AddEventResponse>(ResultType.fail);
		}
		return JSON.toJSONString(result);
	}

	@RequestMapping("/deleteEvent")
	@ResponseBody
	public String DeleteEvent(DeleteEventRequest pojo) {

		BaseResponse<DeleteEventResponse> result = null;

		try {
			result = settingService.deleteEvent(pojo);
		} catch (Exception e) {
			LoggerUtil.error("删除赛事失败", e);
			result = new BaseResponse<DeleteEventResponse>(ResultType.fail);

		}
		return JSON.toJSONString(result);
	}

	@RequestMapping("/withdrawCash")
	@ResponseBody
	public String WithdrawCash(WithdrawCashRequest pojo) {

		BaseResponse<WithdrawCashResponse> result = null;

		try {
			result = settingService.withdrawCash(pojo);
		} catch (Exception e) {
			LoggerUtil.error("提现失败", e);
			result = new BaseResponse<WithdrawCashResponse>(ResultType.fail);

		}
		return JSON.toJSONString(result);
	}

	@RequestMapping("/userSellTicket")
	@ResponseBody
	public String UserSellTicket(UserSellTicketRequest pojo) {

		BaseResponse<UserSellTicketResponse> result = null;

		try {
			result = settingService.userSellTicket(pojo);
		} catch (Exception e) {
			LoggerUtil.error("出售门票失败，请稍后重试", e);
			result = new BaseResponse<UserSellTicketResponse>(ResultType.fail);

		}
		return JSON.toJSONString(result);
	}

	@RequestMapping("/serverCheckTicket")
	@ResponseBody
	public String ServerCheckTicket(ServerCheckTicketRequest pojo) {

		BaseResponse<List<ServerCheckTicketResponse>> result = null;

		try {
			result = settingService.serverCheckTicket(pojo);
		} catch (Exception e) {
			LoggerUtil.error("获取列表失败，请联系客服", e);
			result = new BaseResponse<List<ServerCheckTicketResponse>>(ResultType.fail);

		}
		return JSON.toJSONString(result);
	}

	@RequestMapping("/verifyTickets")
	@ResponseBody
	public String VerifyTickets(VerifyTicketRequest pojo) {

		BaseResponse<Object> result = null;

		try {
			result = settingService.verifyTickets(pojo);
		} catch (Exception e) {
			LoggerUtil.error("审核失败,请稍后再试", e);
			result = new BaseResponse<Object>(ResultType.fail);

		}
		return JSON.toJSONString(result);
	}

	@RequestMapping("/showTicketLeft")
	@ResponseBody
	public String ShowTicketLeft(ShowTicketLeftRequest pojo) {

		BaseResponse<List<ShowTicketLeftResponse>> result = null;

		try {
			result = settingService.showTicketLeft(pojo);
		} catch (Exception e) {
			LoggerUtil.error("剩余票列表请求失败，请稍后尝试", e);
			result = new BaseResponse<List<ShowTicketLeftResponse>>(ResultType.fail);

		}
		return JSON.toJSONString(result);
	}

	@RequestMapping("/buyTicket")
	@ResponseBody
	public String BuyTicket(BuyTicketRequest pojo) {

		BaseResponse<BuyTicketResponse> result = null;

		try {
			result = settingService.buyTicket(pojo);
		} catch (Exception e) {
			LoggerUtil.error("剩余票列表请求失败，请稍后尝试", e);
			result = new BaseResponse<BuyTicketResponse>(ResultType.fail);

		}
		return JSON.toJSONString(result);
	}

	@RequestMapping("/verifyBuyTicket")
	@ResponseBody
	public String VerifyBuyTicket(VerifyBuyTicketRequest pojo) {

		BaseResponse<Object> result = null;

		try {
			result = settingService.verifyBuyTicket(pojo);
		} catch (Exception e) {
			LoggerUtil.error("剩余票列表请求失败，请稍后尝试", e);
			result = new BaseResponse<Object>(ResultType.fail);

		}
		return JSON.toJSONString(result);
	}
	
	@RequestMapping("/buyCredits")
	@ResponseBody
	public String BuyCredits(BuyCreditsRequest pojo) {

		BaseResponse<BuyCreditsResponse> result = null;

		try {
			result = settingService.buyCredits(pojo);
		} catch (Exception e) {
			LoggerUtil.error("提交购买积分订单失败", e);
			result = new BaseResponse<BuyCreditsResponse>(ResultType.fail);

		}
		return JSON.toJSONString(result);
	}
	
	@RequestMapping("/verifyBuyCredits")
	@ResponseBody
	public String VerifyBuyCredits(VerifyBuyCreditsRequest pojo) {

		BaseResponse<Object> result = null;

		try {
			result = settingService.verifyBuyCredits(pojo);
		} catch (Exception e) {
			LoggerUtil.error("微信回调购买积分失败", e);
			result = new BaseResponse<Object>(ResultType.fail);

		}
		return JSON.toJSONString(result);
	}
	
	
	
}
