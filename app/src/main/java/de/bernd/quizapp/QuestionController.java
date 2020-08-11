package de.bernd.quizapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionController {
    private static final QuestionController instance = new QuestionController();

    private QuestionController() {}

    public static QuestionController getInstance() {
        return instance;
    }

    public QuestionModel[] getQuestions(int amount) {
        Gson gson = new Gson();
        IQuestions questionsService = Globals.getInstance().getRetrofit().create(IQuestions.class);

        try {
            questionsService.getQuestions(amount).enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                    JsonElement element = response.body().getAsJsonObject().get("questions");
                    QuestionModel[] questions = new QuestionModel[element.getAsJsonArray().size()];
                    for(int i=0; i<element.getAsJsonArray().size(); i++) {
                        JsonElement jObject = element.getAsJsonArray().get(i);
                        QuestionModel question = new QuestionModel(jObject.getAsJsonObject().get("id").getAsInt(), jObject.getAsJsonObject().get("question").getAsString(), jObject.getAsJsonObject().get("rightAnswer").getAsString(), jObject.getAsJsonObject().get("falseAnswer1").getAsString(), jObject.getAsJsonObject().get("falseAnswer2").getAsString());
                        questions[i] = question;
                    }

                    Globals.getInstance().setQuestions(questions);

                    // Globals.getInstance().getUser().setScore(element.getAsInt());
                    for(int j = 0; j< Globals.getInstance().getQuestions().length; j++){
                        System.out.println(Globals.getInstance().getQuestions()[j]);
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
        return Globals.getInstance().getQuestions();
    }

}
