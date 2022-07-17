package net.springboot2.login.Service;

import net.springboot2.login.Dao.UserMapper;
import net.springboot2.login.Entity.User;
import net.springboot2.login.Service.exception.*;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    StringEncryptor stringEncryptor;

    @Autowired
    private IJWTService jwtService;

    @Override
    public int insertUser(User user){
        return userMapper.insertUser(user);
    }

    @Override
    public User findUserByUserName(String name){
        return userMapper.selectUserByUserName(name);
    }

    @Override
    public User findUserById(int id){
        return userMapper.selectUserById(id);
    }

    @Override
    public User findUserByEmail(String email){
        return userMapper.selectUserByEmail(email);
    }

    @Override
    public void register(User user, String repeatPsw){
        String username = user.getUsername();
        String email = user.getEmail();

        User nameRes = findUserByUserName(username);
        User emailRes = findUserByEmail(email);

        if(nameRes != null || emailRes != null){
            throw new UsernameDuplicateException("用户已存在");
        }

        if(!user.getPassword().equals(repeatPsw)){
            throw new PasswordValidException("两次密码不一致");
        }else {
            user.setPassword(stringEncryptor.encrypt(repeatPsw));
        }

        Integer rows = insertUser(user);

        if(rows != 1){
            throw new InsertException("Unknown error occurred when adding user data. Contact your system administrator");
        }
    }

    @Override
    public User login(HttpServletResponse response, String uname_or_email, String password){
        User newUser;
        if(uname_or_email.indexOf("@")>0){
            newUser = findUserByEmail(uname_or_email);
        }else{
            newUser = findUserByUserName(uname_or_email);
        }
        if(newUser == null){
            throw new UserNotFoundException("用户不存在");
        }
        if(!stringEncryptor.decrypt(newUser.getPassword()).equals(password)){
            throw new PasswordNotMatchException("密码错误");
        }

        User user = new User();
        user.setId(newUser.getId());
        user.setEmail(newUser.getEmail());
        user.setUsername(newUser.getUsername());
        user.setPassword(newUser.getPassword());

        return user;
    }
}
