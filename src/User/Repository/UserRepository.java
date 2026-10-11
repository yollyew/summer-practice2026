package User.Repository;

import User.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    public void save(User user);
    public Optional<User> findByEmail(String email);
    public Optional<User> findById(int id);
    public boolean changeDescription(String email, String description);
    public List<User> findAll();
    public List<User> findByDescription(String description);
}
