package com.charlotte.cblog.security;


import cn.hutool.core.lang.Validator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.charlotte.cblog.bean.User;
import com.charlotte.cblog.common.exception.BaseException;
import com.charlotte.cblog.enums.UserStatus;
import com.charlotte.cblog.model.LoginUser;
import com.charlotte.cblog.service.impl.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.getOne(new QueryWrapper<>(new User()).eq("username", s));

        if (Validator.isNull(user)) {
            log.info("登录用户：{} 不存在.", s);
            throw new UsernameNotFoundException("登录用户：" + s + " 不存在");
        }
        else if (UserStatus.DELETED.getCode().equals(user.getIsDeleted().toString()))
        {
            log.info("登录用户：{} 已被删除.", s);
            throw new BaseException("对不起，您的账号：" + s + " 已被删除");
        }
        return new LoginUser(user);

    }


}
