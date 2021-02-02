---
title: "spring Security"
date: 2020-08-06T15:01:33+08:00
tags: [spring]
categories: [note]
---

## maven
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

## config
```java
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and().formLogin().loginProcessingUrl("/login").permitAll()
                .and().csrf().disable();
    }
}
```

```properties
spring.security.user.name=admin
spring.security.user.password={bcrypt}$2a$10$io1baOv5SiP2G0DR.Dye.OQjtj1W8Aba31pGecyYdxgoXLkLjdfm.
```