package persistence;

import model.Participant;
import model.Studies;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

// Represents a reader that reads studies from the JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructor for reader, constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads studies from file and returns it, throws IOException if an error occurs reading data from file
    public Studies read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseStudies(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses studies from the JSON object and returns it
    private Studies parseStudies(JSONObject jsonObject) {
        Studies s = new Studies();
        addParticipants(s, jsonObject);
        return s;
    }

    // MODIFIES: studies
    // EFFECTS: parses participants from JSON object and adds them to studies.
    private void addParticipants(Studies s, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("participants");

        for (Object json : jsonArray) {
            JSONObject nextParticipant = (JSONObject) json;
            addParticipant(s, nextParticipant); 
        }
    }

    // MODIFIES: studies
    // EFFECTS: parses participant from JSON object and adds it to the participants in studies
    private void addParticipant(Studies s, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int age = jsonObject.getInt("age");
        int id = jsonObject.getInt("ID");
        String study = jsonObject.getString("study");
        double results = jsonObject.getDouble("results");
        int condition = jsonObject.getInt("condition");
        boolean completed = jsonObject.getBoolean("completed");
        boolean keepable = jsonObject.getBoolean("keepable");
        Participant p = new Participant(name, age, id, study, results, completed, keepable, condition);
        s.addParticipant(p);
    }


}
