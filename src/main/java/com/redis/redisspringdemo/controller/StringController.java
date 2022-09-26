package com.redis.redisspringdemo.controller;

import com.redis.redisspringdemo.model.StringItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/string")
public class StringController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/getString")
    public String getStringByKey(String keyName) {
        String stringGet = stringRedisTemplate.opsForValue().get(keyName);
        if (!stringGet.equals(null)) {
            return "Value : " + stringGet;
        } else {
            return "Value is null.";
        }
    }

    @PostMapping("/setString")
    public String setStringByKey(@RequestBody StringItem item) {
        try {
            stringRedisTemplate.opsForValue().set(item.getKeyName(), item.getKeyValue());
            return "Set string OK!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PutMapping("/updateString")
    public String updateStringByKey(@RequestBody StringItem item) {
        try {
            stringRedisTemplate.opsForValue().set(item.getKeyName(), item.getKeyValue());
            return "Update string OK!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @DeleteMapping("/deleteString")
    public String deleteStringByKey(String keyName) {
        Boolean result = stringRedisTemplate.delete(keyName);
        if (result) {
            return "Delete success! The key is : " + keyName;
        } else {
            return keyName + " delete failed.";
        }
    }
}
