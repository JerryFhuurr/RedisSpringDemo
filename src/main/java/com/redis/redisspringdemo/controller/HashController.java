package com.redis.redisspringdemo.controller;

import com.redis.redisspringdemo.model.UserInfo;
import com.redis.redisspringdemo.utils.GuidUtil;
import com.redis.redisspringdemo.utils.MapToObj;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/hash")
public class HashController {
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/getOne")
    public String getUserById(String id) throws Exception {
        Map o = redisTemplate.opsForHash().entries(id);
        UserInfo user = MapToObj.mapToObj(o, UserInfo.class);
        System.out.println(user.getUsername());
        return user.toString();
    }

    @PostMapping("addUser")
    public String addUser(String username, String password){
        String id = GuidUtil.getGuid();
        Map<String, String> values = new HashMap<>();
        values.put("username", username);
        values.put("password", password);
        redisTemplate.opsForHash().putAll(id, values);
        return "User id " + id + " added.";
    }
}
