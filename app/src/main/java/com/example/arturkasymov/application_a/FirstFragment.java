package com.example.arturkasymov.application_a;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FirstFragment extends Fragment {

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

        final Button button = view.findViewById(R.id.button);
        final EditText editText = view.findViewById(R.id.editText);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DBHandler dbHandler = new DBHandler(getContext());

               // Re_cord record = new Re_cord(editText.getText().toString(), 1, 1);
               // dbHandler.addRe_cord(record);
                //editText.setText(dbHandler.getRe_cord(0).getReference().toString());

            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}