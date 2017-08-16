package com.spirit.porker.util;

public class SwitchUtil {
	
	/** 
	* @Title: isNewUserCharge 
	* @Description: TODO(新用户送奖励开关) 
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	public static boolean isNewUserCharge(){
		String newUserChargeSwitch = PropertyUtil.config.getProperties("new_user_free_charge_switch");
		if(newUserChargeSwitch != null && newUserChargeSwitch.equals("ON")){
			return true;
		}
		return false;
	}

}
