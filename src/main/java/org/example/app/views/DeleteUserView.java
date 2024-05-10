package org.example.app.views;

import org.example.app.controllers.DeleteController;
import org.example.app.controllers.ReadController;
import org.example.app.db_connect.DbConnectInit;
import org.example.app.entity.User;
import org.example.app.utils.ActionAnswer;
import org.example.app.utils.ReadParams;
import org.example.app.utils.ValidateUtils;
import org.example.app.utils.validate.validate_entity.ValidateAnswer;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class DeleteUserView {
    private final Scanner scanner;
    private final DbConnectInit connection;
    private boolean isCorrect = false;

    public DeleteUserView(Scanner scanner, DbConnectInit connection) {
        this.scanner = scanner;
        this.connection = connection;
    }

    public void deleteUserViewProcessing() {
        while (true) {
            String createUserMenu = """

                    Delete user menu

                    1. Delete user by id
                    2. Back
                    """;
            System.out.println(createUserMenu);
            System.out.print("Select action: ");
            String action = scanner.nextLine();
            switch (action) {
                case "1":
                    ReadController readController = new ReadController();
                    ReadParams readParams = new ReadParams();
                    while (!isCorrect) {
                        System.out.print("Enter id: ");
                        String id = scanner.nextLine();
                        ValidateAnswer validateId = readParams.setId(id);
                        isCorrect = ValidateUtils.validateProcessing(validateId);
                    }
                    Optional<List<User>> currentUser = readController.controllerReadById(connection, readParams);
                    if (currentUser.isPresent()) {
                        User user = currentUser.get().getFirst();
                        System.out.println("Current user: " + user);
                        System.out.print("Are you sure you want to delete this user? (yes/no): ");
                        String answer = scanner.nextLine();
                        if (answer.equals("yes")) {
                            DeleteController deleteController = new DeleteController();
                            ActionAnswer deleteUserResult = deleteController.deleteUser(connection, user);
                            System.out.println(deleteUserResult.getSuccessMsg());
                        } else {
                            System.out.println("User not deleted");
                        }

                    } else {
                        System.out.println("No users found");
                    }
                    isCorrect = false;
                    break;
                case "2":
                    System.out.println("Back ...");
                    return;
                default:
                    System.out.println("Invalid action");
                    break;
            }
        }
    }
}
