package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StressStudyTest extends StudiesTest{

    @BeforeEach
    void runBefore() {
        testStudies = new StressStudy();
    }
}
