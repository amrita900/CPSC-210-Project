package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.Objects;

// This class represents the Participant with their name, age, ID, name of the study they participated in,
// their results from the study, if the data is keepable (there were no procedure errors) and if the
// participant completed the study.
public class Participant implements Writable {
    private String name;
    private int age;
    private int participantId;
    private String study;
    private double results;
    private Boolean completed;
    private Boolean keepable;
    private int conditionNum;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Participant that = (Participant) o;
        return age == that.age && participantId == that.participantId && Double.compare(that.results, results) == 0
                && conditionNum == that.conditionNum && Objects.equals(name, that.name)
                && Objects.equals(study, that.study) && Objects.equals(completed, that.completed)
                && Objects.equals(keepable, that.keepable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, participantId, study, results, completed, keepable, conditionNum);
    }

    //EFFECTS: constructor for Participants.
    public Participant(String name, int age, int participantId,
                       String studyName, double results,
                       Boolean completed, Boolean keepable, int condition) {
        this.name = name;
        this.age = age;
        this.participantId = participantId;
        this.study = studyName;
        this.results = results;
        this.completed = completed;
        this.keepable = keepable;
        this.conditionNum = condition;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public int getId() {
        return this.participantId;
    }

    public double getResults() {
        return this.results;
    }

    public String getStudy() {
        return this.study;
    }

    public Boolean getKeepable() {
        return this.keepable;
    }

    public Boolean getCompleted() {
        return this.completed;
    }

    public int getCondition() {
        return this.conditionNum;
    }

    // EFFECTS: creates a JSON object representation for participants.
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("age", age);
        json.put("ID", participantId);
        json.put("study", study);
        json.put("results", results);
        json.put("condition", conditionNum);
        json.put("completed", completed);
        json.put("keepable", keepable);
        return json;
    }



}
