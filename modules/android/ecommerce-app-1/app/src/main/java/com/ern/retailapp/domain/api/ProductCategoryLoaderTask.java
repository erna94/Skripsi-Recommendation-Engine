/*
 * Copyright (c) 2017. http://hiteshsahu.com- All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * If you use or distribute this project then you MUST ADD A COPY OF LICENCE
 * along with the project.
 *  Written by Hitesh Sahu <hiteshkrsahu@Gmail.com>, 2017.
 */

package com.ern.retailapp.domain.api;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.ern.retailapp.model.CenterRepository;
import com.ern.retailapp.model.entities.ProductCategoryModel;
import com.ernchatbot.service.response.Product;
import com.ernchatbot.service.ECommerceService;
import com.ern.retailapp.R;
import com.ern.retailapp.util.AppConstants;
import com.ern.retailapp.util.Utils;
import com.ern.retailapp.util.Utils.AnimationType;
import com.ern.retailapp.view.activities.ECartHomeActivity;
import com.ern.retailapp.view.adapter.CategoryListAdapter;
import com.ern.retailapp.view.adapter.CategoryListAdapter.OnItemClickListener;
import com.ern.retailapp.view.fragment.ProductOverviewFragment;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class ImageLoaderTask.
 */
public class ProductCategoryLoaderTask extends AsyncTask<String, Void, Void> {

    private static final int NUMBER_OF_COLUMNS = 2;
    private Context context;
    private RecyclerView recyclerView;

    public ProductCategoryLoaderTask(RecyclerView listView, Context context) {

        this.recyclerView = listView;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();

        if (null != ((ECartHomeActivity) context).getProgressBar())
            ((ECartHomeActivity) context).getProgressBar().setVisibility(
                    View.VISIBLE);

    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        if (null != ((ECartHomeActivity) context).getProgressBar())
            ((ECartHomeActivity) context).getProgressBar().setVisibility(
                    View.GONE);

        if (recyclerView != null) {
            CategoryListAdapter simpleRecyclerAdapter = new CategoryListAdapter(
                    context);

            recyclerView.setAdapter(simpleRecyclerAdapter);

            simpleRecyclerAdapter
                    .SetOnItemClickListener(new OnItemClickListener() {

                        @Override
                        public void onItemClick(View view, int position) {

                            AppConstants.CURRENT_CATEGORY = position;

                            Utils.switchFragmentWithAnimation(
                                    R.id.frag_container,
                                    new ProductOverviewFragment(),
                                    ((ECartHomeActivity) context), null,
                                    AnimationType.SLIDE_LEFT);

                        }
                    });
        }

    }

    @Override
    protected Void doInBackground(String... params) {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // ERNA: DIGANTI DENGAN memanggil ECommerce Service
        ECommerceService service = new ECommerceService();

        // memberikan category awal
        service.initCategory();
        CenterRepository repository = CenterRepository.getCenterRepository();
        Long categoryId = 0L;

        try {
            ArrayList<ProductCategoryModel> categories = repository.getListOfCategory();

            if (categories.size() > 0) {
                // Panggil Web Service dengan list of product untuk category pertama
                categoryId = categories.get(0).getCategoryId();

                String response = service.getProductByCategory(categoryId);
                ObjectMapper objectMapper = new ObjectMapper();

                    // membuat mapping dari JSON Product list untuk mapping
                    // JSON String ke dalam object
                    List<Product> products = objectMapper.readValue(response, new TypeReference<List<Product>>() {
                    });

            }
        } catch (Exception e) {
            Log.println(Log.VERBOSE, "eCommerceTask", "Tidak menemukan kategory " + categoryId);
            e.printStackTrace();
            // abaikan error dan response akan tetap menjadi null
            // yang kita akan deteksi nanti
        }

        return null;
    }

}