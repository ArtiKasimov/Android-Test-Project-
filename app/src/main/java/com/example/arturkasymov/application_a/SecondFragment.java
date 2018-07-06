package com.example.arturkasymov.application_a;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends Fragment {


    private final String EXTRA_FRAGMENT_ID = "com.example.arturkasymov.application_a.FRAGMENT_ID";
    private final String FRAGMENT_ID = "2";

    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment, container, false);
        DBHandler db = new DBHandler(getContext());


        db.addRecord(new Re_cord(0,"1111111", 1, 0));
        db.addRecord(new Re_cord(1,"2222222", 1, 0));
        db.addRecord(new Re_cord(2,"3333333", 1, 0));
        db.addRecord(new Re_cord(3,"4444444", 1, 0));
        db.addRecord(new Re_cord(4,"5555555", 1, 0));

        List<Re_cord> re_cords = db.getAllRecords();

        // Here must be customAdapter
        ArrayList<String> references = new ArrayList<String>();
        for (Re_cord temp: re_cords){
            references.add(temp.getReference());
        }
        // it's end

        // it's must be improved
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_list_item_1,
                references);

        ListView listView =view.findViewById(R.id.listview);
        listView.setAdapter(adapter);
        // it's end

        // Inflate the layout for this fragment


        /* Потом впихнуть туда, где будет открываться приложение Б с вкладки история
        Intent i = new Intent();
        i.setComponent(new ComponentName("com.example.arturkasymov.application_b",
                "com.example.arturkasymov.application_b.MainActivity"));
        i.putExtra(EXTRA_FRAGMENT_ID,FRAGMENT_ID);
        startActivity(i);

        */



        return view;
    }

}