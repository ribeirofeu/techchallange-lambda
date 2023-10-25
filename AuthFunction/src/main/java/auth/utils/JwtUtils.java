package auth.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

public class JwtUtils {

  public static String genereateToken(String userId) {
    try {
      Algorithm algorithm = Algorithm.HMAC256("techfood");
      return JWT.create().withIssuer("auth").withSubject(userId).sign(algorithm);
    } catch (JWTCreationException exception) {
      throw new RuntimeException("Error generate token");
    }
  }
}
