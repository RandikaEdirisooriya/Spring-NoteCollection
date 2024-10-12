package lk.ijse.NoteCollector_V2.CustomStatusCode;


import lk.ijse.NoteCollector_V2.Dto.NoteStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedNoteStatus implements NoteStatus {
    private int StatusCode;
    private String Status;

    private String StatusMassage;
}
