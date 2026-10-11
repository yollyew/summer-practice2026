package User.Model;

public class UserMapper {
    public String toLine(User user) {
        return user.getId() + "|" +
                user.getName() + "|" +
                user.getEmail() + "|" +
                user.getPassword() + "|" +
                user.getDescription();
    }

    public User fromLine(String line) {
        String[] fields = line.split("\\|");

        return new User(fields[0], fields[1], fields[2], fields[3], fields[4]);
    }
}
