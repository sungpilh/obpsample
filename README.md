# OAuth 1.0a 샘플 클라이언트

Spring boot Application 으로 구현되어 있습니다.

maven을 필요로 합니다.

[실행 명령] 콘솔에서

mvn spring-boot:run

- Signature 생성시 사용하는 API Method(POST, GET, PUT, DELETE)도 맞춰주어야 함
- PUT, POST 요청시 content-type은 반드시 application/json으로 지정


[참고]

https://github.com/OpenBankProject/OBP-API/wiki/OAuth-1.0-Server

https://github.com/OpenBankProject/OBP-API/wiki/OAuth-Client-SDKS

http://commandlinefanatic.com/cgi-bin/showarticle.cgi?article=art014

https://oauth.net/core/1.0a/


2016/11/21

- API 클래스의 필드 중 오타를 수정하였습니다.

Customer 클래스의 mobile_hone_number -> mobile_phone_number

Image 클래스의 URL -> url

- OauthController 에 Customer 생성 샘플이 추가되었습니다.



