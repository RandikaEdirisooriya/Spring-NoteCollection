package lk.ijse.NoteCollector_V2.Service.impl;

import lk.ijse.NoteCollector_V2.Service.JWTService;
import org.springframework.security.core.userdetails.UserDetails;

public class JWTServiceImpl implements JWTService {
    @Override
    public String ExtractToken(String token) {
        return null;
    }

    @Override
    public String GenerateToken(UserDetails userDetails) {
        return null;
    }

    @Override
    public boolean validateToken(String token, UserDetails userDetails) {
        return false;
    }

    @Override
    public String refreshToken(String pretoken) {
        return null;
    }

    /*valid token,extract token*/
}
