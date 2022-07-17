package net.springboot2.login.Service;

import net.springboot2.login.Entity.User;
import net.springboot2.login.Service.exception.ServiceException;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletResponse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private IUserService iUserService;

    @Autowired
    StringEncryptor stringEncryptor;

    @Test
    public void register() {
        try {
            User user = new User();
            user.setUsername("ceshi");
            user.setPassword("123456");
            user.setEmail("ceshi@qq.com");
            String repeatPas = "123456";
            iUserService.register(user, repeatPas);
            System.out.println("register success！");
        } catch (ServiceException e) {
            System.out.println("register failed！" + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void login(HttpServletResponse response) {
        try {
            String username_or_email = "ceshi@qq.com";
            String password = "123456";
            System.out.println(stringEncryptor.decrypt("c2sYhy/D6eEtRAN9j/KKLg=="));
            User user = iUserService.login(response, username_or_email, password);
            System.out.println("login success！" + user);
        } catch (ServiceException e) {
            System.out.println("login failed！" + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
