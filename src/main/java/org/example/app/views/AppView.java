package org.example.app.views;

import org.example.app.controllers.DBController;
import org.example.app.db_connect.DbConnectInit;

import java.util.Scanner;

public class AppView {
    private final Scanner scanner;
    private final DbConnectInit connection;

    public AppView() {
        this.scanner = new Scanner(System.in);
        DBController appController = new DBController();
        this.connection = appController.connect();
    }
    public void appViewProcessing() {
        String title = "Project JDBC User";
        System.out.println(title);
        while (true) {
            String menu = """
                    1. Get all users
                    2. Create user
                    3. Find user by id
                    4. Update user
                    5. Delete user
                    6. Exit
                    """;
            System.out.println(menu);
            System.out.print("Select action: ");
            String action = scanner.nextLine();
//            CRUDController crudController = new CRUDController();
            switch (action) {
                case "1":
                    GetUsersView getUsersView = new GetUsersView(scanner, connection);
                    getUsersView.getUsersViewProcessing();
                    break;
                case "2":
                    CreateUserView createUserView = new CreateUserView(scanner, connection);
                    createUserView.createUserViewProcessing();
                    break;
                case "3":
                    GetUserByIdView getUserByIdView = new GetUserByIdView(scanner, connection);
                    getUserByIdView.getUserByIdProcessing();
                    break;
                case "4":
                    UpdateUserView updateUserView = new UpdateUserView(scanner, connection);
                    updateUserView.createUserViewProcessing();
                    break;
                case "5":
                    DeleteUserView deleteUserView = new DeleteUserView(scanner, connection);
                    deleteUserView.deleteUserViewProcessing();
                    break;
                case "6":
                    System.out.println("Exit..");
                    return;
                default:
                    System.out.println("Invalid action");
                    break;
            }
        }
    }
}
