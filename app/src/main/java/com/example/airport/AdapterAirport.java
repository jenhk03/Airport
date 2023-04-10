package com.example.airport;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class AdapterAirport extends RecyclerView.Adapter<AdapterAirport.ViewHolderAirport>
{
    private Context ctx;
    private ArrayList arrID, arrName, arrCity, arrAddress;
    public AdapterAirport(Context ctx, ArrayList arrID, ArrayList arrName, ArrayList arrCity, ArrayList arrAddress)
    {
        this.ctx = ctx;
        this.arrID = arrID;
        this.arrName = arrName;
        this.arrCity = arrCity;
        this.arrAddress = arrAddress;
    }
    @NonNull
    @Override
    public ViewHolderAirport onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(ctx).inflate(R.layout.list_item_airport, parent, false);
        return new ViewHolderAirport(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolderAirport holder, int position)
    {
        holder.tvID.setText(arrID.get(position).toString());
        holder.tvName.setText(arrName.get(position).toString());
        holder.tvCity.setText(arrCity.get(position).toString());
        holder.tvAddress.setText(arrAddress.get(position).toString());
    }
    @Override
    public int getItemCount()
    {
        return arrName.size();
    }
    public class ViewHolderPlayer extends RecyclerView.ViewHolder
    {
        private TextView tvID, tvName, tvCity, tvAddress;
        public ViewHolderPlayer(@NonNull View itemView)
        {
            super(itemView);
            tvID = itemView.findViewById(R.id.tv_id);
            tvName = itemView.findViewById(R.id.tv_name);
            tvCity = itemView.findViewById(R.id.tv_city);
            tvAddress = itemView.findViewById(R.id.tv_address);
            itemView.setOnLongClickListener(new View.OnLongClickListener()
                                            {
                                                @Override
                                                public boolean onLongClick(View v)
                                                {
                                                    AlertDialog.Builder message = new AlertDialog.Builder(ctx);
                                                    message.setTitle("Caution!");
                                                    message.setMessage("Anda memilih " + tvName.getText().toString() + ". Pilih perintah yang diinginkan");
                                                    message.setCancelable(true);
                                                    message.setPositiveButton("Ubah", new DialogInterface.OnClickListener()
                                                            {
                                                                @Override
                                                                public void onClick(DialogInterface dialog, int which)
                                                                {
                                                                    Intent intent = new Intent(ctx, ChangeActivity.class);
                                                                    intent.putExtra("varID", tvID.getText().toString());
                                                                    intent.putExtra("varName", tvName.getText().toString());
                                                                    intent.putExtra("varCity", tvCity.getText().toString());
                                                                    intent.putExtra("varAddress", tvAddress.getText().toString());
                                                                    ctx.startActivity(intent);
                                                                }
                                                            }
                                                    );
                                                    message.setNegativeButton("Hapus", new DialogInterface.OnClickListener()
                                                            {
                                                                @Override
                                                                public void onClick(DialogInterface dialog, int which)
                                                                {
                                                                    MyDatabaseHelper myDB = new MyDatabaseHelper(ctx);
                                                                    long exec = myDB.deletePlayer(tvID.getText().toString());
                                                                    if (exec == -1)
                                                                    {
                                                                        Toast.makeText(ctx, "Gagal menghapus data", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                    else
                                                                    {
                                                                        Toast.makeText(ctx, "Sukses menghapus data", Toast.LENGTH_SHORT).show();
                                                                        dialog.dismiss();
                                                                        ((MainActivity) ctx).onResume();
                                                                    }
                                                                }
                                                            }
                                                    );
                                                    message.show();
                                                    return false;
                                                }
                                            }
            );
        }
    }
}