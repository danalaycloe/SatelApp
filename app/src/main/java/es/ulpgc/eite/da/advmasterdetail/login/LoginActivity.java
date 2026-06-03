package es.ulpgc.eite.da.advmasterdetail.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.advmasterdetail.R;
import es.ulpgc.eite.da.advmasterdetail.app.CatalogMediator;
import es.ulpgc.eite.da.advmasterdetail.database.UserEntity;
import es.ulpgc.eite.da.advmasterdetail.home.HomeActivity;
import es.ulpgc.eite.da.advmasterdetail.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private LoginContract.Presenter presenter;

    private EditText usernameInput;
    private EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);

        usernameInput = findViewById(R.id.usernameInput);
        passwordInput = findViewById(R.id.passwordInput);

        Button loginButton = findViewById(R.id.loginButton);
        TextView registerText = findViewById(R.id.registerText);
        TextView invitadoText = findViewById(R.id.invitadoText);

        presenter = new LoginPresenter(CatalogMediator.getInstance());
        presenter.injectView(new WeakReference<>(this));
        presenter.injectModel(new LoginModel(getApplicationContext()));

        if(savedInstanceState == null){
            presenter.onCreateCalled();
        } else {
            presenter.onRecreateCalled();
        }

        loginButton.setOnClickListener(view -> {
            presenter.updateData(usernameInput.getText().toString(), passwordInput.getText().toString());

            presenter.onLoginClicked();
        });

        registerText.setOnClickListener(view -> {
            presenter.onRegisterClicked();
        });

        invitadoText.setOnClickListener(view -> {
            presenter.onGuestClicked();
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        presenter.updateData(usernameInput.getText().toString(), passwordInput.getText().toString());

        presenter.onPauseCalled();
    }

    @Override
    public void displayLoginData(LoginViewModel viewModel) {

        usernameInput.setText(viewModel.username);
        passwordInput.setText(viewModel.password);
    }

    @Override
    public void navigateToHome(UserEntity user) {

        getSharedPreferences("session", MODE_PRIVATE).edit().putBoolean("isLoggedIn", true).putInt("userId", user.id).apply();

        Toast.makeText(this, "Inicio de sesión correcto", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToHomeAsGuest() {

        getSharedPreferences("session", MODE_PRIVATE).edit().putBoolean("isLoggedIn", false).putInt("userId", -1).apply();

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToRegister() {

        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void showLoginError() {

        Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void injectPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }
}