package de.bernd.quizapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserController {
    private static final UserController instance = new UserController();

    static final String BASE_URL = "http://192.168.178.29:1337";
    // private Retrofit retrofit;

    private UserController() {
        //OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient okHttpClient = new OkHttpClient();
        // this.retrofit = new Retrofit.Builder().baseUrl("http://192.168.178.29:1337/").addConverterFactory(GsonConverterFactory.create(gson)).build();
        //addConverterFactory(ScalarsConverterFactory.create())
        // GsonConverterFactory.create(gson)
    }

    public static UserController getInstance() {
        return instance;
    }

    public boolean registerUser(String nickName, String password) {
        boolean loggedIn = false;
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
                    Globals.getInstance().getUser().setLoggedIn(element.getAsBoolean());
                    Globals.getInstance().setOnCall(false);
                }

                @Override
                public void onFailure(Call<JsonElement> call, Throwable t) {
                    System.out.println(t);
                    Globals.getInstance().setOnCall(false);
                }
            });
            loggedIn = Globals.getInstance().getUser().isLoggedIn();
            return Globals.getInstance().getUser().isLoggedIn();
        } catch (Exception e) {
            Globals.getInstance().setOnCall(false);
            System.out.println(e);
            return false;
        }
    }

    public boolean loginUser(final String nickName, final String password) {
        boolean loggedIn = false;
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
                    Globals.getInstance().getUser().setLoggedIn(element.getAsBoolean());
                    Globals.getInstance().getUser().setNickName(nickName);
                    Globals.getInstance().getUser().setPassword(password);
                    System.out.println("User logged in successfully");
                    Globals.getInstance().setOnCall(false);
                }

                @Override
                public void onFailure(Call<JsonElement> call, Throwable t) {
                    Globals.getInstance().getUser().setLoggedIn(false);
                    System.out.println(t);
                    Globals.getInstance().setOnCall(false);
                }
            });
            loggedIn = Globals.getInstance().getUser().isLoggedIn();
            return loggedIn;
        } catch (Exception e) {
            Globals.getInstance().getUser().setLoggedIn(false);
            Globals.getInstance().setOnCall(false);
            System.out.println(e);
            return false;
        }
        //return Globals.getInstance().getUser().isLoggedIn();
    }

    public int getScore(String nickName, String password) {
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

    public UserModel[] getLeaderBoard(String nickName, String password) {
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
                    for(int i=0; i<element.getAsJsonArray().size(); i++) {
                        JsonElement jObject = element.getAsJsonArray().get(i);
                        UserModel leaderUser = new UserModel(jObject.getAsJsonObject().get("nickName").getAsString(), jObject.getAsJsonObject().get("score").getAsInt());
                        leaderBoard[i] = leaderUser;
                    }

                    Globals.getInstance().setLeaderBoard(leaderBoard);

                    // Globals.getInstance().getUser().setScore(element.getAsInt());
                    for(int j = 0; j< Globals.getInstance().getLeaderBoard().length; j++){
                        System.out.println(Globals.getInstance().getLeaderBoard()[j]);
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

    public void start() {

        UserModel user = new UserModel("Bernd", "abc");
        Gson gson1 = new Gson();
        JsonElement jsonElement = gson1.fromJson(user.toString(), JsonElement.class);
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        IUser userService = Globals.getInstance().getRetrofit().create(IUser.class);
        // Call<ResponseBody> call = userService.putLogin(user);
        IQuestions questionService = Globals.getInstance().getRetrofit().create(IQuestions.class);

        //Call call = userService.putLogin(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString()));
        // Call call = userService.putLogin(jsonObject);
        try {
            final Call<JsonElement> listCall = questionService.getHello();
            //final Call<List<QuestionModel>> questionCall = questionService.getQuestions(3);
            //final List<QuestionModel> list = questionCall.execute().body();

            userService.putLogin(jsonObject).enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                    System.out.println(response.body());
                }

                @Override
                public void onFailure(Call<JsonElement> call, Throwable t) {
                    System.out.println("Mega FAIIIIL!!!!! Login");
                }
            });

            userService.getLeaderBoard(jsonObject).enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                    System.out.println(response.body());
                }

                @Override
                public void onFailure(Call<JsonElement> call, Throwable t) {
                    System.out.println("Mega FAIIIIL!!!!! LeaderBoard");
                }
            });

            questionService.getQuestions(2).enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                    System.out.println(response.body());
                    JsonElement jo = response.body().getAsJsonObject().get("questions");
                    JsonElement ja = jo.getAsJsonArray().get(0);
                    JsonElement question = ja.getAsJsonObject().get("question");
                    System.out.println(jo);
                    System.out.println(ja);
                    System.out.println(question);
                }

                @Override
                public void onFailure(Call<JsonElement> call, Throwable t) {
                    System.out.println("Mega FAIIIIL!!!!! Questions");
                }
            });
            //listCall.enqueue(this);
            //System.out.println("Mein Call!!!!!!" + list);
        } catch (Exception e) {
            System.out.println("Schwerwiegender Fehler!!!");
            System.out.println(e);
        }


    }
}

