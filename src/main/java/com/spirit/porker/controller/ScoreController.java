package com.spirit.porker.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spirit.porker.service.ScoreService;
import com.spirit.porker.service.ServiceHandler;
import com.spirit.porker.vo.request.ScoreHisRequest;
import com.spirit.porker.vo.request.ScoreRankingRequest;
import com.spirit.porker.vo.response.BaseResponse;

@Controller
@RequestMapping("auth")
public class ScoreController extends BaseController{
	
	@Resource
	ScoreService scoreService;
	
	@RequestMapping(value = "/scoreHis")
	@ResponseBody
	public String scoreHis( ScoreHisRequest pojo,HttpServletRequest servletRequest,HttpServletResponse servletResponse){
		return super.doMain(pojo, servletRequest, servletResponse, new ServiceHandler<ScoreHisRequest>() {

			@SuppressWarnings("unchecked")
			@Override
			public <T extends BaseResponse> T doService(ScoreHisRequest pojo, HttpServletRequest servletRequest,
					HttpServletResponse servletResponse) throws Exception {
				return (T) scoreService.getScoreHis(pojo, servletRequest, servletResponse);
			}
		});
	}
	
	@RequestMapping(value = "/scoreRanking")
	@ResponseBody
	public String scoreRanking(ScoreRankingRequest pojo,HttpServletRequest servletRequest,HttpServletResponse servletResponse){
		return super.doMain(pojo, servletRequest, servletResponse, new ServiceHandler<ScoreRankingRequest>() {

			@SuppressWarnings("unchecked")
			@Override
			public <T extends BaseResponse> T doService(ScoreRankingRequest pojo, HttpServletRequest servletRequest,
					HttpServletResponse servletResponse) throws Exception {
				return (T) scoreService.scoreRanking(pojo, servletRequest, servletResponse);
			}

		});
	}
	
	
}


