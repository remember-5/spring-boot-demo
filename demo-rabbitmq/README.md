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
    host: 116.236.68.213
    port: 5672
    username: test # RabbitMQ 服务的账号
    password: i0EVRVko6B7A # RabbitMQ 服务的密码
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





