package es.ulpgc.eite.da.advmasterdetail.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import es.ulpgc.eite.da.advmasterdetail.data.CategoryItem;
import es.ulpgc.eite.da.advmasterdetail.data.ProductItem;


@Database(entities = {CategoryItem.class, ProductItem.class ,UserEntity.class,
        FavoriteEntity.class}, version = 3)
public abstract class CatalogDatabase extends RoomDatabase {

  public abstract CategoryDao categoryDao();
  public abstract ProductDao productDao();
  public abstract UserDao userDao();
  public abstract FavoriteDao favoriteDao();
}
