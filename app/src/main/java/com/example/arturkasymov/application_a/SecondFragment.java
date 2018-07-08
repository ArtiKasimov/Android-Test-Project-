package com.example.arturkasymov.application_a;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.graphics.Color.GRAY;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;

public class SecondFragment extends Fragment {


    private final String EXTRA_FRAGMENT_ID = "com.example.arturkasymov.application_a.FRAGMENT_ID";
    private final String FRAGMENT_ID = "2";
    private final String ROW_ID = "ID";

    private RecyclerView mRe_cordRecyclerView;
    private Re_codrAdapter mAdapter;

    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.second_fragment, container, false);

        DBHandler db = new DBHandler(getContext());
        List<Re_cord> re_cords = db.getAllRecords();

        mRe_cordRecyclerView = (RecyclerView) v.findViewById(R.id.Re_cord_Recycler_View);
        mRe_cordRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // mRe_cordRecyclerView.setOnClickListener();


        ////// it's Misha's code
        /*final ArrayList<Re_cord> alc = new ArrayList<>(re_cords);
        ListView lv = (ListView)v.findViewById(R.id.listView);
        //lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
        //    @Override
        //    public void onItemClick(AdapterView<?> parent, View view,
        //                            int position, long id) {
        //        tvl.setText(alc.get(position).getReference());
        //        tvr.setText(alc.get(position).getStatus());
        //    }
        //});
        MyAdapter ma= new MyAdapter(alc,this.getLayoutInflater());//
        lv.setAdapter(ma);*///////////////


        /*
        // Here must be customAdapter
        ArrayList<String> references = new ArrayList<String>();
        for (Re_cord temp: re_cords){
            references.add(temp.getReference());
        }
        // it's end*/



        updateUI();

        return v;
    }



    private void updateUI(){
        DBHandler db = new DBHandler(getContext());
        List<Re_cord> Re_cords = db.getAllRecords();
        mAdapter = new Re_codrAdapter(Re_cords);
        mRe_cordRecyclerView.setAdapter(mAdapter);
}

/////// Misha's
/*class MyAdapter extends BaseAdapter{
    class ViewHolder {
        TextView tvLeft;
    }
    ArrayList<Re_cord> alc;
    LayoutInflater li;
    MyAdapter(ArrayList<Re_cord> alc, LayoutInflater li){
        this.alc = alc; this.li = li;
    }
    public int getCount(){ return alc.size(); }
    public long getItemId(int i){ return 0; }
    public Object getItem(int index){ return alc.get(index); }
    public View getView(int index, View v, ViewGroup vg){
        Re_cord temp = (Re_cord) getItem(index);
        ViewHolder vh = null;
        if(v != null){
            vh = (ViewHolder)v.getTag();
        }else{
            v = li.inflate(R.layout.second_fragment2, null);
            vh = new ViewHolder();
            vh.tvLeft = (TextView)v.findViewById(R.id.textViewLeft);
        }
        vh.tvLeft.setText(temp.getReference());
        vh.tvLeft.setBackgroundColor(R.color.colorAccent);
        v.setTag(vh);
        return v;
    }
}*//////////////

    private class Re_cordHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mLinkTextView;
        private TextView mDateTextView;
        private RelativeLayout mLayoutItem;
        int id;

        private Re_cord mRe_cord;

        public Re_cordHolder(View itemView){
            super(itemView);
            mLinkTextView = (TextView) itemView.findViewById(R.id.list_item_Re_cord_link_textView);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_re_cord_date_textView);
            mLayoutItem = (RelativeLayout) itemView.findViewById(R.id.list_item_layout);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v){
            id = getAdapterPosition();
            Intent i = new Intent();
            i.setComponent(new ComponentName("com.example.arturkasymov.application_b",
                    "com.example.arturkasymov.application_b.MainActivity"));
            i.putExtra(EXTRA_FRAGMENT_ID,FRAGMENT_ID);
            i.putExtra(ROW_ID,""+id);
            ///////////
            /// здесь место для передачи чего-то там
            /////////////
            startActivity(i);

        }

        public void bindRe_cord(Re_cord re_cord){
            mRe_cord =re_cord;
            mLinkTextView.setText(mRe_cord.getReference());
            mDateTextView.setText(mRe_cord.getTime().toString());
            switch (re_cord.getStatus()){
                case 1:{
                    mLayoutItem.setBackgroundColor(GREEN);
                    break;
                }
                case 2:{
                    mLayoutItem.setBackgroundColor(RED);
                    break;
                }
                case 3:{
                    mLayoutItem.setBackgroundColor(GRAY);
                    break;
                }
            }

        }


    }


    private class Re_codrAdapter extends RecyclerView.Adapter<Re_cordHolder>{

        private List<Re_cord> mRe_cords;

        public Re_codrAdapter(List<Re_cord> crimes){
            mRe_cords = crimes;
        }

        @Override
        public Re_cordHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View v = layoutInflater.inflate(R.layout.list_item_re_cord,parent,false);
            return new Re_cordHolder(v);
        }

        @SuppressLint("ResourceAsColor")
        @Override
        public void onBindViewHolder(Re_cordHolder holder, int position){
            Re_cord re_cord = mRe_cords.get(position);

            holder.bindRe_cord(re_cord);


            /*часть которая красит, переделать
            switch (re_cord.getStatus()){
                case 1:{
                    holder.mTitleTextView.setBackgroundColor(R.color.colorGreen);
                    break;
                }
                case 2:{
                    holder.mTitleTextView.setBackgroundColor(R.color.colorRed);
                    break;
                }
                case 3:{
                    holder.mTitleTextView.setBackgroundColor(R.color.colorGray);
                    break;
                }
            }*/

        }

        public int getItemCount(){
            return mRe_cords.size();
        }
    }

}