package clinica.medica.ApiRest.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    public String generarToken(){
        try {
            //Mala práctica dejar el secret.
            Algorithm algorithm = Algorithm.HMAC256("1234");
            return JWT.create()
                    .withIssuer("auth0")
                    .withSubject("luismazo")
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }
}
