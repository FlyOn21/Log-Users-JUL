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

public class GetUserByIdView {
    private final Scanner scanner;
    private final DbConnectInit connection;
    private boolean isCorrect = false;

    public GetUserByIdView(Scanner scanner, DbConnectInit connection) {
        this.scanner = scanner;
        this.connection = connection;
    }

    public void getUserByIdProcessing() {
        while (true) {
            String createUserMenu = """
                    
                    View current users by id menu

                    1. View current users by id without exclude_columns
                    2. View current users by id with exclude_columns
                    3. Back
                    """;
            System.out.println(createUserMenu);
            System.out.print("Select action: ");
            String action = scanner.nextLine();
            switch (action) {
                case "1":
                    ReadParams readParamsWithout = new ReadParams();
                    while (!isCorrect) {
                        System.out.print("Enter id: ");
                        String id = scanner.nextLine();
                        ValidateAnswer validateId = readParamsWithout.setId(id);
                        isCorrect = ValidateUtils.validateProcessing(validateId);
                    }
                    isCorrect = false;
                    ReadController readControllerWithout = new ReadController();
                    dataProcessingById(connection, readParamsWithout, readControllerWithout);
                    break;
                case "2":
                    ReadParams readParamsWith = new ReadParams();
                    while (!isCorrect) {
                        System.out.print("Enter id: ");
                        String id = scanner.nextLine();
                        ValidateAnswer validateId = readParamsWith.setId(id);
                        isCorrect = ValidateUtils.validateProcessing(validateId);
                    }
                    isCorrect = false;
                    while (!isCorrect) {
                        System.out.print("Enter exclude_columns (string comma separated or empty string): ");
                        String excludeColumns = scanner.nextLine();
                        ValidateAnswer excludeColumnsAnswer = readParamsWith.setExcludeColumns(excludeColumns);
                        isCorrect = ValidateUtils.validateProcessing(excludeColumnsAnswer);
                    }
                    isCorrect = false;
                    ReadController readControllerWith = new ReadController();
                    dataProcessingById(connection, readParamsWith, readControllerWith);
                    break;
                case "3":
                    System.out.println("Back..");
                    return;
                default:
                    System.out.println("Invalid action!");
            }
        }
    }

    private void dataProcessingById(DbConnectInit connection, ReadParams readParams, ReadController readController){
        try {
            Optional<List<User>> resultByIDWithEC = readController.controllerReadById(connection, readParams);
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
