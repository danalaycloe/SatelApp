package es.ulpgc.eite.da.advmasterdetail.home;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.advmasterdetail.app.CatalogMediator;

public class HomePresenter implements HomeContract.Presenter {

    private WeakReference<HomeContract.View> view;
    private HomeContract.Model model;
    private HomeState state;
    private CatalogMediator mediator;

    public HomePresenter(CatalogMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void onCreateCalled() {
        state = new HomeState();
        model.loadCatalog();
    }

    @Override
    public void onRecreateCalled() {
        state = mediator.getHomeState();

        if(state == null){
            state = new HomeState();
        }

        model.loadCatalog();
    }

    @Override
    public void onPauseCalled() {
        mediator.setHomeState(state);
    }

    @Override
    public void onSatellitesClicked() {
        mediator.setCategory(model.getSatellitesCategory());
        view.get().navigateToProductList();
    }

    @Override
    public void onMissionsClicked() {
        mediator.setCategory(model.getMissionsCategory());
        view.get().navigateToProductList();
    }

    @Override
    public void onFavoritesClicked(boolean isLoggedIn) {

        if(!isLoggedIn){
            view.get().showGuestFavoritesMessage();
            return;
        }

        view.get().navigateToFavorites();
    }

    @Override
    public void injectView(WeakReference<HomeContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(HomeContract.Model model) {
        this.model = model;
    }
}