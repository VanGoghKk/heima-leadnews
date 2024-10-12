package com.heima.user.service;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.dtos.LoginDto;

public interface ApUserService {
    public ResponseResult login(LoginDto dto);
    void testGet();
}
