package com.furkansahan.controller;


import com.furkansahan.model.QuestionForm;
import com.furkansahan.model.Result;
import com.furkansahan.service.QuestionService;
import com.furkansahan.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    Result result;

    @Autowired
    QuestionService questionService;

    @Autowired
    ResultService resultService;


    Boolean submitted = false;

    @ModelAttribute("result")
    public Result getResult(){
        return result;
    }

    @ModelAttribute("result")
    public Result getResultSatisfaction(){
        return result;
    }

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @PostMapping("/quiz")
    public String quiz(@RequestParam String username, Model m, RedirectAttributes ra){
        if(username.equals("")) {
            ra.addFlashAttribute("warning","You Must Enter Your Name");
            return "redirect:/";
        }

        submitted = false;
        result.setUsername(username);

        QuestionForm qForm = questionService.getQuestions();

        m.addAttribute("qForm", qForm);


        return "quiz";
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute QuestionForm qForm,String message, Model m) {

        if(!submitted) {
            result.setTotalCorrect(resultService.getResult(qForm));
            result.setSatisfaction(resultService.getResultSatisfaction(qForm));
            result.setMessage(resultService.getMessage(qForm));

            result.setMessage(message);

            resultService.saveScore(result);

            submitted = true;
        }
        return "result";
    }

    @GetMapping("/score")
    public String score(Model m) {
        List<Result> sList = resultService.getTopScore();

        m.addAttribute("sList", sList);

        return "scoreboard";
    }


}