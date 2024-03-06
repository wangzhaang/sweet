package org.example.test;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.Mapper.UserMapper;
import org.example.pojo.User;
import org.example.Mapper.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class springBootTest {
    @Autowired
private UserMapper userMapper;
    @Test
    public void test001(){
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }
    @Test
    public  void  insert(){
        User user = new User();
        user.setAge(20);
        user.setId(10L);
        user.setName("zhangsan");
        user.setEmail("2927649295@qq.com");
        int insert = userMapper.insert(user);
        System.out.println("影响行数为："+insert);
    }
    @Test
    public  void deleteByid(){
        int i = userMapper.deleteById(2L);
        System.out.println("影响行数为："+i);
    }
    @Autowired
    private UserService userService;
    @Test
    public void test_save(){
        List<User> userList=new ArrayList<>();
        User user = new User();
        user.setEmail("2020101@2qq.com");
        user.setId(6L);
        user.setAge(29);
        user.setName("李白");
        userList.add(user);
        User user1 = new User();
        user1.setName("杜甫");
        user1.setAge(21);
        user1.setEmail("sus0q@qq.com");
        user1.setId(8L);
        userList.add(user1);
        boolean b = userService.saveBatch(userList);
        System.out.println("插入的结果为："+b);

    }
    @Test
    public void testRemove(){
        boolean b = userService.removeById(8L);

        System.out.println("插入的结果为："+b);
    }
@Test
    public  void  testPage(){
        //自定义的分页查询
    Page<User> userPage=new  Page<>(1, 3);
    Page<User> userPage1 = userMapper.selectPage(userPage, null);
    long current = userPage.getCurrent();//页码
    long size = userPage.getSize();//页容量
    List<User> records = userPage.getRecords();//当前页的数量
    long total = userPage.getTotal();
    System.out.println(userPage1);
}
    @Test
    public  void  testPage2(){
        //分页查询

        Page<User> userPage=new  Page<>(1, 3);
userMapper.queryByAge(userPage,1);
        long current = userPage.getCurrent();//页码
        System.out.println("页码的值为：+"+current);
        long size = userPage.getSize();//页容量
        System.out.println("页容量的值为："+size);
        List<User> records = userPage.getRecords();//当前页的数量
        System.out.println(records);
        long total = userPage.getTotal();

    }
    @Test
    public  void  testPage3(){
        //按照年龄降序排序，如果年龄相同则按照id排序
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age").orderByAsc("id");
        List<User> userList = userMapper.selectList(queryWrapper);
        for (Object o :userList) {
            System.out.println(o);

        }


    }
    @Test
    public  void  testPage4(){
        //将年龄大于20并且用户名包含a或者邮箱为null的用户信息修改

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age",20)
                .like("name","a")
                .or().isNull("email");
        User user = new User();
        user.setAge(10);
        user.setEmail("2929@qq.com");
        int update = userMapper.update(user, queryWrapper);
        System.out.println(update);


    }
    @Test
    public void  testPage6(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("id",1L);
        queryWrapper.select("name","age");//选择列
        List<User> userList = userMapper.selectList(queryWrapper);
        for (Object o :userList) {
            System.out.println(o);
        }
    }
    @Test
    public void testLambda(){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(User::getName,"a").between(User::getAge,20,30).isNotNull(User::getEmail);
        List<User> userList = userMapper.selectList(lambdaQueryWrapper);
       userList.forEach(System.out::println);
    }
    @Test
    public void testQuick2(){

//        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
//        //将id = 3 的email设置为null, age = 18
//        updateWrapper.eq("id",3)
//                .set("email",null)  // set 指定列和结果
//                .set("age",18);

        //使用lambdaUpdateWrapper
        LambdaUpdateWrapper<User> updateWrapper1 = new LambdaUpdateWrapper<>();
        updateWrapper1.eq(User::getId,3)
                .set(User::getEmail,null)
                .set(User::getAge,18);

        //如果使用updateWrapper 实体对象写null即可!
        int result = userMapper.update(null, updateWrapper1);
        System.out.println("result = " + result);
    }
    @Test
    public void testLogic(){
        //物理删除 delete from user where id=1
        int i = userMapper.deleteById(3);
        //其实是 update user set deleted=1 where id=3

        System.out.println(i);
        List<User> userList = userMapper.selectList(null);
        //只会查询deleted值为0的值

        System.out.println(userList);
    }

}
