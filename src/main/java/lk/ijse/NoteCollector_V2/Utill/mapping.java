package lk.ijse.NoteCollector_V2.Utill;

import lk.ijse.NoteCollector_V2.Dto.Impl.NoteDto;
import lk.ijse.NoteCollector_V2.Dto.Impl.UserDto;
import lk.ijse.NoteCollector_V2.Entity.NoteEntity;
import lk.ijse.NoteCollector_V2.Entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class mapping {
    @Autowired
    private ModelMapper modelMapper;

    public UserEntity toUserEntity(UserDto userDto){
        return modelMapper.map(userDto, UserEntity.class);
    }
    public UserDto toUserDto(UserEntity userEntity){
        return modelMapper.map(userEntity, UserDto.class);
    }
    public List<UserDto> asUserDtoList(List<UserEntity> userEntityList){
        return modelMapper.map(userEntityList,new TypeToken<List<UserDto>>(){}.getType());
    }
    public NoteEntity toNoteEntity(NoteDto noteDto){
        return modelMapper.map(noteDto, NoteEntity.class);
    }
    public NoteDto toNoteDto(NoteEntity noteEntity){
        return modelMapper.map(noteEntity, NoteDto.class);
    }

    public List<NoteDto> asNoteDtoList(List<NoteEntity> noteEntityList){
        return modelMapper.map(noteEntityList,new TypeToken<List<NoteDto>>(){}.getType());
    }


}
