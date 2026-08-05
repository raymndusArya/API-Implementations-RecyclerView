package com.example.api_recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.jspecify.annotations.NonNull;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private Context context;
    private ArrayList<UserModel> userList;

    public UserAdapter(Context context, ArrayList<UserModel> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserModel user = userList.get(position);

        holder.txtLat.setText("Latitude : " + user.getLat());
        holder.txtLng.setText("Longitude : " + user.getLng());
        holder.txtCompany.setText("Company : " + user.getCompany());
        holder.txtId.setText("ID : " + user.getId());
        holder.txtPhone.setText("Phone Number : " + user.getPhone());
        holder.txtWebsite.setText("Website : " + user.getWebsite());
        holder.txtAddress.setText("Address : " + user.getAddress());
        holder.txtName.setText("Name : " + user.getNama());
        holder.txtUsername.setText("Username :" + user.getUsername());
        holder.txtEmail.setText("Email Address : " + user.getEmail());
        holder.txtCatchPhrase.setText("Catch Phrase : " + user.getCatchPhrase());
        holder.txtBs.setText("Bussiness : " + user.getBs());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtUsername, txtEmail, txtId, txtAddress, txtPhone, txtWebsite, txtCompany, txtLat, txtLng, txtCatchPhrase, txtBs;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtLat = itemView.findViewById(R.id.txtLat);
            txtLng = itemView.findViewById(R.id.txtLng);
            txtCompany = itemView.findViewById(R.id.txtCompany);
            txtWebsite = itemView.findViewById(R.id.txtWebsite);
            txtPhone = itemView.findViewById(R.id.txtPhone);
            txtAddress = itemView.findViewById(R.id.txtAddress);
            txtId = itemView.findViewById(R.id.txtId);
            txtName = itemView.findViewById(R.id.txtNama);
            txtUsername = itemView.findViewById(R.id.txtUsername);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            txtCatchPhrase = itemView.findViewById(R.id.txtCatchPhrase);
            txtBs = itemView.findViewById(R.id.txtBs);
        }
    }
}
