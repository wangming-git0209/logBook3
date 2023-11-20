package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.service.autofill.UserData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private Context context;
    private Activity activity;
    private int layoutResourceId;
    private List<UserModel> users;

    public CustomAdapter(Context context, int layoutResourceId, List<UserModel> users) {
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.users = users;
    }

    public CustomAdapter(Activity activity, Context context, List<UserModel> users) {
        this.activity = activity;
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.info_card_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.convertView = view; // Thêm dòng này để gán giá trị cho convertView
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        UserModel userModel = users.get(position);
        holder.textView_name.setText(String.valueOf(userModel.getName()));
        holder.textView_Dob.setText(String.valueOf(userModel.getDateOfBirth()));
        holder.textView_email.setText(String.valueOf(userModel.getEmail()));
        String selectedTag = userModel.getSelectedImageTag();
        int imageResourceId = context.getResources().getIdentifier(selectedTag, "drawable", context.getPackageName());
        if (imageResourceId != 0) {

            holder.imageSelectedView.setImageResource(imageResourceId);
        }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView_name, textView_Dob, textView_email;
        private ImageView imageSelectedView;


        private LinearLayout item_view;
        private View convertView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_name = itemView.findViewById(R.id.textView_name);
            textView_Dob = itemView.findViewById(R.id.textView_Dob);
            textView_email = itemView.findViewById(R.id.textView_email);
            imageSelectedView = itemView.findViewById(R.id.imageView_SelectedImage);



        }
    }

}
