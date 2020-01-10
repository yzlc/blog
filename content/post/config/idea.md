---
title: "idea"
date: 2019-08-31T10:32:33+08:00
tags: [idea]
categories: [配置]
---

## 设置同步
1. File - Settings Repository
2. Upstream URL:  
`https://github.com/yzlc/jetbrainsSettings.git`
3. Overwrite Local

## Help
- Edit Custom VM Options...
```properties
-Dfile.encoding=UTF-8
-Dsun.jnu.encoding=UTF-8
```
## Settings
### Editor
- File and Code Templates
   - Files-->Class
      ```java
      /**
       * @author ${USER}
       */
      ```
- File Types
   - ignore files and folders  
添加`*.idea;*.iml;`

#### General
- Auto Import
   - [x] Add unambiguous imports on the fly
   - [x] Optimize imports on the fly
- Code Completion 
   - [ ] Match case
- Editor Tabs
   - [ ] Show tabs in one row  
   Tab limit `30`
### intall Plugins
```
FindBugs
```

### disable Plugins
```
.env files support
Copyright
Coverage
CVS Integration
IntelliLang
Java EE:Batch Applications
Java EE:Bean Validation Support
Java EE:Contexts and Dependency Injection
Java EE:Java Server Faces
Java EE:JMS,JSON Processing,Concurrency,Transaction
Java EE:RESTful Web Services(JAX-RS)
Java EE:RESTful Web Services(JAX-WS)
Java EE:WebSockets
Perforce Integration
Settings Repository
Spring Batch
Spring Data
Spring Integration Patterns
Spring OSGi
Spring Web Flow
Spring WebSocket
Tapestry support
Task Management
UI Designer
YAML support
```