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
import com.ern.retailapp.model.entities.ProductUI;
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
import java.util.concurrent.ConcurrentHashMap;

/**
 * The Class ImageLoaderTask.
 */
public class ProductCategoryLoaderTask extends AsyncTask<String, Void,  List<Product>> {

    private static final int NUMBER_OF_COLUMNS = 2;
    private Context context;
    private RecyclerView recyclerView;
    private int position;

    public ProductCategoryLoaderTask(RecyclerView listView, Context context, int position) {

        this.recyclerView = listView;
        this.context = context;

        // posisi category yang kita click
        this.position = position;
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();

        if (null != ((ECartHomeActivity) context).getProgressBar())
            ((ECartHomeActivity) context).getProgressBar().setVisibility(
                    View.VISIBLE);

    }

    @Override
    protected void onPostExecute( List<Product> result) {
        super.onPostExecute(result);

        // menaruh hasil product di dalam repository
        putProductsInRepository(result);

        if (null != ((ECartHomeActivity) context).getProgressBar())
            ((ECartHomeActivity) context).getProgressBar().setVisibility(
                    View.GONE);

        if (recyclerView != null) {

            Utils.switchFragmentWithAnimation(
                    R.id.frag_container,
                    new ProductOverviewFragment(),
                    ((ECartHomeActivity) context), null,
                    AnimationType.SLIDE_LEFT);
        }
    }

    public void putProductsInRepository(List<Product> products) {
        ConcurrentHashMap<String, ArrayList<ProductUI>> productMap = new ConcurrentHashMap<String, ArrayList<ProductUI>>();
        ArrayList<ProductUI> productlist = new ArrayList<ProductUI>();
        CenterRepository repository = CenterRepository.getCenterRepository();
        ArrayList<ProductCategoryModel> categories = repository.getListOfCategory();
        Log.println(Log.VERBOSE, "android-app", "Category size " + categories.size() + " position " + position);

        if (categories.size() > position) {
            // Panggil Web Service dengan list of product untuk category pertama
            String categoryName = categories.get(position).getProductCategoryName();

            Log.println(Log.VERBOSE, "android-app", "Menemukan produk dengan jumlah " + products.size());
            // membuat definisi untuk product dan menaruh di dalam central repository
            for (int i = 0; i < products.size(); i++) {
                Product current = products.get(i);
                String description = current.getDeskripsiProduct();
                String productName = current.getNamaProduct();
                String longDescription = current.getDeskripsiProduct();
                String salePrice = current.getHargaProduct() + "";
                String imgUrl = current.getImageLink();
                String productId = current.getIdProduct() + "";
                Log.println(Log.VERBOSE, "android-app", "Title " + productName + " dengan harga " + salePrice);

                ProductUI productUI = new ProductUI(productName, description, description, salePrice, "",
                        salePrice, 1 +"", imgUrl, productId);

                productlist.add(productUI);
            }

            productMap.put(categoryName, productlist);
            repository.setMapOfProductsInCategory(productMap);
        }
    }

    @Override
    protected  List<Product> doInBackground(String... params) {
        List<Product> products = new ArrayList<Product>();

        // ERNA: DIGANTI DENGAN memanggil ECommerce Service
        ECommerceService service = new ECommerceService();

        CenterRepository repository = CenterRepository.getCenterRepository();
        Long categoryId = 0L;

        try {
            ArrayList<ProductCategoryModel> categories = repository.getListOfCategory();

            if (categories.size() > 0) {
                // Panggil Web Service dengan list of product untuk category pertama
                categoryId = categories.get(position).getCategoryId();

                String response = service.getProductByCategory(categoryId);
                ObjectMapper objectMapper = new ObjectMapper();

                // membuat mapping dari JSON ProductUI list untuk mapping
                // JSON String ke dalam object
                products = objectMapper.readValue(response, new TypeReference<List<Product>>() {
                });

                Log.println(Log.VERBOSE, "android-app", "Menemukan hasil dengan jumlah " + products.size());
            }
        } catch (Exception e) {
            Log.println(Log.VERBOSE, "eCommerceTask", "Tidak menemukan kategory " + categoryId);
            e.printStackTrace();
            // abaikan error dan response akan tetap menjadi null
            // yang kita akan deteksi nanti
        }

        return products;
    }

}