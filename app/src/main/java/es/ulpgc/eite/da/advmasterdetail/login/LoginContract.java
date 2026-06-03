package es.ulpgc.eite.da.advmasterdetail.login;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.advmasterdetail.database.UserEntity;

public interface LoginContract {

    interface View {
        void injectPresenter(Presenter presenter);
        void displayLoginData(LoginViewModel viewModel);
        void navigateToHome(UserEntity user);
        void navigateToHomeAsGuest();
        void navigateToRegister();
        void showLoginError();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);
        void injectModel(Model model);

        void onCreateCalled();
        void onRecreateCalled();
        void onPauseCalled();

        void updateData(String username, String password);
        void onLoginClicked();
        void onGuestClicked();
        void onRegisterClicked();
    }

    interface Model {
        UserEntity login(String username, String password);
    }
}