package com.mate.starter.database.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.mate.starter.database.annotation.EnableDB;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Kevin on 2022/4/8 16:54
 */
@Slf4j
public class DataSourceRegistrar implements ImportBeanDefinitionRegistrar, BeanClassLoaderAware, EnvironmentAware {

    public static Map<String, DruidDataSource> dataSources;

    @Getter
    private ClassLoader beanClassLoader;

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.beanClassLoader = classLoader;
    }

    @Override
    public void setEnvironment(Environment environment) {
        String tmpUrl = environment.getProperty("jdbc.url");
        if (StringUtils.hasText(tmpUrl)) {
            String[] urls = tmpUrl.split(StringPool.COMMA);
            String[] sourceName = Objects.requireNonNull(environment.getProperty("jdbc.sourceName")).split(StringPool.COMMA);
            if (urls.length != sourceName.length) {
                throw new IllegalStateException("no datasource config");
            } else {
                String[] usernames = Objects.requireNonNull(environment.getProperty("jdbc.username")).split(StringPool.COMMA);
                String[] password = Objects.requireNonNull(environment.getProperty("jdbc.password")).split(StringPool.COMMA);
                String[] initialSize = Objects.requireNonNull(environment.getProperty("jdbc.initialSize")).split(StringPool.COMMA);
                String[] maxActive = Objects.requireNonNull(environment.getProperty("jdbc.maxActive")).split(StringPool.COMMA);
                String[] minIdle = Objects.requireNonNull(environment.getProperty("jdbc.minIdle")).split(StringPool.COMMA);
                String[] maxWait = Objects.requireNonNull(environment.getProperty("jdbc.maxWait")).split(StringPool.COMMA);
                dataSources = new HashMap<>(10);
                for (int i = 0; i < urls.length; ++i) {
                    DruidDataSource dataSource = new DruidDataSource();
                    dataSource.setUrl(urls[i] + "?tinyInt1isBit=false&allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&autoReconnect=true&useSSL=false&serverTimezone=Asia/Shanghai&allowMultiQueries=true");
                    dataSource.setUsername(usernames[i]);
                    dataSource.setPassword(password[i]);
                    dataSource.setInitialSize(Integer.parseInt(initialSize[i]));
                    dataSource.setMaxActive(Integer.parseInt(maxActive[i]));
                    dataSource.setMinIdle(Integer.parseInt(minIdle[i]));
                    dataSource.setMaxWait(Long.parseLong(maxWait[i]));
                    dataSources.put(sourceName[i], dataSource);
                }
            }
        } else {
            throw new IllegalStateException("no datasource config");
        }
    }


    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(EnableDB.class.getName()));
        if (ObjectUtils.isEmpty(attributes)) {
            return;
        }
        // 注册至BeanDefinitions
        registerDsConfiguration(registry, getDsName(attributes));
    }

    private String getDsName(AnnotationAttributes attributes) {
        return (String) attributes.get("value");
    }

    private void registerDsConfiguration(BeanDefinitionRegistry registry, String dsName) {
        DruidDataSource druidDataSource = dataSources.get(dsName);
        if(Objects.isNull(druidDataSource)){
            throw new IllegalStateException("数据源配置错误");
        }
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(DruidDataSource.class);
        beanDefinitionBuilder.addPropertyValue("driverClassName", "com.mysql.cj.jdbc.Driver");
        beanDefinitionBuilder.addPropertyValue("url", druidDataSource.getUrl());
        beanDefinitionBuilder.addPropertyValue("username", druidDataSource.getUsername());
        beanDefinitionBuilder.addPropertyValue("password", druidDataSource.getPassword());
        beanDefinitionBuilder.addPropertyValue("initialSize", druidDataSource.getInitialSize());
        beanDefinitionBuilder.addPropertyValue("maxActive", druidDataSource.getMaxActive());
        beanDefinitionBuilder.addPropertyValue("minIdle", druidDataSource.getMinIdle());
        beanDefinitionBuilder.addPropertyValue("maxWait", druidDataSource.getMaxWait());
        beanDefinitionBuilder.addPropertyValue("filters", new String[]{"stat", "wall"});
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        registry.registerBeanDefinition("dataSource", beanDefinition);
        log.warn("[{}]库加载成功", dsName);
    }
}
