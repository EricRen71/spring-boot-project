package net.springboot2.login.Controller;

import com.alibaba.fastjson.JSONArray;
import net.springboot2.login.Dao.UserMapper;
import net.springboot2.login.Entity.User;
import net.springboot2.login.Interceptor.PassToken;
import net.springboot2.login.Interceptor.UserLoginToken;
import net.springboot2.login.Service.IJWTService;
import net.springboot2.login.Service.IUserService;
import net.springboot2.login.Service.UserService;
import net.springboot2.login.Util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class UserController extends BaseController{
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IUserService userService;

    @Autowired
    private IJWTService jwtService;

    @PassToken
    @PostMapping("/login")
    public JsonResult<String> login(@RequestBody JSONObject jsonObject, HttpServletRequest request,
                                  HttpServletResponse response){
        String username = (String) jsonObject.get("username");
        String psw = (String) jsonObject.get("password");
        User data = userService.login(response, username, psw);
        HttpSession session = request.getSession();
        session.setAttribute("username", data.getUsername());
        String token = jwtService.createToken(data);
        Cookie cookies = new Cookie("token", token);
        cookies.setMaxAge(24 * 60 * 60);
        response.addCookie(cookies);
        //System.out.println("username:" + username);
        //System.out.println("password:" + psw);
        return new JsonResult<>(OK, token, "登录成功");
    }

    @PostMapping("/register")
    public JsonResult<Void> reg(@RequestBody JSONObject jsonObject) {
        User user = new User();
        user.setUsername((String) jsonObject.get("username"));
        user.setPassword((String) jsonObject.get("password"));
        user.setEmail((String) jsonObject.get("email"));
        String repeatPsw = (String) jsonObject.get("repeatPsw");
        //System.out.println("username:" + user.getUsername());
        userService.register(user, repeatPsw);
        return new JsonResult<>(OK, "注册成功");
    }

    /*@PostMapping("/test")
    public String test(@RequestBody JSONObject jsonObject){
        return jsonObject.get("username").toString();
    }*/

    @RequestMapping("/logout")
    public JsonResult<Void> logout(HttpSession session, HttpServletRequest request,
                         HttpServletResponse response){
        session.removeAttribute("username");
        session.invalidate();

        // 根据 key 将 value 置空
        Cookie cookie_username = new Cookie("token", "");
        // 设置持久时间为0
        cookie_username.setMaxAge(0);
        // 设置共享路径
        cookie_username.setPath(request.getContextPath());
        // 向客户端发送 Cookie
        response.addCookie(cookie_username);

        return new JsonResult<>(OK, "退出登录");
    }

    @UserLoginToken
    @RequestMapping("/getmessage")
    public JsonResult<Void> getmessage(){

        return new JsonResult<>(OK, "你已经通过验证");
    }
}
