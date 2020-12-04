package com.example.easymenuplanner.menu;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easymenuplanner.R;
import com.example.easymenuplanner.cookbook.CookbookFragmentDirections;
import com.example.easymenuplanner.recipe.Recipe;

public class MenuViewHolder extends RecyclerView.ViewHolder {

    private TextView recipeName;
    private Recipe recipe;
    private boolean isAddRecipe;

    public MenuViewHolder(@NonNull View itemView) {
        super(itemView);
        recipeName = itemView.findViewById(R.id.tvRecipeName);

        /*
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {

                if () {
                    // Go to Cookbook
                } else {
                    // Go to Recipe view
                }

                Navigation.findNavController(v).navigate(action);
            }
        });
        
         */


    }

    public void bindData(Recipe recipe) {
        this.recipe = recipe;
        if (recipe == null) {
            recipeName.setText("Add Recipe");
        } else {
            recipeName.setText(recipe.getRecipeName());
        }

    }
}