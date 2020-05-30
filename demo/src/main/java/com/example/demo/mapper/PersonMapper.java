package com.example.demo.mapper;

import com.example.demo.entities.Person;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PersonMapper {

    @Select("select * from person")
    List<Person> getUser();

    @Select("select * from person where id=#{id}")
    Person getUserById(Integer id);

    @Select("select * from person where username=#{username}")
    Person findByUserName(String userName);

    @Delete("delete from person where id=#{id}")
    void deleteUserById(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into person(username, password, roles) values(#{username}, #{password}, #{roles})")
    void insertUser(Person person);

    @Update("update person set username=#{username}, passWord=#{password}, roles=#{roles} where id=#{id}")
    void updateUser(Person person);
}
