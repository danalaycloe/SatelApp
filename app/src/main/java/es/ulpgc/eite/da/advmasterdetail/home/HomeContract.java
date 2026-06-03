package es.ulpgc.eite.da.advmasterdetail.home;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.advmasterdetail.data.CategoryItem;

public interface HomeContract {

    interface View {
        void injectPresenter(Presenter presenter);
        void navigateToProductList();
        void navigateToFavorites();
        void showGuestFavoritesMessage();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);
        void injectModel(Model model);

        void onCreateCalled();
        void onRecreateCalled();
        void onPauseCalled();

        void onSatellitesClicked();
        void onMissionsClicked();
        void onFavoritesClicked(boolean isLoggedIn);
    }

    interface Model {
        void loadCatalog();
        CategoryItem getSatellitesCategory();
        CategoryItem getMissionsCategory();
    }
}