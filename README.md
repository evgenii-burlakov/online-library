Online library application

Stack:

Spring Cloud (Config server, Eureka, Spring Messaging Gateway, Feign …)
Spring Actuator
Spring Data JPA (Relative DB)
Spring Web
AJAX
Spring Test
Spring Security (Form-based, user to other services is passed in the header)
Service start order:

config-server
service-discovery-server (Eureka)
facade-gateway (Spring Cloud Gateway)
user-microservice, library-microservice, comment-microservice
