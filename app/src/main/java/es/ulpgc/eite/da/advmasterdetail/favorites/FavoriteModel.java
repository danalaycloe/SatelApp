package es.ulpgc.eite.da.advmasterdetail.favorites;

import android.content.Context;

import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import es.ulpgc.eite.da.advmasterdetail.data.ProductItem;
import es.ulpgc.eite.da.advmasterdetail.database.CatalogDatabase;
import es.ulpgc.eite.da.advmasterdetail.database.FavoriteEntity;

public class FavoriteModel implements FavoriteContract.Model {

    private CatalogDatabase database;

    public FavoriteModel(Context context) {
        database = Room.databaseBuilder(
                context,
                CatalogDatabase.class,
                "catalog.db"
        ).allowMainThreadQueries().build();
    }

    @Override
    public List<ProductItem> getFavoriteProducts(int userId) {

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

        return products;
    }
}