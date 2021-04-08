package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MahasiswaViewHolder> implements Filterable {

    private ArrayList<Mahasiswa> dataList;
    private ArrayList<Mahasiswa> dataListFilter;
    Context CT;
    boolean isDark = false;

    public MahasiswaAdapter(ArrayList<Mahasiswa> dataList, Context CT, boolean isDark) {
        this.dataList = dataList;
        this.CT = CT;
        this.isDark = isDark;
        this.dataListFilter = dataList;
    }

    public MahasiswaAdapter(ArrayList<Mahasiswa> dataList, Context Con) {
        this.dataList = dataList;
        this.CT = Con;
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
        holder.txtNama.setText(dataListFilter.get(position).getNama());
        holder.txtNpm.setText(dataListFilter.get(position).getNim());
        holder.txtNoHp.setText(dataListFilter.get(position).getNohp());
        holder.txtemail.setText(dataListFilter.get(position).getEmail());
        holder.MainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent NewMain = new Intent(CT, MahasiswaDetails.class);
                NewMain.putExtra("Nama", dataListFilter.get(position).getNama());
                NewMain.putExtra("Nim", dataListFilter.get(position).getNim());
                NewMain.putExtra("Nomor", dataListFilter.get(position).getNohp());
                NewMain.putExtra("Email", dataListFilter.get(position).getEmail());
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
                    ArrayList<Mahasiswa> isFiltered = new ArrayList<>();
                    for (Mahasiswa row : dataList) {
                        if (row.getNama().toLowerCase().contains(Key)) {
                            isFiltered.add(row);
                        }
                        if (row.getEmail().toLowerCase().contains(Key)) {
                            isFiltered.add(row);
                        }
                        if (row.getNohp().toLowerCase().contains(Key)) {
                            isFiltered.add(row);
                        }
                        if (row.getNim().toLowerCase().contains(Key)) {
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
                dataListFilter = (ArrayList<Mahasiswa>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MahasiswaViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNama, txtNpm, txtNoHp , txtemail;
        CardView MainLayout;

        public MahasiswaViewHolder(View itemView) {
            super(itemView);
            txtNama = (TextView) itemView.findViewById(R.id.txt_nama_mahasiswa);
            txtNpm = (TextView) itemView.findViewById(R.id.txt_npm_mahasiswa);
            txtNoHp = (TextView) itemView.findViewById(R.id.txt_nohp_mahasiswa);
            txtemail = (TextView) itemView.findViewById(R.id.txtemail);
            MainLayout = (CardView) itemView.findViewById(R.id.mainlayout);

            if(isDark) {
                setDarkTheme();
            }

        }

        private void setDarkTheme() {

            MainLayout.setBackgroundResource(R.drawable.card_bg_dark);

        }

    }

}