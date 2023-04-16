package ru.shprot.userlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.google.android.material.tabs.TabLayout;

import ru.shprot.userlist.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding
                .bind(LayoutInflater.from(this).inflate(R.layout.activity_main, null));
        setContentView(binding.getRoot());

        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();

        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(navController.getGraph()).build();

        setSupportActionBar(binding.toolbar);
        addTabs();
    }

    private void addTabs() {
        String[] tabs = getResources().getStringArray(R.array.tabs);
        for (int i = 0; i < tabs.length; i++)
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(tabs[i]), i);
    }
}