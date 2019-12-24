---
title: "idea配置"
date: 2019-08-20
description: ""
tags: [idea]
---

## Help
- Edit Custom VM Options...
```
-Dfile.encoding=UTF-8
-Dsun.jnu.encoding=UTF-8
-Xverify:none
```
## Settings
### Editor
- File and Code Templates
   - Files-->Class
      ```    
      /**
       - @author ${USER}
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
## maven下载源码才能搜索到
