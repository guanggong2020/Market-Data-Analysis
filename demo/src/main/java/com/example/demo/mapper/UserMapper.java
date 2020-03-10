package com.example.demo.mapper;

import com.example.demo.entities.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    @Select("select * from user")
    List<User> getUser();

    @Select("select * from user where id=#{id}")
    User getUserById(Integer id);

    @Delete("delete from user where id=#{id}")
    void deleteUserById(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into user(userName, passWord) values(#{userName}, #{passWord})")
    void insertUser(User user);

    @Update("update user set userName=#{userName}, passWord=#{passWord} where id=#{id}")
    void updateUser(User user);
}
