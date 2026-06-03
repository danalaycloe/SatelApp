package es.ulpgc.eite.da.advmasterdetail.register;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.advmasterdetail.app.CatalogMediator;

public class RegisterPresenter implements RegisterContract.Presenter {

    private WeakReference<RegisterContract.View> view;
    private RegisterContract.Model model;
    private RegisterState state;
    private CatalogMediator mediator;

    public RegisterPresenter(CatalogMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void onCreateCalled() {
        state = new RegisterState();
        view.get().displayRegisterData(state);
    }

    @Override
    public void onRecreateCalled() {
        state = mediator.getRegisterState();

        if (state == null) {
            state = new RegisterState();
        }

        view.get().displayRegisterData(state);
    }

    @Override
    public void onPauseCalled() {
        mediator.setRegisterState(state);
    }

    @Override
    public void updateData(String username, String password, String confirmPassword) {
        state.username = username;
        state.password = password;
        state.confirmPassword = confirmPassword;
    }

    @Override
    public void onRegisterClicked() {

        if(state.username.isEmpty()
                || state.password.isEmpty()
                || state.confirmPassword.isEmpty()){

            view.get().showEmptyFieldsError();
            return;
        }

        if(!state.password.equals(state.confirmPassword)){

            view.get().showDifferentPasswordsError();
            return;
        }

        if(model.userExists(state.username)){

            view.get().showUserAlreadyExistsError();
            return;
        }

        model.registerUser(state.username, state.password);

        view.get().showRegisterSuccess();
        view.get().navigateToLogin();
    }

    @Override
    public void onLoginClicked() {
        view.get().navigateToLogin();
    }

    @Override
    public void injectView(WeakReference<RegisterContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(RegisterContract.Model model) {
        this.model = model;
    }
}