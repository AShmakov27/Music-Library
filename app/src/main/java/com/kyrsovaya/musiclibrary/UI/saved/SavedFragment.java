package com.kyrsovaya.musiclibrary.UI.saved;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kyrsovaya.musiclibrary.R;
import com.kyrsovaya.musiclibrary.databinding.FragmentSavedBinding;

public class SavedFragment extends Fragment {

    private FragmentSavedBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSavedBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }
}