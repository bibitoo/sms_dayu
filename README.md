use:
applicationContext-shiro.xml
<bean id="smsService" class="cc.landking.web.sms_dayu.SmsAliDayuService">
	<property name="url" value="${sms.dayu.url}"/>
		<property name="appKey" value="${sms.dayu.key}"/>
		<property name="appSecret" value="${sms.dayu.secret}"/>
	</bean>
	
	java:
	@Autowired
	private ISmsService smsService;
	...
	
			   smsService.sendResetPasswordCode(captcha, phone);
		  
			   smsService.sendRegisterCode(captcha, phone);
		   