package fr.charlin.mobileproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserAdapterVH> {
    private List<User> userList;
    private Context context;
    private ClickedItem clickedItem;

    public UsersAdapter(ClickedItem clickedItem) {
        this.clickedItem = clickedItem;
    }

    public void setData(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new UsersAdapter.UserAdapterVH(LayoutInflater.from(context).inflate(R.layout.row_users,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapterVH holder, int position) {
        User user = userList.get(position);

        String name = user.getName();
        String prefix = Character.toUpperCase(name.charAt(0)) + "";
        holder.prefix.setText(prefix);
        holder.name.setText(name);
        holder.imageMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedItem.ClickedUser(user);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UserAdapterVH extends  RecyclerView.ViewHolder {
        TextView name;
        TextView prefix;
        ImageView imageMore;
        public UserAdapterVH(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            prefix = itemView.findViewById(R.id.prefix);
            imageMore = itemView.findViewById(R.id.imageMore);
        }
    }

    public interface ClickedItem{
        public void ClickedUser(User user);
    }
}
