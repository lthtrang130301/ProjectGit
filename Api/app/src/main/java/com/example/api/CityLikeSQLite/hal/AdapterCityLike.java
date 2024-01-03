package com.example.api.CityLikeSQLite.hal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.api.R;

import java.util.ArrayList;


public class AdapterCityLike extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<CityModel> data;
    public AdapterCityLike(Context context, int resource, ArrayList<CityModel>data) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public View getView(int i,View view, ViewGroup viewGroup) {

        View viewTemp;
        if (view == null) {
            viewTemp = View.inflate(viewGroup.getContext(), R.layout.item_list_location, null);
        } else {
            viewTemp = view;
        }

        TextView textTenTp = viewTemp.findViewById(R.id.txtCity);
        TextView textMota = viewTemp.findViewById(R.id.note);
        ImageButton deleteButton = viewTemp.findViewById(R.id.deleteButton);

        CityModel cityModel = data.get(i);

        textTenTp.setText(cityModel.getTenCity());
        textMota.setText(cityModel.getMota());

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete");
                builder.setMessage("Are you sure delete ?");
                builder.setIcon(android.R.drawable.ic_menu_delete);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        DbCityLike dbCityLike = new DbCityLike(context);
                        int result = dbCityLike.DELETE(cityModel.getId());
                        if( result > 0){
                            Toast.makeText(context,"delete", Toast.LENGTH_SHORT).show();
                            data.remove(cityModel);
                            notifyDataSetChanged();

                        }else {
                            Toast.makeText(context,"error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("No", null);
                builder.show();

            }
        });

        return viewTemp;
    }
}
