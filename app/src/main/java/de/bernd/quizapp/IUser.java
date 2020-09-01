package de.bernd.quizapp;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface IUser {

    @POST("/auth/register")
    public Call<JsonElement> postRegister(@Body JsonObject user);

    @Headers("Content-Type: application/json")
    @PUT("/auth/login")
    public Call<JsonElement> putLogin(@Body JsonObject user);

    @PUT("/auth/score")
    public Call<JsonElement> getScore(@Body JsonObject user);

    @PUT("/auth/score/new")
    public Call<JsonElement> updateScore(@Body JsonObject user);

    @PUT("/auth/leaderboard")
    public Call<JsonElement> getLeaderBoard(@Body JsonObject user);

    @GET("/auth/clock")
    public Call<JsonElement> getClock();

}
