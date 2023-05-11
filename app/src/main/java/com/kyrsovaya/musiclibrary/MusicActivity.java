package com.kyrsovaya.musiclibrary;

import static com.kyrsovaya.musiclibrary.utils.Constants.PUT_ALBUM_NAME;
import static com.kyrsovaya.musiclibrary.utils.Constants.PUT_ARTIST_NAME;
import static com.kyrsovaya.musiclibrary.utils.Constants.PUT_TRACK_NAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kyrsovaya.musiclibrary.adapter.GeoTopArtistsAdapter;
import com.kyrsovaya.musiclibrary.adapter.TopArtistsAdapter;
import com.kyrsovaya.musiclibrary.data.ClickListener;
import com.kyrsovaya.musiclibrary.models.ViewModel.MusicActivityViewModel;
import com.kyrsovaya.musiclibrary.models.artist.Artist;
import com.kyrsovaya.musiclibrary.models.artist.Artists;

import java.util.Collections;
import java.util.List;


public class MusicActivity extends AppCompatActivity{
    MusicActivityViewModel viewModel = null;
    RecyclerView topArtistsRecyclerView;
    RecyclerView geoTopArtistsRecyclerView;
    TopArtistsAdapter topArtistsAdapter;
    GeoTopArtistsAdapter geoTopArtistsAdapter;
    List<Artist> topArtists = Collections.emptyList();
    List<Artist> geoTopArtists = Collections.emptyList();
    LinearLayoutManager linearLayoutManagerTopArtists;
    LinearLayoutManager linearLayoutManagerGeoTopArtists;

    private String artistName;
    private String albumName;
    private String trackName;
    private Button artistsButton;
    private Button albumsButton;
    private Button tracksButton;
    private TextView artistSearchEditText;
    private TextView albumSearchEditText;
    private TextView trackSearchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        viewModel = new ViewModelProvider(this).get(MusicActivityViewModel.class);
        linearLayoutManagerTopArtists = new LinearLayoutManager(MusicActivity.this, linearLayoutManagerTopArtists.HORIZONTAL, false);
        linearLayoutManagerGeoTopArtists = new LinearLayoutManager(MusicActivity.this, linearLayoutManagerGeoTopArtists.HORIZONTAL, false);
        viewModel.fetchTopArtists();
        viewModel.fetchGeoTopArtists();
        setUpObservers();
        setUpUI();

        topArtistsRecyclerView = findViewById(R.id.topArtistsRecycleView);
        topArtistsRecyclerView.setLayoutManager(linearLayoutManagerTopArtists);
        topArtistsAdapter = new TopArtistsAdapter(topArtists, getApplication());
        topArtistsRecyclerView.setAdapter(topArtistsAdapter);

        geoTopArtistsRecyclerView = findViewById(R.id.geoTopArtistsRecycleView);
        geoTopArtistsRecyclerView.setLayoutManager(linearLayoutManagerGeoTopArtists);
        geoTopArtistsAdapter = new GeoTopArtistsAdapter(geoTopArtists, getApplication());
        geoTopArtistsRecyclerView.setAdapter(geoTopArtistsAdapter);

        searchArtist();
        searchAlbum();
        searchTrack();

        setUpAlbumButton();
        setUpArtistButton();
        setUpTrackButton();
        clickItemFromTopList();
        clickItemFromGeoList();

        viewModel = new ViewModelProvider(this).get(MusicActivityViewModel.class);
    }
    private void setUpObservers() {

        viewModel.getTopArtists().observe(this, new Observer<Artists>() {
            @Override
            public void onChanged(Artists artists) {
                topArtists = artists.getArtist();
                topArtistsAdapter.addTopArtistsList(topArtists);
            }
        });

        viewModel.getGeoTopArtists().observe(this, new Observer<Artists>() {
            @Override
            public void onChanged(Artists artists) {
                geoTopArtists = artists.getArtist();
                geoTopArtistsAdapter.addGeoTopArtistsList(geoTopArtists);
            }
        });
    }

    private void searchArtist() {
        artistSearchEditText = findViewById(R.id.searchArtistEditText);

        artistSearchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                artistName = s.toString();
            }
        });
    }

    private void searchAlbum() {
        albumSearchEditText = findViewById(R.id.searchAlbumEditText);

        albumSearchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                albumName = s.toString();
            }
        });
    }

    private void searchTrack() {
        trackSearchEditText = findViewById(R.id.searchTrackEditText);

        trackSearchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                trackName = s.toString();
            }
        });
    }

    private void setUpUI() {
        artistsButton = findViewById(R.id.artistSearchButton);
        albumsButton = findViewById(R.id.albumSearchButton);
        tracksButton = findViewById(R.id.trackSearchButton);
    }

    private void setUpArtistButton() {
        artistsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MusicActivity.this, ArtistSearchActivity.class);
                intent.putExtra(PUT_ARTIST_NAME, artistName);
                startActivity(intent);
            }
        });
    }

    private void setUpAlbumButton() {
        albumsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MusicActivity.this, AlbumSearchActivity.class);
                intent.putExtra(PUT_ALBUM_NAME, albumName);
                startActivity(intent);
            }
        });
    }

    private void setUpTrackButton() {
        tracksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MusicActivity.this, TrackSearchActivity.class);
                intent.putExtra(PUT_TRACK_NAME, trackName);
                startActivity(intent);
            }
        });
    }

    private void clickItemFromTopList(){
        topArtistsAdapter.setOnItemClickListener(new ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent intent = new Intent(MusicActivity.this, ArtistDetailsActivity.class);
                intent.putExtra(PUT_ARTIST_NAME, topArtists.get(position).getName());
                startActivity(intent);
            }
        });
    }

    private void clickItemFromGeoList(){
        geoTopArtistsAdapter.setOnItemClickListener(new ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent intent = new Intent(MusicActivity.this, ArtistDetailsActivity.class);
                intent.putExtra(PUT_ARTIST_NAME, geoTopArtists.get(position).getName());
                startActivity(intent);
            }
        });
    }
}