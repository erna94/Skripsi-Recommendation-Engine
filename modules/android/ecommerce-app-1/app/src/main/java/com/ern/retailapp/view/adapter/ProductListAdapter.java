/*
 * Copyright (c) 2017. http://hiteshsahu.com- All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * If you use or distribute this project then you MUST ADD A COPY OF LICENCE
 * along with the project.
 *  Written by Hitesh Sahu <hiteshkrsahu@Gmail.com>, 2017.
 */

package com.ern.retailapp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView.BufferType;

import com.bumptech.glide.Glide;
import com.ern.retailapp.R;
import com.ern.retailapp.model.CenterRepository;
import com.ern.retailapp.model.entities.Money;
import com.ern.retailapp.model.entities.ProductUI;
import com.ern.retailapp.util.ColorGenerator;
import com.ern.retailapp.util.Utils;
import com.ern.retailapp.view.activities.ECartHomeActivity;
import com.ern.retailapp.view.customview.TextDrawable;
import com.ern.retailapp.view.customview.TextDrawable.IBuilder;

// ERNA: custom item handler
import com.ern.retailapp.view.customview.ProductViewHolder;
import com.ernchatbot.ui.ProductViewHolderMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Hitesh Sahu (hiteshsahu.com)
 */
public class ProductListAdapter extends
        RecyclerView.Adapter<ProductViewHolder> implements
        ItemTouchHelperAdapter  {

    private ColorGenerator mColorGenerator = ColorGenerator.MATERIAL;

    private IBuilder mDrawableBuilder;

    private TextDrawable drawable;

    private String imageUrl;

    private List<ProductUI> productList = new ArrayList<ProductUI>();
    private ProductViewHolder.OnItemClickListener clickListener;

    private Context context;

    ProductViewHolderMapper productViewHolderMapper;

    public ProductListAdapter(String subcategoryKey, Context context,
                              boolean isCartlist) {

        if (isCartlist) {
            productList = CenterRepository.getCentralRepository()
                    .getListOfProductsInShoppingList();
        } else {
            productList = CenterRepository.getCentralRepository().getMapOfProductsInCategory()
                    .get(subcategoryKey);
        }

        this.context = context;
        productViewHolderMapper = new ProductViewHolderMapper(context);
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.item_product_list, viewGroup, false);
        ProductViewHolder viewHolder = new ProductViewHolder(view, clickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder,
                                 final int position) {

        ProductUI productUI = productList.get(position);
        productViewHolderMapper.mapProductViewHolder(holder, productUI);

        holder.addItem.findViewById(R.id.add_item).setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        //current object
                        ProductUI tempObj = productList.get(position);


                        //if current object is lready in shopping list
                        if (CenterRepository.getCentralRepository()
                                .getListOfProductsInShoppingList().contains(tempObj)) {


                            //get position of current item in shopping list
                            int indexOfTempInShopingList = CenterRepository
                                    .getCentralRepository().getListOfProductsInShoppingList()
                                    .indexOf(tempObj);

                            // increase quantity of current item in shopping list
                            if (Integer.parseInt(tempObj.getQuantity()) == 0) {

                                ((ECartHomeActivity) getContext())
                                        .updateItemCount(true);

                            }


                            // update quanity in shopping list
                            CenterRepository
                                    .getCentralRepository()
                                    .getListOfProductsInShoppingList()
                                    .get(indexOfTempInShopingList)
                                    .setQuantity(
                                            String.valueOf(Integer
                                                    .valueOf(tempObj
                                                            .getQuantity()) + 1));


                            //update checkout amount
                            ((ECartHomeActivity) getContext()).updateCheckOutAmount(
                                    BigDecimal
                                            .valueOf(Double
                                                    .valueOf(productList
                                                            .get(position)
                                                            .getSellMRP())),
                                    true);

                            // update current item quanitity
                            holder.quanitity.setText(tempObj.getQuantity());

                        } else {

                            ((ECartHomeActivity) getContext()).updateItemCount(true);

                            tempObj.setQuantity(String.valueOf(1));

                            holder.quanitity.setText(tempObj.getQuantity());

                            CenterRepository.getCentralRepository()
                                    .getListOfProductsInShoppingList().add(tempObj);

                            ((ECartHomeActivity) getContext()).updateCheckOutAmount(
                                    BigDecimal
                                            .valueOf(Double.valueOf(productList
                                                            .get(position)
                                                            .getSellMRP())),
                                    true);

                        }

                        Utils.vibrate(getContext());

                    }
                });

        holder.removeItem.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                ProductUI tempObj = (productList).get(position);

                if (CenterRepository.getCentralRepository().getListOfProductsInShoppingList()
                        .contains(tempObj)) {

                    int indexOfTempInShopingList = CenterRepository
                            .getCentralRepository().getListOfProductsInShoppingList()
                            .indexOf(tempObj);

                    if (Integer.valueOf(tempObj.getQuantity()) != 0) {

                        CenterRepository
                                .getCentralRepository()
                                .getListOfProductsInShoppingList()
                                .get(indexOfTempInShopingList)
                                .setQuantity(
                                        String.valueOf(Integer.valueOf(tempObj
                                                .getQuantity()) - 1));

                        ((ECartHomeActivity) getContext()).updateCheckOutAmount(
                                BigDecimal.valueOf(Double.valueOf(productList
                                        .get(position).getSellMRP())),
                                false);

                        holder.quanitity.setText(CenterRepository
                                .getCentralRepository().getListOfProductsInShoppingList()
                                .get(indexOfTempInShopingList).getQuantity());

                        Utils.vibrate(getContext());

                        if (Integer.valueOf(CenterRepository
                                .getCentralRepository().getListOfProductsInShoppingList()
                                .get(indexOfTempInShopingList).getQuantity()) == 0) {

                            CenterRepository.getCentralRepository()
                                    .getListOfProductsInShoppingList()
                                    .remove(indexOfTempInShopingList);

                            notifyDataSetChanged();

                            ((ECartHomeActivity) getContext())
                                    .updateItemCount(false);

                        }

                    }
                }
            }

        });

    }


    private ECartHomeActivity getContext() {
        // TODO Auto-generated method stub
        return (ECartHomeActivity) context;
    }

    @Override
    public int getItemCount() {
        return productList == null ? 0 : productList.size();
    }

    public void SetOnItemClickListener(
            final ProductViewHolder.OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @Override
    public void onItemDismiss(int position) {
        productList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(productList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(productList, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }
}
