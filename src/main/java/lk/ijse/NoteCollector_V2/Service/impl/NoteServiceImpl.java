package lk.ijse.NoteCollector_V2.Service.impl;


import jakarta.transaction.Transactional;
import lk.ijse.NoteCollector_V2.CustomStatusCode.SelectedNoteStatus;
import lk.ijse.NoteCollector_V2.Dto.Impl.NoteDto;
import lk.ijse.NoteCollector_V2.Dto.NoteStatus;
import lk.ijse.NoteCollector_V2.Entity.NoteEntity;
import lk.ijse.NoteCollector_V2.Entity.UserEntity;
import lk.ijse.NoteCollector_V2.Exception.UserNotFoundException;
import lk.ijse.NoteCollector_V2.Service.NoteServise;
import lk.ijse.NoteCollector_V2.Utill.mapping;
import lk.ijse.NoteCollector_V2.dao.NoteDao;
import lk.ijse.NoteCollector_V2.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NoteServiceImpl implements NoteServise {
    private static List<NoteDto> noteDtoList = new ArrayList<>();

    public NoteServiceImpl() {
        noteDtoList.add(new NoteDto("n0001", "java", "eeeee", "444444", "ffff", "wwwwww"));
    }

    @Autowired
    private NoteDao noteDao;
    @Autowired
    private UserDao userDao; // Add UserDao to fetch the userEntity

    @Autowired
    private mapping mapping;

    @Override
    public void saveNote(NoteDto noteDTO) {
        System.out.println(noteDTO);

        // Map NoteDTO to NoteEntity
        NoteEntity noteEntity = mapping.toNoteEntity(noteDTO);

        // Fetch the UserEntity from the database using userId from noteDTO
        UserEntity userEntity = userDao.findById(noteDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Set the fetched UserEntity to NoteEntity
        noteEntity.setUserEntity(userEntity);

        // Save the NoteEntity to the database
        noteDao.save(noteEntity);

    }

    @Override
    public List<NoteDto> getAllNote() {
        return mapping.asNoteDtoList(noteDao.findAll());
    }

    @Override
    public NoteStatus getSelectedNote(String id) {
        if (noteDao.existsById(id)) {
            var selectedUser = noteDao.getReferenceById(id);
            return mapping.toNoteDto(selectedUser);
        } else {
            return new SelectedNoteStatus(1, "Selected note not found", "Error");
        }
    }

    @Override
    public void deleteNote(String id) {
        Optional<NoteEntity> foundNote = noteDao.findById(id);
        if (!foundNote.isPresent()) {
            throw new UserNotFoundException("Note not found");
        } else {
            noteDao.deleteById(id);
        }
    }

    @Override
    public void updateNote(String id, NoteDto updatedNoteDTO) {
        NoteEntity existingNote = noteDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        // Update the existing NoteEntity with values from updatedNoteDTO
        existingNote.setNoteTitle(updatedNoteDTO.getNoteTitle());
        existingNote.setNoteDesc(updatedNoteDTO.getNoteDesc());
        existingNote.setCreateDate(updatedNoteDTO.getCreateDate());
        existingNote.setPrioratyLevel(updatedNoteDTO.getPrioratyLevel());

        // Optionally, handle userEntity if userId needs to be updated
        UserEntity userEntity = userDao.findById(updatedNoteDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        existingNote.setUserEntity(userEntity);

        // Save the updated NoteEntity
        noteDao.save(existingNote);
    }
}
