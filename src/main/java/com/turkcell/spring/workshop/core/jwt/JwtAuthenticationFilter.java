package com.turkcell.spring.workshop.core.jwt;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//Authentication -> giriş yapmış mı yapmamış mı?
//Authorization  -> yetkisi var mı yok mu?

// Sadece bağlantı yapmayacağız aynı zamanda hangi kullanıcının (rol ün) hangi
// url çağırıp çağıramayacağımıza da karar veriyoruz.

//spring içerisine dahil edebilmek için @component key word ünü kullanmamız lazım
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {//(OncePerRequestFilter) istisnasız her gelen request i önce filtreye tabi tutar.
    private final JwtService jwtService;
    //loaduserbyname metodunun içini biz dolduracağız. Çünkü interface
    //Çünkü farklı şekillerde okuyabilirsin
    private final UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,//gelen request
                                    @NonNull HttpServletResponse response,//gönderilecek response
                                    @NonNull FilterChain filterChain)//bir filtreden sonra diğerine geçmeyi sağlayan yapı
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");//jwt ye ulaşmak için postman üzerinden gelen header
      //başka bir noktada değeri deişmesin                              //daki Authorization (giriş yapmış kullanıcı)
                                                                        // key inin değerini alıyoruz.

                //Jwt validation
           //null ise           //Bearer  şeklinde başlamıyorsa
        if(authHeader==null || !authHeader.startsWith("Bearer ")){
            //kullanıcı geçersiz bir authHeader göndermiş
            filterChain.doFilter(request,response);//bir sonraki filtreye request ve response göndermek
            return;
        }

        final String jwt = authHeader.substring(7);//gelen header (Authorization) dan yalnızca token ı almak

        //gelen jwt den username i alıyoruz
        final String username = jwtService.extractUsername(jwt);//(jwtService) jwt yi kontrol etmek (geçerli mi?) ve encode/decode etmek işlemleri
                                //burada kod kalabalığı yapmamak için ve jwt nin birden fazla yerde kullanılacağı için bir service oluşturuldu.

           //username varsa    //Kullanıcı hali hazırda giriş yapmamış ise
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null)
        {
            //Burada user değişkeni userDetailsService interface i içerisinde bulunan metod kullanılarak dolduruluyor.
            UserDetails user = userDetailsService.loadUserByUsername(username);

            // gelen jwtnin süresinin geçmemiş olması..
            if(jwtService.isTokenValid(jwt, user)){
                // Giriş işlemleri                                                                              //giriş yapmış kullanıcı, kimlik bilgileri (extra bilgiler) ve roller
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                //requestin detaylarını token a yüklüyoruz.
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);//Authentication nın yüklenmesi
            }
        }

        filterChain.doFilter(request,response);//diğer filtrelerin çağırılması
    }
}

