package es.ulpgc.eite.da.advmasterdetail.register;

import java.lang.ref.WeakReference;

public interface RegisterContract {

    interface View {
        void injectPresenter(Presenter presenter);
        void displayRegisterData(RegisterViewModel viewModel);

        void navigateToLogin();

        void showEmptyFieldsError();
        void showDifferentPasswordsError();
        void showUserAlreadyExistsError();
        void showRegisterSuccess();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);
        void injectModel(Model model);

        void onCreateCalled();
        void onRecreateCalled();
        void onPauseCalled();

        void updateData(String username, String password, String confirmPassword);

        void onRegisterClicked();
        void onLoginClicked();
    }

    interface Model {
        boolean userExists(String username);
        void registerUser(String username, String password);
    }
}