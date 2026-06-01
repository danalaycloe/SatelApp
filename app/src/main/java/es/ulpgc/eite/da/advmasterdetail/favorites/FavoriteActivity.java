package es.ulpgc.eite.da.advmasterdetail.favorites;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import es.ulpgc.eite.da.advmasterdetail.R;
import es.ulpgc.eite.da.advmasterdetail.app.CatalogMediator;
import es.ulpgc.eite.da.advmasterdetail.data.ProductItem;
import es.ulpgc.eite.da.advmasterdetail.database.CatalogDatabase;
import es.ulpgc.eite.da.advmasterdetail.database.FavoriteEntity;
import es.ulpgc.eite.da.advmasterdetail.product.ProductDetailActivity;
import es.ulpgc.eite.da.advmasterdetail.products.ProductListAdapter;

public class FavoriteActivity extends AppCompatActivity {

    private CatalogDatabase database;

    private ProductListAdapter adapter;

    private CatalogMediator mediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.favoritos);

        mediator = CatalogMediator.getInstance();

        database = Room.databaseBuilder(
                getApplicationContext(),
                CatalogDatabase.class,
                "catalog.db"
        ).allowMainThreadQueries().build();

        RecyclerView recyclerView =
                findViewById(R.id.favoritesRecyclerView);

        adapter = new ProductListAdapter(view -> {

            ProductItem item = (ProductItem) view.getTag();

            mediator.setProduct(item);

            Intent intent =
                    new Intent(this, ProductDetailActivity.class);

            startActivity(intent);
        });

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );

        recyclerView.setAdapter(adapter);

        loadFavorites();
    }

    private void loadFavorites() {

        int userId = getSharedPreferences(
                "session",
                MODE_PRIVATE
        ).getInt("userId", -1);

        List<FavoriteEntity> favorites =
                database.favoriteDao().getFavoritesByUser(userId);

        List<ProductItem> products = new ArrayList<>();

        for(FavoriteEntity favorite : favorites){

            ProductItem product =
                    database.productDao().loadProduct(favorite.productId);

            if(product != null){
                products.add(product);
            }
        }

        adapter.setItems(products);
    }
}