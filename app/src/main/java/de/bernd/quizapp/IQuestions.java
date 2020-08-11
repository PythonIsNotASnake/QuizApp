package de.bernd.quizapp;

import com.google.gson.JsonElement;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IQuestions {
    @GET("/quiz/{amount}")
    public Call<JsonElement> getQuestions(@Path("amount") int amount);

    @GET("/")
    public Call<JsonElement> getHello();
}
