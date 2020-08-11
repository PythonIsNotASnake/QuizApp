package de.bernd.quizapp;

public class UserModel {

    private String nickName;
    private String password;
    private int points;
    private int score;
    private boolean loggedIn;

    public UserModel() {
        this.nickName = "";
        this.password = "";
        this.points = 0;
        this.score = 0;
        this.loggedIn = false;
    }

    public UserModel(String nickName, String password) {
        this.nickName = nickName;
        this.password = password;
        this.points = 0;
        this.score = 0;
        this.loggedIn = false;
    }

    public UserModel(String nickName, String password, int score) {
        this.nickName = nickName;
        this.password = password;
        this.points = 0;
        this.score = score;
        this.loggedIn = false;
    }

    public UserModel(String nickName, int score) {
        this.nickName = nickName;
        this.password = "";
        this.points = 0;
        this.score = score;
        this.loggedIn = false;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "{\"nickName\" : \"" + this.nickName + "\", \"password\": \"" + this.password + "\", \"score\" : \"" + this.score + "\", \"points\" : \"" + this.points + "\"}";
    }
}
