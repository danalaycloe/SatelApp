package es.ulpgc.eite.da.advmasterdetail.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorites")
public class FavoriteEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public int userId;

    public int productId;
}