package es.ulpgc.eite.da.advmasterdetail.favorites;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.advmasterdetail.R;
import es.ulpgc.eite.da.advmasterdetail.app.CatalogMediator;
import es.ulpgc.eite.da.advmasterdetail.data.ProductItem;
import es.ulpgc.eite.da.advmasterdetail.product.ProductDetailActivity;
import es.ulpgc.eite.da.advmasterdetail.products.ProductListAdapter;

public class FavoriteActivity extends AppCompatActivity implements FavoriteContract.View {

    private FavoriteContract.Presenter presenter;
    private ProductListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.favoritos);

        presenter = new FavoritePresenter(CatalogMediator.getInstance());
        presenter.injectView(new WeakReference<>(this));
        presenter.injectModel(new FavoriteModel(getApplicationContext()));

        initFavoriteList();

        if(savedInstanceState == null){
            presenter.onCreateCalled();
        } else {
            presenter.onRecreateCalled();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        int userId = getSharedPreferences(
                "session",
                MODE_PRIVATE
        ).getInt("userId", -1);

        presenter.fetchFavoriteData(userId);
    }

    @Override
    protected void onPause() {
        super.onPause();

        presenter.onPauseCalled();
    }

    private void initFavoriteList() {

        RecyclerView recyclerView =
                findViewById(R.id.favoritesRecyclerView);

        adapter = new ProductListAdapter(view -> {

            ProductItem item = (ProductItem) view.getTag();

            presenter.selectedProductData(item);
        });

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void displayFavoriteData(FavoriteViewModel viewModel) {
        adapter.setItems(viewModel.products);
    }

    @Override
    public void navigateToProductDetailScreen() {
        Intent intent =
                new Intent(this, ProductDetailActivity.class);

        startActivity(intent);
    }

    @Override
    public void injectPresenter(FavoriteContract.Presenter presenter) {
        this.presenter = presenter;
    }
}