package com.tj330.questionservice.controller;

import com.tj330.questionservice.model.Question;
import com.tj330.questionservice.model.QuestionWrapper;
import com.tj330.questionservice.model.Response;
import com.tj330.questionservice.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/questions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return ResponseEntity.status(HttpStatus.OK).body(questionService.getAllQuestions());
    }

    @GetMapping("/questions/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        return ResponseEntity.status(HttpStatus.OK).body(questionService.getQuestionsByCategory(category));
    }

    @PostMapping("/question")
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        return ResponseEntity.status(HttpStatus.CREATED).body(questionService.saveQuestion(question));
    }

    @PutMapping("/question/{id}")
    public ResponseEntity<Question> updateQuestionById(@PathVariable Integer id, @RequestBody Question question) {
        return ResponseEntity.status(HttpStatus.OK).body(questionService.updateQuestionById(id, question));
    }

    @DeleteMapping("/question/{id}")
    public ResponseEntity<Void> deleteQuestionById(@PathVariable Integer id) {
        questionService.deleteQuestionById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> generateQuestions(
            @RequestParam String categoryName, @RequestParam Integer numberOfQuestions
    ) {
        return questionService.getQuestionsForQuiz(categoryName, numberOfQuestions);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds) {
        List<QuestionWrapper> questions = questionService.getQuestionsFromId(questionIds);

        return ResponseEntity.status(HttpStatus.OK).body(questions);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses) {
        Integer score = questionService.getScore(responses);

        return ResponseEntity.status(HttpStatus.OK).body(score);
    }
}
