package lk.ijse.NoteCollector_V2.Service;



import lk.ijse.NoteCollector_V2.Dto.Impl.NoteDto;
import lk.ijse.NoteCollector_V2.Dto.NoteStatus;

import java.util.List;

public interface NoteServise {
    void saveNote(NoteDto noteDto);
    List<NoteDto> getAllNote();
    NoteStatus getSelectedNote(String id);
    void deleteNote(String id);
    void updateNote(String id,NoteDto noteDto);
}
