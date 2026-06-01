package es.ulpgc.eite.da.advmasterdetail.product;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.room.Room;

import es.ulpgc.eite.da.advmasterdetail.database.CatalogDatabase;
import es.ulpgc.eite.da.advmasterdetail.database.FavoriteEntity;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import es.ulpgc.eite.da.advmasterdetail.R;
import es.ulpgc.eite.da.advmasterdetail.data.ProductItem;


public class ProductDetailActivity
    extends AppCompatActivity implements ProductDetailContract.View {

  public static String TAG = "AdvMasterDetail.ProductDetailActivity";
    private CatalogDatabase database;
    private ProductItem currentProduct;

  ProductDetailContract.Presenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.detalles_list);
      database = Room.databaseBuilder(getApplicationContext(), CatalogDatabase.class, "catalog.db").allowMainThreadQueries().build();


    setTitle(R.string.title_product_detail);

    // do the setup
    ProductDetailScreen.configure(this);

    // do some work
    if(savedInstanceState == null) {
      presenter.onCreateCalled();

    }else{
      presenter.onRecreateCalled();
    }
  }

  @Override
  protected void onResume() {
    super.onResume();

    // do some work
    presenter.fetchProductDetailData();
  }

  @Override
  protected void onPause() {
    super.onPause();

    presenter.onPauseCalled();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
  }

    @Override
    public void displayProductDetailData(ProductDetailViewModel viewModel) {
        Log.e(TAG, "displayProductDetailData");

        ProductItem product = viewModel.product;

        if (product != null) {
            currentProduct = product;


            ((TextView) findViewById(R.id.detailName)).setText(product.content);

            ((TextView) findViewById(R.id.detailType)).setText("📡 Tipo: " + product.type);

            ((TextView) findViewById(R.id.detailAgency)).setText("🏢 Agencia: " + product.agency);

            ((TextView) findViewById(R.id.detailCountry)).setText("🌍 País: " + product.country);

            ((TextView) findViewById(R.id.detailYear)).setText("📅 Año: " + product.year);

            ((TextView) findViewById(R.id.detailOrbitOrObjective)).setText("🛰️ Órbita / objetivo: " + product.orbit);

            ((TextView) findViewById(R.id.detailStatus)).setText("✅ Estado: " + product.status);

            ((TextView) findViewById(R.id.detailDescription)).setText(product.details);

            ((TextView) findViewById(R.id.detailCuriosity)).setText(product.curiosity);

            loadImageFromDrawable((ImageView) findViewById(R.id.detailImage), product.picture);
            setupFavoriteButton(product);
        }
    }

    private void loadImageFromDrawable(ImageView imageView, String imageName) {

        int imageId = getResources().getIdentifier(
                imageName,
                "drawable",
                getPackageName()
        );

        if (imageId != 0) {
            imageView.setImageResource(imageId);
        }
    }
    private void setupFavoriteButton(ProductItem product) {

        Button favoriteButton = findViewById(R.id.favoriteButton);

        boolean isLoggedIn = getSharedPreferences("session", MODE_PRIVATE).getBoolean("isLoggedIn", false);

        int userId = getSharedPreferences("session", MODE_PRIVATE).getInt("userId", -1);

        if (!isLoggedIn) {
            favoriteButton.setVisibility(View.GONE);
            return;
        }

        int count = database.favoriteDao().isFavorite(userId, product.id);

        if (count > 0) {
            favoriteButton.setText("Quitar de favoritos");
        } else {
            favoriteButton.setText("Añadir a favoritos");
        }

        favoriteButton.setOnClickListener(view -> {

            int currentCount =
                    database.favoriteDao().isFavorite(userId, product.id);

            if (currentCount > 0) {

                database.favoriteDao().deleteFavorite(userId, product.id);

                favoriteButton.setText("Añadir a favoritos");

                Toast.makeText(this, "Eliminado de favoritos", Toast.LENGTH_SHORT).show();

            } else {

                FavoriteEntity favorite = new FavoriteEntity();

                favorite.userId = userId;
                favorite.productId = product.id;

                database.favoriteDao().insertFavorite(favorite);

                favoriteButton.setText("Quitar de favoritos");

                Toast.makeText(this, "Añadido a favoritos", Toast.LENGTH_SHORT).show();
            }
        });
    }


  @Override
  public void injectPresenter(ProductDetailContract.Presenter presenter) {
    this.presenter = presenter;
  }
}
