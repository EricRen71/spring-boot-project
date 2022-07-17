package net.springboot2.login.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import net.springboot2.login.Entity.User;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class JWTService implements IJWTService{
    @Override
    public String createToken(User user) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);

        String token="";
        token= JWT.create().withAudience(user.getId().toString())
                .withClaim("email", user.getEmail())
                .withClaim("userName", user.getUsername())    //载荷，随便写几个都可以
                .withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256(user.getUsername()));   //加密
        return token;
    }

    public static DecodedJWT verifyToken(String token, User user) throws Exception {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(user.getUsername())).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jwt;
    }


    public static String getAudience(String token) throws Exception {
        String audience = null;
        try {
            audience = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            j.printStackTrace();
        }
        return audience;
    }

    public static Claim getClaimByName(String token, String name){
        return JWT.decode(token).getClaim(name);
    }

}
