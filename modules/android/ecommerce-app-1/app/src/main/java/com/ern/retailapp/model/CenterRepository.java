/*
 * Copyright (c) 2017. http://hiteshsahu.com- All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * If you use or distribute this project then you MUST ADD A COPY OF LICENCE
 * along with the project.
 *  Written by Hitesh Sahu <hiteshkrsahu@Gmail.com>, 2017.
 */

package com.ern.retailapp.model;

import com.ern.retailapp.model.entities.ProductUI;
import com.ern.retailapp.model.entities.ProductCategoryModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class CenterRepository {

    private static CenterRepository centerRepository;

    private ArrayList<ProductCategoryModel> listOfCategory = new ArrayList<ProductCategoryModel>();
    private ConcurrentHashMap<String, ArrayList<ProductUI>> mapOfProductsInCategory = new ConcurrentHashMap<String, ArrayList<ProductUI>>();
    private List<ProductUI> listOfProductsInShoppingList = Collections.synchronizedList(new ArrayList<ProductUI>());
    private List<Set<String>> listOfItemSetsForDataMining = new ArrayList<>();

    public static CenterRepository getCenterRepository() {

        if (null == centerRepository) {
            centerRepository = new CenterRepository();
        }
        return centerRepository;
    }


    public List<ProductUI> getListOfProductsInShoppingList() {
        return listOfProductsInShoppingList;
    }

    public void setListOfProductsInShoppingList(ArrayList<ProductUI> getShoppingList) {
        this.listOfProductsInShoppingList = getShoppingList;
    }

    public Map<String, ArrayList<ProductUI>> getMapOfProductsInCategory() {

        return mapOfProductsInCategory;
    }

    public void setMapOfProductsInCategory(ConcurrentHashMap<String, ArrayList<ProductUI>> mapOfProductsInCategory) {
        this.mapOfProductsInCategory = mapOfProductsInCategory;
    }

    public ArrayList<ProductCategoryModel> getListOfCategory() {

        return listOfCategory;
    }

    public void setListOfCategory(ArrayList<ProductCategoryModel> listOfCategory) {
        this.listOfCategory = listOfCategory;
    }

    public List<Set<String>> getItemSetList() {

        return listOfItemSetsForDataMining;
    }

    public void addToItemSetList(HashSet list) {
        listOfItemSetsForDataMining.add(list);
    }

}
