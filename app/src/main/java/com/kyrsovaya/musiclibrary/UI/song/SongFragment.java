package com.kyrsovaya.musiclibrary.UI.song;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kyrsovaya.musiclibrary.R;
import com.kyrsovaya.musiclibrary.databinding.FragmentSongBinding;

public class SongFragment extends Fragment {

    private FragmentSongBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSongBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }
}