package ru.shprot.userlist.model;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

import ru.shprot.userlist.ui.ListFragment;

public class PagerAdapter extends FragmentStateAdapter {

    List<User> users;
    String[] tabs;
    String[] departments;

    public PagerAdapter(@NonNull Fragment fragment, List<User> list, String[] tabs, String[] departments) {
        super(fragment);
        this.users = list;
        this.tabs = tabs;
        this.departments = departments;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        ArrayList<User> currentList = new ArrayList<>();
        if (position == 0)
            currentList.addAll(users);
        else
            for (int i = 0; i < users.size(); i++)
                if (users.get(i).department.equals(departments[position]))
                    currentList.add(users.get(i));
        return new ListFragment(currentList);
    }

    @Override
    public int getItemCount() {
        return 13;
    }
}
