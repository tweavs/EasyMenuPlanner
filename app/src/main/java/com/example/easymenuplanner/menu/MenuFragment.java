package com.example.easymenuplanner.menu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.easymenuplanner.R;
import com.example.easymenuplanner.cookbook.CookbookAdapter;
import com.example.easymenuplanner.cookbook.CookbookFragmentArgs;
import com.example.easymenuplanner.cookbook.CookbookViewModel;
import com.example.easymenuplanner.cookbook.CookbookViewModelFactory;
import com.example.easymenuplanner.home.CardAdapter;
import com.example.easymenuplanner.recipe.Recipe;
import com.example.easymenuplanner.recipe.RecipeFragmentArgs;
import com.example.easymenuplanner.recipe.Recipedb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MenuFragment extends Fragment {

    private MenuViewModel menuViewModel;
    private ViewPager2 pagerView;
    private MenuAdapter menuAdapter;
    private List<Menudb> menus;
    private List<MenuDisplay> menuDisplayList;
    private int menuKey;
    private int recipeKey;

    //private MenuCalendar myMenus;

    public MenuFragment() {
        // Required empty public constructor
        // Temp Code for passing menus
        menus = new ArrayList<>();

//        Menudb mMenu = new Menudb();
//        mMenu.id = 1;
//        Calendar today = Calendar.getInstance();
//        Date date = today.getTime();
//        mMenu.date = date;
//        menus.add(mMenu);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

//        try {
////            MenuFragmentArgs args = MenuFragmentArgs.fromBundle(getArguments());
////            newRecipe = args.getRecipe();
////            meal = args.getMeal();
//        } catch (Exception e) {
//
//        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pagerView = view.findViewById(R.id.vp2Menu);
        menuViewModel = new ViewModelProvider(getActivity(), new MenuViewModelFactory(getActivity().getApplication())).get(MenuViewModel.class);
        menuViewModel.init();
        menuViewModel.getMenus().observe(getViewLifecycleOwner(), new Observer<List<MenuDisplay>>() {
            @Override
            public void onChanged(List<MenuDisplay> displayMenus) {
                menuAdapter.notifyDataSetChanged();
            }
        });

        try {
            MenuFragmentArgs args = MenuFragmentArgs.fromBundle(getArguments());
            menuKey = (int) args.getMenuKey();
            recipeKey = (int) args.getRecipeKey();
            menuViewModel.replaceRecipeInMenu(menuKey, recipeKey);

        } catch (Exception e) {
            menuKey = -999;
            recipeKey = -999;
        }

        menuAdapter = new MenuAdapter(menuViewModel.getMenus().getValue());
        //menuAdapter = new MenuAdapter(menus);
        pagerView.setAdapter(menuAdapter);

        pagerView.setClipToPadding(false);
        pagerView.setClipChildren(false);
        pagerView.setOffscreenPageLimit(3);
        pagerView.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);


        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(10));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(1.0f + r*0.05f);
            }
        });
        pagerView.setPageTransformer(compositePageTransformer);


    }
}