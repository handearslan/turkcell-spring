package com.turkcell.spring.workshop.core.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    //128 bit şartını sağladığı takdirde istersen abc yaz
    @Value("${application.security.jwt.secret-key}")
    private String secretkey;
    @Value("${application.security.jwt.expiration}")
    private long expiration;

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public String generateToken(UserDetails user){
        return Jwts
                .builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    //kullanıcı doğru mu ve token süresinin durumu ne?
    public boolean isTokenValid(String token, UserDetails user){
        final String usernameFromToken = extractUsername(token);
        //gelen user değeri ile sistemdeki user aynı kişi mi (eşit mi?)
        return (user.getUsername().equals(usernameFromToken)) && !isTokenExpired(token);
    }

    //kullanıcının token nının süresi dolmuş mu?
    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    //Generic method
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){//Function olarak geçmesinin sebebi Claims içindeki hazır metodları kullanabilme (örnek extractClaim(token, Claims::getExpiration);)
        final Claims claims = extractAllClaims(token);//token dan tüm claim leri getirildi.
        return claimsResolver.apply(claims);
    }

    //Claim = field
    public Claims extractAllClaims(String token)
    {           //(parserBuilder) hali hazırdaki jwt yi kullanabileceğimiz bir türe dönüştürür.
        return Jwts.parserBuilder().setSigningKey(getSigninKey()).build().parseClaimsJws(token).getBody(); // Jwt içerisindeki datayı parse eder.
                                    //okurken kullanılacak olan key burada belirtiliyor.
    }

    public Key getSigninKey(){
        //Belirttiğimiz SECRET_KEY ile gelen verileri okuyoruz.
        //Aynı zamanda oluşturma da bu key ile yapılacak...
        byte[] keyBytes = Decoders.BASE64.decode(secretkey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
