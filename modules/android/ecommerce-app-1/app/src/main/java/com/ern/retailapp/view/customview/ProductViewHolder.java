package com.ern.retailapp.view.customview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ern.retailapp.R;

public class ProductViewHolder extends RecyclerView.ViewHolder implements
        View.OnClickListener {
    public TextView itemName, itemDesc, itemCost, availability, quanitity,
            addItem, removeItem;
    public ImageView imageView;
    OnItemClickListener clickListener;

    public ProductViewHolder(View itemView, OnItemClickListener clickListener) {
        super(itemView);

        itemName = (TextView) itemView.findViewById(R.id.item_name);

        itemDesc = (TextView) itemView.findViewById(R.id.item_short_desc);

        itemCost = (TextView) itemView.findViewById(R.id.item_price);

        availability = (TextView) itemView
                .findViewById(R.id.iteam_avilable);

        quanitity = (TextView) itemView.findViewById(R.id.iteam_amount);

        itemName.setSelected(true);

        imageView = ((ImageView) itemView.findViewById(R.id.product_thumb));

        addItem = ((TextView) itemView.findViewById(R.id.add_item));

        removeItem = ((TextView) itemView.findViewById(R.id.remove_item));
        this.clickListener = clickListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        clickListener.onItemClick(v, getPosition());
    }


    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }
}


