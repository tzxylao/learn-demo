package com.tzxylao.dubbo;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

@EnableAutoConfiguration
public class DubboProviderApplication {

	public static void main(String[] args) {
        new SpringApplicationBuilder(DubboProviderApplication.class).web(WebApplicationType.NONE).run(args);
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
