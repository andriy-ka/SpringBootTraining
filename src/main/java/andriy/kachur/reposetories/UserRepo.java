package andriy.kachur.reposetories;


import andriy.kachur.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername(String Username);
}
