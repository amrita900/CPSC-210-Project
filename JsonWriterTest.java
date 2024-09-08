package persistence;

import model.Participant;
import model.Studies;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            Studies s = new Studies();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("");
        } catch (IOException e) {
            // pass
        }
    }


    @Test
    void testWriterEmptyStudies() {
        try {
            Studies s = new Studies();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyStudies.json");
            writer.open();
            writer.write(s);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyStudies.json");
            s = reader.read();
            assertTrue(s.getParticipants().isEmpty());
        } catch (IOException e) {
            fail("");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Studies s = new Studies();
            Participant p1 = new Participant("aaa", 27, 1, "Memory Study",
                    1.0, true, true, 0);
            Participant p2 = new Participant("bbb", 36, 2, "Stress Study",
                    2.0, true, false, 1);
            s.addParticipant(p1);
            s.addParticipant(p2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(s);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            s = reader.read();
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
