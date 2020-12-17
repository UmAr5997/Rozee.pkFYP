package com.example.rozeepk.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rozeepk.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NewsFeed extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.news_feed, container, false);
    }
}
