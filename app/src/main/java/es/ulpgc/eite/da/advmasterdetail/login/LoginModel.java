package es.ulpgc.eite.da.advmasterdetail.login;

import android.content.Context;

import androidx.room.Room;

import es.ulpgc.eite.da.advmasterdetail.database.CatalogDatabase;
import es.ulpgc.eite.da.advmasterdetail.database.UserEntity;

public class LoginModel implements LoginContract.Model {

    private CatalogDatabase database;
    public LoginModel(Context context) {
        database = Room.databaseBuilder(context, CatalogDatabase.class, "catalog.db").allowMainThreadQueries().build();
    }

    @Override
    public UserEntity login(String username, String password) {
        return database.userDao().login(username, password);
    }
}