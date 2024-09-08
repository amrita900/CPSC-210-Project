package model;

import org.junit.jupiter.api.BeforeEach;

public class CognitiveProcessingStudyTest extends StudiesTest{

    @BeforeEach
    void runBefore() {
        testStudies = new CognitiveProcessingStudy();
    }
}
