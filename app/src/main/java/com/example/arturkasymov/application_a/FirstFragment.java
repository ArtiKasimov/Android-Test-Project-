package com.example.arturkasymov.application_a;


import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FirstFragment extends Fragment {

    private String image_URL;
    private final String EXTRA_IMAGE_URL = "com.example.arturkasymov.application_a.image_URL";
    private final String EXTRA_FRAGMENT_ID = "com.example.arturkasymov.application_a.FRAGMENT_ID";
    private final String FRAGMENT_ID = "1";

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.first_fragment, container, false);

        final Button button = view.findViewById(R.id.buttonOK);
        final EditText editText = view.findViewById(R.id.editText);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                image_URL=s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent();
                i.setComponent(new ComponentName("com.example.arturkasymov.application_b",
                        "com.example.arturkasymov.application_b.MainActivity"));
                i.putExtra( EXTRA_IMAGE_URL, image_URL);
                i.putExtra(EXTRA_FRAGMENT_ID,FRAGMENT_ID);
                startActivity(i);
            }
        });
        return view;
    }

}