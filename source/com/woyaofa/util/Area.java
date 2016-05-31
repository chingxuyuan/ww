
package com.woyaofa.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;

import org.nutz.json.Json;

import sun.net.www.protocol.http.HttpURLConnection;

public class Area {

	public static String getAreaFromIP (String ip) {

		final String API_KEY = "a2050db8e5efe171b5954cd3b4527a01";
		final String API_URL = "http://apis.baidu.com/apistore/iplookupservice/iplookup?ip=";

		try {
			URL url = new URL(API_URL + ip);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("apikey", API_KEY);
			connection.connect();

			InputStream is = connection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

			StringBuffer sb = new StringBuffer();
			String s = null;

			while ((s = reader.readLine()) != null) {
				sb.append(s);
				sb.append("\r\n");
			}

			reader.close();
			s = sb.toString();

			Map<String, Object> result = Json.fromJsonAsMap(Object.class, s);
			int err = (int) result.get("errNum");

			if (err != 0) {
				return null;
			}

			@SuppressWarnings("unchecked")
			Map<String, Object> retData = (Map<String, Object>) result.get("retData");

			if (retData == null) {
				return null;
			}

			String city = (String) retData.get("city");
			return city;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main (String[] args) {

		String area = getAreaFromIP("211.138.104.28");
		System.out.println(area);
	}
}
