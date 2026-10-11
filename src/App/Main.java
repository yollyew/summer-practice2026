package App;

import User.API.UserConsoleUI;
import User.App.UserService;

public class Main {
    static void main(String[] args) {
        UserService userService = new UserService("users.txt");
        UserConsoleUI userConsoleUI = new UserConsoleUI(userService);

        while (true) userConsoleUI.run();
    }
}
