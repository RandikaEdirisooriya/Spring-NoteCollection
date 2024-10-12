package lk.ijse.NoteCollector_V2.Config;

import ch.qos.logback.core.util.StringUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Configuration
public class JWTConfigFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
String authorization = request.getHeader("Authorization");
String UserEmail ;
String ExtractedToken;
if(StringUtil.isNullOrEmpty(authorization) || !authorization.startsWith("Bearer ")){
    filterChain.doFilter(request,response);
}else {
    ExtractedToken = authorization.substring(7);

}
    }
}
