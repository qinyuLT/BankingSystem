
##企业级系统架构:Teller+BSB+COREBANK
--------
开发一款小型的银行业务综合系统， 系统可以通过银行工作人员应答客户需求， 实现基本的账户开户、客户开户、 转账、 贷款、 冲正、 日终等操作。 
使用工具和技术： myEclipse、 Tomcat、 Toad for Oracle、 PowerDesigned、 ActiveMQ、 Socket、 JMS 等

##Teller端：
  Teller端用户从IE页面输入消息，通过http与tomcat通信，每一个业务功能对应一个servlet进行处理</br>
  servlet将从IE页面获取到的消息再通过Tcp短连接与ESB端建立socket通信，进行消息传递；</br>
  
##ESB端：
  ESB端每收到一次teller端的socket连接请求，就会开一个线程处理该消息请求，这个工作是由Listener守护线程完成，它负责监听、建立新的连接、开辟新的线程、读、发、收消息请求以及消息的回发（Teller端）。在每一个线程里面处理消息会进行以下步骤：
  - 1.生成消息MessageID（JMS）；
  - 2.读取该ID的消息，由于ESB与Core通过MQ通信，读取通信就会清除该消息；
  - 3.发送请求消息；
  - 4.接收回应消息；
  - 5.验证消息；
  
##COREBANK端：
  Core端的listener线程和ESB端的listener的作用相似，listener每次从MQ中读取到一条消息就会开一个线程处理该消息，并将处理后的消息放回回传MQ队列中。
  
####整个系统对数据库的操作都是采用存储过程，系统的每一端都有不同的数据库系统，各种表以及存储过程完成不同的功能，所有对消息的处理都包含在这些存储过程之中，同时数据库也会记录每一次重要操作的流程。
