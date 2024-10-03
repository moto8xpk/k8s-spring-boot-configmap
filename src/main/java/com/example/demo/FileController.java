package com.example.demo;

import io.fabric8.kubernetes.api.model.ConfigMap;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.dsl.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RefreshScope
@RestController
public class FileController {

    @Autowired
    private KubernetesClient kubernetesClient;

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${greeting.message}")
    private String welcomeMsg;

    @GetMapping("/hello")
    public String index() {
        return welcomeMsg;
    }

    @PutMapping("/update")
    public void updateConfigMap(@RequestParam String key, @RequestParam String value) {
        try {
            // Tên ConfigMap và Namespace của ứng dụng
            String configMapName = "my-app-env-config";
            String namespace = "default";

            log.info("Updating config map " + configMapName + " with key " + key + " and value " + value);
            // Lấy ConfigMap hiện tại
            Resource<ConfigMap> configMapResource = kubernetesClient.configMaps().inNamespace(namespace).withName(configMapName);
            log.info("Updating configMap " + configMapName + " with value " + value);
            ConfigMap configMap = configMapResource.get();
            log.info("Updating configMap " + configMap);

            // Cập nhật giá trị key mới
            if (configMap != null) {
                configMap.getData().put(key, value);
                configMapResource.createOrReplace(configMap);
                log.info("Updating configMap " + configMap);
//                ((ConfigurableApplicationContext) applicationContext).close();
                int exitCode = SpringApplication.exit(applicationContext, (ExitCodeGenerator) () -> 0);
                System.exit(exitCode);
            }
        } catch (KubernetesClientException e) {
            e.printStackTrace();
        }
    }
}
