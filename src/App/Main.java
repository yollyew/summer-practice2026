package App;

import Repository.DriverManagerDataSource;
import User.API.UserConsoleUI;
import User.App.UserService;
import User.Repository.UserDBRepository;

import javax.sql.DataSource;

public class Main {
    static final String dbUrl = "jdbc:postgresql://localhost:5432/shop-app";
    static final String dbUser = "murad";
    static final String dbPassword = "";

    static void main(String[] args) {
        DataSource dataSource = new DriverManagerDataSource(dbUrl, dbUser, dbPassword);
        UserDBRepository userRepo = new UserDBRepository(dataSource);
        UserService userService = new UserService(userRepo);
        UserConsoleUI userConsoleUI = new UserConsoleUI(userService);

        while (true) userConsoleUI.run();
    }
}
