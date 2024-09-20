package org.emsc.modules.demo.authAll.service.impl;

import org.emsc.modules.demo.authAll.entity.Auth;
import org.emsc.modules.demo.authAll.mapper.AuthMapper;
import org.emsc.modules.demo.authAll.service.IAuthService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 不设置权限配置
 * @Author: jeecg-boot
 * @Date:   2024-09-03
 * @Version: V1.0
 */
@Service
public class AuthServiceImpl extends ServiceImpl<AuthMapper, Auth> implements IAuthService {

}
