package com.tzxylao.dubbo;

import com.tzxylao.dubbo.service.GreetingService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

@EnableAutoConfiguration
public class DubboConsumerApplication {

	@Reference(version = "1.0.0")
	private GreetingService greetingService;

	public static void main(String[] args) throws InterruptedException {
		new SpringApplicationBuilder(DubboConsumerApplication.class).web(WebApplicationType.NONE).run(args);
		/*ReferenceConfig<GreetingService> ref erenceConfig = new ReferenceConfig<>();
		referenceConfig.setApplication(new ApplicationConfig("first-dubbo-consumer"));
		referenceConfig.setRegistry(new RegistryConfig("zookeeper://i.tzxylao.com:2181"));
		referenceConfig.setInterface(GreetingService.class);
		while (true) {
			referenceConfig.checkAndUpdateSubConfigs();
			GreetingService greetingService = referenceConfig.get();
			System.out.println(greetingService.sayHello("world"));
			Thread.sleep(2000);
		}*/
	}

	@Bean
	public void runner(){
		while (true) {
			System.out.println(greetingService.sayHello("World "));
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
