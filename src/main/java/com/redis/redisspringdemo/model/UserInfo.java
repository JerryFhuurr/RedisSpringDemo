package com.redis.redisspringdemo.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// For Hash in Redis
public class UserInfo {
    private String id;
    private String username;
    private String password;
}
