package de.bernd.quizapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Globals {
    private static final Globals instance = new Globals();

    private UserModel user;
    private QuestionModel[] questions;
    private UserModel[] leaderBoard;
    private Retrofit retrofit;

    private Globals() {
       Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        this.retrofit = new Retrofit.Builder().baseUrl("http://192.168.178.29:1337/").addConverterFactory(GsonConverterFactory.create(gson)).build();
        this.user = new UserModel();
    }

    public static Globals getInstance() {
        return instance;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public QuestionModel[] getQuestions() {
        return questions;
    }

    public void setQuestions(QuestionModel[] questions) {
        this.questions = questions;
    }

    public UserModel[] getLeaderBoard() {
        return leaderBoard;
    }

    public void setLeaderBoard(UserModel[] leaderBoard) {
        this.leaderBoard = leaderBoard;
    }

}
