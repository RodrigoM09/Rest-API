package com.API.springboot.restapi.survey;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyResourceIT {

    String str = """
            {
            "id":"Question1",
            "description":"Most Popular Cloud Platform Today",
            "options":[
                "AWS",
                "Azure",
                "Google Cloud",
                "Oracle Cloud"
            ],
            "correctAnswer":"AWS"
            }
            """;

    private static String SPECIFIC_QUESTION_URL = "/surveys/survey1/questions/Question1";

    @Autowired
    private TestRestTemplate template;

    @Test
     void  retrieveSpecificSurveyQuestion_basicScenario(){
        ResponseEntity<String> responseEntity = template.getForEntity(SPECIFIC_QUESTION_URL, String.class);
        String expectedResponse =
                """
                {"id":"Question1","description":"Most Popular Cloud Platform Today","options":["AWS","Azure","Google Cloud","Oracle Cloud"],"correctAnswer":"AWS"}
                        """;
        assertEquals(expectedResponse.trim(), responseEntity.getBody());
        System.out.println(responseEntity.getBody());
        System.out.println(responseEntity.getHeaders());
    }
}