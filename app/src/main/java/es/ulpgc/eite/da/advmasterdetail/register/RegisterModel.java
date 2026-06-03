package es.ulpgc.eite.da.advmasterdetail.register;

import android.content.Context;

import androidx.room.Room;

import es.ulpgc.eite.da.advmasterdetail.database.CatalogDatabase;
import es.ulpgc.eite.da.advmasterdetail.database.UserEntity;

public class RegisterModel implements RegisterContract.Model {

    private CatalogDatabase database;

    public RegisterModel(Context context) {
        database = Room.databaseBuilder(context, CatalogDatabase.class, "catalog.db").allowMainThreadQueries().build();
    }

    @Override
    public boolean userExists(String username) {

        UserEntity existingUser = database.userDao().getUserByUsername(username);

        return existingUser != null;
    }

    @Override
    public void registerUser(String username, String password) {

        UserEntity user = new UserEntity(); //Crea un nuevo usuario

        user.username = username;
        user.password = password;

        database.userDao().insertUser(user);//Guarda el usuario en la tabla de datos de users
    }
}