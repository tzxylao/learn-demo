package com.tzxylao.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@EnableAutoConfiguration
public class DubboApplication {

	public static void main(String[] args) {
		SpringApplication.run(DubboApplication.class, args);
		/*ServiceConfig<GreetingService> serviceConfig = new ServiceConfig<>();
		serviceConfig.setApplication(new ApplicationConfig("first-dubbo-provider"));
		serviceConfig.setRegistry(new RegistryConfig("zookeeper://i.tzxylao.com:2181"));
		serviceConfig.setInterface(GreetingService.class);
		serviceConfig.setRef(new GreetingServiceImpl());
        ProviderConfig providerConfig = new ProviderConfig();
        ProtocolConfig protocolConfig = new ProtocolConfig("dubbo", 20883);
        providerConfig.setProtocol(protocolConfig);
        serviceConfig.setProvider(providerConfig);
		serviceConfig.export();*/
	}

}
