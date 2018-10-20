package com.xxl.rpc.sample.server.conf;

import com.xxl.rpc.registry.ServiceRegistry;
import com.xxl.rpc.remoting.provider.impl.XxlRpcSpringProviderFactory;
import com.xxl.rpc.util.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * xxl-rpc provider config
 *
 * @author xuxueli 2018-10-19
 */
@Configuration
public class XxlRpcProviderConfig {
    private Logger logger = LoggerFactory.getLogger(XxlRpcProviderConfig.class);

    @Value("${xxl-rpc.remoting.port}")
    private int port;

    @Value("${xxl-rpc.registry.zk.zkaddress}")
    private String zkaddress;

    @Value("${xxl-rpc.registry.zk.zkdigest}")
    private String zkdigest;

    @Value("${xxl-rpc.env}")
    private String env;

    @Bean
    public XxlRpcSpringProviderFactory xxlRpcSpringProviderFactory() {

        logger.info(">>>>>>>>>>> xxl-rpc provider config init.");
        XxlRpcSpringProviderFactory providerFactory = new XxlRpcSpringProviderFactory();
        providerFactory.setPort(port);
        if (zkaddress != null) {
            providerFactory.setServiceRegistry(ServiceRegistry.ServiceRegistryEnum.ZK.name());
            providerFactory.setServiceRegistryParam(new HashMap<String, String>(){{
                put(Environment.ZK_ADDRESS, zkaddress);
                put(Environment.ZK_DIGEST, zkdigest);
                put(Environment.ENV, env);
            }});
        }

        return providerFactory;
    }

}