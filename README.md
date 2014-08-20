Spring-security-POC
----------------------------
A POC on spring-security which features 
Authentication and authorization. 
Used tiles as view (simple implementation). 
JUnit for Unit/Integration testing.

Features:

* HSQL Database Layer
* Allowed session concurrency = 1 (invalidate 1st session)
* Authorization using spring-security taglib
* Tiles as view layer
* Twitter bootstrap/Jquery in front end


---------------------------
Deployment guide:

clone via git. build/deploy using maven

>mvn tomcat:run

Users:

u:admin p:admin roles: ROLE_USER, ROLE_ADMIN 
user:user roles: ROLE_USER

---------------------------

Screenshots:

> mvn tomcat:run

![Maven Screenshot](https://raw.github.com/ultragaylord/spring-security-POC/new/tiles/screenshot/mvntomcatrun.png)

---------------------------
Login Page
![Maven Screenshot](https://raw.github.com/ultragaylord/spring-security-POC/new/gui/screenshot/loginpage.png)

---------------------------
Common Page - Admin user is currently logged. Admin Menu and User Menu available.
![Maven Screenshot](https://raw.github.com/ultragaylord/spring-security-POC/new/gui/screenshot/commonpage.png)

---------------------------
Admin Page - Only admin can access
![Maven Screenshot](https://raw.github.com/ultragaylord/spring-security-POC/new/gui/screenshot/userpage.png)

---------------------------
Denied Page - Using user account and attempt to access secured page.
![Maven Screenshot](https://raw.github.com/ultragaylord/spring-security-POC/new/gui/screenshot/denied.png)

---------------------------
404 Page - Replace Tomcat generic 404 page with custom page
![Maven Screenshot](https://raw.github.com/ultragaylord/spring-security-POC/new/gui/screenshot/404.png)



