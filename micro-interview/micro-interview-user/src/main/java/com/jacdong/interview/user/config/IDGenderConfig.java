package com.jacdong.interview.user.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jacdong.interview.common.id.SnowFlakeFactory;


/**
 * 
 * @ClassName: IDGenderConfig
 * @Description: TODO
 * 				实例化id 生成器。
 * @author DongBin
 * @date 2021-09-08 11:31:08
 */
@Configuration
@ConfigurationProperties(prefix = "snowflake")
public class IDGenderConfig {

    //数据中心[0,31] 配置文件中不配置就是0
    private long datacenterId;

    //机器标识[0,31] 配置文件中不配置就是0
    private long machineId;

    @Bean
    public SnowFlakeFactory getSnowFlakeFactory() {
        SnowFlakeFactory snowFlakeFactory = new SnowFlakeFactory(datacenterId,machineId);
        return snowFlakeFactory;
    }

    public long getDatacenterId() {
        return datacenterId;
    }

    public void setDatacenterId(long datacenterId) {
        this.datacenterId = datacenterId;
    }

    public long getMachineId() {
        return machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }
}