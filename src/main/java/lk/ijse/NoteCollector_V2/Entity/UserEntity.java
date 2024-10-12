package lk.ijse.NoteCollector_V2.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
public class UserEntity implements SuperEntity{
    @Id
    private String userId;
    private String FirstName;
    private String LastName;
    @Column(unique = true)
    private String Email;
    private String Password;
    @Column(columnDefinition = "LONGTEXT")
    private String ProfilePicture;
    @OneToMany(mappedBy = "userEntity")
    private List<NoteEntity> NoteId;
}
