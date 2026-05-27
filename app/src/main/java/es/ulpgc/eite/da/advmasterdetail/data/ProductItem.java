package es.ulpgc.eite.da.advmasterdetail.data;


import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity(
    tableName = "products",
    foreignKeys = @ForeignKey(
        entity = CategoryItem.class,
        parentColumns = "id",
        childColumns = "category_id",
        onDelete = CASCADE
    )
)
public class ProductItem {

  @PrimaryKey
  public int id;

  public String content;
  public String details;
  public String picture;
  public String type;
  public String agency;
  public String country;
  public int year;
  public String orbit;
  public String status;
  public String curiosity;

  @ColumnInfo(name = "category_id")
  public int categoryId;


  @Override
  public String toString() {
    return content;
  }
}