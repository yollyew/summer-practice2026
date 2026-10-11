package User.API;

import User.App.UserService;
import User.Model.User;

import java.util.Scanner;

public class UserConsoleUI {
    private final Scanner scanner;
    private final UserService userService;

    public void run() {
        showMenu();

        String input = scanner.nextLine();

        switch (input) {
            case "1" -> signIn();
            case "2" -> signUp();
            case "3" -> findById();
            case "4" -> changeDescription();
            case "0" -> stop();
        }
    }

    private void showMenu() {
        System.out.println("\nMenu: ");
        System.out.println("1. Sign In. ");
        System.out.println("2. Sign Up. ");
        System.out.println("3. Find User by ID. ");
        System.out.println("4. Change Profile Description by email. ");
        System.out.println("0. Exit. ");

        System.out.print("\nSelect the option: ");
    }

    private void signIn() {
        System.out.print("\nEnter user email: ");
        String email = scanner.nextLine();
        System.out.print("Enter user password: ");
        String password = scanner.nextLine();

        if (userService.signIn(email, password)) {
            System.out.println("\nSigned in! ");
        } else {
            System.out.println("\nIncorrect user email or password. ");
        }
    }

    private void signUp() {
        System.out.print("\nEnter user name: ");
        String name = scanner.nextLine();
        System.out.print("Enter user email: ");
        String email = scanner.nextLine();
        System.out.print("Enter user password: ");
        String password = scanner.nextLine();
        System.out.print("Enter user description: ");
        String description = scanner.nextLine();

        User user = new User(name, email, password, description);

        userService.signUp(user);
    }

    private void findById() {
        System.out.print("\nEnter user ID: ");
        String id = scanner.nextLine();

        System.out.println(userService.findById(id));
    }

    private void changeDescription() {
        System.out.print("\nEnter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter new user description: ");
        String description = scanner.nextLine();

        System.out.println(userService.changeDescription(email, description));
    }

    private void stop() { System.exit(0); }

    public UserConsoleUI(UserService userService) {
        this.scanner = new Scanner(System.in);
        this.userService = userService;
    }
}
