package app.sales.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import app.sales.domain.entity.user;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

@Service
public class JwtUtils {
    @Value("${security.jwt.expiracao}")
    private String expiracao;
    @Value("${security.jwt.chave-assinatura}")
    private String chaveAssinatura;

    public String gerarToken (user us){
        long expString = Long.valueOf(expiracao);
        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);
        Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);

        HashMap<String,Object> claims = new HashMap<>();
        claims.put("id", us.getId());
        claims.put("admin", us.getIsAdmin());

        return Jwts.builder()
                .setSubject(us.getName())
                .setClaims(claims)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.ES256, chaveAssinatura)
                .compact();
    }

    public Claims getClaims (String token) throws ExpiredJwtException {
        return Jwts.parser()
                .setSigningKey(chaveAssinatura)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean tokenIsValid (String token){
        try{
            Claims claims = getClaims(token);
            Date dataExpiracao = claims.getExpiration();

            LocalDateTime date = dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            boolean isValid = LocalDateTime.now().isBefore(date);
            return isValid;
        }catch (Exception e){
            System.out.println("\n\nERROR:"+ e.toString() +"\n\n");
            return false;
        }
    }

    public String getUserName (String token) throws ExpiredJwtException{
        return (String) getClaims(token).getSubject();
    }
}
