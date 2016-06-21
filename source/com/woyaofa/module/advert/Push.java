package com.woyaofa.module.advert;

import java.io.IOException;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;

import com.woyaofa.exchange.web.Result;

@IocBean
@Fail("http:500")
@Filters
public class Push {

	
	AbstractXMPPConnection conn2 = null;
	@Inject
	Dao dao = null;

	@At("/advert/pushAdvert")
	@Ok("json")
	public Result pushAdvert(String subject, String body, String id) {
		System.out.println(subject + " " + body);
		push();
		return Result.newSuccessResult();
	}

	private void push() {
		
		if(conn2 !=null && conn2.isConnected()){
			conn2.disconnect();
		}
//		XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
//				// .setUsernameAndPassword("username", "password")
//				.setServiceName("cpc").setHost("192.168.1.119").setPort(5222).build();
//
//		conn2 = new XMPPTCPConnection(config);
//		try {
//			conn2.connect();
//		} catch (SmackException | IOException | XMPPException e) {
//			e.printStackTrace();
//			conn2.disconnect();
//		}
	}
}
