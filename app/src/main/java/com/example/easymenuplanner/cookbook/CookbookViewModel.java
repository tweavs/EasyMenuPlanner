package com.example.easymenuplanner.cookbook;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.easymenuplanner.recipe.Recipedb;
import com.example.easymenuplanner.repositories.CookbookRepository;

import java.util.List;

public class CookbookViewModel extends AndroidViewModel {

    private CookbookRepository cookbookRepository;
    private MutableLiveData<List<Recipedb>> liveCookbook;
    private List<Recipedb> cookbook;
    Application application;
//    String recipeName;

    public CookbookViewModel(Application application) {
        super(application);
        this.application = application;
    }

    public void init() {
        if (cookbook != null) {
            return;
        }
        cookbookRepository =  cookbookRepository.getInstance(application);
        cookbook = cookbookRepository.getCookbook();
        liveCookbook = new MutableLiveData<>();
        liveCookbook.setValue(cookbook);
    }

    public void findRecipe(String recipeName) {
        cookbook = cookbookRepository.findRecipe(recipeName);
        liveCookbook.setValue(cookbook);
    }

    public MutableLiveData<List<Recipedb>> getCookbook() {
        readFromDatabase();
        return liveCookbook;
    }

//    public MutableLiveData<List<Recipedb>> getLiveData() {
//        if (liveCookbook == null) {
//            cookbook = cookbookRepository.getCookbook();
//            liveCookbook.setValue(cookbook);
//        }
//        return liveCookbook;
//    }

    private void readFromDatabase() {
        cookbook = cookbookRepository.getCookbook();
        liveCookbook.setValue(cookbook);
    }

}
