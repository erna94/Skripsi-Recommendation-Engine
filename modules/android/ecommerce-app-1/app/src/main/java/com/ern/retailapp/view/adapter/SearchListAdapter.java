package com.ern.retailapp.view.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import com.ern.retailapp.R;
import com.ern.retailapp.model.CenterRepository;
import com.ern.retailapp.model.entities.ProductUI;
import com.ern.retailapp.util.Utils;
import com.ern.retailapp.view.activities.ECartHomeActivity;
import com.ern.retailapp.view.customview.ProductViewHolder;
import com.ern.retailapp.view.fragment.ProductDetailsFragment;
import com.ernchatbot.service.response.Product;
import com.ernchatbot.ui.ProductViewHolderMapper;

import java.util.ArrayList;
import java.util.List;

/***
 * ERNA: Class baru untuk menghandle hasil dari search result berdasarkan
 * percapakapan chatbot
 */
public class SearchListAdapter extends BaseAdapter {
    CenterRepository repository;
    ViewGroup viewGroup;
    Context context;
    ProductViewHolderMapper productViewHolderMapper;
    // ERNA: Perlu membuat program ini menjadi lebih rapi dengan menggabungkan konsep dalam ProductCategoryLoaderTask
    List<ProductUI> products;

    public SearchListAdapter(ViewGroup viewGroup, Context context) {
        repository =  CenterRepository.getCentralRepository();
        this.viewGroup = viewGroup;
        this.context = context;
        products = new ArrayList<ProductUI>();
        this.productViewHolderMapper = new ProductViewHolderMapper(context);
    }

    public void setProducts(List<Product> microServiceResult) {
        ProductViewHolder.OnItemClickListener onClickListener = new ProductViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
            }
        };

        for(int i=0;i<microServiceResult.size();i++) {
            Product current = microServiceResult.get(i);
            String description = current.getDeskripsiProduct();
            String productName = current.getNamaProduct();
            String longDescription = current.getDeskripsiProduct();
            String salePrice = current.getHargaProduct() + "";
            String imgUrl = current.getImageLink();
            String productId = current.getIdProduct() + "";
            Log.println(Log.VERBOSE, "android-app", "Title " + productName + " dengan harga " + salePrice);

            ProductUI productUI = new ProductUI(productName, description, description, salePrice, "",
                    salePrice, 1 + "", imgUrl, productId);

            products.add(productUI);
        }

        notifyDataSetChanged();
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProductUI productUI = products.get(position);

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.item_product_list, viewGroup, false);

        ProductViewHolder holder = new ProductViewHolder(view, new ProductViewHolder.OnItemClickListener() {
            public void onItemClick(View view, int position) {
            }
        });

        productViewHolderMapper.mapProductViewHolder(holder, productUI);

        return view;
    }

    @Override
    public int getItemViewType(int position) {
        return products.size();
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return products.isEmpty();
    }
}
