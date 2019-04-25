# Getting Started

## 启动准备
1. 分别先后启动
`springcloud/eureka-all/eureka`,
`springcloud/feign/feign-client`,
`springcloud/feign/feign-server`,
`springcloud/hystrix/hystrix-client`

2. 浏览器可以输入`localhost:8761`查看注册情况，正常则调用
`http://localhost:7020/hi/tom`，最后的`tom`参数随便换

3. 正常返回`call result: hello tom !,feign port:7010 header:my-test`
4. 测试关闭服务的情况，关闭`feign-server`，返回
`hi, tom,sorry,error!`说明断路器熔断成功，关闭`feign-client`也是同样的效果
5. 至于为什么启用两个feign服务，为了测试`feign-client`中拦截器`MyInterceptor`
是否起作用