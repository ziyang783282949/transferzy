package com.spirit.porker.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.spirit.porker.util.DateUtil;

@Service
public class OrderIdGenerator {
	
	@Resource
	SeqDao seqDao;
	
	/**
	 * 生成唯一的订单号
	 * @return yyyymmddhhmmss+uniqueId(5位长度)
	 */
	public String getOrderId(){
		
		String prefix = DateUtil.getCurTime();
		int uniqueId = seqDao.querySeqId();
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(prefix).append(uniqueId);
		
		return sBuilder.toString();
	}

}


