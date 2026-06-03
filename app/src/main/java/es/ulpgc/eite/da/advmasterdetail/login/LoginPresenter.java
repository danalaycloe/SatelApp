package es.ulpgc.eite.da.advmasterdetail.login;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.advmasterdetail.app.CatalogMediator;
import es.ulpgc.eite.da.advmasterdetail.database.UserEntity;

public class LoginPresenter implements LoginContract.Presenter {

    private WeakReference<LoginContract.View> view;
    private LoginContract.Model model;
    private LoginState state;
    private CatalogMediator mediator;

    public LoginPresenter(CatalogMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void onCreateCalled() {
        state = new LoginState();
        view.get().displayLoginData(state);
    }

    @Override
    public void onRecreateCalled() {
        state = mediator.getLoginState();

        if (state == null) {
            state = new LoginState();
        }

        view.get().displayLoginData(state);
    }

    @Override
    public void onPauseCalled() {
        mediator.setLoginState(state);
    }

    @Override
    public void updateData(String username, String password) {
        state.username = username;
        state.password = password;
    }

    @Override
    public void onLoginClicked() {
        UserEntity user = model.login(state.username, state.password);

        if (user != null) {
            view.get().navigateToHome(user);
        } else {
            view.get().showLoginError();
        }
    }

    @Override
    public void onGuestClicked() {
        view.get().navigateToHomeAsGuest();
    }

    @Override
    public void onRegisterClicked() {
        view.get().navigateToRegister();
    }

    @Override
    public void injectView(WeakReference<LoginContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(LoginContract.Model model) {
        this.model = model;
    }
}