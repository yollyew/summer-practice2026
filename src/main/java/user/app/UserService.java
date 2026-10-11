package user.app;

import user.api.dto.UserDto;
import user.model.User;
import user.repository.UserDBRepository;
import user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
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

    public String findById(int id) {
        Optional<User> optionalUser = userRepo.findById(id);

        if (optionalUser.isPresent())
            return "User " + optionalUser.get().getName() + " was found. ";

        return "User with ID - " + id + " not found. ";
    }

    public String changeDescription(String email, String description) {
        if (userRepo.changeDescription(email, description))
            return "Profile description changed";

        return "User with email - " + email + " not found";
    }

    public List<UserDto> findAll() {
        List<UserDto> users = new ArrayList<>();

        for (User user : userRepo.findAll()) {
            users.add(new UserDto(user.getName()));
        }
        return users;
    }

    public List<UserDto> findByDescription(String description) {
        List<UserDto> users = new ArrayList<>();

        for (User user : userRepo.findByDescription(description)) {
            users.add(new UserDto(user.getName()));
        }
        return users;
    }

    public UserService(UserDBRepository userRepo) {
        this.userRepo = userRepo;
    }
}
