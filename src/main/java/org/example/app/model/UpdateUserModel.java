package org.example.app.model;

import org.example.app.db_connect.DbConnectInit;
import org.example.app.entity.User;
import org.example.app.repository.UserRepository;
import org.example.app.utils.ActionAnswer;
import org.example.app.utils.CreateUpdateParams;

public class UpdateUserModel {
    private final User user;

    public UpdateUserModel(User user, CreateUpdateParams createUpdateParams) {
        this.user = user;
        this.user.setFirstName(createUpdateParams.getFirstName());
        this.user.setLastName(createUpdateParams.getLastName());
        this.user.setEmail(createUpdateParams.getEmail());
        this.user.setPhone(createUpdateParams.getPhone());
    }

    public ActionAnswer updateUser(DbConnectInit connection) {
        UserRepository userRepository = new UserRepository(connection);
        return userRepository.update(this.user);
    }

}
