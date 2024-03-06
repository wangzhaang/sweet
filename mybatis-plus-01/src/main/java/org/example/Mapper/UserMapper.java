package org.example.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.example.pojo.User;

public interface UserMapper extends BaseMapper<User> {
    //自定义一个根据年龄的分页查询
    IPage<User> queryByAge(IPage<User> page, @Param("age") Integer age);

}
