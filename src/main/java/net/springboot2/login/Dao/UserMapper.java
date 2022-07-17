package net.springboot2.login.Dao;

import net.springboot2.login.Entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public User selectUserById(int id);
    public User selectUserByUserName(String userName);
    public User selectUserByEmail(String email);
    public int insertUser(User user);
    public int deleteUser(int id);
}
