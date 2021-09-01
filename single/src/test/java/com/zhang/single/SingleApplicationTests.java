package com.zhang.single;

import com.zhang.single.base.RedisBaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class SingleApplicationTests {

    @Autowired
    private RedisBaseServiceImpl redisBaseService;
    @Test
    void contextLoads() {
        String s = redisBaseService.setString("key", "value");
        System.out.println(s);
    }

}
