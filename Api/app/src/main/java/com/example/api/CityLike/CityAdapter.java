package com.example.api.CityLike;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.api.R;

import java.util.List;

public class CityAdapter extends  RecyclerView.Adapter<CityAdapter.CityViewHodel>{
    private List<CityModel> mListCityModel;//khai bao list du lieu
    private IClick iClick;
    public interface IClick{
        void deleteCity(CityModel cityModel);
    }
    public CityAdapter(IClick iClick){
        this.iClick = iClick;
    }
    public void setData(List<CityModel> list){
        this.mListCityModel = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CityViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_location, parent, false);
        return new CityViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHodel holder, int position) {//ham set du lieu
        CityModel cityModel = mListCityModel.get(position);
        if(cityModel == null){
            return;
        }
        holder.txtCity.setText(cityModel.getCityname());
        holder.note.setText(cityModel.getDescription());

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClick.deleteCity(cityModel);
            }
        });


    }

    @Override
    public int getItemCount() {
        if (mListCityModel != null){
            return mListCityModel.size();
        }
        return 0;
    }

    public class CityViewHodel extends RecyclerView.ViewHolder {
        private TextView txtCity,note;
        private ImageButton deleteButton;

        public CityViewHodel(@NonNull View itemView) {//anhxa view
            super(itemView);

            txtCity = itemView.findViewById(R.id.txtCity);
            note = itemView.findViewById(R.id.note);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
