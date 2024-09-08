package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//abstract class that has methods that could be applied to all studies like finding participants that completed
// the study and those that did not, filtering participants through keepable data and condtions
public class Studies implements Writable {
    private List<Participant> participants;
    private static List<Participant> allParticipants = new ArrayList<>();

    //EFFECTS: constructor for Studies, constructs a study with empty list of participants
    public Studies() {
        participants = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a new participant to the list of participants in the study
    public void addParticipant(Participant participant) {
        this.allParticipants.add(participant);
        this.participants.add(participant);
        EventLog.getInstance().logEvent(new Event("new participant added"));
    }

    // EFFECTS: returns a list of all the participants that have done the study
    public List<Participant> getParticipants() {
        return this.participants;
    }


    // EFFECTS: finds a specific participant given their name and returns the participant found, if the participant
    // name is not found, returns a null participant
    public Participant findSpecificParticipant(String name) {
        List<Participant> allParticipants = this.participants;
        Participant nullParticipant = new Participant("", 0, 0, "", 0,
                false, false, 0);
        Participant returnParticipant = nullParticipant;

        for (Participant p : allParticipants) {
            if (p.getName().equals(name)) {
                returnParticipant = p;
            }
        }
        EventLog.getInstance().logEvent(new Event("Found participant"));
        return returnParticipant;
    }

    // MODIFIES: this
    // EFFECTS: removes a specific participant given their name, if the participant name
    // is not found, nothing is changed
    public void removeSpecificParticipant(String name) {
        Participant p = findSpecificParticipant(name);
        Participant nullParticipant = new Participant("", 0, 0, "", 0,
                false, false, 0);

        if (!(p.equals(nullParticipant))) {
            allParticipants.remove(p);
            participants.remove(p);
        }
        EventLog.getInstance().logEvent(new Event("Participant removed"));
    }


    // EFFECTS: returns a list of all the participants that completed the study in the order in which
    // they were added to the dataset
    public List<Participant> getCompletedParticipants() {
        List<Participant> completedParticipants = new ArrayList<>();

        for (Participant participant: this.participants) {
            if (participant.getCompleted()) {
                completedParticipants.add(participant);
            }
        }
        EventLog.getInstance().logEvent(new Event("Found list of completed participants"));
        return completedParticipants;

    }

    // EFFECTS: returns a list of all the participants that did not complete the study in the order in which
    // they were added to the dataset
    public List<Participant> getNotCompletedParticipants() {
        List<Participant> notCompletedParticipants = new ArrayList<>();

        for (Participant participant: this.participants) {
            if (!participant.getCompleted()) {
                notCompletedParticipants.add(participant);
            }
        }
        EventLog.getInstance().logEvent(new Event("Found list of not completed participants"));
        return notCompletedParticipants;
    }

    // EFFECTS: returns a list of all the participants for which there were no errors during data collection
    // in the order in which they were added to the dataset
    public List<Participant> getKeepableParticipants() {
        List<Participant> keepableParticipants = new ArrayList<>();

        for (Participant participant: this.participants) {
            if (participant.getKeepable()) {
                keepableParticipants.add(participant);
            }
        }
        EventLog.getInstance().logEvent(new Event("Found list of keepable participants"));
        return keepableParticipants;
    }

    // EFFECTS: returns a list of all the participants for which there were errors during data collection
    // in the order in which they were added to the dataset
    public List<Participant> getNotKeepableParticipants() {
        List<Participant> notKeepableParticipants = new ArrayList<>();

        for (Participant participant: this.participants) {
            if (!participant.getKeepable()) {
                notKeepableParticipants.add(participant);
            }
        }
        EventLog.getInstance().logEvent(new Event("Found list of not completed participants"));
        return notKeepableParticipants;
    }

    // REQUIRES: participant condition to either be the integer 0 or 1
    // EFFECTS: returns a list of all the participants that were in the Control Condition in the order in
    // which they were added to the dataset
    public List<Participant> getControlParticipants() {
        List<Participant> controlParticipants = new ArrayList<>();

        for (Participant participant: this.participants) {
            if (participant.getCondition() == 0) {
                controlParticipants.add(participant);
            }
        }
        EventLog.getInstance().logEvent(new Event("Found list of control participants"));
        return controlParticipants;
    }

    // REQUIRES: participant condition to either be the integer 0 or 1
    // EFFECTS: returns a list of all the participants that were in the Experimental Condition in the order
    // in which they were added to the dataset
    public List<Participant> getExperimentalParticipants() {
        List<Participant> experimentalParticipants = new ArrayList<>();

        for (Participant participant: this.participants) {
            if (participant.getCondition() == 1) {
                experimentalParticipants.add(participant);
            }
        }
        EventLog.getInstance().logEvent(new Event("Found list of experimental participants"));
        return experimentalParticipants;
    }

    // EFFECTS: returns a list of all the participants that were in the Memory Study in the order
    // in which they were added to the dataset
    public List<Participant> getMemoryStudyParticipants() {
        List<Participant> memoryStudyParticipants = new ArrayList<>();

        for (Participant participant: this.participants) {
            if (participant.getStudy().equals("Memory Study")) {
                memoryStudyParticipants.add(participant);
            }
        }
        EventLog.getInstance().logEvent(new Event("going to Memory Study"));
        return memoryStudyParticipants;
    }

    // EFFECTS: returns a list of all the participants that were in the Stress Study in the order
    // in which they were added to the dataset
    public List<Participant> getStressStudyParticipants() {
        List<Participant> stressStudyParticipants = new ArrayList<>();

        for (Participant participant: this.participants) {
            if (participant.getStudy().equals("Stress Study")) {
                stressStudyParticipants.add(participant);
            }
        }
        EventLog.getInstance().logEvent(new Event("going to Stress Study"));
        return stressStudyParticipants;
    }

    // EFFECTS: returns a list of all the participants that were in the Cognitive Processing Study in the order
    // in which they were added to the dataset
    public List<Participant> getCognitiveProcessingStudyParticipants() {
        List<Participant> cognitiveProcessingStudyParticipants = new ArrayList<>();

        for (Participant participant: this.participants) {
            if (participant.getStudy().equals("Cognitive Processing Study")) {
                cognitiveProcessingStudyParticipants.add(participant);
            }
        }
        EventLog.getInstance().logEvent(new Event("going to Cognitive Processing Study"));
        return cognitiveProcessingStudyParticipants;
    }

    // EFFECTS: returns a JSON object representation for studies
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("participants", participantsToJson());
        json.put("allparticipants", allParticipantsToJson());
        return json;
    }

    // EFFECTS: returns participants in this workroom as a JSON array
    private JSONArray participantsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Participant p : participants) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns allparticipants in this workroom as a JSON array
    private JSONArray allParticipantsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Participant p : allParticipants) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

}
