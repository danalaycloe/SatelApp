package es.ulpgc.eite.da.advmasterdetail.favorites;

import java.lang.ref.WeakReference;
import java.util.List;

import es.ulpgc.eite.da.advmasterdetail.app.CatalogMediator;
import es.ulpgc.eite.da.advmasterdetail.data.ProductItem;

public class FavoritePresenter implements FavoriteContract.Presenter {

    private WeakReference<FavoriteContract.View> view;
    private FavoriteContract.Model model;
    private FavoriteState state;
    private CatalogMediator mediator;

    public FavoritePresenter(CatalogMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void onCreateCalled() {
        state = new FavoriteState();
    }

    @Override
    public void onRecreateCalled() {
        state = mediator.getFavoriteState();

        if(state == null){
            state = new FavoriteState();
        }

        view.get().displayFavoriteData(state);
    }

    @Override
    public void onPauseCalled() {
        mediator.setFavoriteState(state);
    }

    @Override
    public void fetchFavoriteData(int userId) {

        List<ProductItem> products =
                model.getFavoriteProducts(userId);

        state.products = products;

        view.get().displayFavoriteData(state);
    }

    @Override
    public void selectedProductData(ProductItem item) {
        mediator.setProduct(item);
        view.get().navigateToProductDetailScreen();
    }

    @Override
    public void injectView(WeakReference<FavoriteContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(FavoriteContract.Model model) {
        this.model = model;
    }
}