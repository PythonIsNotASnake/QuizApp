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
    private int score;
    private String nickName;
    private String password;
    private Retrofit retrofit;

    private Globals() {
        // Gson gson = new GsonBuilder().setLenient().create();
        // this.retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient okHttpClient = new OkHttpClient();
        this.retrofit = new Retrofit.Builder().baseUrl("http://192.168.178.29:1337/").addConverterFactory(GsonConverterFactory.create(gson)).build();
        this.score = 0;
        this.user = new UserModel();
    }

    public static Globals getInstance() {
        return instance;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
