package es.ulpgc.eite.da.advmasterdetail.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Insert
    void insertFavorite(FavoriteEntity favorite);

    @Query("DELETE FROM favorites WHERE userId = :userId AND productId = :productId")
    void deleteFavorite(int userId, int productId);

    @Query("SELECT * FROM favorites WHERE userId = :userId")
    List<FavoriteEntity> getFavoritesByUser(int userId);

    @Query("SELECT COUNT(*) FROM favorites WHERE userId = :userId AND productId = :productId")
    int isFavorite(int userId, int productId);
}