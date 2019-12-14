package com.example.sqliteinsertandview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<User> users;
    private Context context;
    private DatabaseHelper helper;

    UserAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
       View view= inflater.inflate(R.layout.model_user_design,parent,false);
        return new  ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final User user = users.get(position);

        holder.idTV.setText(String.valueOf(user.getId()));
        holder.nameTV.setText(user.getName());
        holder.ageTV.setText(user.getAge());
        //dotTV is three horizontal line in recyclerview
        holder.dotTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context,holder.dotTV);
                popupMenu.inflate(R.menu.option_menu);

                popupMenu.setOnMenuItemClickListener(new  PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){
                            case R.id.mnu_item_update:
                                Toast.makeText(context,"Updated",Toast.LENGTH_LONG).show();
                                break;
                            case R.id.mnu_item_delete:
                                //called DatabseHelper class
                                helper = new DatabaseHelper(context);
                                //Delete data from databse
                                helper.deleteData(user.getId());
                                //remove display item position from recyclerview
                                users.remove(position);
                                notifyDataSetChanged();
                                Toast.makeText(context,"Deleted",Toast.LENGTH_LONG).show();
                                break;
                            default:
                                break;

                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTV, idTV, ageTV,dotTV;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTV = itemView.findViewById(R.id.nameTV);
            idTV = itemView.findViewById(R.id.idTV);
            ageTV = itemView.findViewById(R.id.ageTV);
            dotTV = itemView.findViewById(R.id.dotTV);
        }
    }
}
