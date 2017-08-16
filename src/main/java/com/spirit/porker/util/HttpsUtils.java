package com.spirit.porker.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.mail.internet.ContentType;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.springframework.web.servlet.mvc.condition.ParamsRequestCondition;

import com.alibaba.fastjson.JSON;

/**
 * 关于客户端处理证书的问题
 * 
 * 两种方式：人工加入方式和X509自动方式
 * 一、人工加入方式
 * 
 * Java提供了命令行工具keytool用于创建证书或者把证书从其它文件中导入到Java自己的TrustStore文件中。把证书从其它文件导入到TrustStore文件中的命令行格式为：

　　keytool -import -file src_cer_file –keystore dest_cer_store

　　其中，src_cer_file为存有证书信息的源文件名，dest_cer_store为目标TrustStore文件。
	
	（1）首先要取得源证书文件，这个源文件可使用IE浏览器获得，IE浏览器会把访问过的HTTPS站点的证书保存到本地。
	（2）将获取到的文件复制到jre安装路径下的lib/security/。
	（3）最后就可利用keytool把该证书导入到Java的TrustStore文件中
 * 
 * 优点：编码简单
 * 缺点：证书变化时，需要人工处理，同时多点服务器时会遇到安装证书问题，比较麻烦。
 * 
 * 二、X509方式，这种方式可以让客户端在连接时指定证书，也可以不做任何认证过程（即认为对方服务是认证过的）
 * HttpsURLConnection通过SSLSocket来建立与HTTPS的安全连接，SSLSocket对象是由SSLSocketFactory生成的。
 * HttpsURLConnection提供了方法setSSLSocketFactory(SSLSocketFactory)设置它使用的SSLSocketFactory对象。
 * SSLSocketFactory通过SSLContext对象来获得，在初始化SSLContext对象时，可指定信任管理器对象。
 * 因此，这里实现自己的新人管理器：MyX509TrustManager
 * 
 * 本方法中使用第二种
 * @author user
 *
 */
public class HttpsUtils {
	
	public static int connectionTimeOut = 5000;//单位毫秒
	public static String utf8 = "utf-8";
	
	public static String contentForm = "application/x-www-form-urlencoded";
	public static String contentHtml = "text/html";
	
	/**
	 * @param url
	 * @param params 不支持直接以key=value拼接好的形式
	 * @param timeout
	 * @return
	 */
	public static <T> T get(String url,Object param,int timeout,Class<T> responseClass){
		
		URL httpsUrl;
		HttpsURLConnection conn = null;
		try {
			httpsUrl = new URL(url+"?"+ParamUtil.genHttpParam(param));
			
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
					new java.security.SecureRandom());
			
			conn = (HttpsURLConnection)httpsUrl.openConnection();

			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			
			//设置是否从httpUrlConnection的outputStram中写入数据
			conn.setDoOutput(true);
			//设置是否从httpUrlConnection的getinputStream中读取
			conn.setDoInput(true);
			conn.connect();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			StringBuilder sBuilder = new StringBuilder();
			String content = "";
			while( (content = bufferedReader.readLine()) != null){
				sBuilder.append(content);
			}
			
			return JSON.parseObject(sBuilder.toString(),responseClass);
			
		}catch(KeyManagementException e){
			e.printStackTrace();
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}catch (Exception e1) {
			e1.printStackTrace();
		}finally {
			if(conn != null){
				conn.disconnect();
			}
		}
		
		return null;
		
	}
	
	/**
	 * @param url
	 * @param params 不支持直接以key=value拼接好的形式
	 * @param timeout
	 * @return
	 */
	public static <T> T get(String url,Object param,String cookie,String contentType,int timeout,Class<T> responseClass){
		
		URL httpsUrl;
		HttpsURLConnection conn = null;
		try {
			httpsUrl = new URL(url+"?"+ParamUtil.genHttpParam(param));
			
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
					new java.security.SecureRandom());
			
			conn = (HttpsURLConnection)httpsUrl.openConnection();

			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", contentType);
			conn.setRequestProperty("Cookie", cookie);
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			
			//设置是否从httpUrlConnection的outputStram中写入数据
			conn.setDoOutput(true);
			//设置是否从httpUrlConnection的getinputStream中读取
			conn.setDoInput(true);
			conn.connect();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			StringBuilder sBuilder = new StringBuilder();
			String content = "";
			while( (content = bufferedReader.readLine()) != null){
				sBuilder.append(content);
			}
			
			return JSON.parseObject(sBuilder.toString(),responseClass);
			
		}catch(KeyManagementException e){
			e.printStackTrace();
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}catch (Exception e1) {
			e1.printStackTrace();
		}finally {
			if(conn != null){
				conn.disconnect();
			}
		}
		
		return null;
		
	}
	
	/**
	 * @param url
	 * @param params 要求格式为key=value形式
	 * @param timeout 超时时间，默认为5000
	 * @return
	 */
	public static String post(String url,String params,int timeout){
		
		URL httpsUrl;
		try {
			httpsUrl = new URL(url);
			
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
					new java.security.SecureRandom());
			
			HttpsURLConnection conn = (HttpsURLConnection)httpsUrl.openConnection();
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			
			//设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在,http正文内，因此需要设为true, 默认情况下是false;     
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			conn.setConnectTimeout(timeout==0?connectionTimeOut:timeout);
			OutputStream outputStream = conn.getOutputStream();//这里会隐含调用connect方法
			outputStream.write(params.getBytes(utf8));
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			StringBuilder sBuilder = new StringBuilder();
			String content = "";
			while( (content = bufferedReader.readLine()) != null){
				sBuilder.append(content);
			}
			
			return sBuilder.toString();
			
		}catch(KeyManagementException e){
			e.printStackTrace();
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return null;
		
	}
	
	
}
