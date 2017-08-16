package com.spirit.porker.service.adminservice;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.spirit.porker.dao.admindao.AdminDao;

@Service
public class AdminService {
	@Resource
	AdminDao adminDao;
	
	
}
