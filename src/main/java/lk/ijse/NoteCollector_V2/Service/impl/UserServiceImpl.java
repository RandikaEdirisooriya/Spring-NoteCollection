package lk.ijse.NoteCollector_V2.Service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.NoteCollector_V2.CustomStatusCode.SelectedUserStatus;
import lk.ijse.NoteCollector_V2.Dto.Impl.UserDto;
import lk.ijse.NoteCollector_V2.Dto.UserStatus;
import lk.ijse.NoteCollector_V2.Entity.UserEntity;
import lk.ijse.NoteCollector_V2.Exception.DataPersistException;
import lk.ijse.NoteCollector_V2.Exception.UserNotFoundException;
import lk.ijse.NoteCollector_V2.Service.UserService;
import lk.ijse.NoteCollector_V2.Utill.mapping;
import lk.ijse.NoteCollector_V2.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
@Autowired
private UserDao userDao;

    @Autowired
    private mapping mapping;
    @Override
    public void saveUser(UserDto userDto) {
        UserEntity savedUser =
                userDao.save(mapping.toUserEntity(userDto));
        if (savedUser == null) {
            throw new DataPersistException("User not saved");
        }
    }

    @Override
    public List<UserDto> getAllUser() {
       List<UserEntity> alluser=userDao.findAll();
       return mapping.asUserDtoList(alluser);
    }

    @Override
    public UserStatus getSelectedUser(String id) {
if(userDao.existsById(id)){
    var selectedUser= userDao.getReferenceById(id);
    return mapping.toUserDto(selectedUser);

}else {
    return new SelectedUserStatus(1,"Error","User Not Found");

}


    }

    @Override
    public void deleteUser(String id) {
        Optional<UserEntity> existUser=userDao.findById(id);
        if(!existUser.isPresent()){
            throw  new UserNotFoundException("User does not exist");
        }else {
            userDao.deleteById(id);
        }
    }

    @Override
    public void updateUser(String id,UserDto userDto) {
     Optional<UserEntity> tempUser= userDao.findById(id);
     if(tempUser.isPresent()){
       tempUser.get().setFirstName(userDto.getFirstName());
       tempUser.get().setLastName(userDto.getLastName());
       tempUser.get().setEmail(userDto.getEmail());
       tempUser.get().setPassword(userDto.getPassword());
       tempUser.get().setProfilePicture(userDto.getProfilePicture());

     }
    }

    @Override
    public UserDetailsService userDetailsService() {
return  username -> userDao.findByEmail(username).orElseThrow(()->new UserNotFoundException("User not found"));
    }
}
