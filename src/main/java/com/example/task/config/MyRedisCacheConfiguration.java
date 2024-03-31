package com.example.task.config;


import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurationSelector;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
@EnableCaching
public class MyRedisCacheConfiguration extends CachingConfigurationSelector {

    private final CacheConfigurationProperties cacheConfigurationProperties;

    public MyRedisCacheConfiguration(@Lazy CacheConfigurationProperties cacheConfigurationProperties) {
        this.cacheConfigurationProperties = cacheConfigurationProperties;
    }

    private RedisCacheConfiguration createCacheConfiguration(long timeoutInSeconds) {
        return org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(timeoutInSeconds));
    }

    @Bean
    public CacheManager cacheManager(LettuceConnectionFactory redisConnectionFactory) {
        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
        if (Objects.nonNull(cacheConfigurationProperties.getCaches())) {
            for (Map.Entry<String, String> cacheNameAndTimeout : cacheConfigurationProperties.getCaches().entrySet()) {
                cacheConfigurations.put(cacheNameAndTimeout.getKey(), createCacheConfiguration(Long.parseLong(cacheNameAndTimeout.getValue())));
            }
        }
        return RedisCacheManager
                .builder(redisConnectionFactory)
                .cacheDefaults(createCacheConfiguration(60000))
                .withInitialCacheConfigurations(cacheConfigurations).build();
    }

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(cacheConfigurationProperties.getHost());
        redisStandaloneConfiguration.setPort(Integer.parseInt(cacheConfigurationProperties.getPort()));
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }


}
