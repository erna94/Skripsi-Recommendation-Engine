/*
 * Copyright (c) 2017. http://hiteshsahu.com- All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * If you use or distribute this project then you MUST ADD A COPY OF LICENCE
 * along with the project.
 *  Written by Hitesh Sahu <hiteshkrsahu@Gmail.com>, 2017.
 */

package com.ern.retailapp.view.fragment;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ern.retailapp.R;
import com.ern.retailapp.model.CenterRepository;
import com.ern.retailapp.model.entities.ProductUI;
import com.ern.retailapp.util.Utils;
import com.ern.retailapp.util.Utils.AnimationType;
import com.ern.retailapp.view.activities.ECartHomeActivity;
import com.ern.retailapp.view.adapter.ProductListAdapter;
import com.ern.retailapp.view.adapter.ProductsInCategoryPagerAdapter;
import com.ern.retailapp.view.adapter.SearchListAdapter;
import com.ernchatbot.ui.WitAITask;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Set;

public class SearchProductFragment extends Fragment {

    private static final int REQ_SCAN_RESULT = 200;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    ArrayList<ProductUI> searchProductList = new ArrayList<>();
    boolean searchInProgress = false;
    private TextView heading;
    private ImageButton btnSpeak;
    private EditText searchInput;
    private ListView searchListView;

    /** The search adapter. */
    // private SearchListArrayAdapter searchAdapter;
    /**
     * The root view.
     */
    private View rootView;

    public static Fragment newInstance() {
        // TODO Auto-generated method stub
        return new SearchProductFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.frag_search_product,
                container, false);

        btnSpeak = (ImageButton) rootView.findViewById(R.id.btnSpeak);

        heading = (TextView) rootView.findViewById(R.id.txtSpeech_heading);
        searchInput = (EditText) rootView.findViewById(R.id.edt_search_input);
        searchInput.setSelected(true);
        searchListView = (ListView) rootView.findViewById(R.id.search_list_view);


        final SearchListAdapter searchAdapter = new SearchListAdapter(container, getContext());
        searchListView.setAdapter(searchAdapter);

        searchListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getActivity(), "Selected" + position, 500)
                        .show();
            }
        });


        searchInput.setOnKeyListener(
                new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {

                        Log.println(Log.VERBOSE, "android-app", "User menekan tombol " + keyCode);
                        if(keyCode == KeyEvent.KEYCODE_ENTER) {
                            // user menekan Enter
                            Log.println(Log.VERBOSE, "android-app", "Enter is pressed");

                            // memanggil Wit AsyncTask
                            WitAITask witAITask = new WitAITask(searchAdapter, searchListView);
                            witAITask.execute(searchInput.getText().toString());
                        }

                        return true;
                    }
                });


        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });

        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
        rootView.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    Log.println(Log.VERBOSE, "android-app", "User menekan tombol " + keyCode);

                    Utils.switchContent(R.id.frag_container,
                            Utils.HOME_FRAGMENT,
                            ((ECartHomeActivity) (getContext())),
                            AnimationType.SLIDE_DOWN);

                }

                return true;
            }
        });
        return rootView;

    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(android.app.Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * Showing google speech input dialog.
     */
    private void promptSpeechInput() {
        searchInProgress = true;
        searchProductList.clear();

        heading.setText("Search Products");

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "What do you wish for");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getActivity(),
                    "Voice search not supported by your device ",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input.
     *
     * @param requestCode the request code
     * @param resultCode  the result code
     * @param data        the data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        searchInProgress = false;

        if (resultCode == getActivity().RESULT_OK && null != data) {
            switch (requestCode) {
                case REQ_CODE_SPEECH_INPUT: {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    heading.setText("Showing Results for " + result.get(0));

                    break;
                }

                case REQ_SCAN_RESULT:
                    //
                    // String contents = data.getStringExtra("SCAN_RESULT");
                    // String format = data.getStringExtra("SCAN_RESULT_FORMAT");
                    // Toast.makeText(getActivity(), "Scan Success", 1000).show();
                    break;

            }
        }
    }

}
