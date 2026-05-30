package es.ulpgc.eite.da.advmasterdetail.login;

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
import es.ulpgc.eite.da.advmasterdetail.home.HomeActivity;
import es.ulpgc.eite.da.advmasterdetail.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity {

    private CatalogDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);

        database = Room.databaseBuilder(getApplicationContext(), CatalogDatabase.class, "catalog.db").allowMainThreadQueries().build(); //Abre el Room

        //ahora lee lo que se ha escrito en el campo de texto
        EditText usernameInput = findViewById(R.id.usernameInput);

        EditText passwordInput = findViewById(R.id.passwordInput);

        Button loginButton = findViewById(R.id.loginButton);

        TextView registerText = findViewById(R.id.registerText);

        TextView invitadoText = findViewById(R.id.invitadoText);

        loginButton.setOnClickListener(view -> {

            String username = usernameInput.getText().toString();

            String password =passwordInput.getText().toString();

            UserEntity user = database.userDao().login(username, password);

            if(user != null){

                Toast.makeText(this, "Inicio de sesión correcto", Toast.LENGTH_SHORT).show();//Muestra un mensaje de inicio de sesión correcto

                Intent intent = new Intent(this, HomeActivity.class);

                startActivity(intent);

            } else {

                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();//Muestra un mensaje de eeror de inicio de sesión
            }
        });

        registerText.setOnClickListener(view -> {

            Intent intent = new Intent(this, RegisterActivity.class);

            startActivity(intent);
        });

        invitadoText.setOnClickListener(view -> {

            Intent intent = new Intent(this, HomeActivity.class);

            startActivity(intent);
        });
    }
}