package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MahasiswaViewHolder> {

    private ArrayList<Mahasiswa> dataList;
    Context CT;

    public MahasiswaAdapter(ArrayList<Mahasiswa> dataList, Context Con) {
        this.dataList = dataList;
        CT = Con;
    }

    @Override
    public MahasiswaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.itemview, parent, false);
        // method ini gunanya untuk menghubungkan ke layout itemview
        return new MahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MahasiswaViewHolder holder, int position) {
        // 0 : Ani , 1 : Budi
        holder.txtNama.setText(dataList.get(position).getNama());
        holder.txtNpm.setText(dataList.get(position).getNim());
        holder.txtNoHp.setText(dataList.get(position).getNohp());
        holder.txtemail.setText(dataList.get(position).getEmail());
        holder.MainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent NewMain = new Intent(CT, MahasiswaDetails.class);
                NewMain.putExtra("Nama", dataList.get(position).getNama());
                NewMain.putExtra("Nim", dataList.get(position).getNim());
                NewMain.putExtra("Nomor", dataList.get(position).getNohp());
                NewMain.putExtra("Email", dataList.get(position).getEmail());
                CT.startActivity(NewMain);
            }
        });
    }

    // data nya berapa = 2, index 0 dan 1
    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class MahasiswaViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNama, txtNpm, txtNoHp , txtemail;
        ConstraintLayout MainLayout;

        public MahasiswaViewHolder(View itemView) {
            super(itemView);
            txtNama = (TextView) itemView.findViewById(R.id.txt_nama_mahasiswa);
            txtNpm = (TextView) itemView.findViewById(R.id.txt_npm_mahasiswa);
            txtNoHp = (TextView) itemView.findViewById(R.id.txt_nohp_mahasiswa);
            txtemail = (TextView) itemView.findViewById(R.id.txtemail);
            MainLayout = (ConstraintLayout) itemView.findViewById(R.id.mainlayout);
        }
    }
}