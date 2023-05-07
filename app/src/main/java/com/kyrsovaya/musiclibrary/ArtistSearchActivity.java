package com.kyrsovaya.musiclibrary;

import static com.kyrsovaya.musiclibrary.utils.Constants.LOG_TAG;
import static com.kyrsovaya.musiclibrary.utils.Constants.PUT_ARTIST_NAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.kyrsovaya.musiclibrary.adapter.ArtistsAdapter;
import com.kyrsovaya.musiclibrary.data.ClickListener;
import com.kyrsovaya.musiclibrary.models.ViewModel.ArtistSearchViewModel;
import com.kyrsovaya.musiclibrary.models.artist.Artist;
import com.kyrsovaya.musiclibrary.models.artist.ArtistSearchResults;

import java.util.Collections;
import java.util.List;

public class ArtistSearchActivity extends AppCompatActivity {

    ArtistSearchViewModel viewModel = null;
    RecyclerView recyclerView;
    ArtistsAdapter artistsAdapter;
    List<Artist> artistList = Collections.emptyList();
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_search);

        Intent intent = getIntent();
        String artistName = intent.getStringExtra(PUT_ARTIST_NAME);

        viewModel = new ViewModelProvider(this).get(ArtistSearchViewModel.class);
        linearLayoutManager = new LinearLayoutManager(ArtistSearchActivity.this);

        viewModel.fetchArtistSearchResults(artistName);
        setUpObservers();

        recyclerView = findViewById(R.id.artistsSearchRecycleView);
        recyclerView.setLayoutManager(linearLayoutManager);
        artistsAdapter = new ArtistsAdapter(artistList, getApplication());
        recyclerView.setAdapter(artistsAdapter);

        clickItemFromList();
    }
    private void setUpObservers() {

        viewModel.getArtistSearchResults().observe(this, new Observer<ArtistSearchResults>() {
                    @Override
                    public void onChanged(ArtistSearchResults artistSearchResults) {
                        Log.i(LOG_TAG, "Activity onResponse: " + artistSearchResults);
                        artistList = artistSearchResults.getArtistmatches().getArtist();
                        artistsAdapter.addList(artistList);
                    }
                }
        );

    }

    private void clickItemFromList() {
        artistsAdapter.setOnItemClickListener(new ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent intent = new Intent(ArtistSearchActivity.this, ArtistDetailsActivity.class);
                intent.putExtra(PUT_ARTIST_NAME, artistList.get(position).getName());
                startActivity(intent);
            }
        });

    }
}