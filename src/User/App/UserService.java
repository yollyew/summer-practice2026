package User.App;

import User.Model.User;
import User.Repository.UserFileRepository;
import User.Repository.UserRepository;

import java.util.Optional;

public class UserService {
    private final UserRepository userRepo;

    public boolean signIn(String email, String password) {
        Optional<User> optionalUser = userRepo.findByEmail(email);
        if (optionalUser.isPresent())
            return optionalUser.get().getPassword().equals(password);

        return false;
    }

    public String signUp(User user) {
        userRepo.save(user);
        return "User " + user.getName() + " signed up! ";
    }

    public String findById(String id) {
        Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent())
            return "User " + optionalUser.get().getName() + " was found. ";

        return "User with ID - " + id + " not found. ";
    }

    public String changeDescription(String email, String description) {
        Optional<User> optionalUser = userRepo.changeDescription(email, description);
        if (optionalUser.isPresent())
            return "Changed profile description for user - " + optionalUser.get().getName();

        return "User with email - " + email + " not found";
    }

    public UserService(String filename) {
        this.userRepo = new UserFileRepository(filename);
    }
}
