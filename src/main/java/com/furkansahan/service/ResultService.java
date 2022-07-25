package com.furkansahan.service;

import com.furkansahan.model.QuestionForm;
import com.furkansahan.model.Result;

import java.util.List;

public interface ResultService {


    //  Sonuçları Getirme
    double getResult(QuestionForm qForm);

//    Result saveResult(Result result);

    void saveScore(Result result);

    List<Result> getTopScore();

    String getResultSatisfaction(QuestionForm qForm);

    String getMessage(QuestionForm questionForm);




}
