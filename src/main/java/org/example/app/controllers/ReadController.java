package org.example.app.controllers;

import org.example.app.db_connect.DbConnectInit;
import org.example.app.entity.User;
import org.example.app.model.GetUsersModel;
import org.example.app.utils.ReadParams;

import java.util.List;
import java.util.Optional;

public class ReadController {
    public Optional<List<User>> controllerReadAll(DbConnectInit connect, ReadParams readParams) {
        GetUsersModel getUsersModel = new GetUsersModel(readParams);
        return getUsersModel.getUsers(connect);
    }

    public Optional<List<User>> controllerReadById(DbConnectInit connect, ReadParams readParams) {
        GetUsersModel getUsersModel = new GetUsersModel(readParams);
        return getUsersModel.getUser(connect);
    }
}
