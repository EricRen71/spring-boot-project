package net.springboot2.login.Controller;

import net.springboot2.login.Service.exception.*;
import net.springboot2.login.Util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import java.lang.RuntimeException;

import javax.servlet.http.HttpSession;

public class BaseController {
    public static final int OK = 200;
    @ExceptionHandler({ServiceException.class,
            JWTDecodeException.class,
            JWTVerificationException.class,
            RuntimeException.class})
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<Void>(e);
        if (e instanceof UsernameDuplicateException) {
            result.setState(4000);
            result.setMessage(e.getMessage());
        } else if (e instanceof RuntimeException){
            result.setState(401);
            result.setMessage(e.getMessage());
        }else if (e instanceof JWTDecodeException){
            result.setState(401);
            result.setMessage(e.getMessage());
        }else if (e instanceof JWTVerificationException){
            result.setState(401);
            result.setMessage(e.getMessage());
        } else if (e instanceof UserNotFoundException) {
            result.setState(4001);
            result.setMessage(e.getMessage());
        } else if (e instanceof PasswordNotMatchException) {
            result.setState(4002);
            result.setMessage(e.getMessage());
        } else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMessage(e.getMessage());
        } else if (e instanceof PasswordValidException) {
            result.setState(5001);
        }

        if(result.getState() == 200){
            result.setMessage("成功");
        }
        return result;
    }

    protected final String getUserNameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }
}
