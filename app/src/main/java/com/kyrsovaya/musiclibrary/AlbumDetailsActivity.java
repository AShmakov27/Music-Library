package com.kyrsovaya.musiclibrary;

import static com.kyrsovaya.musiclibrary.utils.Constants.PUT_ALBUM_NAME;
import static com.kyrsovaya.musiclibrary.utils.Constants.PUT_ARTIST_NAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kyrsovaya.musiclibrary.adapter.AlbumTracksAdapter;
import com.kyrsovaya.musiclibrary.models.ViewModel.AlbumDetailsViewModel;
import com.kyrsovaya.musiclibrary.models.album.Album;
import com.kyrsovaya.musiclibrary.models.track.Track;

import java.util.Collections;
import java.util.List;

public class AlbumDetailsActivity extends AppCompatActivity {

    AlbumDetailsViewModel viewModel = null;
    RecyclerView tracksRecyclerView;
    AlbumTracksAdapter albumTracksAdapter;
    List<Track> albumTracks = Collections.emptyList();
    LinearLayoutManager linearLayoutManager;
    TextView albumNameTextView;
    TextView artistNameTextView;
    ImageView albumImageView;
    TextView albumWikiTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_details);

        Intent intent = getIntent();
        String albumName = intent.getStringExtra(PUT_ALBUM_NAME);
        String artistName = intent.getStringExtra(PUT_ARTIST_NAME);

        viewModel = new ViewModelProvider(this).get(AlbumDetailsViewModel.class);
        linearLayoutManager = new LinearLayoutManager(AlbumDetailsActivity.this);
        tracksRecyclerView = findViewById(R.id.albumTracksRecycleView);
        tracksRecyclerView.setLayoutManager(linearLayoutManager);

        viewModel.fetchAlbumInfo(artistName, albumName);
        setUpObservers();
        setUpUI();

        albumTracksAdapter = new AlbumTracksAdapter(albumTracks, getApplication());
        tracksRecyclerView.setAdapter(albumTracksAdapter);
    }

    private void setUpObservers() {

        viewModel.getAlbum().observe(this, new Observer<Album>() {
                    @Override
                    public void onChanged(Album album) {
                        if(album.getTracks() != null) {
                            albumTracks = album.getTracks().getTrack(); }
                        albumTracksAdapter.addAlbumTrackList(albumTracks);
                        albumNameTextView.setText(String.valueOf(album.getName()));
                        artistNameTextView.setText(String.valueOf(album.getArtist()));
                        if (album.getWiki() != null) {
                            albumWikiTextView.setText(String.valueOf(album.getWiki().getContent())); }
                        if(album.getImage() != null) {
                            Glide.with(AlbumDetailsActivity.this)
                                    .load(album.getImage().get(3).getText()); }
                    }
                }
        );
    }

    private void setUpUI() {
        albumNameTextView = findViewById(R.id.albumNameTextView);
        artistNameTextView = findViewById(R.id.albumArtistNameTextView);
        albumImageView = findViewById(R.id.albumImageView);
        albumWikiTextView = findViewById(R.id.albumWikiTextView);
    }
}