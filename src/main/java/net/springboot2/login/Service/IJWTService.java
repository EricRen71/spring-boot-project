package net.springboot2.login.Service;

import net.springboot2.login.Entity.User;

public interface IJWTService {
    String createToken(User user);
}
