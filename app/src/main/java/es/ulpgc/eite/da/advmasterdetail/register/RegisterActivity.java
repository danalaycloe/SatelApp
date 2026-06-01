package es.ulpgc.eite.da.advmasterdetail.register;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import es.ulpgc.eite.da.advmasterdetail.R;
import es.ulpgc.eite.da.advmasterdetail.database.CatalogDatabase;
import es.ulpgc.eite.da.advmasterdetail.database.UserEntity;
import es.ulpgc.eite.da.advmasterdetail.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity {

    private CatalogDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register);

        database = Room.databaseBuilder(getApplicationContext(), CatalogDatabase.class, "catalog.db").allowMainThreadQueries().build();//Abre el Room

        EditText usernameInput = findViewById(R.id.registerUsernameInput);//lee lo que se ha escrito en el campo de texto

        EditText passwordInput = findViewById(R.id.registerPasswordInput);

        EditText confirmPasswordInput = findViewById(R.id.registerConfirmPasswordInput);

        Button registerButton = findViewById(R.id.registerButton);

        TextView loginText = findViewById(R.id.loginText);

        registerButton.setOnClickListener(view -> {

            String username = usernameInput.getText().toString();

            String password = passwordInput.getText().toString();

            String confirmPassword = confirmPasswordInput.getText().toString();

            if(username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){

                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();

                return;
            }

            if(!password.equals(confirmPassword)){

                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();

                return;
            }

            UserEntity existingUser = database.userDao().getUserByUsername(username);

            if(existingUser != null){

                Toast.makeText(this, "Ese usuario ya existe", Toast.LENGTH_SHORT).show();

                return;
            }

            UserEntity user = new UserEntity(); //Crea un nuevo usuario

            user.username = username;
            user.password = password;

            database.userDao().insertUser(user);//Guarda el usuario en la tabla de datos de users

            Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, LoginActivity.class);

            startActivity(intent);

            finish();
        });

        loginText.setOnClickListener(view -> {

            Intent intent = new Intent(this, LoginActivity.class);

            startActivity(intent);

            finish();
        });
    }
}