package lk.ijse.NoteCollector_V2.Service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {
    String ExtractToken(String token);
    String GenerateToken(UserDetails userDetails);
    boolean validateToken(String token, UserDetails userDetails);
    String refreshToken(String pretoken);
}
