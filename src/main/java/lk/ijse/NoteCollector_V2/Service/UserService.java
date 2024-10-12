package lk.ijse.NoteCollector_V2.Service;


import lk.ijse.NoteCollector_V2.Dto.Impl.UserDto;
import lk.ijse.NoteCollector_V2.Dto.UserStatus;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    List<UserDto> getAllUser();
    UserStatus getSelectedUser(String id);
    void deleteUser(String id);
    void updateUser(String id,UserDto userDto);
    UserDetailsService userDetailsService();

}
