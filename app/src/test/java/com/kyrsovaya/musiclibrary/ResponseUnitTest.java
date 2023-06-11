package com.kyrsovaya.musiclibrary;

import static com.kyrsovaya.musiclibrary.utils.Constants.ALBUM_NAME;
import static com.kyrsovaya.musiclibrary.utils.Constants.API_KEY;
import static com.kyrsovaya.musiclibrary.utils.Constants.ARTIST_NAME;
import static com.kyrsovaya.musiclibrary.utils.Constants.BASE_URL;
import static com.kyrsovaya.musiclibrary.utils.Constants.COUNTRY;
import static com.kyrsovaya.musiclibrary.utils.Constants.JSON_FORMAT;
import static com.kyrsovaya.musiclibrary.utils.Constants.TRACK_NAME;

import org.junit.Test;

import static org.junit.Assert.*;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;



public class ResponseUnitTest {

    @Test
    public void getArtistInfoTest() {
        ValidatableResponse artist = given()
                .when()
                .contentType(ContentType.JSON)
                .get(BASE_URL+"?method=artist.getinfo&artist="+ARTIST_NAME+"&api_key="+API_KEY+"&format="+JSON_FORMAT)
                .then().assertThat().statusCode(200);
    }
    @Test
    public void getArtistSearchTest(){
        ValidatableResponse artist = given()
                .when()
                .contentType(ContentType.JSON)
                .get(BASE_URL+"?method=artist.search&artist="+ARTIST_NAME+"&api_key="+API_KEY+"&format="+JSON_FORMAT)
                .then().assertThat().statusCode(200);
    }
    @Test
    public void getAlbumInfoTest(){
        ValidatableResponse album = given()
                .when()
                .contentType(ContentType.JSON)
                .get(BASE_URL+"?method=album.getInfo&artist="+ARTIST_NAME+"&album="+ALBUM_NAME+"&api_key="+API_KEY+"&format="+JSON_FORMAT)
                .then().assertThat().statusCode(200);
    }
    @Test
    public void getAlbumSearchTest(){
        ValidatableResponse album = given()
                .when()
                .contentType(ContentType.JSON)
                .get(BASE_URL+"?method=album.search&album="+ALBUM_NAME+"&api_key="+API_KEY+"&format="+JSON_FORMAT)
                .then().assertThat().statusCode(200);
    }
    @Test
    public void getTopAlbumsTest(){
        ValidatableResponse album = given()
                .when()
                .contentType(ContentType.JSON)
                .get(BASE_URL+"?method=artist.gettopalbums&artist="+ARTIST_NAME+"&api_key="+API_KEY+"&format="+JSON_FORMAT)
                .then().assertThat().statusCode(200);
    }
    @Test
    public void getTrackSearchTest(){
        ValidatableResponse track = given()
                .when()
                .contentType(ContentType.JSON)
                .get(BASE_URL+"?method=track.search&track="+TRACK_NAME+"&api_key="+API_KEY+"&format="+JSON_FORMAT)
                .then().assertThat().statusCode(200);
    }
    @Test
    public void getChartTopArtistsTest(){
        ValidatableResponse artist = given()
                .when()
                .contentType(ContentType.JSON)
                .get(BASE_URL+"?method=chart.gettopartists"+"&api_key="+API_KEY+"&format="+JSON_FORMAT)
                .then().assertThat().statusCode(200);
    }
    @Test
    public void getGeoTopArtistsTest(){
        ValidatableResponse artist = given()
                .when()
                .contentType(ContentType.JSON)
                .get(BASE_URL+"?method=geo.gettopartists&country="+COUNTRY+"&api_key="+API_KEY+"&format="+JSON_FORMAT)
                .then().assertThat().statusCode(200);
    }
}