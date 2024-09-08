package persistence;

import model.Participant;
import model.Studies;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {


    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Studies s = reader.read();
            fail("");
        } catch (IOException e) {
            // pass
        }
    }


    @Test
    void testReaderEmptyStudies() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyStudies.json");
        try {
            Studies s = reader.read();
            assertTrue(s.getParticipants().isEmpty());
        } catch (IOException e) {
            fail("");
        }
    }

    @Test
    void testReaderGeneralWorkRoom(){
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkroom.json");
        try {
            Studies s = reader.read();
            List<Participant> participants = s.getParticipants();
            assertEquals(2, participants.size());
            checkParticipant("aaa", 27, 1, "Memory Study", 1.0, 0,
                    true, true, participants.get(0));
            checkParticipant("bbb", 36, 2, "Stress Study", 2.0, 1,
                    true, false, participants.get(1));
        } catch (IOException e) {
            fail("");
        }
    }

}