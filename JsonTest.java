package persistence;

import model.Participant;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkParticipant(String name, int age, int participantID, String study, double results,
                                    int condition, boolean completed, boolean keepable, Participant participant) {
        assertEquals(name, participant.getName());
        assertEquals(age, participant.getAge());
        assertEquals(study, participant.getStudy());
        assertEquals(results, participant.getResults());
        assertEquals(condition, participant.getCondition());
        assertEquals(completed, participant.getCompleted());
        assertEquals(keepable, participant.getKeepable());
    }
}
