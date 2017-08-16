package com.spirit.porker.util;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.spirit.porker.constant.Constant;

/**
 * Aes encryption
 */
public class AES {

	private static SecretKeySpec secretKey;
	private static byte[] key;

	private static String decryptedString;
	private static String encryptedString;
	
	static{
		setKey(Constant.AESKey);
	}

	public static void setKey(String myKey) {
		MessageDigest sha = null;
		try {
			key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16); // use only first 128 bit
			secretKey = new SecretKeySpec(key, "AES");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getDecryptedString() {
		return decryptedString;
	}

	public static void setDecryptedString(String decryptedString) {
		AES.decryptedString = decryptedString;
	}

	public static String getEncryptedString() {
		return encryptedString;
	}

	public static void setEncryptedString(String encryptedString) {
		AES.encryptedString = encryptedString;
	}

	public static String encrypt(String strToEncrypt) {
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			setEncryptedString(Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes("UTF-8"))));
			return getEncryptedString();
		} catch (Exception e) {
			System.out.println("Error while encrypting: " + e.toString());
		}
		return null;
	}
	
	public static String decrypt(String strToDecrypt) {
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			setDecryptedString(new String(cipher.doFinal(Base64.decodeBase64(strToDecrypt))));
			return getDecryptedString();
		} catch (Exception e) {
			System.out.println("Error while decrypting: " + e.toString());
		}
		return null;
	}
	
	public static String decrypt(InputStream strToDecrypt) {
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			setDecryptedString(new String(cipher.doFinal(Base64.decodeBase64(strToDecrypt.toString()))));
			return getDecryptedString();
		} catch (Exception e) {
			System.out.println("Error while decrypting: " + e.toString());
		}
		return null;
	}


	public static void main(String args[]) {
		String str2 = "XB1WGVOuIyUUmgNjRlqiGFy9V5TE5rUJoSU6d0tiOJbk6VaNQMh4dEr5/PREYiOetcuQhPdh0+r2XQyCYSFrKke2pg9SF/kZmJIKsnBCokoEaO4rM0mwhlDEkJuDi1yOSnkIVTiZOFi/vXRzkL7sUg==";
		String str = "{\"sessionToken\":\"A5B17E2789D3AE9378FB11A9779F4159\",\"openId\":\"164B727F483F3886B223E3FECB519803\",\"taskInfo\":\"1\"}";
		
//		String str3 = "{\"openId\":\"EA06D4785285E4C50FD5E587E6B08E86\",\"paginationInfo\":{\"currentPage\":2,\"recordPerPage\":10,\"totalPage\":1,\"totalRecord\":5},\"sessionToken\":\"89549D7612ECEC8C7F4D12D6CEA00B7F\",\"type\":1}";
		String str3 = "{\"imei\":\"99000715061069\",\"invitationCode\":\"11934229\"}";
//		String str3 = "{\"imei\":\"99000715061069\"}";
		
		String withdrawListForAdmin="{\"openId\":\"EA06D4785285E4C50FD5E587E6B08E86\",\"paginationInfo\":{\"currentPage\":1,\"recordPerPage\":10,\"totalPage\":2,\"totalRecord\":11},\"sessionToken\":\"89549D7612ECEC8C7F4D12D6CEA00B7F\",\"type\":1}";
		
//		String withdrawListForAdmin2="dUs28mWjZkHbxw+vL4t04jHsiOQJhHAlXafEfRDx9TpPeoIyvSsEslvTUjJMstkEp/hvJl7wAdiMKyIowFq2uw==";
		String withdrawListForAdmin2="dUs28mWjZkHbxw+vL4t04v9bffTHpeVAauFjypkLBik=";
//		String str5="{\"openId\":\"EA06D4785285E4C50FD5E587E6B08E86\",\"sessionToken\":\"89549D7612ECEC8C7F4D12D6CEA00B7F\",\"type\":0}";
		
//		String withdrawForUser="{\"openId\":\"EA06D4785285E4C50FD5E587E6B08E86\",\"sessionToken\":\"89549D7612ECEC8C7F4D12D6CEA00B7F\",\"operType\":1,\"amount\":0,\"realName\":\"测试\",\"number\":\"18515102492\",\"id\":51}";
		System.out.println(AES.encrypt(str3));
		System.out.println(AES.decrypt(withdrawListForAdmin2));
		
	}
	
}