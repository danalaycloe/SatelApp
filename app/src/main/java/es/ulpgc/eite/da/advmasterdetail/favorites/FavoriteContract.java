package es.ulpgc.eite.da.advmasterdetail.favorites;

import java.lang.ref.WeakReference;
import java.util.List;

import es.ulpgc.eite.da.advmasterdetail.data.ProductItem;

public interface FavoriteContract {

    interface View {
        void injectPresenter(Presenter presenter);
        void displayFavoriteData(FavoriteViewModel viewModel);
        void navigateToProductDetailScreen();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);
        void injectModel(Model model);

        void onCreateCalled();
        void onRecreateCalled();
        void onPauseCalled();

        void fetchFavoriteData(int userId);
        void selectedProductData(ProductItem item);
    }

    interface Model {
        List<ProductItem> getFavoriteProducts(int userId);
    }
}