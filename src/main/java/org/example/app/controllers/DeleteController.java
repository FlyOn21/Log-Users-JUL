package org.example.app.controllers;

import org.example.app.db_connect.DbConnectInit;
import org.example.app.entity.User;
import org.example.app.model.DeleteUserModel;
import org.example.app.utils.ActionAnswer;

public class DeleteController {
    public ActionAnswer deleteUser(DbConnectInit connection, User user) {
        DeleteUserModel deleteUserModel = new DeleteUserModel(user);
        return deleteUserModel.deleteUser(connection);
    }
}
