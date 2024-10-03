package com.example.demo;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KubernetesClientConfig {

    @Bean
    @ConditionalOnMissingBean(KubernetesClient.class)
    public KubernetesClient kubernetesClient() {
        // Sử dụng Config để tạo kết nối tới Kubernetes cluster
//        Config config = Config.autoConfigure(null);
        return new DefaultKubernetesClient();
    }
}

