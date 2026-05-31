package es.ulpgc.eite.da.advmasterdetail.product;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

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

  ProductDetailContract.Presenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.detalles_list);
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

            ((TextView) findViewById(R.id.detailName))
                    .setText(product.content);

            ((TextView) findViewById(R.id.detailType))
                    .setText("📡 Tipo: " + product.type);

            ((TextView) findViewById(R.id.detailAgency))
                    .setText("🏢 Agencia: " + product.agency);

            ((TextView) findViewById(R.id.detailCountry))
                    .setText("🌍 País: " + product.country);

            ((TextView) findViewById(R.id.detailYear))
                    .setText("📅 Año: " + product.year);

            ((TextView) findViewById(R.id.detailOrbitOrObjective))
                    .setText("🛰️ Órbita / objetivo: " + product.orbit);

            ((TextView) findViewById(R.id.detailStatus))
                    .setText("✅ Estado: " + product.status);

            ((TextView) findViewById(R.id.detailDescription))
                    .setText(product.details);

            ((TextView) findViewById(R.id.detailCuriosity))
                    .setText(product.curiosity);

            loadImageFromDrawable(
                    (ImageView) findViewById(R.id.detailImage),
                    product.picture
            );
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


  @Override
  public void injectPresenter(ProductDetailContract.Presenter presenter) {
    this.presenter = presenter;
  }
}
