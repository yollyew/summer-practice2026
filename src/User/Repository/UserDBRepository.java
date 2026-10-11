package User.Repository;

import Repository.RowMapper;
import User.Model.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDBRepository implements UserRepository {
    private final DataSource dataSource;

    private final RowMapper<User> userRowMapper = row -> new User(
            row.getInt("id"),
            row.getString("name"),
            row.getString("email"),
            row.getString("password"),
            row.getString("description")
    );

    @Override
    public void save(User user) {
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("insert into account(name, email, password, description) values(?, ?, ?, ?)")) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getDescription());

            int resultSet = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from account where email = ?")) {

            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next())
                    return Optional.of(userRowMapper.mapRow(resultSet));
                else
                    return Optional.empty();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findById(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("select * from account where id = ?")) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next())
                    return Optional.of(userRowMapper.mapRow(resultSet));
                else
                    return Optional.empty();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean changeDescription(String email, String description) {
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("update account set description = ? where email = ?")) {

            statement.setString(1, description);
            statement.setString(2, email);

            int result = statement.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll() {
        try (Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from account")) {

            List<User> users = new ArrayList<>();

            while (resultSet.next()) {
                User user = userRowMapper.mapRow(resultSet);
                users.add(user);
            }

            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findByDescription(String description) {
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from account where description = ?")) {

            statement.setString(1, description);

            try (ResultSet resultSet = statement.executeQuery()) {
                List<User> users = new ArrayList<>();

                while (resultSet.next()) {
                    User user = userRowMapper.mapRow(resultSet);
                    users.add(user);
                }

                return users;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UserDBRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
