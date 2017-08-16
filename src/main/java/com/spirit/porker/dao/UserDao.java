package com.spirit.porker.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spirit.porker.model.UserModel;

@Repository
public class UserDao extends BaseDao<UserModel>{
	public List<UserModel> selectMttScore(){
		List result = this.getSqlSession().selectList("UserModel.selectMttScore", null);
		return result;
	}
	public List<UserModel> selectMasterScore(){
		List result = this.getSqlSession().selectList("UserModel.selectMasterScore", null);
		return result;
	}
}


