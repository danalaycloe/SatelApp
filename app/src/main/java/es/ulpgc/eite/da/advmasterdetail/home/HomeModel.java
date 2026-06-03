package es.ulpgc.eite.da.advmasterdetail.home;

import android.content.Context;

import es.ulpgc.eite.da.advmasterdetail.data.CatalogRepository;
import es.ulpgc.eite.da.advmasterdetail.data.CategoryItem;
import es.ulpgc.eite.da.advmasterdetail.data.RepositoryContract;

public class HomeModel implements HomeContract.Model {

    private RepositoryContract repository;

    public HomeModel(Context context) {
        repository = CatalogRepository.getInstance(context);
    }

    @Override
    public void loadCatalog() {
        repository.loadCatalog(false, error -> {
        });
    }

    @Override
    public CategoryItem getSatellitesCategory() {
        CategoryItem category = new CategoryItem();
        category.id = 1;
        category.content = "Satélites";
        return category;
    }

    @Override
    public CategoryItem getMissionsCategory() {
        CategoryItem category = new CategoryItem();
        category.id = 2;
        category.content = "Misiones";
        return category;
    }
}