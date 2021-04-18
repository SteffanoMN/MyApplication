package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MicrosoftAdapter extends RecyclerView.Adapter<MicrosoftAdapter.MahasiswaViewHolder> implements Filterable {

    private ArrayList<Microsoft> dataList;
    private ArrayList<Microsoft> dataListFilter;
    Context CT;

    public MicrosoftAdapter(ArrayList<Microsoft> dataList, Context CT) {
        this.dataList = dataList;
        this.CT = CT;
        this.dataListFilter = dataList;
    }

    @Override
    public MahasiswaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(CT);
        View view = layoutInflater.inflate(R.layout.itemview, parent, false);
        return new MahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MahasiswaViewHolder holder, int position) {
        holder.MainLayout.setAnimation(AnimationUtils.loadAnimation(CT, R.anim.fade_transition));
        holder.txtName.setText(dataListFilter.get(position).getProduct());
        holder.txtDesc.setText(dataListFilter.get(position).getDescription());
        holder.imgProduct.setImageResource(dataListFilter.get(position).getPicture());
        holder.MainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent NewMain = new Intent(CT, MicrosoftAppDetails.class);
                NewMain.putExtra("Name", dataListFilter.get(position).getProduct());
                NewMain.putExtra("Version", dataListFilter.get(position).getVers());
                NewMain.putExtra("Description", dataListFilter.get(position).getDescription());
                NewMain.putExtra("Picture", dataListFilter.get(position).getPicture());
                CT.startActivity(NewMain);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataListFilter != null) ? dataListFilter.size() : 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String Key = constraint.toString();
                if (Key.isEmpty()){
                    dataListFilter = dataList;
                } else {
                    ArrayList<Microsoft> isFiltered = new ArrayList<>();
                    for (Microsoft row : dataList) {
                        if (row.getProduct().toLowerCase().contains(Key)) {
                            isFiltered.add(row);
                        }
                    }
                    dataListFilter = isFiltered;
                }
                FilterResults results = new FilterResults();
                results.values = dataListFilter;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                dataListFilter = (ArrayList<Microsoft>) results.values;
            }
        };
    }

    public class MahasiswaViewHolder extends RecyclerView.ViewHolder{
        private TextView txtName, txtDesc;
        private ImageView imgProduct;
        CardView MainLayout;

        public MahasiswaViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtDesc = (TextView) itemView.findViewById(R.id.txtDesc);
            imgProduct = (ImageView) itemView.findViewById(R.id.imgProduct);

            MainLayout = (CardView) itemView.findViewById(R.id.mainlayout);

        }

    }

}