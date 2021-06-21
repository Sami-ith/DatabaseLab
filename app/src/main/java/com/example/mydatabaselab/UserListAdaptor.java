package com.example.mydatabaselab;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mydatabaselab.db.AppDatabase;
import com.example.mydatabaselab.db.User;
import java.util.List;

public class UserListAdaptor extends RecyclerView.Adapter<UserListAdaptor.MyViewHolder> {

    private Context context;
    private List<User> userList;

    public UserListAdaptor(Context context) {
        this.context=context;

    }
    public void setUserList(List<User> userList) {
        this.userList=userList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public UserListAdaptor.MyViewHolder  onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListAdaptor.MyViewHolder holder, int position) {
        holder.tvFName.setText(this.userList.get(position).firstName);
        holder.tvLName.setText(this.userList.get(position).lastName);
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition=position;
                User removedUser=userList.get(itemPosition);
                userList.remove(itemPosition);
                notifyItemRemoved(itemPosition);
                AppDatabase db=AppDatabase.getINSTANCE(context.getApplicationContext());
                db.userDao().delete(removedUser);
                notifyDataSetChanged();

            }
        });



    }

    @Override
    public int getItemCount() {
        return this.userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvFName;
        TextView tvLName;
        ImageView imgDelete;

        public MyViewHolder( View itemView) {
            super(itemView);
             tvFName=itemView.findViewById(R.id.tv_first_name);
             tvLName=itemView.findViewById(R.id.tv_last_name);
             imgDelete=itemView.findViewById(R.id.img_delete);
        }
    }
    }

