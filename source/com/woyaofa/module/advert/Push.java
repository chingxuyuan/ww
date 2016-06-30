package com.woyaofa.module.advert;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;

import com.google.gson.Gson;
import com.woyaofa.data.meta.PushMessage;
import com.woyaofa.exchange.web.Result;

@IocBean
@Fail("http:500")
@Filters
public class Push {

	
	@Inject
	Dao dao = null;

	@At("/advert/push-adevrt")
	@Ok("json")
	public Result pushAdvert(String topic, String b,String content) {
		System.out.println(topic + " " + content);
		return Result.newSuccessResult();
	}
	
	public  void push( PushMessage pushMsg){
          String broker       = "tcp://112.124.127.154:1883";
          String clientId     = "wwlhmqtt";
         Gson gson = new Gson();
         String content=gson.toJson(pushMsg);
          MemoryPersistence persistence = new MemoryPersistence();
          try {
              MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
              MqttConnectOptions connOpts = new MqttConnectOptions();
              connOpts.setCleanSession(true);
              System.out.println("Connecting to broker: "+broker);
              sampleClient.connect(connOpts);
              System.out.println("Connected");
              System.out.println("Publishing message: "+content);
              MqttMessage message = new MqttMessage(content.getBytes());
            //2, 仅仅接收一次
              message.setQos(2);
              sampleClient.publish("wwlh", message);
              System.out.println("Message published");
              sampleClient.disconnect();
              System.out.println("Disconnected");
          } catch(MqttException me) {
              System.out.println("reason "+me.getReasonCode());
              System.out.println("msg "+me.getMessage());
              System.out.println("loc "+me.getLocalizedMessage());
              System.out.println("cause "+me.getCause());
              System.out.println("excep "+me);
              me.printStackTrace();
          }
		
	}
	
	
	

}
