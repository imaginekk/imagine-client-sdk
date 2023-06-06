package com.imagine.imagineclientsdk;

import com.imagine.imagineclientsdk.client.ApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author imagine
 * @date 2023/5/17/0017 - 9:37
 */
@Configuration
@ConfigurationProperties("imagine.client")
@Data
@ComponentScan
public class imagineApiClientConfig {

    private String accessKey;
    private String secretKey;

    @Bean
    public ApiClient ApiClient() {
        return new ApiClient(accessKey, secretKey);
    }



}
