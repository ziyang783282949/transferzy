package com.spirit.porker.util;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * 信任任何的host，如果需要，可以在verify加入自己的host地址。
 * @author user
 *
 */
public class TrustAnyHostnameVerifier implements HostnameVerifier{

	public boolean verify(String hostname, SSLSession session) {
		// TODO Auto-generated method stub
		return true;
	}

}
