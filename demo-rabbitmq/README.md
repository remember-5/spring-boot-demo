## 功能分布
rabbitmq-demo1 (demo1=DIRECT,demo2=TOPIC,demo3=FANOUT,demo4=HEADERS)  
rabbitmq-demo2 (demo5=batchSend，这个是批量发送，消费者一条一条消费)  
rabbitmq-demo3 (demo6A，这个是定时没有消息的话，才发送，消费者批量消费)   
rabbitmq-demo4 (demo6B，这个是优化版本，如果条目数量超过多少，也会发送，消费者批量消费)    
rabbitmq-demo5 (demo7，消费重试,死信队列)    
rabbitmq-demo6 (demo8，定时消息)    






## 功能清单
- [x] 发送普通文本消息
- [ ] 发送json消息
- [ ] 序列对象发送
- [ ] 自动ack
- [ ] 手动ack
- [ ] 死信队列
- [ ] 延迟消费

## 发送普通文本消息

最基本的发送，不考虑其他因素

修改配置
```yaml
rabbitmq:
    host: 
    port: 
    username:  # RabbitMQ 服务的账号
    password:  # RabbitMQ 服务的密码
    virtual-host: test
```

### direct模式

查看`Demo1Consumer` `Demo1Provider` `Send1Test`

### topic模式
[分发图](./doc/topic-img.png)
routingKey="quick.orange.rabbit" 的消息会同时路由到 Q1 与 Q2 。  
routingKey="lazy.orange.fox" 的消息会路由到 Q1 。  
routingKey="lazy.brown.fox" 的消息会路由到 Q2 。  
routingKey="lazy.pink.rabbit" 的消息会路由到Q2（只会投递给 Q2 一次，虽然这个 routingKey 与 Q2 的两个 bindingKey 都匹配）。  
routingKey="quick.brown.fox"、routingKey="orange"、routingKey="quick.orange.male.rabbit" 的消息将会被丢弃，因为它们没有匹配任何 bindingKey 。  

## 配置介绍



## 其他





