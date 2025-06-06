package com.tj330.quizservice.controller;


import com.tj330.quizservice.feign.QuizInterface;
import com.tj330.quizservice.model.QuestionWrapper;
import com.tj330.quizservice.model.Quiz;
import com.tj330.quizservice.model.QuizDto;
import com.tj330.quizservice.model.Response;
import com.tj330.quizservice.service.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
@AllArgsConstructor
public class QuizController {
    private final QuizService quizService;
    private final QuizInterface quizInterface;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto) {
        quizService.createQuiz(quizDto.getCategory(), quizDto.getNumQuestions(), quizDto.getTitle());

        return ResponseEntity.status(HttpStatus.CREATED).body("Quiz created");
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id) {

        return quizService.getQuizQuestions(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> response) {

        return quizInterface.getScore(response);
    }
}
