package de.bernd.quizapp;

public class QuestionModel {

    private int id;
    private String question;
    private String rightAnswer;
    private String falseAnswer1;
    private String falseAnswer2;

    public QuestionModel() {
        this.id = 0;
        this.question = "";
        this.rightAnswer = "";
        this.falseAnswer1 = "";
        this.falseAnswer2 = "";
    }

    public QuestionModel(int id, String question, String rightAnswer, String falseAnswer1, String falseAnswer2) {
        this.id = id;
        this.question = question;
        this.rightAnswer = rightAnswer;
        this.falseAnswer1 = falseAnswer1;
        this.falseAnswer2 = falseAnswer2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getFalseAnswer1() {
        return falseAnswer1;
    }

    public void setFalseAnswer1(String falseAnswer1) {
        this.falseAnswer1 = falseAnswer1;
    }

    public String getFalseAnswer2() {
        return falseAnswer2;
    }

    public void setFalseAnswer2(String falseAnswer2) {
        this.falseAnswer2 = falseAnswer2;
    }

    @Override
    public String toString() {
        return "{\"question\" : \"" + this.question + "\", \"rightAnswer\": \"" + this.rightAnswer + "\", \"falseAnswer1\" : \"" + this.falseAnswer1 + "\", \"falseAnswer2\" : \"" + this.falseAnswer2 + "\"}";
    }
}
