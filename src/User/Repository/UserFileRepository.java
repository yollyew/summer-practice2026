package User.Repository;

import User.Model.User;
import User.Model.UserMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserFileRepository implements UserRepository {
    private final String filename;
    private final UserMapper userMapper;

    @Override
    public Optional<User> findByEmail(String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();

            while (line != null) {
                User user = userMapper.fromLine(line);
                if (user.getEmail().equals(email))
                    return Optional.of(user);

                line = reader.readLine();
            }

            return Optional.empty();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void save(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(userMapper.toLine(user));
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<User> findById(String id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();

            while (line != null) {
                User user = userMapper.fromLine(line);
                if (user.getId().equals(id))
                    return Optional.of(user);

                line = reader.readLine();
            }

            return Optional.empty();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<User> changeDescription(String email, String description) {
        Optional<User> optionalUser = Optional.empty();
        List<User> users = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();

            while (line != null) {
                User user = userMapper.fromLine(line);
                if (user.getEmail().equals(email)) {
                    optionalUser = Optional.of(user);
                    user.setDescription(description);
                }

                users.add(user);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false))) {
            for (User user : users) {
                writer.write(userMapper.toLine(user));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        return optionalUser;
    }

    public UserFileRepository(String filename) {
        this.filename = filename;
        this.userMapper = new UserMapper();
    }
}
