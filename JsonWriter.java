package persistence;

import model.Studies;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Writer the JSON representation of studies to the file
public class JsonWriter {

    private PrintWriter writer;
    private String destination;
    private static final int INDENT = 4;

    //EFFECTS: constructor for writer, writes to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer to be open for writing, throws FileNotFoundException,
    // if destination file can not be found
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes the JSON representation of studies
    public void write(Studies s) {
        JSONObject json = s.toJson();
        saveToFile(json.toString(INDENT));
    }

    // MODIFIES: this
    // EFFECTS: closes the writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
