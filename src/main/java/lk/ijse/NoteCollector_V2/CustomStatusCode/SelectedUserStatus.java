package lk.ijse.NoteCollector_V2.CustomStatusCode;


import lk.ijse.NoteCollector_V2.Dto.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedUserStatus implements UserStatus {
    private int StatusCode;
    private String Status;

    private String StatusMassage;
}
