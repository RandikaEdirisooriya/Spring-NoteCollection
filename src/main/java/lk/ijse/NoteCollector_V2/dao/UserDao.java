package lk.ijse.NoteCollector_V2.dao;


import lk.ijse.NoteCollector_V2.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserEntity,String> {
}
