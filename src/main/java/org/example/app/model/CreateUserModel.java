package org.example.app.model;

import org.example.app.db_connect.DbConnectInit;
import org.example.app.entity.User;
import org.example.app.repository.UserRepository;
import org.example.app.utils.ActionAnswer;
import org.example.app.utils.CreateUpdateParams;


public class CreateUserModel {

    public CreateUserModel() {
    }

    public ActionAnswer createUser (DbConnectInit connection, CreateUpdateParams createParams) {
        User newUser = new User(createParams.getFirstName(), createParams.getLastName(), createParams.getEmail(), createParams.getPhone());
        UserRepository userRepository = new UserRepository(connection);
        return userRepository.create(newUser);
    }

}
