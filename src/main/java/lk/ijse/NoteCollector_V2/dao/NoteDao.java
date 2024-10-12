package lk.ijse.NoteCollector_V2.dao;


import lk.ijse.NoteCollector_V2.Entity.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteDao  extends JpaRepository<NoteEntity,String> {
}
