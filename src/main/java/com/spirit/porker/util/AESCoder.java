package com.spirit.porker.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.web.bind.annotation.InitBinder;

public class AESCoder {

	private static byte[] btAESKey = new byte[16];// 128 bits AES
	private static Cipher cObj;
	private static SecretKeySpec sAesKey;

	public static void AESInit() throws Exception {
		sAesKey = new SecretKeySpec("[B@5122152b".getBytes(), "AES");
		cObj = Cipher.getInstance("AES/ECB/NoPadding");// NoPadding PKCS5Padding
	}

	// AES Encrypt content
	public static byte[] AESEncrypt(byte[] btCont) throws Exception {
		cObj.init(Cipher.ENCRYPT_MODE, sAesKey);
		return cObj.doFinal(btCont);
	}

	// AES Decrypt content
	public static byte[] AESDecrypt(byte[] btCont) throws Exception {
		cObj.init(Cipher.DECRYPT_MODE, sAesKey);
		return cObj.doFinal(btCont);
	}
	
	public static void main(String[] args) throws Exception{
		
		AESCoder.AESInit();
	    byte[] resC = AESCoder.AESEncrypt("巨能赚".getBytes());
		System.out.println("AESEnc:"+resC);
//	    byte[] resD = AESCoder.AESDecrypt(resA);
//		System.out.println("AESDec:"+HexToString(resD,resD.length));
		
	}

}