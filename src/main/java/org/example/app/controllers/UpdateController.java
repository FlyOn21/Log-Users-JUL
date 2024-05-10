package org.example.app.controllers;

import org.example.app.db_connect.DbConnectInit;
import org.example.app.entity.User;
import org.example.app.model.UpdateUserModel;
import org.example.app.utils.ActionAnswer;
import org.example.app.utils.CreateUpdateParams;

public class UpdateController {


    public ActionAnswer updateUser(DbConnectInit connection, User user, CreateUpdateParams createUpdateParams) {
        UpdateUserModel updateUserModel = new UpdateUserModel(user, createUpdateParams);
        return updateUserModel.updateUser(connection);

    }
}
