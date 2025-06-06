package com.tj330.questionservice.service;

import com.tj330.questionservice.exception.ResourceNotFoundException;
import com.tj330.questionservice.model.Question;
import com.tj330.questionservice.model.QuestionWrapper;
import com.tj330.questionservice.model.Response;
import com.tj330.questionservice.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionRepository.findQuestionsByCategory(category);
    }

    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    public void deleteQuestionById(Integer id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with ID: " + id));

        questionRepository.delete(question);
    }


    public Question updateQuestionById(Integer id, Question question) {
        Question existingQuestion = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));

        existingQuestion .setContent(question.getContent());
        existingQuestion .setOption1(question.getOption1());
        existingQuestion .setOption2(question.getOption2());
        existingQuestion .setOption3(question.getOption3());
        existingQuestion .setOption4(question.getOption4());
        existingQuestion .setLevel(question.getLevel());
        existingQuestion .setAnswer(question.getAnswer());
        existingQuestion .setCategory(question.getCategory());
        return questionRepository.save(existingQuestion);
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numberOfQuestions) {
        List<Integer> questions = questionRepository.findRandomNoOfQuestionsByCategory(categoryName, numberOfQuestions);

        return ResponseEntity.status(HttpStatus.OK).body(questions);
    }

    public List<QuestionWrapper> getQuestionsFromId(List<Integer> questionIds) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for (Integer id: questionIds) {
            questions.add(questionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Question not found with ID: " + id)));
        }

        for (Question question: questions) {
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setContent(question.getContent());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());
            wrappers.add(wrapper);
        }
        
        return wrappers;
    }

    public Integer getScore(List<Response> responses) {
        int right = 0;

        for (Response response : responses) {
            Question question = questionRepository.findById(response.getId()).get();

            if (response.getResponse().equals(question.getAnswer())) {
                right++;
            }
        }

        return right;
    }
}
