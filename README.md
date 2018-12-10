# rabbitmq_websocket 
当前为整合layIM版  只是初步整合了一些页面 许多功能还并未实现。可以实现基本聊天群聊功能
SpringBoot2.0 整合 RabbitMQ 消息队列示例  
Spring WebSocket 使用 RabbitMQ 作为消息代理 启用 Stomp 完成简单的聊天功能。  
用户登录使用了spring security及session机制  
### 本地运行该项目需注意 执行根目录下mysql的sql脚本，确保redis及rabbitmq正常运行。
## 用户账号配置在数据库中：  
admin , anne , hehe , vip  四个用户  密码统一为：123456  
目前暂未实现注册，添加好友，群功能，若想添加，请自行在数据库中添加或在代码中自行实现。  
本项目仅供参考学习，需要有spring boot及spring security的部分基础，并非给小白装逼使用，见谅！
### 项目截图如下：