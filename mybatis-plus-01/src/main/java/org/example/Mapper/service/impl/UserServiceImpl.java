package org.example.Mapper.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.Mapper.UserMapper;
import org.example.pojo.User;
import org.example.Mapper.service.UserService;
import org.springframework.stereotype.Service;

//ServiceImpl这个类事项一部分crud
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
