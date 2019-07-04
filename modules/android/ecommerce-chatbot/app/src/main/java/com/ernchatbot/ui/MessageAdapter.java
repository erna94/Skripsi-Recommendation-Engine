package com.ernchatbot.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ernchatbot.service.response.Product;

public class MessageAdapter extends BaseAdapter {
    Context context;
    ArrayList<Message> messages;

    public MessageAdapter(Context context) {
        this.context = context;
        this.messages = new ArrayList<Message>();
    }

    public void tambahMessage(Message message) {
        // ERN_STEP 3: menambah ke ArrayList
        this.messages.add(message);

        // ERN_STEP 4: Memberi tahu UI untuk merefresh screen
        notifyDataSetChanged();
    }

    public void setProducts(List<Product> microServiceResult) {
        for(int i=0;i<microServiceResult.size();i++) {
            Product current = microServiceResult.get(i);
            String description = current.getDeskripsiProduct();
            String productName = current.getNamaProduct();
            String longDescription = current.getDeskripsiProduct();
            double salePrice = current.getHargaProduct();
            String imgUrl = current.getImageLink();
            String productId = current.getIdProduct() + "";
            Log.println(Log.VERBOSE, "android-app", "Title " + productName + " dengan harga " + salePrice);


            ProductInfo productInfo = new ProductInfo();
            productInfo.setImageLink(imgUrl);
            productInfo.setItemName(productName);
            productInfo.setItemPrice(salePrice);
            productInfo.setItemDescription(description);

            Message balasanSiBot =
                    new Message("Product List", false);

            balasanSiBot.setReplyType(Message.PRODUCT_LIST);
            balasanSiBot.setProductInfo(productInfo);

            messages.add(balasanSiBot);
        }

        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        // ERN_STEP 5: List view mengecheck jumlah benda dalam message
        int banyaknyaMessage =  messages.size();
        return banyaknyaMessage;
    }

    @Override
    public Object getItem(int i) {
        //Mendapatkan item berdasarkan jumlah message
       Message messageDiIndexI = messages.get(i);
       return messageDiIndexI;
    }

    @Override
    //mendapatkan itemId berdasarkan jumlah
    public long getItemId(int i) {
        return i;
    }

    @Override

    public View getView(int i, View view, ViewGroup viewGroup) {
        Message message = messages.get(i);
        // ERN_STEP 5: Untuk tiap message, ListView melakukan hal di bawah tersebut
        LayoutInflater messageInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        MessageHolder holder = new MessageHolder();

        if(message.isBelongsToCurrentUser()) {
            // ERN_STEP 6: XML Layout my_message.xml di hidupkan
            view = messageInflater.inflate(R.layout.my_message, null);

            // ERN_STEP 7: message body di set di holder
            holder.messageBody = (TextView) view.findViewById(R.id.message_body);
            view.setTag(holder);

            // ERN_STEP 8: Ambil message di index i, lalu masukan ke dalam layout messageBody
            holder.messageBody.setText(message.getText());
        } else if (message.getReplyType() == Message.NORMAL_REPLY) {
            view = messageInflater.inflate(R.layout.their_message, null);
            holder.avatar = (View) view.findViewById(R.id.avatar);
            holder.name = (TextView) view.findViewById(R.id.name);
            holder.messageBody = (TextView) view.findViewById(R.id.message_body);
            view.setTag(holder);

            holder.name.setText("Chatbot");
            holder.messageBody.setText(message.getText());
            GradientDrawable drawable = (GradientDrawable) holder.avatar.getBackground();
            drawable.setColor(Color.GRAY);
        } else if (message.getReplyType() == Message.PRODUCT_LIST) {
            view = messageInflater.inflate(R.layout.product_list, null);
            ProductHolder productHolder = new ProductHolder();
            productHolder.productImage =  (ImageView) view.findViewById(R.id.product_thumb);
            productHolder.itemName = (TextView) view.findViewById(R.id.item_name);
            productHolder.itemPrice = (TextView) view.findViewById(R.id.item_price);
            productHolder.itemDescription = (TextView) view.findViewById(R.id.item_short_desc);

            ProductInfo productInfo = message.getProductInfo();

            if(productInfo != null) {
                Log.println(Log.VERBOSE, "ernaBot", "Masuk ke product Info...");

                String imageLink = productInfo.getImageLink();
                Glide.with(context).load(imageLink).centerCrop().into(productHolder.productImage );
                productHolder.itemName.setText(productInfo.getItemName());
                productHolder.itemPrice.setText("IDR " +  productInfo.getItemPrice());
                productHolder.itemDescription.setText(productInfo.getItemDescription());
            }
        }

        return view;
    }
}


