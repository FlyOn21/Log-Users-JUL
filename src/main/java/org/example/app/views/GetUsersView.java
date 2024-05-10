package org.example.app.views;

import org.example.app.controllers.ReadController;
import org.example.app.db_connect.DbConnectInit;
import org.example.app.entity.User;
import org.example.app.exceptions.ConnectException;
import org.example.app.utils.ReadParams;
import org.example.app.utils.ValidateUtils;
import org.example.app.utils.validate.validate_entity.ValidateAnswer;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class GetUsersView {
    private final Scanner scanner;
    private final DbConnectInit connection;
    private boolean isCorrect = false;

    public GetUsersView(Scanner scanner, DbConnectInit connection) {
        this.scanner = scanner;
        this.connection = connection;
    }

    public void getUsersViewProcessing() {
        ReadParams readParams = new ReadParams();
        while (true) {
            String createUserMenu = """
                    
                    View all users menu

                    1. View all users without limit, offset, exclude_columns
                    2. View all users with limit, offset, exclude_columns
                    3. Back
                    """;
            System.out.println(createUserMenu);
            System.out.print("Select action: ");
            String action = scanner.nextLine();
            switch (action) {
                case "1":
                    ReadController readControllerWithout = new ReadController();
                    dataProcessingAll(connection, readParams, readControllerWithout);
                    break;
                case "2":
                    ReadController readControllerWith = new ReadController();
                    while (!isCorrect) {
                        System.out.print("Enter limit: ");
                        String limit = scanner.nextLine();
                        ValidateAnswer limitAnswer = readParams.setLimit(limit);
                        isCorrect = ValidateUtils.validateProcessing(limitAnswer);
                    }
                    isCorrect = false;
                    while (!isCorrect) {
                        System.out.print("Enter offset: ");
                        String offset = scanner.nextLine();
                        ValidateAnswer offsetAnswer = readParams.setOffset(offset);
                        isCorrect = ValidateUtils.validateProcessing(offsetAnswer);
                    }
                    isCorrect = false;
                    while (!isCorrect) {
                        System.out.print("Enter exclude_columns (string comma separated or empty string): ");
                        String excludeColumns = scanner.nextLine();
                        if (!excludeColumns.isEmpty()) {
                            ValidateAnswer excludeColumnsAnswer = readParams.setExcludeColumns(excludeColumns);
                            isCorrect = ValidateUtils.validateProcessing(excludeColumnsAnswer);
                        } else {
                            isCorrect = true;
                        }
                    }
                    isCorrect = false;
                    dataProcessingAll(connection, readParams, readControllerWith);
                    break;
                case "3":
                    System.out.println("Back..");
                    return;
                default:
                    System.out.println("Invalid action");
                    break;
            }
        }
    }

    private void dataProcessingAll(DbConnectInit connection, ReadParams readParams, ReadController readController){
        try {
            Optional<List<User>> resultByIDWithEC = readController.controllerReadAll(connection, readParams);
            if (resultByIDWithEC.isPresent()) {
                resultByIDWithEC.get().forEach(System.out::println);
            } else {
                System.out.println("No users found");
            }
        } catch (ConnectException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
