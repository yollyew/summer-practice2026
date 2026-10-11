package User.Repository;

import User.Model.User;
import java.util.Optional;

public interface UserRepository {
    public void save(User user);
    public Optional<User> findByEmail(String email);
    public Optional<User> findById(String id);
    public Optional<User> changeDescription(String email, String description);
}
