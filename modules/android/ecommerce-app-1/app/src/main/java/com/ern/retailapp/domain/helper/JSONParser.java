/*
 * Copyright (c) 2017. http://hiteshsahu.com- All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * If you use or distribute this project then you MUST ADD A COPY OF LICENCE
 * along with the project.
 *  Written by Hitesh Sahu <hiteshkrsahu@Gmail.com>, 2017.
 */

package com.ern.retailapp.domain.helper;

import android.util.Log;

import com.ern.retailapp.model.CenterRepository;
import com.ern.retailapp.model.entities.ProductUI;
import com.ern.retailapp.model.entities.ProductCategoryModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class JSONParser {

    private int networktaskType;
    private String jsonResponse;

    /**
     * @param networktaskType
     * @param jsonResponse
     */
    public JSONParser(int networktaskType, String jsonResponse) {
        super();
        this.networktaskType = networktaskType;
        this.jsonResponse = jsonResponse;
    }

    public static JSONObject toJSON(Object object) throws JSONException,
            IllegalAccessException {
        Class c = object.getClass();
        JSONObject jsonObject = new JSONObject();
        for (Field field : c.getDeclaredFields()) {
            field.setAccessible(true);
            String name = field.getName();
            String value = String.valueOf(field.get(object));
            jsonObject.put(name, value);
        }
        System.out.println(jsonObject.toString());

        return jsonObject;
    }

    public static JSONArray toJSONFromList(List list) throws JSONException,
            IllegalAccessException {
        JSONArray jsonArray = new JSONArray();
        for (Object i : list) {
            JSONObject jstr = toJSON(i);
            // JSONObject jsonObject = new JSONObject(jstr);
            jsonArray.put(jstr);
        }
        return jsonArray;
    }

    public void parse() {

        if (jsonResponse != null) {
            try {
                switch (networktaskType) {
                    case NetworkConstants.GET_ALL_PRODUCT_BY_CATEGORY:

                        if (NetworkConstants.DEBUGABLE)
                            Log.e("Parse: ", "GetAllCategoryTask " + jsonResponse);

                        JSONArray categoryArray = new JSONArray(jsonResponse);

                        CenterRepository.getCentralRepository().getListOfCategory()
                                .clear();

                        for (int i = 0; i < categoryArray.length(); i++) {
                            CenterRepository
                                    .getCentralRepository()
                                    .getListOfCategory()
                                    .add(new ProductCategoryModel(categoryArray
                                            .getJSONObject(i).getString(
                                                    "categoryName"), categoryArray
                                            .getJSONObject(i).getString(
                                                    "productDescription"),
                                            categoryArray.getJSONObject(i)
                                                    .getString("productDiscount"),
                                            categoryArray.getJSONObject(i)
                                                    .getString("productUrl")));

                        }

                        break;

                    case NetworkConstants.GET_ALL_PRODUCT:

                        if (NetworkConstants.DEBUGABLE)
                            Log.e("Parse: ", "GetAllProductFromCategoryTask "
                                    + jsonResponse);

                        JSONObject productMapObject = new JSONObject(jsonResponse);

                        CenterRepository.getCentralRepository().getMapOfProductsInCategory()
                                .clear();

                        for (int categoryCount = 0; categoryCount < CenterRepository
                                .getCentralRepository().getListOfCategory().size(); categoryCount++) {

                            ArrayList<ProductUI> tempProductList = new ArrayList<ProductUI>();

                            // get json array for stored category

                            if (productMapObject.optJSONArray(CenterRepository
                                    .getCentralRepository().getListOfCategory()
                                    .get(categoryCount).getProductCategoryName()) != null) {

                                JSONArray productListWithCategory = productMapObject
                                        .getJSONArray(CenterRepository
                                                .getCentralRepository()
                                                .getListOfCategory()
                                                .get(categoryCount)
                                                .getProductCategoryName());

                                System.out.println(CenterRepository
                                        .getCentralRepository().getListOfCategory()
                                        .get(categoryCount)
                                        .getProductCategoryName());

                                // ArrayList<ProductUI> tempProductList = new
                                // ArrayList<ProductUI>();

                                for (int i = 0; i < productListWithCategory
                                        .length(); i++) {

                                    // get all product in that category
                                    JSONObject productListObjecty = productListWithCategory
                                            .getJSONObject(i);

                                    if (productListObjecty.length() != 0) {

                                        tempProductList.add(new ProductUI(
                                                productListObjecty

                                                        .getString("productName"),
                                                productListObjecty

                                                        .getString("description"),
                                                productListObjecty

                                                        .getString("longDescription"),
                                                productListObjecty

                                                        .getString("mrp"),
                                                productListObjecty

                                                        .getString("discount"),
                                                productListObjecty

                                                        .getString("salePrice"),
                                            /*
                                             * productListObjecty
											 *
											 * .getString("avbleQuantity"),
											 */
                                                "0", productListObjecty

                                                .getString("imageUrl"),
                                                productListObjecty
                                                        .getString("productId")));

                                        Log.d("Parse:GetAllProduct",
                                                "tempProductList" + tempProductList);

                                    }

                                }
                            }
                            CenterRepository
                                    .getCentralRepository()
                                    .getMapOfProductsInCategory()
                                    .put(String.valueOf(categoryCount),
                                            tempProductList);

                        }

                        break;


                    case NetworkConstants.GET_SHOPPING_LIST:

                        if (NetworkConstants.DEBUGABLE)
                            Log.e("Parse: ", "GetAllProductFromCategoryTask "
                                    + jsonResponse);

                        // JSONObject productMapObject = new
                        // JSONObject(jsonResponse);

                        CenterRepository.getCentralRepository().getListOfProductsInShoppingList()
                                .clear();

                        JSONArray mycartArray = new JSONArray(jsonResponse);

                        //
                        // for (int categoryCount = 0; categoryCount <
                        // CenterRepository
                        // .getCentralRepository().getListOfCategory().size();
                        // categoryCount++) {
                        //
                        ArrayList<ProductUI> tempProductList = new ArrayList<ProductUI>();

                        // get json array for stored category

                        // if (productMapObject.optJSONArray(CenterRepository
                        // .getCentralRepository().getListOfCategory()
                        // .get(categoryCount).getProductCategoryName()) != null) {
                        //

                        // JSONArray productListWithCategory = productMapObject
                        // .getJSONArray(CenterRepository
                        // .getCentralRepository()
                        // .getListOfCategory()
                        // .get(categoryCount)
                        // .getProductCategoryName());
                        //
                        // System.out.println(CenterRepository
                        // .getCentralRepository().getListOfCategory()
                        // .get(categoryCount)
                        // .getProductCategoryName());

                        // ArrayList<ProductUI> tempProductList = new
                        // ArrayList<ProductUI>();

                        for (int i = 0; i < mycartArray.length(); i++) {

                            // get all product in that category
                            JSONObject productListObjecty = mycartArray
                                    .getJSONObject(i);

                            if (productListObjecty.length() != 0) {

                                CenterRepository
                                        .getCentralRepository()
                                        .getListOfProductsInShoppingList().add(new ProductUI(productListObjecty

                                        .getString("productName"), productListObjecty

                                        .getString("description"), productListObjecty

                                        .getString("longDescription"), productListObjecty

                                        .getString("mrp"), productListObjecty

                                        .getString("discount"), productListObjecty

                                        .getString("salePrice"),
                            /*
                             * productListObjecty
							 *
							 * .getString("avbleQuantity"),
							 */
                                        "0", productListObjecty

                                        .getString("imageUrl"), productListObjecty
                                        .getString("productId")));

                                Log.d("GetAllProduct",
                                        "tempProductList" + tempProductList);

                            }

                        }
                        // }
//					CenterRepository
//							.getCentralRepository()
//							.getShppingItemList()
//							.put( tempProductList);

                        // }

                        break;

                    default:
                        break;
                }

                // TODO parse JSON acc to request
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("ServiceHandler", "Couldn't get any data from the url");
        }

    }

}
