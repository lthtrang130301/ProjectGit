//package com.example.api.Weather.adapter;
//
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//
//import com.example.api.R;
//import com.example.api.Weather.model.day.TIME;
//
//import java.util.ArrayList;
//
//public class ListAdapter extends BaseAdapter {
//
//    ArrayList<TIME> mlist;
//    public ListAdapter(ArrayList<TIME> mlist){
//        this.mlist = mlist;
//    }
//    @Override
//    public int getCount() {
//        return mlist.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return mlist.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View view;
//        if(convertView == null){
//            view = View.inflate(parent.getContext(), R.layout.item_list_location,null);
//        }else {
//            view = convertView;
//        }
//
//        TIME week = mlist.get(position);
//        TextView datetime, nhietdo,mota,icon,gio, max, min,doam;
//
////        datetime = view.findViewById(R.id.datetime);
////        nhietdo = view.findViewById(R.id.nhietdo);
////        mota = view.findViewById(R.id.mota);
////        icon = view.findViewById(R.id.icon);
////        gio = view.findViewById(R.id.gio);
////        max = view.findViewById(R.id.max);
////        min = view.findViewById(R.id.min);
////        doam = view.findViewById(R.id.doam);
//
//
//
//        return view;
//    }
//}
