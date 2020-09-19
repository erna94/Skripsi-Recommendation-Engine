package com.ernchatbot.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;


public class AboutActivity extends AppCompatActivity {
    CarouselView carouselView;
    int[] sampleImages = {R.drawable.about1, R.drawable.about2, R.drawable.about3, R.drawable.about4};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        carouselView = findViewById(R.id.carrouselView);
        carouselView.setPageCount(sampleImages.length);
        ImageListener imageListener = new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(sampleImages[position]);
            }
        };
        carouselView.setImageListener(imageListener);
    }
    //fungsi buat menampilkan keranjang icon di menu
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    // fungsi untuk menghandle click sewaktu cart icon di click user
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}