package com.example.arturkasymov.application_a;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SecondFragment extends Fragment {

    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment, container, false);
        DBHandler dbHandler = new DBHandler(getContext());


        //dbHandler.getAllRecords();
        ArrayList<String> references = new ArrayList<>();
        references.add("first reference");
        references.add("second reference");
        references.add("third reference");
        references.add("fourth reference");


        //ArrayList<Re_cord> allRecords = (ArrayList) (dbHandler.getAllRe_cords());

        // it's needed while we haven't custom adapter
        //ArrayList<String> references = new ArrayList<String>();
       // for (Re_cord temp: allRecords){
       //     references.add(temp.getReference());
        //}
        // it's end

        // it's must be improved
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    getContext(),
                    android.R.layout.simple_list_item_1,
                    references);

        ListView listView = view.findViewById(R.id.listview);
        listView.setAdapter(adapter);
        // it's end

        // Inflate the layout for this fragment
        return view;
    }

}
