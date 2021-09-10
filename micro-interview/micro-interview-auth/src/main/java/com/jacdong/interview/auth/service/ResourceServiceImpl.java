package com.jacdong.interview.auth.service;

import cn.hutool.core.collection.CollUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.jacdong.interview.auth.constant.RedisConstant;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * @ClassName: ResourceServiceImpl
 * @Description: 资源与角色匹配关系管理业务类
 * @author DongBin
 * @date 2021-09-09 10:31:14
 */
@Service
public class ResourceServiceImpl {

    private Map<String, List<String>> resourceRolesMap;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @PostConstruct
    public void initData() {
        resourceRolesMap = new TreeMap<>();
        resourceRolesMap.put("/api/v1/hello", CollUtil.toList("ADMIN"));
        resourceRolesMap.put("/api/v1/user/add", CollUtil.toList("ADMIN"));
        resourceRolesMap.put("/api/v1/user/get", CollUtil.toList("ADMIN", "TEST"));
        resourceRolesMap.put("/api/v1/user/del", CollUtil.toList("ADMIN"));
        resourceRolesMap.put("/api/v1/user/update", CollUtil.toList("ADMIN"));
        redisTemplate.opsForHash().putAll(RedisConstant.RESOURCE_ROLES_MAP, resourceRolesMap);
    }
}
