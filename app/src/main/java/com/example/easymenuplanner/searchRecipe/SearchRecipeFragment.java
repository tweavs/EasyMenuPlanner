package com.example.easymenuplanner.searchRecipe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.easymenuplanner.R;

public class SearchRecipeFragment extends Fragment {

    private EditText et_searchItem;
    private ListView lv_recipesHits;

    public SearchRecipeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_search_recipe, container, false);
        et_searchItem = view.findViewById(R.id.etSearchItem);
        lv_recipesHits = view.findViewById(R.id.lvRecipesHits);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public void searchItem(View view) {
        ApiSearchTask task = new ApiSearchTask(et_searchItem.getText().toString());
    }

    private void displayHits(GetRecipes hits) {
        /*Adapter adapter = new ArrayAdapter<GetRecipes>(this,
                android.R.layout.simple_list_item_1, hits.getTitle());
        lv_recipesHits.setAdapter(adapter);

         */

    }
    private class ApiSearchTask extends Thread {

        private String searchItem;

        public ApiSearchTask(String searchItem) {
            this.searchItem = searchItem;
        }


        @Override
        public void run() {
            SearchRecipe newApiSearch = new SearchRecipe();
            final GetRecipes hits = newApiSearch.getRecipes(searchItem);
            /* runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    displayHits(hits.getTitle());
                    displayHits(hits.getIngredients());
                    displayHits(hits.getUrl());
                }
            }

             */

        }
    }


}