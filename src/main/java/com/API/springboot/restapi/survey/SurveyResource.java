package com.API.springboot.restapi.survey;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class SurveyResource {

    private SurveyService surveyService;

    public SurveyResource(SurveyService surveyService) {
        super();
        this.surveyService = surveyService;
    }

    //Surveys
    @RequestMapping("/surveys/{surveyID}")
    public Survey retrieveSurveyById(@PathVariable String surveyID){
        Survey survey = surveyService.retrieveSurveyById(surveyID);
        if(survey==null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return survey;
    }

    @RequestMapping("/surveys/{surveyID}/questions")
    public List<Question> retrieveAllSurveyQuestions(@PathVariable String surveyID){
        List<Question> questions = surveyService.retrieveAllSurveyQuestions(surveyID);
        if(questions==null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return questions;
    }

    @RequestMapping("/surveys/{surveyID}/questions/{questionId}")
    public Question retrieveSpecificSurveyQuestion(@PathVariable String surveyID, @PathVariable String questionId){
        Question question = surveyService.retrieveSpecificSurveyQuestion(surveyID, questionId);
        if(question==null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return question;
    }

    @RequestMapping(value="/surveys/{surveyID}/questions", method = RequestMethod.POST)
    public ResponseEntity<Object> addNewSurveyQuestion(@PathVariable String surveyID, @RequestBody Question question){

        String questionId = surveyService.addNewSurveyQuestion(surveyID, question);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{questionId}").buildAndExpand(questionId).toUri();
        return ResponseEntity.created(location).build();
    }
    @RequestMapping(value = "/surveys/{surveyID}/questions/{questionId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteSurveyQuestion(@PathVariable String surveyID, @PathVariable String questionId){
        surveyService.deleteSurveyQuestion(surveyID, questionId);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/surveys/{surveyID}/questions/{questionId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateSurveyQuestion(@PathVariable String surveyID, @PathVariable String questionId, @RequestBody Question question){

            surveyService.updateSurveyServiceQuestion(surveyID, questionId, question);

        return ResponseEntity.noContent().build();
    }


}
