package cc.landking.web.sms_dayu;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cc.landking.web.core.service.ISmsService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class SmsAliDayuService implements ISmsService {
	private static Log log = LogFactory.getLog(SmsAliDayuService.class);
	
	private String url = "http://gw.api.taobao.com/router/rest";
	
	private String appKey;
	
	private String appSecret;
	
	
	
	public void setUrl(String url) {
		this.url = url;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	@Override
	public void sendRegisterCode(String code, String phone) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("smsType", "normal");
		params.put("smsFreeSignName", "注册验证");
		params.put("smsTemplateCode", "SMS_16756088");
		params.put("code", code);
		params.put("product", "捷兔");
		sendSms(null,phone,params);


	}

	@Override
	public void sendResetPasswordCode(String code, String phone) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("smsType", "normal");
		params.put("smsFreeSignName", "变更验证");
		params.put("smsTemplateCode", "SMS_16756086");
		params.put("code", code);
		params.put("product", "捷兔");
		sendSms(null,phone,params);


	}

	@Override
	public void sendSms(String key, String phone, Map<String, Object> params) {
		try {
			
			TaobaoClient client = new DefaultTaobaoClient(url, appKey,appSecret);
			AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
			req.setSmsType((String)params.get("smsType"));
			req.setSmsFreeSignName((String)params.get("smsFreeSignName"));
			ObjectMapper mapper = new ObjectMapper();
			req.setSmsParamString(mapper.writeValueAsString(params));
			req.setRecNum(phone);
			req.setSmsTemplateCode((String)params.get("smsTemplateCode"));
			AlibabaAliqinFcSmsNumSendResponse rsp;
		
			rsp = client.execute(req);
			log.debug("call " + url + " param:" + req.toString() + " response:"+rsp.getBody());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
