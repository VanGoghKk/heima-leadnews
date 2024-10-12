package com.heima.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.user.dtos.LoginDto;
import com.heima.model.user.pojos.ApUser;
import com.heima.user.mapper.ApUserMapper;
import com.heima.user.service.ApUserService;

import com.heima.utils.common.AppJwtUtil;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.sql.Wrapper;
import java.util.HashMap;
import java.util.List;

@Transactional
@Service
@Slf4j
public class ApUserServiceImpl extends ServiceImpl<ApUserMapper, ApUser> implements ApUserService {
    @Override
    public ResponseResult login(LoginDto dto) {
        if(StringUtils.isNotBlank(dto.getPhone())&&StringUtils.isNotBlank(dto.getPassword())){
            ApUser dbUser = getOne(Wrappers.<ApUser>lambdaQuery().eq(ApUser::getPhone, dto.getPhone()));
            if (dbUser == null){
                return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST,"用户不存在");
            }
            String salt = dbUser.getSalt();
            String password = dto.getPassword();
            String pswd = DigestUtils.md5DigestAsHex((password + salt).getBytes());
            if (!pswd.equals(dbUser.getPassword())){
                return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
            }

            String token = AppJwtUtil.getToken(dbUser.getId().longValue());
            HashMap<String, Object> map = new HashMap<>();
            map.put("token",token);
            dbUser.setSalt("");
            dbUser.setPassword("");
            map.put("user",dbUser);

            return ResponseResult.okResult(map);
        }else {
            HashMap<String, Object> map = new HashMap<>();
            map.put("token",AppJwtUtil.getToken(0L));
            return ResponseResult.okResult(map);
        }

    }

    public void testGet(){
        List<ApUser> list = list();
        System.out.println(list);
    }
}
