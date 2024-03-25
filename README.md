
# OTP-Verification-Phone-Number Through Twilio


## application.Properties
```bash
server.port=9189
twilio.AccountSID= .......(update your AccountSID)
twilio.AuthToken=..........(update your AuthToken)
twilio.phoneNumber=........(update your phoneNumber)
```
## Twilio Dependecies



```bash
 <!-- https://mvnrepository.com/artifact/com.twilio.sdk/twilio -->
		<dependency>
			<groupId>com.twilio.sdk</groupId>
			<artifactId>twilio</artifactId>
			<version>10.0.0</version>
		</dependency>
```


## API Reference

#### OTP SEND

```http
 POST
  localhost:9189/api/v1/send-otp

{
  "username": "smith",
  "phoneNumber": "Twilio Register Number"
}

Response
{
    "status": "DELIVERED",
    "message": "Dear Customer , Your OTP is  ###### for sending sms through Spring boot application. Thank You."
}
```



#### validate Otp

```http
 POST
  localhost:9189/api/v1/validate-otp

   {
  "username": "smith",
  "otpNumber": " ####"
}


Response

OTP is valid!
```


#### Wrong Number Provide

```http
 POST
  localhost:9189/api/v1/send-otp

{
  "username": "smith",
  "phoneNumber": "+XXXXXXXXXX (Twilio Not Register Number)"
}

Response
{
    {
    "status": "FAILED",
    "message": "Invalid 'To' Phone Number: +XXXXXXXXXX"
}
}
```



#### Invalidate Otp

```http
 POST
  localhost:9189/api/v1/validate-otp

   {
  "username": "smith",
  "otpNumber": " ####"
}


Response

OTP is invalid!
```





