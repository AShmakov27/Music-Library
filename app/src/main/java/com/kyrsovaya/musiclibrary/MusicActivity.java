package com.kyrsovaya.musiclibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;


import com.kyrsovaya.musiclibrary.UI.main.MainFragment;
import com.kyrsovaya.musiclibrary.UI.saved.SavedFragment;
import com.kyrsovaya.musiclibrary.UI.search.SearchFragment;
import com.kyrsovaya.musiclibrary.databinding.ActivityMusicBinding;

public class MusicActivity extends AppCompatActivity{

    private ActivityMusicBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMusicBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new MainFragment());

        binding.bottomNavMenu.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.mainFragment:
                    replaceFragment(new MainFragment());
                    break;
                case R.id.savedFragment:
                    replaceFragment(new SearchFragment());
                    break;
                case R.id.searchFragment:
                    replaceFragment(new SavedFragment());
                    break;
            }
            return  true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}