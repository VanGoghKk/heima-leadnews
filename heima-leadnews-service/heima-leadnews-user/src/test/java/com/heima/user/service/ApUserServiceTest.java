package com.heima.user.service;

import com.baomidou.mybatisplus.core.injector.methods.SelectList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ApUserServiceTest {

    @Autowired
    private ApUserService apUserService;

    @Test
    public void test01(){
        apUserService.testGet();
    }
}
