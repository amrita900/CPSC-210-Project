package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class StudiesTest {
    Studies testStudies;

    @Test
    void testConstructor() {
        List<Participant> participants = testStudies.getParticipants();
        assertTrue(participants.isEmpty());
    }

    @Test
    void testGetOnlyCompletedParticipant(){
        Participant participant1 = new Participant("p1", 36,1,
                "Memory Study",0.5, true, true, 0);

        testStudies.addParticipant(participant1);

        List<Participant> completedParticipants = testStudies.getCompletedParticipants();

        assertEquals(participant1, completedParticipants.get(0));
        assertEquals(1, completedParticipants.size());

    }

    @Test
    void testGetMultipleCompletedParticipant(){
        Participant participant1 = new Participant("p1", 36,1,
                "Memory Study",0.5, true, true, 0);
        Participant participant2 = new Participant("p2", 15,2,
                "Memory Study",10, true, false, 0);

        testStudies.addParticipant(participant1);
        testStudies.addParticipant(participant2);

        List<Participant> completedParticipants = testStudies.getCompletedParticipants();

        assertEquals(participant1, completedParticipants.get(0));
        assertEquals(participant2, completedParticipants.get(1));
        assertEquals(2, completedParticipants.size());
    }

    @Test
    void testGetNoneCompletedParticipant(){
        Participant participant1 = new Participant("p1", 36,1,
                "Memory Study",0.5, false, true, 0);
        Participant participant2 = new Participant("p2", 15,2,
                "Memory Study",10, false, false, 0);

        testStudies.addParticipant(participant1);
        testStudies.addParticipant(participant2);

        List<Participant> completedParticipants = testStudies.getCompletedParticipants();

        assertTrue(completedParticipants.isEmpty());
    }

    @Test
    void testGetCompletedParticipantInMix(){
        Participant participant1 = new Participant("p1", 36,1,
                "Memory Study",0.5, true, true, 0);
        Participant participant2 = new Participant("p2", 15,2,
                "Memory Study",10, true, false, 0);
        Participant participant3 = new Participant("p3", 25,3,
                "Memory Study",9, false, false, 0);
        Participant participant4 = new Participant("p4", 19,4,
                "Memory Study",9, false, true, 0);

        testStudies.addParticipant(participant1);
        testStudies.addParticipant(participant2);
        testStudies.addParticipant(participant3);
        testStudies.addParticipant(participant4);

        List<Participant> completedParticipants = testStudies.getCompletedParticipants();

        assertEquals(participant1, completedParticipants.get(0));
        assertEquals(participant2, completedParticipants.get(1));
        assertEquals(2, completedParticipants.size());
    }

    @Test
    void testGetOnlyNotCompletedParticipant(){
        Participant participant1 = new Participant("p1", 36,1,
                "Memory Study",0.5, false, true, 0);

        testStudies.addParticipant(participant1);

        List<Participant> notCompletedParticipants = testStudies.getNotCompletedParticipants();

        assertEquals(participant1, notCompletedParticipants.get(0));
        assertEquals(1, notCompletedParticipants.size());

    }

    @Test
    void testGetMultipleNotCompletedParticipant(){
        Participant participant1 = new Participant("p1", 36,1,
                "Memory Study",0.5, false, true, 0);
        Participant participant2 = new Participant("p2", 15,2,
                "Memory Study",10, false, false, 0);

        testStudies.addParticipant(participant1);
        testStudies.addParticipant(participant2);

        List<Participant> notCompletedParticipants = testStudies.getNotCompletedParticipants();

        assertEquals(participant1, notCompletedParticipants.get(0));
        assertEquals(participant2, notCompletedParticipants.get(1));
        assertEquals(2, notCompletedParticipants.size());
    }

    @Test
    void testGetNoneNotCompletedParticipant(){
        Participant participant1 = new Participant("p1", 36,1,
                "Memory Study",0.5, true, true, 0);
        Participant participant2 = new Participant("p2", 15,2,
                "Memory Study",10, true, false, 0);

        testStudies.addParticipant(participant1);
        testStudies.addParticipant(participant2);

        List<Participant> notCompletedParticipants = testStudies.getNotCompletedParticipants();

        assertTrue(notCompletedParticipants.isEmpty());
    }

    @Test
    void testGetNotCompletedParticipantInMix(){
        Participant participant1 = new Participant("p1", 36,1,
                "Memory Study",0.5, true, true, 0);
        Participant participant2 = new Participant("p2", 15,2,
                "Memory Study",10, true, false, 0);
        Participant participant3 = new Participant("p3", 25,3,
                "Memory Study",9, false, false, 0);
        Participant participant4 = new Participant("p4", 19,4,
                "Memory Study",9, false, true, 0);

        testStudies.addParticipant(participant1);
        testStudies.addParticipant(participant2);
        testStudies.addParticipant(participant3);
        testStudies.addParticipant(participant4);

        List<Participant> notCompletedParticipants = testStudies.getNotCompletedParticipants();

        assertEquals(participant3, notCompletedParticipants.get(0));
        assertEquals(participant4, notCompletedParticipants.get(1));
        assertEquals(2, notCompletedParticipants.size());
    }

    @Test
    void testGetOnlyKeepableParticipant(){
        Participant participant1 = new Participant("p1", 36,1,
                "Memory Study",0.5, true, true, 0);

        testStudies.addParticipant(participant1);

        List<Participant> keepableParticipants = testStudies.getKeepableParticipants();

        assertEquals(participant1, keepableParticipants.get(0));
        assertEquals(1, keepableParticipants.size());

    }

    @Test
    void testGetMultipleKeepableParticipant(){
        Participant participant1 = new Participant("p1", 36,1,
                "Memory Study",0.5, true, true, 0);
        Participant participant2 = new Participant("p2", 15,2,
                "Memory Study",10, false, true, 0);

        testStudies.addParticipant(participant1);
        testStudies.addParticipant(participant2);

        List<Participant> keepableParticipants = testStudies.getKeepableParticipants();

        assertEquals(participant1, keepableParticipants.get(0));
        assertEquals(participant2, keepableParticipants.get(1));
        assertEquals(2, keepableParticipants.size());
    }

    @Test
    void testGetNoneKeepableParticipant(){
        Participant participant1 = new Participant("p1", 36,1,
                "Memory Study",0.5, true, false, 0);
        Participant participant2 = new Participant("p2", 15,2,
                "Memory Study",10, false, false, 0);

        testStudies.addParticipant(participant1);
        testStudies.addParticipant(participant2);

        List<Participant> keepableParticipants = testStudies.getKeepableParticipants();

        assertTrue(keepableParticipants.isEmpty());
    }

    @Test
    void testGetKeepableParticipantInMix() {
        Participant participant1 = new Participant("p1", 36, 1,
                "Memory Study", 0.5, true, true, 0);
        Participant participant2 = new Participant("p2", 15, 2,
                "Memory Study", 10, true, false, 0);
        Participant participant3 = new Participant("p3", 25, 3,
                "Memory Study", 9, false, false, 0);
        Participant participant4 = new Participant("p4", 19, 4,
                "Memory Study", 9, false, true, 0);

        testStudies.addParticipant(participant1);
        testStudies.addParticipant(participant2);
        testStudies.addParticipant(participant3);
        testStudies.addParticipant(participant4);

        List<Participant> keepableParticipants = testStudies.getKeepableParticipants();

        assertEquals(participant1, keepableParticipants.get(0));
        assertEquals(participant4, keepableParticipants.get(1));
        assertEquals(2, keepableParticipants.size());
    }

    @Test
    void testGetOnlyNotKeepableParticipant(){
        Participant participant1 = new Participant("p1", 36,1,
                "Memory Study",0.5, true, false, 0);

        testStudies.addParticipant(participant1);

        List<Participant> notKeepableParticipants = testStudies.getNotKeepableParticipants();

        assertEquals(participant1, notKeepableParticipants.get(0));
        assertEquals(1, notKeepableParticipants.size());

    }

    @Test
    void testGetMultipleNotKeepableParticipant(){
        Participant participant1 = new Participant("p1", 36,1,
                "Memory Study",0.5, true, false, 0);
        Participant participant2 = new Participant("p2", 15,2,
                "Memory Study",10, false, false, 0);

        testStudies.addParticipant(participant1);
        testStudies.addParticipant(participant2);

        List<Participant> notKeepableParticipants = testStudies.getNotKeepableParticipants();

        assertEquals(participant1, notKeepableParticipants.get(0));
        assertEquals(participant2, notKeepableParticipants.get(1));
        assertEquals(2, notKeepableParticipants.size());
    }

    @Test
    void testGetNoneNotKeepableParticipant(){
        Participant participant1 = new Participant("p1", 36,1,
                "Memory Study",0.5, true, true, 0);
        Participant participant2 = new Participant("p2", 15,2,
                "Memory Study",10, true, true, 0);

        testStudies.addParticipant(participant1);
        testStudies.addParticipant(participant2);

        List<Participant> notKeepableParticipants = testStudies.getNotKeepableParticipants();

        assertTrue(notKeepableParticipants.isEmpty());
    }

    @Test
    void testGetNotKeepableParticipantInMix(){
        Participant participant1 = new Participant("p1", 36,1,
                "Memory Study",0.5, true, true, 0);
        Participant participant2 = new Participant("p2", 15,2,
                "Memory Study",10, true, false, 0);
        Participant participant3 = new Participant("p3", 25,3,
                "Memory Study",9, false, false, 0);
        Participant participant4 = new Participant("p4", 19,4,
                "Memory Study",9, false, true, 0);

        testStudies.addParticipant(participant1);
        testStudies.addParticipant(participant2);
        testStudies.addParticipant(participant3);
        testStudies.addParticipant(participant4);

        List<Participant> notKeepableParticipants = testStudies.getNotKeepableParticipants();

        assertEquals(participant2, notKeepableParticipants.get(0));
        assertEquals(participant3, notKeepableParticipants.get(1));
        assertEquals(2, notKeepableParticipants.size());
    }


    @Test
    void findSpecificParticipantTestSingleParticipant() {
        Participant participant1 = new Participant("p1", 36,1,
                "Memory Study",0.5, true, true, 0);

        testStudies.addParticipant(participant1);
        assertEquals(participant1,testStudies.findSpecificParticipant("p1"));
    }

    @Test
    void findSpecificParticipantNoMatchParticipantTest() {
        Participant participant1 = new Participant("p1", 36,1,
                "Memory Study",0.5, true, true, 0);

        Participant nullParticipant = new Participant("", 0, 0, "", 0,
                false, false, 0);

        testStudies.addParticipant(participant1);

        assertEquals(nullParticipant, testStudies.findSpecificParticipant("null"));
    }

    @Test
    void findSpecificParticipantInMultipleParticipantsTest() {
        Participant participant1 = new Participant("p1", 36,1,
                "Memory Study",0.5, true, true, 0);
        Participant participant2 = new Participant("p2", 15,2,
                "Memory Study",10, true, false, 0);
        Participant participant3 = new Participant("p3", 25,3,
                "Memory Study",9, false, false, 0);
        Participant participant4 = new Participant("p4", 19,4,
                "Memory Study",9, false, true, 0);

        testStudies.addParticipant(participant1);
        testStudies.addParticipant(participant2);
        testStudies.addParticipant(participant3);
        testStudies.addParticipant(participant4);
        assertEquals(participant1,testStudies.findSpecificParticipant("p1"));
        assertEquals(participant2,testStudies.findSpecificParticipant("p2"));
    }

    @Test
    void removeSingleParticipantTest() {
        Participant participant1 = new Participant("p1", 36,1,
                "Memory Study",0.5, true, true, 0);

        testStudies.addParticipant(participant1);
        testStudies.removeSpecificParticipant("p1");

        List<Participant> participants = testStudies.getParticipants();
        assertTrue(participants.isEmpty());
    }

    @Test
    void removeSingleWrongParticipantTest() {
        Participant participant1 = new Participant("p1", 36,1,
                "Memory Study",0.5, true, true, 0);

        testStudies.addParticipant(participant1);
        testStudies.removeSpecificParticipant("p2");

        List<Participant> participants = testStudies.getParticipants();
        assertEquals(1, participants.size());
        assertEquals(participant1, participants.get(0));
    }

    @Test
    void removeParticipantInMultipleTest() {
        Participant participant1 = new Participant("p1", 36,1,
                "Memory Study",0.5, true, true, 0);
        Participant participant2 = new Participant("p2", 15,2,
                "Memory Study",10, true, false, 0);
        Participant participant3 = new Participant("p3", 25,3,
                "Memory Study",9, false, false, 0);
        Participant participant4 = new Participant("p4", 19,4,
                "Memory Study",9, false, true, 0);

        testStudies.addParticipant(participant1);
        testStudies.addParticipant(participant2);
        testStudies.addParticipant(participant3);
        testStudies.addParticipant(participant4);
        testStudies.removeSpecificParticipant("p1");

        List<Participant> participants = testStudies.getParticipants();
        assertEquals(3, participants.size());
        assertEquals(participant2, participants.get(0));
    }

    @Test
    void testGetControlParticipant() {
        Participant participant1 = new Participant("p1", 36, 1,
                "Memory Study", 0.5, true, true, 0);
        Participant participant2 = new Participant("p2", 15, 2,
                "Memory Study", 10, true, false, 1);

        testStudies.addParticipant(participant1);
        testStudies.addParticipant(participant2);

        List<Participant> controlParticipants = testStudies.getControlParticipants();

        assertEquals(participant1, controlParticipants.get(0));
        assertEquals(1, controlParticipants.size());
    }

    @Test
    void testGetExperimentalParticipant() {
        Participant participant1 = new Participant("p1", 36, 1,
                "Memory Study", 0.5, true, true, 0);
        Participant participant2 = new Participant("p2", 15, 2,
                "Memory Study", 10, true, false, 1);

        testStudies.addParticipant(participant1);
        testStudies.addParticipant(participant2);

        List<Participant> experimentalParticipants = testStudies.getExperimentalParticipants();

        assertEquals(participant2, experimentalParticipants.get(0));
        assertEquals(1, experimentalParticipants.size());
    }

    @Test
    void testGetNoneControlParticipant() {
        Participant participant1 = new Participant("p1", 36, 1,
                "Memory Study", 0.5, true, true, 1);
        Participant participant2 = new Participant("p2", 15, 2,
                "Memory Study", 10, true, false, 1);

        testStudies.addParticipant(participant1);
        testStudies.addParticipant(participant2);

        List<Participant> controlParticipants = testStudies.getControlParticipants();

        assertEquals(0, controlParticipants.size());
    }

    @Test
    void testGetNoneExperimentalParticipant() {
        Participant participant1 = new Participant("p1", 36, 1,
                "Memory Study", 0.5, true, true, 0);
        Participant participant2 = new Participant("p2", 15, 2,
                "Memory Study", 10, true, false, 0);

        testStudies.addParticipant(participant1);
        testStudies.addParticipant(participant2);

        List<Participant> experimentalParticipants = testStudies.getExperimentalParticipants();

        assertEquals(0, experimentalParticipants.size());
    }

    @Test
    void testGetMemoryStudiesParticipant() {
        Participant participant1 = new Participant("p1", 36, 1,
                "Memory Study", 0.5, true, true, 0);
        Participant participant2 = new Participant("p2", 15, 2,
                "Memory Study", 10, true, false, 0);

        testStudies.addParticipant(participant1);
        testStudies.addParticipant(participant2);

        List<Participant> Participants = testStudies.getMemoryStudyParticipants();

        assertEquals(2, Participants.size());
    }

    @Test
    void testGetNoMemoryStudiesParticipant() {
        Participant participant1 = new Participant("p1", 36, 1,
                "Stress Study", 0.5, true, true, 0);
        Participant participant2 = new Participant("p2", 15, 2,
                "Cognitive Study", 10, true, false, 0);

        testStudies.addParticipant(participant1);
        testStudies.addParticipant(participant2);

        List<Participant> Participants = testStudies.getMemoryStudyParticipants();

        assertEquals(0, Participants.size());
    }

    @Test
    void testGetOneMemoryStudiesParticipant() {
        Participant participant1 = new Participant("p1", 36, 1,
                "Memory Study", 0.5, true, true, 0);
        Participant participant2 = new Participant("p2", 15, 2,
                "Stress Study", 10, true, false, 0);

        testStudies.addParticipant(participant1);
        testStudies.addParticipant(participant2);

        List<Participant> Participants = testStudies.getMemoryStudyParticipants();

        assertEquals(1, Participants.size());
    }

    @Test
    void testGetStressStudiesParticipant() {
        Participant participant1 = new Participant("p1", 36, 1,
                "Stress Study", 0.5, true, true, 0);
        Participant participant2 = new Participant("p2", 15, 2,
                "Stress Study", 10, true, false, 0);

        testStudies.addParticipant(participant1);
        testStudies.addParticipant(participant2);

        List<Participant> Participants = testStudies.getStressStudyParticipants();

        assertEquals(2, Participants.size());
    }

    @Test
    void testGetNoStressStudiesParticipant() {
        Participant participant1 = new Participant("p1", 36, 1,
                "Memory Study", 0.5, true, true, 0);
        Participant participant2 = new Participant("p2", 15, 2,
                "Cognitive Study", 10, true, false, 0);

        testStudies.addParticipant(participant1);
        testStudies.addParticipant(participant2);

        List<Participant> Participants = testStudies.getStressStudyParticipants();

        assertEquals(0, Participants.size());
    }

    @Test
    void testGetOneStressStudiesParticipant() {
        Participant participant1 = new Participant("p1", 36, 1,
                "Memory Study", 0.5, true, true, 0);
        Participant participant2 = new Participant("p2", 15, 2,
                "Stress Study", 10, true, false, 0);

        testStudies.addParticipant(participant1);
        testStudies.addParticipant(participant2);

        List<Participant> Participants = testStudies.getStressStudyParticipants();

        assertEquals(1, Participants.size());
    }

    @Test
    void testGetCognitiveProcessingStudiesParticipant() {
        Participant participant1 = new Participant("p1", 36, 1,
                "Cognitive Processing Study", 0.5, true, true, 0);
        Participant participant2 = new Participant("p2", 15, 2,
                "Cognitive Processing Study", 10, true, false, 0);

        testStudies.addParticipant(participant1);
        testStudies.addParticipant(participant2);

        List<Participant> Participants = testStudies.getCognitiveProcessingStudyParticipants();

        assertEquals(2, Participants.size());
    }

    @Test
    void testGetNoCognitiveProcessingStudiesParticipant() {
        Participant participant1 = new Participant("p1", 36, 1,
                "Stress Study", 0.5, true, true, 0);
        Participant participant2 = new Participant("p2", 15, 2,
                "Memory Study", 10, true, false, 0);

        testStudies.addParticipant(participant1);
        testStudies.addParticipant(participant2);

        List<Participant> Participants = testStudies.getCognitiveProcessingStudyParticipants();

        assertEquals(0, Participants.size());
    }

    @Test
    void testGetOneCognitiveProcessingStudiesParticipant() {
        Participant participant1 = new Participant("p1", 36, 1,
                "Cognitive Processing Study", 0.5, true, true, 0);
        Participant participant2 = new Participant("p2", 15, 2,
                "Stress Study", 10, true, false, 0);

        testStudies.addParticipant(participant1);
        testStudies.addParticipant(participant2);

        List<Participant> Participants = testStudies.getCognitiveProcessingStudyParticipants();

        assertEquals(1, Participants.size());
    }



}