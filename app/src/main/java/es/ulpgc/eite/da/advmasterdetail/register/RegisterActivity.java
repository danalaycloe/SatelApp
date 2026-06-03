package es.ulpgc.eite.da.advmasterdetail.register;

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
import es.ulpgc.eite.da.advmasterdetail.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View {

    private RegisterContract.Presenter presenter;

    private EditText usernameInput;
    private EditText passwordInput;
    private EditText confirmPasswordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register);

        usernameInput = findViewById(R.id.registerUsernameInput);//lee lo que se ha escrito en el campo de texto
        passwordInput = findViewById(R.id.registerPasswordInput);
        confirmPasswordInput = findViewById(R.id.registerConfirmPasswordInput);

        Button registerButton = findViewById(R.id.registerButton);
        TextView loginText = findViewById(R.id.loginText);

        presenter = new RegisterPresenter(CatalogMediator.getInstance());
        presenter.injectView(new WeakReference<>(this));
        presenter.injectModel(new RegisterModel(getApplicationContext()));

        if(savedInstanceState == null){
            presenter.onCreateCalled();
        } else {
            presenter.onRecreateCalled();
        }

        registerButton.setOnClickListener(view -> {

            presenter.updateData(
                    usernameInput.getText().toString(),
                    passwordInput.getText().toString(),
                    confirmPasswordInput.getText().toString()
            );

            presenter.onRegisterClicked();
        });

        loginText.setOnClickListener(view -> {
            presenter.onLoginClicked();
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        presenter.updateData(
                usernameInput.getText().toString(),
                passwordInput.getText().toString(),
                confirmPasswordInput.getText().toString()
        );

        presenter.onPauseCalled();
    }

    @Override
    public void displayRegisterData(RegisterViewModel viewModel) {
        usernameInput.setText(viewModel.username);
        passwordInput.setText(viewModel.password);
        confirmPasswordInput.setText(viewModel.confirmPassword);
    }

    @Override
    public void navigateToLogin() {

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showEmptyFieldsError() {
        Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDifferentPasswordsError() {
        Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUserAlreadyExistsError() {
        Toast.makeText(this, "Ese usuario ya existe", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRegisterSuccess() {
        Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void injectPresenter(RegisterContract.Presenter presenter) {
        this.presenter = presenter;
    }
}