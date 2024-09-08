package persistence;

import org.json.JSONObject;

public interface Writable {
    // EFFECTS: makes this a JSON object
    JSONObject toJson();
}
