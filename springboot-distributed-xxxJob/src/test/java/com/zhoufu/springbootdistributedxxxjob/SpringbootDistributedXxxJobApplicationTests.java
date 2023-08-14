package com.zhoufu.springbootdistributedxxxjob;

import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
public class SpringbootDistributedXxxJobApplicationTests {

    @Test
    public void contextLoads()  {
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("","");
        hashMap.get("");
    }

    @Data
    class Student{
        private String name;
        private Integer age;
    }
}
