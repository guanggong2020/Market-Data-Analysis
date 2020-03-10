package com.example.demo.mapper;

import com.example.demo.entities.Administrator;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface AdministratorMapper {

    @Select("select * from admin")
    List<Administrator> getAdministrator();

    @Select("select * from admin where id=#{id}")
    Administrator getAdministratorById(Integer id);

    @Select("select * from admin where userName=#{id}")
    Administrator getUserByUserName(String userName);

    @Delete("delete from admin where id=#{id}")
    void deleteAdministratorById(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into admin(userName, passWord) values(#{userName}, #{passWord})")
    void insertAdministrator(Administrator administrator);

    @Update("update admin set userName=#{userName}, passWord=#{passWord} where id=#{id}")
    void updateAdministrator(Administrator administrator);
}
