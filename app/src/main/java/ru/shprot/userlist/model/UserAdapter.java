package ru.shprot.userlist.model;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ru.shprot.userlist.R;
import ru.shprot.userlist.databinding.ItemUserBinding;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    ArrayList<User> users;
    ItemUserBinding binding;

    public UserAdapter(List<User> list) {
        this.users = (ArrayList<User>) list;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemUserBinding.bind(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false));
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = users.get(position);
        holder.name.setText(user.firstName + " " + user.lastName);
        holder.position.setText(user.position);
        holder.initials.setText(user.userTag);
        //holder.image.setImageURI(Uri.parse(user.avatarUrl));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setNewData(ArrayList<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }
    class UserViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;
        TextView initials;
        TextView position;

        public UserViewHolder(@NonNull ItemUserBinding b) {
            super(b.getRoot());
            image = b.personImage;
            name = b.nameTextView;
            initials = b.userTagTextView;
            position = b.posisionTextView;
        }
    }
}
