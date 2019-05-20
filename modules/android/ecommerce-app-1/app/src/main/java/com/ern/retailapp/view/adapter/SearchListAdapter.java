package com.ern.retailapp.view.adapter;

import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import com.ern.retailapp.R;
import com.ern.retailapp.model.CenterRepository;
import com.ern.retailapp.util.Utils;
import com.ern.retailapp.view.activities.ECartHomeActivity;
import com.ern.retailapp.view.customview.ProductViewHolder;
import com.ern.retailapp.view.fragment.ProductDetailsFragment;

/***
 * ERNA: Class baru untuk menghandle hasil dari search result berdasarkan
 * percapakapan chatbot
 */
public class SearchListAdapter implements ListAdapter {
    CenterRepository repository;
    ViewGroup viewGroup;

    public SearchListAdapter(ViewGroup viewGroup) {
        repository =  CenterRepository.getCentralRepository();
        this.viewGroup = viewGroup;
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
        return 2;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.item_product_list, viewGroup, false);

        ProductViewHolder.OnItemClickListener onClickListener = new ProductViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
            }
        };

        ProductViewHolder productViewHolder = new ProductViewHolder(view, onClickListener);
        productViewHolder.itemName.setText("Foo");
        productViewHolder.availability.setText("1");
        productViewHolder.itemDesc.setText("Blah");
        productViewHolder.itemCost.setText("65000");
        productViewHolder.availability.setText("10");

        return view;
    }

    @Override
    public int getItemViewType(int position) {
        return 2;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
