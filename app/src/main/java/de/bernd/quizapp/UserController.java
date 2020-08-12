package de.bernd.quizapp;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserController {
    private static final UserController instance = new UserController();

    private UserController() {
    }

    public static UserController getInstance() {
        return instance;
    }

    public void registerUser(final String nickName, final String password, final RegistrationActivity2 activity, final View v) {
        UserModel user = new UserModel(nickName, password);
        Gson gson = new Gson();
        JsonElement userJson = gson.fromJson(user.toString(), JsonElement.class);
        JsonObject jsonObject = userJson.getAsJsonObject();
        IUser userService = Globals.getInstance().getRetrofit().create(IUser.class);

        try {
            userService.postRegister(jsonObject).enqueue(new Callback<JsonElement>() {

                @Override
                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                    JsonElement element = response.body().getAsJsonObject().get("registered");
                    boolean isLoggedIn = element.getAsBoolean();
                    if (isLoggedIn) {
                        Globals.getInstance().getUser().setNickName(nickName);
                        Globals.getInstance().getUser().setPassword(password);
                        Intent goToMenu = new Intent(activity, MenuActivity.class);
                        activity.startActivity(goToMenu);
                    } else {
                        activity.getNickNameInput().setError("Benutzername existiert bereits");
                        activity.getPasswordInput().setError("Benutzername existiert bereits");
                    }
                    System.out.println("User registered successfully");
                    activity.getLoadingProgressBar().setVisibility(v.GONE);
                }

                @Override
                public void onFailure(Call<JsonElement> call, Throwable t) {
                    System.out.println(t);
                    activity.getNickNameInput().setError("Verbindungsfehler");
                    activity.getPasswordInput().setError("Verbindungsfehler");
                    activity.getLoadingProgressBar().setVisibility(v.GONE);
                }
            });
        } catch (Exception e) {
            System.out.println(e);
            activity.getLoadingProgressBar().setVisibility(v.GONE);
        }
    }

    public void loginUser(final String nickName, final String password, final LoginActivity2 activity, final View v) {
        UserModel user = new UserModel(nickName, password);
        Gson gson = new Gson();
        JsonElement userJson = gson.fromJson(user.toString(), JsonElement.class);
        JsonObject jsonObject = userJson.getAsJsonObject();
        IUser userService = Globals.getInstance().getRetrofit().create(IUser.class);

        try {
            userService.putLogin(jsonObject).enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                    JsonElement element = response.body().getAsJsonObject().get("loggedIn");
                    boolean isLoggedIn = element.getAsBoolean();
                    if (isLoggedIn) {
                        Globals.getInstance().getUser().setNickName(nickName);
                        Globals.getInstance().getUser().setPassword(password);
                        Intent goToMenu = new Intent(activity, MenuActivity.class);
                        activity.startActivity(goToMenu);
                    } else {
                        activity.getNickNameInput().setError("Ungültiger Nutzername");
                        activity.getPasswordInput().setError("Ungültiges Passwort");
                    }
                    System.out.println("User logged in successfully");
                    activity.getLoadingProgressBar().setVisibility(v.GONE);
                }

                @Override
                public void onFailure(Call<JsonElement> call, Throwable t) {
                    activity.getNickNameInput().setError("Verbindungsfehler");
                    activity.getPasswordInput().setError("Verbindungsfehler");
                    System.out.println(t);
                    activity.getLoadingProgressBar().setVisibility(v.GONE);
                }
            });
        } catch (Exception e) {
            System.out.println(e);
            activity.getLoadingProgressBar().setVisibility(v.GONE);
        }
    }

    public int getScore(String nickName, String password, final TextView view) {
        UserModel user = new UserModel(nickName, password);
        Gson gson = new Gson();
        JsonElement userJson = gson.fromJson(user.toString(), JsonElement.class);
        JsonObject jsonObject = userJson.getAsJsonObject();
        IUser userService = Globals.getInstance().getRetrofit().create(IUser.class);

        try {
            userService.getScore(jsonObject).enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                    JsonElement element = response.body().getAsJsonObject().get("score");
                    Globals.getInstance().getUser().setScore(element.getAsInt());
                    view.setText("Willkommen " + Globals.getInstance().getUser().getNickName() + ": " + Globals.getInstance().getUser().getScore() + " Punkte");
                    System.out.println("User score of " + Globals.getInstance().getUser().getScore());
                }

                @Override
                public void onFailure(Call<JsonElement> call, Throwable t) {
                    view.setText("Willkommen");
                    System.out.println(t);
                }
            });
        } catch (Exception e) {
            System.out.println(e);
        }
        return Globals.getInstance().getUser().getScore();
    }

    public int updateScore(String nickName, String password, int newPoints) {
        UserModel user = new UserModel(nickName, password);
        user.setPoints(newPoints);
        Gson gson = new Gson();
        JsonElement userJson = gson.fromJson(user.toString(), JsonElement.class);
        JsonObject jsonObject = userJson.getAsJsonObject();
        IUser userService = Globals.getInstance().getRetrofit().create(IUser.class);

        try {
            userService.updateScore(jsonObject).enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                    JsonElement element = response.body().getAsJsonObject().get("score");
                    Globals.getInstance().getUser().setScore(element.getAsInt());
                    System.out.println("User score of " + Globals.getInstance().getUser().getScore());
                }

                @Override
                public void onFailure(Call<JsonElement> call, Throwable t) {
                    System.out.println(t);
                }
            });
        } catch (Exception e) {
            System.out.println(e);
        }
        return Globals.getInstance().getUser().getScore();
    }

    public UserModel[] getLeaderBoard(String nickName, String password, final LeaderboardActivity activity) {
        UserModel user = new UserModel(nickName, password);
        Gson gson = new Gson();
        JsonElement userJson = gson.fromJson(user.toString(), JsonElement.class);
        JsonObject jsonObject = userJson.getAsJsonObject();
        IUser userService = Globals.getInstance().getRetrofit().create(IUser.class);

        try {
            userService.getLeaderBoard(jsonObject).enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                    JsonElement element = response.body().getAsJsonObject().get("leaderBoard");
                    UserModel[] leaderBoard = new UserModel[element.getAsJsonArray().size()];
                    for (int i = 0; i < element.getAsJsonArray().size(); i++) {
                        JsonElement jObject = element.getAsJsonArray().get(i);
                        UserModel leaderUser = new UserModel(jObject.getAsJsonObject().get("nickName").getAsString(), jObject.getAsJsonObject().get("score").getAsInt());
                        leaderBoard[i] = leaderUser;
                    }

                    Globals.getInstance().setLeaderBoard(leaderBoard);

                    for (int j = 0; j < Globals.getInstance().getLeaderBoard().length; j++) {
                        activity.getPlaces().get(j).setText(Globals.getInstance().getLeaderBoard()[j].getNickName() + " " + Globals.getInstance().getLeaderBoard()[j].getScore() + " Punkte");
                    }

                    for (int x = Globals.getInstance().getLeaderBoard().length; x < activity.getPlaces().size(); x++) {
                        activity.getPlaces().get(x).setVisibility(View.INVISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<JsonElement> call, Throwable t) {
                    System.out.println(t);
                }
            });
        } catch (Exception e) {
            System.out.println(e);
        }
        return Globals.getInstance().getLeaderBoard();
    }
}

