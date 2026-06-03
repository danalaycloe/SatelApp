package es.ulpgc.eite.da.advmasterdetail.home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.advmasterdetail.R;
import es.ulpgc.eite.da.advmasterdetail.app.CatalogMediator;
import es.ulpgc.eite.da.advmasterdetail.favorites.FavoriteActivity;
import es.ulpgc.eite.da.advmasterdetail.products.ProductListActivity;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {

    private HomeContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home);

        presenter = new HomePresenter(CatalogMediator.getInstance());
        presenter.injectView(new WeakReference<>(this));
        presenter.injectModel(new HomeModel(getApplicationContext()));

        Button satellitesButton = findViewById(R.id.communicationsButton);
        Button missionsButton = findViewById(R.id.missionsButton);
        Button favoritesButton = findViewById(R.id.favoritesButton);

        if(savedInstanceState == null){
            presenter.onCreateCalled();
        } else {
            presenter.onRecreateCalled();
        }

        satellitesButton.setOnClickListener(view -> {
            presenter.onSatellitesClicked();
        });

        missionsButton.setOnClickListener(view -> {
            presenter.onMissionsClicked();
        });

        favoritesButton.setOnClickListener(view -> {

            boolean isLoggedIn = getSharedPreferences("session", MODE_PRIVATE)
                    .getBoolean("isLoggedIn", false);

            presenter.onFavoritesClicked(isLoggedIn);
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        presenter.onPauseCalled();
    }

    @Override
    public void navigateToProductList() {
        Intent intent = new Intent(this, ProductListActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToFavorites() {
        Intent intent = new Intent(this, FavoriteActivity.class);
        startActivity(intent);
    }

    @Override
    public void showGuestFavoritesMessage() {
        Toast.makeText(this, "Si quieres acceder a favoritos, inicia sesión", Toast.LENGTH_SHORT
        ).show();
    }

    @Override
    public void injectPresenter(HomeContract.Presenter presenter) {
        this.presenter = presenter;
    }
}