package per.even.HouseObserver.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import org.springframework.util.StringUtils;

/**
 * 
 * 发送http请求
 *
 */
public class HttpRequest {
	/**
	 * 发送Get请求
	 * @param url
	 * @param param
	 * @return
	 */
	public static String sendGet(String url, String param) {
		String urlString = url;
		if(!StringUtils.isEmpty(param)) {
			urlString = urlString + "?" + param;
		}
		Scanner in = null;
		StringBuilder result = new StringBuilder();
		try {
			URL urlObj = new URL(urlString);
			URLConnection conn = urlObj.openConnection();
			conn.connect();
			in = new Scanner(conn.getInputStream());
			result = new StringBuilder();
			while(in.hasNextLine()) {
				result.append(in.nextLine());
			}
		} catch(Exception e) {
			System.out.println("发送Get请求时发生异常！(URL:" + urlString + ")");
			e.printStackTrace();
		} finally {
			if(in != null) {
				in.close();
			}
		}
		return result.toString(); 
	}
	
	/**
	 * 发送post请求
	 * @param url
	 * @param param
	 * @return
	 */
	public static String sendPost(String url, String param) {
		String urlString = url;
		if(!StringUtils.isEmpty(param)) {
			urlString = urlString + "?" + param;
		}
		Scanner in = null;
		OutputStream out = null;
		StringBuilder result = new StringBuilder();
		try {
			URL urlObj = new URL(urlString);
			URLConnection conn = (HttpURLConnection)urlObj.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.connect();
			out = conn.getOutputStream();
			in = new Scanner(conn.getInputStream());
			result = new StringBuilder();
			while(in.hasNextLine()) {
				result.append(in.nextLine());
			}
		} catch(Exception e) {
			System.out.println("发送Post请求时发生异常！(URL:" + urlString + ")");
			e.printStackTrace();
		} finally {
			if(in != null) {
				in.close();
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result.toString(); 
	}
}
