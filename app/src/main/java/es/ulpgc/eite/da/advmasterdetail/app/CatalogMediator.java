package es.ulpgc.eite.da.advmasterdetail.app;


import es.ulpgc.eite.da.advmasterdetail.data.CategoryItem;
import es.ulpgc.eite.da.advmasterdetail.data.ProductItem;
import es.ulpgc.eite.da.advmasterdetail.product.ProductDetailState;
import es.ulpgc.eite.da.advmasterdetail.products.ProductListState;

public class CatalogMediator {

//  private ProductListState productListState = new ProductListState();
//  private ProductDetailState productDetailState = new ProductDetailState();
  private es.ulpgc.eite.da.advmasterdetail.login.LoginState loginState;

  private es.ulpgc.eite.da.advmasterdetail.register.RegisterState registerState;

  private es.ulpgc.eite.da.advmasterdetail.favorites.FavoriteState favoriteState;
  private es.ulpgc.eite.da.advmasterdetail.home.HomeState homeState;




  private ProductListState productListState;
  private ProductDetailState productDetailState;
  private CategoryItem category;
  private ProductItem product;


  private static CatalogMediator INSTANCE;

  private CatalogMediator() {

  }

  public static void resetInstance() {
    INSTANCE = null;
  }


  public static CatalogMediator getInstance() {
    if(INSTANCE == null){
      INSTANCE = new CatalogMediator();
    }

    return INSTANCE;
  }


  public void setLoginState(es.ulpgc.eite.da.advmasterdetail.login.LoginState state) {
        loginState = state;
    }

    public es.ulpgc.eite.da.advmasterdetail.login.LoginState getLoginState() {
        return loginState;
    }

    public void setRegisterState(es.ulpgc.eite.da.advmasterdetail.register.RegisterState state) {
        registerState = state;
    }

    public es.ulpgc.eite.da.advmasterdetail.register.RegisterState getRegisterState() {
        return registerState;
    }
    public void setFavoriteState(es.ulpgc.eite.da.advmasterdetail.favorites.FavoriteState state) {
        favoriteState = state;
    }
    public es.ulpgc.eite.da.advmasterdetail.favorites.FavoriteState getFavoriteState() {
        return favoriteState;
    }
    public void setHomeState(es.ulpgc.eite.da.advmasterdetail.home.HomeState state) {
        homeState = state;
    }

    public es.ulpgc.eite.da.advmasterdetail.home.HomeState getHomeState() {
        return homeState;
    }



  public ProductDetailState getProductDetailState() {
    return productDetailState;
  }

  public ProductListState getProductListState() {
    return productListState;
  }

  public ProductItem getProduct() {
    ProductItem item = product;
    //product = null;
    return item;
  }


  public void setProduct(ProductItem item) {
    product = item;
  }

  public void setCategory(CategoryItem item) {
    category = item;
  }

  public CategoryItem getCategory() {
    CategoryItem item = category;
    //category = null;
    return item;
  }



  public void setProductListState(ProductListState state) {
    productListState=state;

  }

  public void setProductDetailState(ProductDetailState state) {
    productDetailState=state;
  }
}
