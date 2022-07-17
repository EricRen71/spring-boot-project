package net.springboot2.login.Service;

import net.springboot2.login.Entity.User;

import javax.servlet.http.HttpServletResponse;

public interface IUserService {
    int insertUser(User user);
    User findUserByUserName(String name);
    User findUserById(int id);
    User findUserByEmail(String email);
    void register(User user, String repeatPsw);
    User login(HttpServletResponse response, String uname_or_email, String password);
}
