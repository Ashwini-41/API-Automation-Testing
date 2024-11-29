package Api_Automation.API_Automation;


import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class SpotifyAPI {

  String userID = null;
  private final String authCode = "BQDZhm1F6zdnjTq13earedxgnt6NBqJ9KjwLz4USgXe2pmGJpGVMH0DuU0NyNatmTEfiLEZn1isV2Crv01g60oJHqGqEOh8zCLjwBuPce3fwCKzXDEgr33YPpPyJ2GiyhjjvPK1ktkcCrmOLckdeujklqoxRXSiz45ReqtXz96p7KeTwOUe6BrTZxxiJRhnOcZfbNqViWoEGDvaY6GCpSNN63blqonYit05dLccwz08CG2-UijSvdDqKyK0yXFu0_91vt6HjotUxRQOiv7dCtm3XIBUklY3pHoKZJwM6LFo5ul6qVcimyD9jM1Ya3zyNGwENVEuwPvJb-oFq3zDF5grSdIw";
  // Users

  // Get Current User's Profile
  @Test(priority = 1)
  public void getCurrentUserProfile() {
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/me");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();

      userID = res.path("id");
  }

  // Get User's Top Items
  @Test(dependsOnMethods = "getCurrentUserProfile")
  public void getUsersTopItems(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/me/top/artists");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Get User's Profile
  @Test(priority = 2)
  public void getUsersProfile(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/users/"+userID);
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Follow Playlist
  @Test
  public void followPlaylist(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .put("https://api.spotify.com/v1/playlists/37i9dQZF1DWTyiBJ6yEqeu/followers");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Unfollow Playlist
  @Test
  public void unfollowPlaylist(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .delete("https://api.spotify.com/v1/playlists/37i9dQZF1DWTyiBJ6yEqeu/followers");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Get Followed Artists
  @Test
  public void getFollowedArtists(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/me/following?type=artist");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Follow Artists or Users
  @Test
  public void followArtistOrUsers(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .put("https://api.spotify.com/v1/me/following?type=artist&ids=5UdFr0GeO7jKIaNIJgwB36");
      res.then().assertThat().statusCode(204);
      res.prettyPrint();
  }

  // Unfollow Artists or Users
  @Test
  public void unfollowArtistOrUsers(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .delete("https://api.spotify.com/v1/me/following?type=artist&ids=5UdFr0GeO7jKIaNIJgwB36");
      res.then().assertThat().statusCode(204);
      res.prettyPrint();
  }

  // Check If User Follows Artists or Users
  @Test
  public void checkIfUserFollowsArtistsOrUsers(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/me/following/contains?type=artist&ids=5UdFr0GeO7jKIaNIJgwB36,0TwG8C39WJIfFlcPrhxHST");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Check if Current User Follows Playlist
  @Test
  public void checkIfCurrentUserFollowsPlaylist(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/playlists/37i9dQZF1EJAxvG65sjVon/followers/contains?ids=3147og3ve4tr7jb5csegik357f3a");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Tracks

  // Get Track
  @Test
  public void getTrack(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/tracks/6AhYiJyXQTOjuytJ0khZBD");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Get Several Tracks
  @Test
  public void getSeveralTracks(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/tracks?ids=54woTHhitonXA8nWL7blMW,57JgnvdInhr9wO4tvjDq2K,3e1g159cSgose2jHQj9uzd");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Get User's Saved Tracks
  @Test
  public void getUsersSavedTracks(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/me/tracks");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Save Tracks for Current User
  @Test
  public void saveTracksForCurrentUser(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .put("https://api.spotify.com/v1/me/tracks?ids=3Y2QAPM1Vsw7yKKnVMsxhD,6Kynli1iHBqJRWUCohcV9h,2gNMXJDKRmKWuevBGjN8wo");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Remove User's Saved Tracks
  @Test
  public void removeUsersSavedTracks(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .delete("https://api.spotify.com/v1/me/tracks?ids=3Y2QAPM1Vsw7yKKnVMsxhD,6Kynli1iHBqJRWUCohcV9h,2gNMXJDKRmKWuevBGjN8wo");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Check User's Saved Tracks
  @Test
  public void checkUsersSavedTracks(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/me/tracks/contains?ids=3Y2QAPM1Vsw7yKKnVMsxhD,6Kynli1iHBqJRWUCohcV9h,2gNMXJDKRmKWuevBGjN8wo");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Get Several Tracks' Audio Features
  @Test
  public void getTracksAudioFeatures(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/audio-features/3Y2QAPM1Vsw7yKKnVMsxhD");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Get Track's Audio Features
  @Test
  public void getSeveralTracksAudioFeatures(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/audio-features?ids=3Y2QAPM1Vsw7yKKnVMsxhD,6Kynli1iHBqJRWUCohcV9h,2gNMXJDKRmKWuevBGjN8wo");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Get Track's Audio Analysis
  @Test
  public void getTracksAudioAnalysis(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/audio-analysis/3Y2QAPM1Vsw7yKKnVMsxhD");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Get Recommendations
  @Test
  public void getRecommendations(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/recommendations?seed_artists=4NHQUGzhtTLFvgF5SZesLK&seed_genres=classical,country&seed_tracks=0c6xIDDpzE81m2q797ordA");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Search

  // Search for item
  @Test
  public void searchForItem(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/search?q=Hard%2520Drive%2520Vol.%25202&type=album");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Markets

  // Get Available Markets
  @Test
  public void getAvailableMarkets(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/markets");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Genres

  // Get Available Genre Seeds
  @Test
  public void getAvailableGenreSeeds(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/recommendations/available-genre-seeds");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Categories

  // Get Several Browse Categories
  @Test
  public void getSeveralBrowseCategories(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/browse/categories");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Get Single Browse Category
  @Test
  public void getSingleBrowseCategory(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/browse/categories/dinner");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Albums

  // Get Album
  @Test
  public void getAlbum(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/albums/3bnBokwvwAquTvlExGG9Y9");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();

  }

  // Get Several Albums
  @Test
  public void getSeveralAlbums(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/albums?ids=3bnBokwvwAquTvlExGG9Y9,6adVQtQUqBzdWx49n03C4G,16PSZwABl4VFJvfDFOPOoB");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Get Album Tracks
  @Test
  public void getAlbumTracks(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/albums/16PSZwABl4VFJvfDFOPOoB/tracks");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Get User's Saved Albums
  @Test
  public void getUsersSavedAlbums(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/me/albums");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Save Albums for Current User
  @Test
  public void saveAlbumsforCurrentUser(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .put("https://api.spotify.com/v1/me/albums?ids=3bnBokwvwAquTvlExGG9Y9,6adVQtQUqBzdWx49n03C4G,1AHeeHqsxY6YxuMzHESiEv,");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Remove Users' Saved Albums
  @Test
  public void removeUsersSavedAlbums(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .delete("https://api.spotify.com/v1/me/albums?ids=3bnBokwvwAquTvlExGG9Y9,6adVQtQUqBzdWx49n03C4G,1AHeeHqsxY6YxuMzHESiEv,");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Check User's Saved Albums
  @Test
  public void checkUsersSavedAlbums(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/me/albums/contains?ids=382ObEPsp2rxGrnsizN5TX,1A2GTWGtFfWp7KSQTwWOyo,2noRn2Aes5aoNVsU6iWThc");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Get New Releases
  @Test
  public void getNewReleases(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/browse/new-releases");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Artists

  // Get Artist
  @Test
  public void getArtist(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/artists/2RS1R0tueoL8EJXTSBAt2F");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Get Several Artists
  @Test
  public void getSeveralArtists(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/artists?ids=2RS1R0tueoL8EJXTSBAt2F,5UdFr0GeO7jKIaNIJgwB36,0TwG8C39WJIfFlcPrhxHST");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Get Artist's Albums
  @Test
  public void getArtistsAlbums(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/artists/0TwG8C39WJIfFlcPrhxHST/albums");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Get Artist's Top Tracks
  @Test
  public void getArtistsTopTracks(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/artists/5UdFr0GeO7jKIaNIJgwB36/top-tracks");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Get Artist's Related Artists
  @Test
  public void getArtistsRelatedArtists(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/artists/0TwG8C39WJIfFlcPrhxHST/related-artists");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Episodes

  // Get Episode
  @Test
  public void getEpisode(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/episodes/6NxcvuurZShPMoxTY88LJr");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Get Several Episodes
  @Test
  public void getSeveralEpisodes(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/episodes?ids=6NxcvuurZShPMoxTY88LJr,3jjEv7LyRatOu6JBqvQUzr,19tHtvWKuYO9cHytaRVW4o");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Get User's Saved Episodes
  @Test
  public void getUsersSavedEpisodes(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/me/episodes");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Save Episodes for Current User
  @Test
  public void saveEpisodesforCurrentUser(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/me/episodes?ids=77o6BIVlYM3msb4MMIL1jH,0Q86acNRm6V9GYx55SXKwf");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Remove User's Saved Episodes
  @Test
  public void removeUsersSavedEpisodes(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .delete("https://api.spotify.com/v1/me/episodes?ids=77o6BIVlYM3msb4MMIL1jH,0Q86acNRm6V9GYx55SXKwf");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Check User's Saved Episodes
  @Test
  public void checkUsersSavedEpisodes(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/me/episodes/contains?ids=77o6BIVlYM3msb4MMIL1jH,0Q86acNRm6V9GYx55SXKwf");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Player

  // Get Playback State
  @Test(dependsOnMethods = "getAvailableDevices" )
  public void getPlaybackState(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/me/player");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Get Available Devices
  @Test
  public void getAvailableDevices(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/me/player/devices");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Transfer Playback
  @Test (dependsOnMethods = "getAvailableDevices")
  public void transferPlayback(){
      Response res = given()
              .auth().oauth2(authCode)
              .header("Content-Type", "application/json")
              .when()
              .body("{\n" +
                      "    \"device_ids\": [\n" +
                      "        \"00881688fc88e392f4f14ab1b3d50132f666ff71\"\n" +
                      "    ]\n" +
                      "}")
              .put("https://api.spotify.com/v1/me/player");
      res.then().assertThat().statusCode(204);
      res.prettyPrint();
  }

  // Get Currently Playing Track
  @Test(dependsOnMethods = "getPlaybackState")
  public void getCurrentlyPlayingTrack(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/me/player/currently-playing");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Start/Resume Playback
  @Test(dependsOnMethods = "getCurrentlyPlayingTrack")
  public void startOrResumePlayBack(){
      Response res = given()
              .auth().oauth2(authCode)
              .header("Content-Type", "application/json")
              .when()
              .body("{\n" +
                      "    \"context_uri\": \"spotify:album:5ht7ItJgpBH7W6vJ5BqpPr\",\n" +
                      "    \"offset\": {\n" +
                      "        \"position\": 5\n" +
                      "    },\n" +
                      "    \"position_ms\": 0\n" +
                      "}")
              .put("https://api.spotify.com/v1/me/player/play");
      res.then().assertThat().statusCode(204);
      res.prettyPrint();
  }

  // Pause Playback
  @Test(dependsOnMethods = "startOrResumePlayBack")
  public void pausePlayback(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .put("https://api.spotify.com/v1/me/player/pause");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Skip To Next
  @Test(dependsOnMethods = "getCurrentlyPlayingTrack")
  public void skipToNext(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .post("https://api.spotify.com/v1/me/player/next");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Skip To Previous
  @Test(dependsOnMethods = "getCurrentlyPlayingTrack")
  public void skipToPrevious(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .post("https://api.spotify.com/v1/me/player/previous");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Seek To Position
  @Test(dependsOnMethods = "getCurrentlyPlayingTrack")
  public void seekToPosition(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .put("https://api.spotify.com/v1/me/player/seek?position_ms=25000");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Set Repeat Mode
  @Test(dependsOnMethods = "getCurrentlyPlayingTrack")
  public void setRepeatMode(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .put("https://api.spotify.com/v1/me/player/repeat?state=context");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Set Playback Volume
  @Test(dependsOnMethods = "getCurrentlyPlayingTrack")
  public void setPlaybackVolume(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .put("https://api.spotify.com/v1/me/player/volume?volume_percent=50");
      res.then().assertThat().statusCode(204);
      res.prettyPrint();
  }

  // Toggle Playback Shuffle
  @Test(dependsOnMethods = "getCurrentlyPlayingTrack")
  public void togglePlaybackShuffle(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .put("https://api.spotify.com/v1/me/player/shuffle?state=true");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }



}
