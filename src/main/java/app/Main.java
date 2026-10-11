package app;

import repository.DriverManagerDataSource;
import user.api.UserConsoleUI;
import user.app.UserService;
import user.repository.UserDBRepository;
import util.PropertiesReader;

import javax.sql.DataSource;
import java.util.Properties;

public class Main {
    static PropertiesReader propertiesReader = new PropertiesReader("application.properties");
    static Properties properties = propertiesReader.loadProperties();

    static void main(String[] args) {
        DataSource dataSource = new DriverManagerDataSource(properties.getProperty("dbUrl"), properties.getProperty("dbUser"), properties.getProperty("dbPassword"));
        UserDBRepository userRepo = new UserDBRepository(dataSource);
        UserService userService = new UserService(userRepo);
        UserConsoleUI userConsoleUI = new UserConsoleUI(userService);

        while (true) userConsoleUI.run();
    }
}