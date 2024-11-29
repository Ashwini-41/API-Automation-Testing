package com.api_automationTesting;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class Spotify_API_Automation {

  String userID = "31bahinygien2xf2xpj3yvaspqaq";
  private final String authCode = "BQD2RhUoY6oHhFEtzcoYKmnx_aqm0qPYu1PxQw2woWeWV4VvcdWyz_Q41_D0cUbw-GdFfczACdjHsmWHbKJ0CRaQ9NT6lrcitF-pkOcNOEq9sJb1FZXorlRusb9vgpE9Vj62zrRpDaN9ODfppOHF3HJIYFgLoudFsVWrbabnVWQMkNGm1s03g8_VUq88S-YBGYQoHyJ36xOgdGjXoQ0vQkgeqeFQ1C3ct5qm102o2maK88w7Sw9tEQ0aQJmjbbPjafR9Sob8qSOWZsxe7jZqgc_dqv9UZJ7DiUtNcqxcQcSXTHrhQdnG13ruDdxF_-zHbSeuRZVGsnWBdthUXaPKvnC4K9T3";
  
  
  // Get Current User's Profile
  @Test(priority = 1)
  public void GetCurrentUserProfile() {
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/me");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();

      userID = res.path("id");
  }

  // Get User's Top Items
  @Test(dependsOnMethods = "GetCurrentUserProfile")
  public void GetUsersTopItems(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/me/top/artists");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Get User's Profile
  @Test(priority = 2)
  public void GetUsersProfile(){
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
              .put("https://api.spotify.com/v1/playlists/1SwwVZjdJrAXlhoDqQa9TA/followers");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Unfollow Playlist
  @Test
  public void UnfollowPlaylist(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .delete("https://api.spotify.com/v1/playlists/3cEYpjA9oz9GiPac4AsH4n/followers");
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
  public void FollowArtistOrUsers(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .put("https://api.spotify.com/v1/me/following?type=artist&ids=5UdFr0GeO7jKIaNIJgwB36");
      res.then().assertThat().statusCode(204);
      res.prettyPrint();
  }

  // Unfollow Artists or Users
  @Test
  public void UnfollowArtistOrUsers(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .delete("https://api.spotify.com/v1/me/following?type=artist&ids=5UdFr0GeO7jKIaNIJgwB36");
      res.then().assertThat().statusCode(204);
      res.prettyPrint();
  }

  // Check If User Follows Artists or Users
  @Test
  public void CheckIfUserFollowsArtistsOrUsers(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/me/following/contains?type=artist&ids=2CIMQHirSU0MQqyYHq0eOx,57dN52uHvrHOxijzpIgu3E,1vCWHaC5f2uS3yhpwWbIA6");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Check if Current User Follows Playlist
  @Test
  public void CheckIfCurrentUserFollowsPlaylist(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/playlists/1SwwVZjdJrAXlhoDqQa9TA/followers/contains");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

 
  // **************** Search ****************

  // Search for item
  @Test
  public void SearchForItem(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/search?q=remaster%20track:Doxy%20artist:Miles%20Davis&type=track");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // ************ Markets **********

  // Get Available Markets
  @Test
  public void GetAvailableMarkets(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/markets");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // ***************** Genres ***************

  // Get Available Genre Seeds
  @Test
  public void GetAvailableGenreSeeds(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/recommendations/available-genre-seeds");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // ********** Categories *************

  // Get Several Browse Categories
  @Test
  public void GetSeveralBrowseCategories(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/browse/categories");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Get Single Browse Category
  @Test
  public void GetSingleBrowseCategory(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/browse/categories/dinner");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // ************** Tracks ************

  // Get Track
  @Test
  public void GetTrack(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/tracks/11dFghVXANMlKmJXsNCbNl");
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
  public void SaveTracksForCurrentUser(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .put("https://api.spotify.com/v1/me/tracks?ids=3Y2QAPM1Vsw7yKKnVMsxhD,6Kynli1iHBqJRWUCohcV9h,2gNMXJDKRmKWuevBGjN8wo");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Remove User's Saved Tracks
  @Test
  public void RemoveUsersSavedTracks(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .delete("https://api.spotify.com/v1/me/tracks?ids=3Y2QAPM1Vsw7yKKnVMsxhD,6Kynli1iHBqJRWUCohcV9h,2gNMXJDKRmKWuevBGjN8wo");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Check User's Saved Tracks
  @Test
  public void CheckUsersSavedTracks(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/me/tracks/contains?ids=3Y2QAPM1Vsw7yKKnVMsxhD,6Kynli1iHBqJRWUCohcV9h,2gNMXJDKRmKWuevBGjN8wo");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Get Several Tracks' Audio Features
  @Test
  public void GetTracksAudioFeatures(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/audio-features/3Y2QAPM1Vsw7yKKnVMsxhD");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Get Track's Audio Features
  @Test
  public void GetSeveralTracksAudioFeatures(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/audio-features?ids=3Y2QAPM1Vsw7yKKnVMsxhD,6Kynli1iHBqJRWUCohcV9h,2gNMXJDKRmKWuevBGjN8wo");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Get Track's Audio Analysis
  @Test
  public void GetTracksAudioAnalysis(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/audio-analysis/11dFghVXANMlKmJXsNCbNl");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Get Recommendations
  @Test
  public void GetRecommendations(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/recommendations?seed_artists=4NHQUGzhtTLFvgF5SZesLK&seed_genres=classical,country&seed_tracks=0c6xIDDpzE81m2q797ordA");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  
  // *************** Albums ***************

  // Get Album
  @Test
  public void GetAlbum(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/albums/4aawyAB9vmqN3uQ7FjRGTy");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();

  }

  // Get Several Albums
  @Test
  public void GetSeveralAlbums(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/albums?ids=3bnBokwvwAquTvlExGG9Y9,6adVQtQUqBzdWx49n03C4G,16PSZwABl4VFJvfDFOPOoB");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Get Album Tracks
  @Test
  public void GetAlbumTracks(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/albums/4aawyAB9vmqN3uQ7FjRGTy/tracks");
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
              .put("https://api.spotify.com/v1/me/albums?ids=382ObEPsp2rxGrnsizN5TX,1A2GTWGtFfWp7KSQTwWOyo,2noRn2Aes5aoNVsU6iWThc");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Remove Users' Saved Albums
  @Test
  public void RemoveUsersSavedAlbums(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .delete("https://api.spotify.com/v1/me/albums?ids=382ObEPsp2rxGrnsizN5TX");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Check User's Saved Albums
  @Test
  public void CheckUsersSavedAlbums(){
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

  // ********** Artists *************

  // Get Artist
  @Test
  public void GetArtist(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/artists/2RS1R0tueoL8EJXTSBAt2F");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Get Several Artists
  @Test
  public void GetSeveralArtists(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/artists?ids=2CIMQHirSU0MQqyYHq0eOx,57dN52uHvrHOxijzpIgu3E,1vCWHaC5f2uS3yhpwWbIA6");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Get Artist's Albums
  @Test
  public void GetArtistsAlbums(){
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

  // ******************* Episodes *********************

  // Get Episode
  @Test
  public void GetEpisode(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/episodes/6NxcvuurZShPMoxTY88LJr");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Get Several Episodes
  @Test
  public void GetSeveralEpisodes(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/episodes?ids=6NxcvuurZShPMoxTY88LJr,3jjEv7LyRatOu6JBqvQUzr,19tHtvWKuYO9cHytaRVW4o");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Get User's Saved Episodes
  @Test
  public void GetUsersSavedEpisodes(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/me/episodes");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Save Episodes for Current User
  @Test
  public void SaveEpisodesforCurrentUser(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/me/episodes?ids=77o6BIVlYM3msb4MMIL1jH,0Q86acNRm6V9GYx55SXKwf");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Remove User's Saved Episodes
  @Test
  public void RemoveUsersSavedEpisodes(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .delete("https://api.spotify.com/v1/me/episodes?ids=77o6BIVlYM3msb4MMIL1jH,0Q86acNRm6V9GYx55SXKwf");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  // Check User's Saved Episodes
  @Test
  public void CheckUsersSavedEpisodes(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/me/episodes/contains?ids=77o6BIVlYM3msb4MMIL1jH,0Q86acNRm6V9GYx55SXKwf");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();
  }

  

 //*********** Playlist ********
  
  //Get playlist
  @Test
  public void GetPlaylist(){
      Response res = given()
              .auth().oauth2(authCode)
              .when()
              .get("https://api.spotify.com/v1/playlists/1SwwVZjdJrAXlhoDqQa9TA");
      res.then().assertThat().statusCode(200);
      res.prettyPrint();

  }
  
  //Get playlist items
  @Test
  public void GetPlaylistItem() {
	  Response res = given()
			  .auth().oauth2(authCode)
			  .when()
			  .get("https://api.spotify.com/v1/playlists/1SwwVZjdJrAXlhoDqQa9TA/tracks");
	  res.then().assertThat().statusCode(200);
	  res.prettyPrint();
  }
 
  //Add items to playlist
  @Test
  public void AddItemsToPlaylist() {
	  Response res = given()
			  .auth().oauth2(authCode)
			  .when()
			  .post("https://api.spotify.com/v1/playlists/1SwwVZjdJrAXlhoDqQa9TA/tracks?uris=spotify:track:6fScgF2Y63ScU73nYju0ny");
	  res.then().assertThat().statusCode(201);
	  res.prettyPrint();
  }
  
  // change playlist details
  @Test
  public void ChangePlaylistDetail() {
	  String resbody = """
	  		{
	  		"name": "Fresh Songs Playlist",
	  		"description": "Updated playlist description",
	  		"public": false
	  		}
	  		""";
	  Response res = given()
			  .auth().oauth2(authCode)
			  .when()
			  .body(resbody)
			  .put("https://api.spotify.com/v1/playlists/1SwwVZjdJrAXlhoDqQa9TA");
	  res.then().assertThat().statusCode(200);
	  res.prettyPrint();
  }
  
  //get current user playlist
  @Test
  public void GetCurrentUserPlaylists() {
	  Response res = given()
			  .auth().oauth2(authCode)
			  .when()
			  .get("https://api.spotify.com/v1/me/playlists");
	  res.then().assertThat().statusCode(200);
	  res.prettyPrint();
  }
  
  //Get Featured Playlists
  @Test
  public void GetFeaturedPlaylists() {
	  Response res = given()
			  .auth().oauth2(authCode)
			  .when()
			  .get("https://api.spotify.com/v1/browse/featured-playlists");
	  res.then().assertThat().statusCode(200);
	  res.prettyPrint();
  }
  
  //Create Playlist
  @Test
  public void CreatePlaylist() {
	  
	  String resbody = """
	  		{
	  		"name": "Birthday Playlist",
	  		"description": "Bday playlist description",
	  		"public": false
	  		}
	  		""";
	  Response res = given()
			  .auth().oauth2(authCode)
			  .when()
			  .body(resbody)
			  .post("https://api.spotify.com/v1/users/31bahinygien2xf2xpj3yvaspqaq/playlists");
	  res.then().assertThat().statusCode(201);
	  res.prettyPrint();
  }
  
  //Get Playlist Cover Image
  @Test
  public void GetPlaylistCoverImage() {
	  Response res = given()
			  .auth().oauth2(authCode)
			  .when()
			  .get("https://api.spotify.com/v1/playlists/1SwwVZjdJrAXlhoDqQa9TA/images");
	  res.then().assertThat().statusCode(200);
	  res.prettyPrint();
  }
  

}
