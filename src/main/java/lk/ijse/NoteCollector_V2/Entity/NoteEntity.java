package lk.ijse.NoteCollector_V2.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "note")
public class NoteEntity implements SuperEntity{
    @Id
    private String noteId;
    private String noteTitle;
    private String noteDesc;
    private String createDate;
    private String prioratyLevel;
    @ManyToOne
    @JoinColumn(name = "userId",nullable = false)
    private UserEntity userEntity;

}
