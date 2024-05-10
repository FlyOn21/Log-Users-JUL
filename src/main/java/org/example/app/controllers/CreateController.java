package org.example.app.controllers;

import org.example.app.db_connect.DbConnectInit;
import org.example.app.model.CreateUserModel;
import org.example.app.utils.ActionAnswer;
import org.example.app.utils.CreateUpdateParams;

public class CreateController {

    public ActionAnswer controllerCreateUser(DbConnectInit connect, CreateUpdateParams createParams) {
        CreateUserModel createUserModel = new CreateUserModel();
        return createUserModel.createUser(connect, createParams);
    }
}
