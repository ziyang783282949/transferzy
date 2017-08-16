package com.spirit.porker.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.spirit.porker.dao.UserDao;
import com.spirit.porker.dao.pagination.PaginationList;
import com.spirit.porker.enums.ResultType;
import com.spirit.porker.model.UserModel;
import com.spirit.porker.vo.request.ScoreHisRequest;
import com.spirit.porker.vo.request.ScoreRankingRequest;
import com.spirit.porker.vo.response.BaseResponse;
import com.spirit.porker.vo.response.ScoreHisResponse;
import com.spirit.porker.vo.response.ScoreRankingResponse;
import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

/**
 * @Description:积分相关服务
 * @author: tony.wang
 * @time:2017年8月1日 下午3:44:03
 */
@Service
public class ScoreService {

	@Resource
	UserDao userDao;

	/**
	 * 获取用户的积分历史
	 * 
	 * @param pojo
	 * @param request
	 * @param response
	 * @return
	 */
	public BaseResponse<ScoreHisResponse> getScoreHis(ScoreHisRequest pojo, HttpServletRequest request,
			HttpServletResponse response) {

		BaseResponse<ScoreHisRequest> result = new BaseResponse<ScoreHisRequest>(ResultType.succes);
		ScoreHisRequest data = new ScoreHisRequest();
		result.setData(data);

		return null;
	}

	/**
	 * 根据提交type获取用户积分
	 * 1代表mtt积分
	 * 2代表master积分
	 * 
	 * @param pojo
	 * @param request
	 * @param response
	 * @return
	 */
	public BaseResponse<List<ScoreRankingResponse>> scoreRanking(ScoreRankingRequest pojo, HttpServletRequest request,
			HttpServletResponse response) {
		if (pojo.getType() == 1) {
			BaseResponse<List<ScoreRankingResponse>> result = new BaseResponse<>(ResultType.succes);
			List<ScoreRankingResponse> data = new ArrayList<>();
			result.setData(data);
			List<UserModel> lists = new ArrayList<>();
			lists = userDao.selectMttScore();
			if (lists == null || lists.size() == 0) {
				return result;
			}
			for (int i = 0; i < lists.size(); i++) {
				ScoreRankingResponse scoreRankingResponse = new ScoreRankingResponse();
				scoreRankingResponse.setId(i + 1);
				scoreRankingResponse.setNickName(lists.get(i).getNickName());
				scoreRankingResponse.setPoint(lists.get(i).getCurMttScore());
				data.add(scoreRankingResponse);
			}

			return result;
		}
		if (pojo.getType() == 2) {
			BaseResponse<List<ScoreRankingResponse>> result = new BaseResponse<>(ResultType.succes);
			List<ScoreRankingResponse> data = new ArrayList<>();
			result.setData(data);
			List<UserModel> lists = new ArrayList<>();
			lists = userDao.selectMasterScore();
			if (lists == null || lists.size() == 0) {
				return result;
			}
			for (int i = 0; i < lists.size(); i++) {
				ScoreRankingResponse scoreRankingResponse = new ScoreRankingResponse();
				scoreRankingResponse.setId(i + 1);
				scoreRankingResponse.setNickName(lists.get(i).getNickName());
				scoreRankingResponse.setPoint(lists.get(i).getCurMasterScore());
				data.add(scoreRankingResponse);
			}

			return result;
		}
		return null;
	}

	

}
