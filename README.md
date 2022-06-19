Online library application

Stack:
- Spring Cloud (Config server, Eureka, Spring Messaging Gateway, Feign …)
- Spring Actuator
- Spring Data JPA (Relative DB)
- Spring Web
- AJAX
- Spring Test
- Spring Security (Form-based, user to other services is passed in the header)

Service start order:
1) config-server
2) service-discovery-server (Eureka)
3) facade-gateway (Spring Cloud Gateway)
4) user-microservice, library-microservice, comment-microservice
