package es.ulpgc.eite.da.advmasterdetail.home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.eite.da.advmasterdetail.R;
import es.ulpgc.eite.da.advmasterdetail.app.CatalogMediator;
import es.ulpgc.eite.da.advmasterdetail.data.CategoryItem;
import es.ulpgc.eite.da.advmasterdetail.favorites.FavoriteActivity;
import es.ulpgc.eite.da.advmasterdetail.products.ProductListActivity;
import es.ulpgc.eite.da.advmasterdetail.data.CatalogRepository;
import es.ulpgc.eite.da.advmasterdetail.data.RepositoryContract;

public class HomeActivity extends AppCompatActivity {

    private CatalogMediator mediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home);
        RepositoryContract repository = CatalogRepository.getInstance(this);
        repository.loadCatalog(false, error -> {
        });

        mediator = CatalogMediator.getInstance();

        Button satellitesButton = findViewById(R.id.communicationsButton);
        Button missionsButton = findViewById(R.id.missionsButton);
        Button favoritesButton = findViewById(R.id.favoritesButton);

        favoritesButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, FavoriteActivity.class);
            startActivity(intent);
        });


        satellitesButton.setOnClickListener(view -> {
            CategoryItem category = new CategoryItem();
            category.id = 1;
            category.content = "Satélites";
            mediator.setCategory(category);

            Intent intent = new Intent(this, ProductListActivity.class);
            startActivity(intent);
        });

        missionsButton.setOnClickListener(view -> {
            CategoryItem category = new CategoryItem();
            category.id = 2;
            category.content = "Misiones";
            mediator.setCategory(category);

            Intent intent = new Intent(this, ProductListActivity.class);
            startActivity(intent);
        });
    }
}