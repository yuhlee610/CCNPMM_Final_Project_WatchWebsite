package com.ccnpmm.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;

public class PaypalConfig {

	private String CLIENT_ID = "AWGrUtMXpzPBPa2PZOnmE4obgbnJHOPArQYzchPDtOr5zWOHQDRr-YM4qB73BHawXhb23f0To5oGLDRx";
	private String CLIENT_SECRET = "EPkXluGafYqJh6YEIiALKKgnCAsicy46PAjFQi3VTp4c4QysCSBykRfaLxy5S3XwQza57SXlqtFn7P5c";
	private String MODE = "sandbox";
	
	@Bean
	public Map<String, String> paypalSdkConfig(){
		Map<String, String> sdkConfig = new HashMap();
		sdkConfig.put("mode", MODE);
		return sdkConfig;
	}
	
	@Bean
	public OAuthTokenCredential authTokenCredential() {
		return new OAuthTokenCredential(CLIENT_ID, CLIENT_SECRET, paypalSdkConfig());
	}
	
	@Bean
	public APIContext apiContext() throws PayPalRESTException{
		APIContext apiContext = new APIContext(authTokenCredential().getAccessToken());
		apiContext.setConfigurationMap(paypalSdkConfig());
		return apiContext();
	}
}
