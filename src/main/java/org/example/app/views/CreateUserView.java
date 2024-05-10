package org.example.app.views;

import org.example.app.controllers.CreateController;
import org.example.app.db_connect.DbConnectInit;
import org.example.app.exceptions.ConnectException;
import org.example.app.utils.ActionAnswer;
import org.example.app.utils.CreateUpdateParams;
import org.example.app.utils.ValidateUtils;
import org.example.app.utils.validate.validate_entity.ValidateAnswer;

import java.util.Scanner;

public class CreateUserView {
    private final Scanner scanner;
    private final DbConnectInit connection;
    private boolean isCorrect = false;

    public CreateUserView(Scanner scanner, DbConnectInit connection) {
        this.scanner = scanner;
        this.connection = connection;
    }

    public void createUserViewProcessing() {
        while (true) {
            String createUserMenu = """
                    
                    Create user menu
                    
                    1. Create user
                    2. Back
                    """;
            System.out.println(createUserMenu);
            System.out.print("Select action: ");
            String action = scanner.nextLine();
            CreateUpdateParams createParams = new CreateUpdateParams();
            switch (action) {
                case "1":
                    while (!isCorrect) {
                        System.out.print("Enter first name: ");
                        String firstName = scanner.nextLine();
                        ValidateAnswer validateFirstName = createParams.setFirstName(firstName);
                        isCorrect = ValidateUtils.validateProcessing(validateFirstName);
                    }
                    isCorrect = false;
                    while (!isCorrect) {
                        System.out.print("Enter last name: ");
                        String lastName = scanner.nextLine();
                        ValidateAnswer validateLastName = createParams.setLastName(lastName);
                        isCorrect = ValidateUtils.validateProcessing(validateLastName);
                    }
                    isCorrect = false;
                    while (!isCorrect) {
                        try{
                            System.out.print("Enter email: ");
                            String email = scanner.nextLine();
                            ValidateAnswer validateEmail = createParams.setEmail(email, connection, false);
                            isCorrect = ValidateUtils.validateProcessing(validateEmail);
                        } catch (ConnectException e) {
                            System.out.println("Error: " + e.getMessage() + " Try again");
                            return;
                        }
                    }
                    isCorrect = false;
                    while (!isCorrect) {
                        System.out.print("Enter phone: ");
                        String phone = scanner.nextLine();
                        ValidateAnswer validatePhone = createParams.setPhone(phone);
                        isCorrect = ValidateUtils.validateProcessing(validatePhone);
                    }
                    isCorrect = false;
                    CreateController controllerCreateUser = new CreateController();
                    ActionAnswer create = controllerCreateUser.controllerCreateUser(connection, createParams);
                    if (create.isSuccess()) {
                        System.out.println(create.getSuccessMsg());
                    } else {
                        create.getErrors().forEach(System.out::println);
                    }
                    break;
                case "2":
                    System.out.println("Back..");
                    return;
                default:
                    System.out.println("Invalid action");
                    break;
            }
        }
    }
}

