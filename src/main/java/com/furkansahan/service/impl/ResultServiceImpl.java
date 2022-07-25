package com.furkansahan.service.impl;

import com.furkansahan.model.Question;
import com.furkansahan.model.QuestionForm;
import com.furkansahan.model.Result;
import com.furkansahan.repository.ResultRepo;
import com.furkansahan.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    Result result;

    @Autowired
    ResultRepo rRepo;

    @Override
    public double getResult(QuestionForm qForm) {
        int total =0;
        double average=0;
        String result1 = null;

        for(Question q : qForm.getQuestions()) {
            if(q.getAns1() == q.getChose()){
                total=total+1;
            } else if (q.getAns2() == q.getChose() ) {
                total=total+2;
            }else if (q.getAns3() == q.getChose() ) {
                total=total+3;
            }else if (q.getAns4() == q.getChose() ) {
                total=total+4;
            }else if (q.getAns5() == q.getChose() ) {
                total=total+5;
            }else{
                total=total+0;
            }
        }
        average = total/5;

        return average;
    }

//    @Override
//    public Result saveResult(Result result) {
//        return  rRepo.save(result);
//    }

    @Override
    public void saveScore(Result result) {
        Result saveResult = new Result();
        saveResult.setUsername(result.getUsername());
        saveResult.setTotalCorrect(result.getTotalCorrect());
        saveResult.setSatisfaction(result.getSatisfaction());
        saveResult.setMessage(result.getMessage());
        rRepo.save(saveResult);
    }

    @Override
    public List<Result> getTopScore() {

        // En yüksek Puan En üstte
        List<Result> sList = rRepo.findAll(Sort.by(Sort.Direction.DESC, "totalCorrect"));

        // En düşük Puan En üstte
        // Sort.Direction.ASC

        return sList;
    }

    @Override
    public String getResultSatisfaction(QuestionForm qForm) {
        int total =0;
        double average=0;
        String result1 = null;

        for(Question q : qForm.getQuestions()) {
            if(q.getAns1() == q.getChose()){
                total=total+1;
            }else if (q.getAns2() == q.getChose() ) {
                total=total+2;
            }else if (q.getAns3() == q.getChose() ) {
                total=total+3;
            }else if (q.getAns4() == q.getChose() ) {
                total=total+4;
            }else if (q.getAns5() == q.getChose() ) {
                total=total+5;
            }else{
                total=total+0;
            }
        }
        average = total/5;


        if(average<=1){
            result1 = "Hic memnun degil" ;
        } else if (average==2) {
            result1 = "Memnun degil";
        }else if (average==3) {
            result1 = "Kısmen memnun";
        }else if (average==4) {
            result1 = "Memnun";
        }else if (average==5){
            result1 = "Cok memnun";
        }
        return result1;
    }

    @Override
    public String getMessage(QuestionForm questionForm) {
        return null;
    }


}
