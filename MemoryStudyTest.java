package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MemoryStudyTest extends StudiesTest{

    @BeforeEach
    void runBefore(){
        testStudies = new MemoryStudy();
    }

}
