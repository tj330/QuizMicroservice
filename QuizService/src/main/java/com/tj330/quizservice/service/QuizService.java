package com.tj330.quizservice.service;

import com.tj330.quizservice.exception.ResourceNotFoundException;
import com.tj330.quizservice.feign.QuizInterface;
import com.tj330.quizservice.model.Question;
import com.tj330.quizservice.model.QuestionWrapper;
import com.tj330.quizservice.model.Quiz;
import com.tj330.quizservice.model.Response;
import com.tj330.quizservice.repository.QuizRepository;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    private final QuizInterface quizInterface;

    public Quiz createQuiz(String category, Integer numQ, String title) {
        List<Integer> questions = quizInterface.generateQuestions(category, numQ).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        return quizRepository.save(quiz);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Quiz quiz = quizRepository.findById(id).get();
        List<Integer> questionIds = quiz.getQuestionIds();

        return quizInterface.getQuestionsFromId(questionIds);
    }

    public Integer calculateResult(Integer id, List<Response> response) {
//        Quiz quiz = quizRepository.findById(id).get();
//
//        List<Question> questions = quiz.getQuestions();
//
        int mark = 0, question = 0;
//
//        for (Response r : response) {
//            if (r.getResponse().equals(questions.get(question).getAnswer())) {
//                mark++;
//            }
//            question++;
//        }

        return mark;
    }
}
