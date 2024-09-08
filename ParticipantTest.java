package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParticipantTest {
    private Participant testParticipant;

    @BeforeEach
    void runBefore() {
        testParticipant = new Participant("Bob", 27, 1, "Memory Study",
                5, true, false, 0);
    }

    @Test
    void testConstructor(){
        assertEquals("Bob", testParticipant.getName());
        assertEquals(27, testParticipant.getAge());
        assertEquals(1, testParticipant.getId());
        assertEquals("Memory Study", testParticipant.getStudy());
        assertEquals(5, testParticipant.getResults());
        assertTrue(testParticipant.getCompleted());
        assertFalse(testParticipant.getKeepable());
        assertEquals(0, testParticipant.getCondition());
    }


}
