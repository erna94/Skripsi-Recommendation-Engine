package com.ernchatbot.ui;

import android.content.Context;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ern.retailapp.R;
import com.ern.retailapp.model.entities.Money;
import com.ern.retailapp.model.entities.ProductUI;
import com.ern.retailapp.util.ColorGenerator;
import com.ern.retailapp.view.customview.ProductViewHolder;
import com.ern.retailapp.view.customview.TextDrawable;

import java.math.BigDecimal;

public class ProductViewHolderMapper {
    TextDrawable.IBuilder mDrawableBuilder;
    private ColorGenerator mColorGenerator = ColorGenerator.MATERIAL;
    private TextDrawable drawable;
    private String imageUrl;
    private Context context;

    public ProductViewHolderMapper(Context context) {
        this.  mDrawableBuilder = TextDrawable.builder().beginConfig().withBorder(4)
                .endConfig().roundRect(10);
        this.context = context;
    }

    public void mapProductViewHolder(ProductViewHolder holder, ProductUI productUI) {

        holder.itemName.setText(productUI.getItemName());

        holder.itemDesc.setText(productUI.getItemShortDesc());

        String sellCostString = Money.asIDR(
                BigDecimal.valueOf(Double.valueOf(productUI.getSellMRP()))).toString()  + "  ";

        String buyMRP = Money.asIDR(
                BigDecimal.valueOf(Double.valueOf(productUI.getMRP()))).toString();

        // ERNA: Check nanti2 kalau discount lebih dari 0% ditambah dengan MRP
        String costString = sellCostString;

        holder.itemCost.setText(costString, TextView.BufferType.SPANNABLE);

        Spannable spannable = (Spannable) holder.itemCost.getText();

        spannable.setSpan(new StrikethroughSpan(), sellCostString.length(),
                costString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        mDrawableBuilder = TextDrawable.builder().beginConfig().withBorder(4)
                .endConfig().roundRect(10);

        drawable = mDrawableBuilder.build(String.valueOf(productUI.getItemName().charAt(0)),
                mColorGenerator
                .getColor(productUI.getItemName()));

        imageUrl = productUI.getImageURL();

        Glide.with(context).load(imageUrl).placeholder(drawable)
                .error(drawable).animate(R.anim.base_slide_right_in)
                .centerCrop().into(holder.imageView);

    }
}
