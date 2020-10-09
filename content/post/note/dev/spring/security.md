---
title: "spring Security"
date: 2020-08-06T15:01:33+08:00
tags: [spring]
categories: [笔记]
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

@Configuration
public class AuthenticationSecurity extends GlobalAuthenticationConfigurerAdapter {
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("admin"));
    }
    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("$2a$10$t8FxsToYcBGUDidjA8xHi.lMvRYNFrpNr.Odq2Hz.VarGdzNkFiCS")
                .authorities("auth")
                .and()
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
```