package com.spirit.porker.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.spirit.porker.dao.SeqDao;

/**
 * 会员卡号生产方法，目前已知的方法有来自twitter的snowflake，美团点评的leaf。
 * leaf也是在数据库生成唯一id的思想基础上实现的一个高可用的解决方案。
 * 但是对于小工程来说，明显不适用。所以对于jnz来说，会员卡号的生产规则需要满足
 * （1）递增的
 * （2）散列的，同一个用户连续两次生产，id不能只显示增加了1。
 * 所以，jnz给出的方案是数据库生成id作为高位，地位用时间戳，这样保证整体的递增性，同时又能满足散列要求。
 * 
 * @author user
 *
 */
@Service
public class CardNoGenerator {
	
	@Resource
	SeqDao seqDao;
	
	/**
	 * @return
	 */
	public long generate(){
		
		int high = seqDao.querySeqId();
		
		String timemillis = Long.toString(System.currentTimeMillis());
		StringBuilder builder = new StringBuilder();
		builder.append(high);
		builder.append(timemillis.substring(timemillis.length()-3,timemillis.length()));
	
		return Long.parseLong(builder.toString());
	}
	
	/**
	 * 生成邀请码
	 * @return
	 */
	public long generateInviCode(){
		
		int high = seqDao.querySeqId();
		
		String timemillis = Long.toString(System.currentTimeMillis());
		StringBuilder builder = new StringBuilder();
		builder.append(timemillis.substring(timemillis.length()-3,timemillis.length()));
		builder.append(high);
	
		return Long.parseLong(builder.toString());
	}
	
}
