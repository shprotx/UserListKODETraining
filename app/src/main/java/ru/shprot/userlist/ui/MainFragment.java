package ru.shprot.userlist.ui;

import static android.content.ContentValues.TAG;

import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.ViewModelInitializer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.shprot.userlist.R;
import ru.shprot.userlist.databinding.FragmentListBinding;
import ru.shprot.userlist.databinding.FragmentMainBinding;
import ru.shprot.userlist.model.PagerAdapter;
import ru.shprot.userlist.model.Repository;
import ru.shprot.userlist.model.User;
import ru.shprot.userlist.model.UserList;
import ru.shprot.userlist.model.UserService;
import ru.shprot.userlist.viewModel.MainViewModel;

public class MainFragment extends Fragment {

    FragmentMainBinding binding;
    MainViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMainBinding.bind(inflater.inflate(R.layout.fragment_main, container,false));
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        showPlaceholdersAsContent();
        Repository repository = new Repository();
        UserService userService = repository.getInstance();
        Call<UserList> call = userService.getUsers();
        call.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                if (response.isSuccessful())
                    showContent(response.body());
            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                Log.d(TAG, "Current error: " + t.getLocalizedMessage());
            }
        });
    }




    private void showPlaceholdersAsContent() {
        binding.placeholderLayout.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.anim_placeholder));
    }

    private void showContent(UserList object) {
        binding.placeholderLayout.setVisibility(View.GONE);
        List<User> list = object.getList();
        String[] tabs = getResources().getStringArray(R.array.tabs);
        String[] departments = getResources().getStringArray(R.array.positions);
        PagerAdapter adapter = new PagerAdapter(this, list, tabs, departments);
        binding.viewPager.setAdapter(adapter);
        TabLayout tabLayout = getActivity().findViewById(R.id.tabLayout);
        TabLayoutMediator.TabConfigurationStrategy strategy = (tab, position) -> {
            tab.setText(tabs[position]);
        };

        new TabLayoutMediator(tabLayout, binding.viewPager, strategy).attach();
    }


}
