package lk.ijse.NoteCollector_V2.Controller;


import lk.ijse.NoteCollector_V2.CustomStatusCode.SelectedUserStatus;
import lk.ijse.NoteCollector_V2.Dto.Impl.UserDto;
import lk.ijse.NoteCollector_V2.Dto.UserStatus;
import lk.ijse.NoteCollector_V2.Exception.DataPersistException;
import lk.ijse.NoteCollector_V2.Exception.UserNotFoundException;
import lk.ijse.NoteCollector_V2.Service.UserService;
import lk.ijse.NoteCollector_V2.Utill.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;


@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveUser(
            @RequestPart("firstName") String firstName,
            @RequestPart("lastName") String lastName,
            @RequestPart("email") String email,
            @RequestPart("password") String password,
            @RequestPart("profilePic") MultipartFile profilePic
    ) {
//        generateUserId
        String userId = AppUtil.genereteUserId();
//        profilePic ---> Base64
        String base64ProPick="";
      /*  String base64ProPick = AppUtil.profilePickToBase64(profilePic);*/
try {
    byte [] bytesProPic=profilePic.getBytes();
    base64ProPick=AppUtil.profilePickToBase64(bytesProPic);




//        Build the Object
        UserDto userDTO = new UserDto();
        userDTO.setUserId(userId);
        userDTO.setFirstName(firstName);
        userDTO.setLastName(lastName);
        userDTO.setEmail(email);
        userDTO.setPassword(password);
        userDTO.setProfilePicture(base64ProPick);
        userService.saveUser(userDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }catch (
    DataPersistException e){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }catch (Exception e){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }
    @GetMapping(value = "/{userId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public UserStatus getSelectedUser(@PathVariable("userId") String userId) {
        String regexForUserID = "^USER-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForUserID);
        var regexMatcher = regexPattern.matcher(userId);

        if(!regexMatcher.matches()){
            return new SelectedUserStatus(1,"User ID is not valid","Error");
        }
        return userService.getSelectedUser(userId);
    }
    @GetMapping(value = "/getAllUsers",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> getAllUsers() {
        return userService.getAllUser();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") String userId) {
        String regexForUserID = "^USER-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForUserID);
        var regexMatcher = regexPattern.matcher(userId);
        try {
            if(!regexMatcher.matches()){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            userService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (UserNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){

            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,value = "put/{userId}")
    public void updateUser( @RequestPart("firstName") String firstName,
                            @RequestPart("lastName") String lastName,
                            @RequestPart("email") String email,
                            @RequestPart("password") String password,
                            @RequestPart("profilePic") MultipartFile profilePic,@PathVariable("userId") String userId) throws IOException {
        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(email );
        System.out.println(password );
        System.out.println(password );

        String base64ProPick = "";
        /*  String base64ProPick = AppUtil.profilePickToBase64(profilePic);*/

            byte[] bytesProPic = profilePic.getBytes();
            base64ProPick = AppUtil.profilePickToBase64(bytesProPic);




            UserDto userDTO = new UserDto();
            userDTO.setUserId(userId);
            userDTO.setFirstName(firstName);
            userDTO.setLastName(lastName);
            userDTO.setEmail(email);
            userDTO.setPassword(password);
            userDTO.setProfilePicture(base64ProPick);
            userService.updateUser(userId,userDTO);

        }

    }



