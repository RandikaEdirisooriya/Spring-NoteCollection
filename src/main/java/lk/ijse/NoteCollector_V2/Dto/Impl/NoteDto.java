package lk.ijse.NoteCollector_V2.Dto.Impl;


import lk.ijse.NoteCollector_V2.Dto.NoteStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NoteDto implements NoteStatus {
    private String noteId;
    private String noteTitle;
    private String noteDesc;
    private String createDate;
    private String prioratyLevel;
    private String userId;

}
