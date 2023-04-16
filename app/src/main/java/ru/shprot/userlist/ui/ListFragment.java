package ru.shprot.userlist.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import ru.shprot.userlist.R;
import ru.shprot.userlist.databinding.FragmentListBinding;
import ru.shprot.userlist.model.User;
import ru.shprot.userlist.model.UserAdapter;

public class ListFragment extends Fragment {

    public ArrayList<User> users;
    FragmentListBinding binding;
    public ListFragment(ArrayList<User> list) {
        this.users = list;
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentListBinding
                .bind(inflater.inflate(R.layout.fragment_list, container, false));

        return binding.getRoot();
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(new UserAdapter(users));

        initSearch();
        initSortMenu();
    }




    private void initSearch() {

        TextInputLayout textInputLayout = getActivity().findViewById(R.id.tv_search);
        TextInputEditText editText = getActivity().findViewById(R.id.search_editText);


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                    ArrayList<User> newData = new ArrayList<>();

                    for (User user: users)
                        if (user.getFirstName().contains(s) || user.getLastName().contains(s)
                        || user.getUserTag().contains(s))
                            newData.add(user);

                    UserAdapter userAdapter = (UserAdapter) binding.recyclerView.getAdapter();
                    userAdapter.setNewData(newData);

                    if (newData.size() == 0)
                        getActivity().findViewById(R.id.noResultsLayout).setVisibility(View.VISIBLE);
                    else
                        getActivity().findViewById(R.id.noResultsLayout).setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
    }




    private void initSortMenu() {

    }

}
