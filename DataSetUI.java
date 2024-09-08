package ui;

import model.*;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DataSetUI extends JFrame {
    private MemoryStudy memoryStudy;
    private CognitiveProcessingStudy cognitiveProcessingStudy;
    private StressStudy stressStudy;
    private Studies studies;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/studies.json";
    private String participantName;
    private int participantAge;
    private int participantID;
    private String participantStudy;
    private int participantResults;
    private int participantCondition;
    private Boolean participantKeepable;
    private Boolean participantCompleted;
    JFrame frame;
    JPanel panel;


    // EFFECTS: runs the DataSet applications
    public DataSetUI() {
        initialise();
    }

    // MODIFIES: this
    // EFFECTS: initialises all the 3 studies in the dataset, creates the main frame adding the graphics and menu
    // learnt from TellerApp.
    private void initialise() {
        memoryStudy = new MemoryStudy();
        cognitiveProcessingStudy = new CognitiveProcessingStudy();
        stressStudy = new StressStudy();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        studies = new Studies();
        frame = new JFrame();
        panel = new JPanel();
        addMenu();
        setGraphics();

        WindowListener listener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                printLog((EventLog.getInstance()));
                super.windowClosing(e);
            }
        };
        frame.addWindowListener(listener);

//        addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosed(WindowEvent e) {
//                System.out.println("GUI has been closed");
//            }
//        });
    }

    //EFFECTS: prints all logs in el
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString());
        }
    }

    //EFFECTS: creates the graphical representation for the initial frame containing the main memu,
    // and save, load and print buttons
    private void setGraphics() {
        panel.setBorder(BorderFactory.createEmptyBorder());
        panel.setLayout(new GridLayout(0, 1));

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("DataSet");
        frame.pack();
        frame.setSize(300, 360);
        frame.setResizable(true);
        addMenu();
        addButtonPanel();
        frame.setVisible(true);
    }

    // EFFECTS: creates the button panel containing save, load and print buttons for the initial frame
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3));
        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadStudies();
            }
        });
        buttonPanel.add(loadButton);
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveStudies();
            }
        });
        buttonPanel.add(saveButton);
        JButton printButton = new JButton("Print all participants");
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printStudies();
            }
        });
        buttonPanel.add(printButton);
        frame.add(buttonPanel);
    }


    // EFFECTS: displays the initial menu of possible actions to user under participants and studies
    private void addMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu participantsMenu = new JMenu("Participants");
        addMenuItem(participantsMenu, new AddParticipants());
        addMenuItem(participantsMenu, new FindParticipants());
        addMenuItem(participantsMenu, new RemoveParticipants());
        menuBar.add(participantsMenu);

        JMenu studiesMenu = new JMenu("Studies");
        addMenuItem(studiesMenu, new MemoryStudyFrame());
        addMenuItem(studiesMenu, new StressStudyFrame());
        addMenuItem(studiesMenu, new CogProcessStudyFrame());
        menuBar.add(studiesMenu);

        frame.setJMenuBar(menuBar);
    }

    //EFFECTS: adds an item with given handler to the given menu
    private void addMenuItem(JMenu theMenu, AbstractAction action) {
        JMenuItem menuItem = new JMenuItem(action);
        theMenu.add(menuItem);
    }


    //EFFECTS: action performed for adding new participants, leads to creation of new frame to add participants
    private class AddParticipants extends AbstractAction {

        AddParticipants() {
            super("Add Participants");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            createFrameAddParticipants();
        }
    }


    // MODIFIES: this
    // EFFECTS: adds a new participant to a specific study, asks user to put participant information and
    // parses the information into the Participant constructor
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void createFrameAddParticipants() {
        JFrame addParticipantFrame = new JFrame();
        addParticipantFrame.setSize(300, 360);
        addParticipantFrame.setResizable(true);
        addParticipantFrame.setLayout(new GridLayout(9, 0));
        JTextField nameTextField = new JTextField();
        JTextField ageTextField = new JTextField();
        JTextField idTextField = new JTextField();
        JTextField resultsTextField = new JTextField();

        participantName(addParticipantFrame, nameTextField);
        participantAge(addParticipantFrame, ageTextField);
        participantID(addParticipantFrame, idTextField);

        JLabel studyQuestion = new JLabel("What study did they participate in?");
        addParticipantFrame.add(studyQuestion);
        String[] studyOptionsList = {"Memory Study", "Stress Study", "Cognitive Processing Study"};
        JComboBox studiesComboBox = new JComboBox(studyOptionsList);
        addParticipantFrame.add(studiesComboBox);

        participantResults(addParticipantFrame, resultsTextField);

        JLabel completedQuestion = new JLabel("Did the participant complete the study?");
        addParticipantFrame.add(completedQuestion);
        String[] completedOptionsList = {"True", "False"};
        JComboBox completedComboBox = new JComboBox(completedOptionsList);
        addParticipantFrame.add(completedComboBox);

        JLabel keepableQuestion = new JLabel("Are the results keepable?");
        addParticipantFrame.add(keepableQuestion);
        String[] keepableOptionsList = {"True", "False"};
        JComboBox keepableComboBox = new JComboBox(keepableOptionsList);
        addParticipantFrame.add(keepableComboBox);

        JLabel conditionQuestion = new JLabel("What is the participant's condition?");
        addParticipantFrame.add(conditionQuestion);
        String[] conditionOptionsList = {"0", "1"};
        JComboBox conditionComboBox = new JComboBox(conditionOptionsList);
        addParticipantFrame.add(conditionComboBox);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                participantName = nameTextField.getText();
                String ageString = ageTextField.getText();
                participantAge = Integer.parseInt(ageString);
                String idString = idTextField.getText();
                participantID = Integer.parseInt(idString);
                participantStudy = (String) studiesComboBox.getSelectedItem();
                String resultsString = resultsTextField.getText();
                participantResults = Integer.parseInt(resultsString);
                String completedString = (String) completedComboBox.getSelectedItem();
                participantCompleted = Boolean.valueOf(completedString);
                String keepableString = (String) keepableComboBox.getSelectedItem();
                participantKeepable = Boolean.valueOf(keepableString);
                String conditionString = (String) conditionComboBox.getSelectedItem();
                participantCondition = Integer.parseInt(conditionString);
                Participant p = new Participant(participantName, participantAge, participantID, participantStudy,
                        participantResults, participantCompleted, participantKeepable, participantCondition);
                addToSpecificStudy(p);
            }
        });
        addParticipantFrame.add(addButton);
        addParticipantFrame.setVisible(true);
    }

    // EFFECTS: creates label for question for name in frame, adds textField to frame
    public void participantName(JFrame frame, JTextField textField) {
        JLabel nameQuestion = new JLabel("What is the participant's name?");
        frame.add(nameQuestion);
        frame.add(textField);
    }

    // EFFECTS: creates label for question for age in frame, adds textField to frame
    public void participantAge(JFrame frame, JTextField textField) {
        JLabel ageQuestion = new JLabel("What is the participant's age?");
        frame.add(ageQuestion);
        frame.add(textField);
    }

    // EFFECTS: creates label for question for ID in frame, adds textField to frame
    public void participantID(JFrame frame, JTextField textField) {
        JLabel idQuestion = new JLabel("What is the participant's ID?");
        frame.add(idQuestion);
        frame.add(textField);
    }

    // EFFECTS: creates label for question for results in frame, adds textField to frame
    public void participantResults(JFrame frame, JTextField textField) {
        JLabel resultsQuestion = new JLabel("What is the participant's results?");
        frame.add(resultsQuestion);
        frame.add(textField);
    }


    // MODIFIES: this
    //EFFECTS: action that takes place when user selects Stress Study, adds all participants of
    // the study to this.stressStudy, presents the memory study frame
    private class MemoryStudyFrame extends AbstractAction {
        MemoryStudyFrame() {
            super("Memory Study");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            List<Participant> memoryStudyParticipants = studies.getMemoryStudyParticipants();
            for (Participant p : memoryStudyParticipants) {
                if (!memoryStudy.getParticipants().contains(p)) {
                    memoryStudy.addParticipant(p);
                }
            }
            createFrameMemoryStudy();
        }
    }

    //EFFECTS: opens the memory study frame with possible options of filtering participants
    // depending on condition, keepable data and completed participants
    public void createFrameMemoryStudy() {
        JFrame memoryFrame = new JFrame();
        memoryFrame.setSize(300, 360);
        memoryFrame.setResizable(true);
        JPanel memoryButtonPanel = new JPanel();
        memoryButtonPanel.setLayout(new GridLayout(9, 0));

        specificStudyProcesses(memoryStudy, memoryButtonPanel);
//        JButton completedButton = new JButton("Get all Completed Participants");
//        completedButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                List<Participant> participants = memoryStudy.getCompletedParticipants();
//                printListOfParticipants(participants);
//            }
//        });
//        memoryButtonPanel.add(completedButton);
//
//        JButton notCompletedButton = new JButton("Get all Not Completed Participants");
//        notCompletedButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                List<Participant> participants = memoryStudy.getNotCompletedParticipants();
//                printListOfParticipants(participants);
//            }
//        });
//        memoryButtonPanel.add(notCompletedButton);
//
//        JButton keepableButton = new JButton("Get all Keepable Participants");
//        keepableButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                List<Participant> participants = memoryStudy.getKeepableParticipants();
//                printListOfParticipants(participants);
//            }
//        });
//        memoryButtonPanel.add(keepableButton);
//
//        JButton notKeepableButton = new JButton("Get all Not Keepable Participants");
//        notKeepableButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                List<Participant> participants = memoryStudy.getNotKeepableParticipants();
//                printListOfParticipants(participants);
//            }
//        });
//        memoryButtonPanel.add(notKeepableButton);
//
//        JButton controlButton = new JButton("Get all Control Participants");
//        controlButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                List<Participant> participants = memoryStudy.getControlParticipants();
//                printListOfParticipants(participants);
//            }
//        });
//        memoryButtonPanel.add(controlButton);
//
//        JButton experimentalButton = new JButton("Get all Experimental Participants");
//        experimentalButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                List<Participant> participants = memoryStudy.getExperimentalParticipants();
//                printListOfParticipants(participants);
//            }
//        });
//        memoryButtonPanel.add(experimentalButton);

        memoryFrame.add(memoryButtonPanel);
        memoryFrame.setVisible(true);
    }

    //EFFECTS: add and handles the buttons for all the filtering that can take place for a study,
    // adds the buttons to buttonPanel
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void specificStudyProcesses(Studies study, JPanel buttonPanel) {
        JButton completedButton = new JButton("Get all Completed Participants");
        completedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Participant> participants = study.getCompletedParticipants();
                printListOfParticipants(participants);
            }
        });
        buttonPanel.add(completedButton);

        JButton notCompletedButton = new JButton("Get all Not Completed Participants");
        notCompletedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Participant> participants = study.getNotCompletedParticipants();
                printListOfParticipants(participants);
            }
        });
        buttonPanel.add(notCompletedButton);

        JButton keepableButton = new JButton("Get all Keepable Participants");
        keepableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Participant> participants = study.getKeepableParticipants();
                printListOfParticipants(participants);
            }
        });
        buttonPanel.add(keepableButton);

        JButton notKeepableButton = new JButton("Get all Not Keepable Participants");
        notKeepableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Participant> participants = study.getNotKeepableParticipants();
                printListOfParticipants(participants);
            }
        });
        buttonPanel.add(notKeepableButton);

        JButton controlButton = new JButton("Get all Control Participants");
        controlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Participant> participants = study.getControlParticipants();
                printListOfParticipants(participants);
            }
        });
        buttonPanel.add(controlButton);

        JButton experimentalButton = new JButton("Get all Experimental Participants");
        experimentalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Participant> participants = study.getExperimentalParticipants();
                printListOfParticipants(participants);
            }
        });
        buttonPanel.add(experimentalButton);
    }


    // MODIFIES: this
    //EFFECTS: action that takes place when user selects Stress Study, adds all participants of
    // the study to this.stressStudy, presents the memory study frame
    private class StressStudyFrame extends AbstractAction {
        StressStudyFrame() {
            super("Stress Study");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            List<Participant> stressStudyParticipants = studies.getStressStudyParticipants();
            for (Participant p : stressStudyParticipants) {
                if (!stressStudy.getParticipants().contains(p)) {
                    stressStudy.addParticipant(p);
                }
            }
            createFrameStressStudy();
        }
    }

    //EFFECTS: opens the stress study frame with possible options of filtering participants
    // depending on condition, keepable data and completed participants
    public void createFrameStressStudy() {
        JFrame stressFrame = new JFrame();
        stressFrame.setSize(300, 360);
        stressFrame.setResizable(true);
        JPanel stressButtonPanel = new JPanel();
        stressButtonPanel.setLayout(new GridLayout(9, 0));

        specificStudyProcesses(stressStudy, stressButtonPanel);
//        JButton completedButton = new JButton("Get all Completed Participants");
//        completedButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                List<Participant> participants = stressStudy.getCompletedParticipants();
//                printListOfParticipants(participants);
//            }
//        });
//        stressButtonPanel.add(completedButton);
//
//        JButton notCompletedButton = new JButton("Get all Not Completed Participants");
//        notCompletedButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                List<Participant> participants = stressStudy.getNotCompletedParticipants();
//                printListOfParticipants(participants);
//            }
//        });
//        stressButtonPanel.add(notCompletedButton);
//
//        JButton keepableButton = new JButton("Get all Keepable Participants");
//        keepableButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                List<Participant> participants = stressStudy.getKeepableParticipants();
//                printListOfParticipants(participants);
//            }
//        });
//        stressButtonPanel.add(keepableButton);
//
//        JButton notKeepableButton = new JButton("Get all Not Keepable Participants");
//        notKeepableButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                List<Participant> participants = stressStudy.getNotKeepableParticipants();
//                printListOfParticipants(participants);
//            }
//        });
//        stressButtonPanel.add(notKeepableButton);
//
//        JButton controlButton = new JButton("Get all Control Participants");
//        controlButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                List<Participant> participants = stressStudy.getControlParticipants();
//                printListOfParticipants(participants);
//            }
//        });
//        stressButtonPanel.add(controlButton);
//
//        JButton experimentalButton = new JButton("Get all Experimental Participants");
//        experimentalButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                List<Participant> participants = stressStudy.getExperimentalParticipants();
//                printListOfParticipants(participants);
//            }
//        });
//        stressButtonPanel.add(experimentalButton);
        stressFrame.add(stressButtonPanel);
        stressFrame.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: action that takes place when user selects Cognitive Processing Study, adds all participants of
    // the study to this.cognitiveProcessingStudy, presents the memory study frame
    private class CogProcessStudyFrame extends AbstractAction {
        CogProcessStudyFrame() {
            super("Cognitive Processing Study");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            List<Participant> cognitiveProcessingStudyParticipants = studies.getCognitiveProcessingStudyParticipants();
            for (Participant p : cognitiveProcessingStudyParticipants) {
                if (!cognitiveProcessingStudy.getParticipants().contains(p)) {
                    cognitiveProcessingStudy.addParticipant(p);
                }
            }
            createFrameCogProcessStudy();
        }
    }

    //EFFECTS: opens the stress study frame with possible options of filtering participants
    // depending on condition, keepable data and completed participants
    public void createFrameCogProcessStudy() {
        JFrame cogProcessFrame = new JFrame();
        cogProcessFrame.setSize(300, 360);
        cogProcessFrame.setResizable(true);
        JPanel cogProcessButtonPanel = new JPanel();
        cogProcessButtonPanel.setLayout(new GridLayout(9, 0));

        specificStudyProcesses(cognitiveProcessingStudy, cogProcessButtonPanel);
//        JButton completedButton = new JButton("Get all Completed Participants");
//        completedButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                List<Participant> participants = cognitiveProcessingStudy.getCompletedParticipants();
//                printListOfParticipants(participants);
//            }
//        });
//        cogProcessButtonPanel.add(completedButton);
//
//        JButton notCompletedButton = new JButton("Get all Not Completed Participants");
//        notCompletedButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                List<Participant> participants = cognitiveProcessingStudy.getNotCompletedParticipants();
//                printListOfParticipants(participants);
//            }
//        });
//        cogProcessButtonPanel.add(notCompletedButton);
//
//        JButton keepableButton = new JButton("Get all Keepable Participants");
//        keepableButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                List<Participant> participants = cognitiveProcessingStudy.getKeepableParticipants();
//                printListOfParticipants(participants);
//            }
//        });
//        cogProcessButtonPanel.add(keepableButton);
//
//        JButton notKeepableButton = new JButton("Get all Not Keepable Participants");
//        notKeepableButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                List<Participant> participants = cognitiveProcessingStudy.getNotKeepableParticipants();
//                printListOfParticipants(participants);
//            }
//        });
//        cogProcessButtonPanel.add(notKeepableButton);
//
//        JButton controlButton = new JButton("Get all Control Participants");
//        controlButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                List<Participant> participants = cognitiveProcessingStudy.getControlParticipants();
//                printListOfParticipants(participants);
//            }
//        });
//        cogProcessButtonPanel.add(controlButton);
//
//        JButton experimentalButton = new JButton("Get all Experimental Participants");
//        experimentalButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                List<Participant> participants = cognitiveProcessingStudy.getExperimentalParticipants();
//                printListOfParticipants(participants);
//            }
//        });
//        cogProcessButtonPanel.add(experimentalButton);
        cogProcessFrame.add(cogProcessButtonPanel);
        cogProcessFrame.setVisible(true);
    }


    //EFFECTS: performs action when user clicks on find participants, opens frame to find participants
    private class FindParticipants extends AbstractAction {

        FindParticipants() {
            super("Find Participants");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            createFrameFindParticipants();
        }
    }

    //EFFECTS: creates frame to find a participant with textbox to enter participant's name,
    // finds the participant with given name and prints the participant's information
    public void createFrameFindParticipants() {
        JFrame frame = new JFrame();
        frame.setSize(300, 360);
        frame.setResizable(true);
        JTextField textField = new JTextField();
        textField.setSize(100,100);
        JLabel question = new JLabel("What is the participant's name?");
        frame.add(textField);
        frame.add(question);
        frame.setVisible(true);

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textField.getText();
                Participant returnParticipant;
                returnParticipant = studies.findSpecificParticipant(name);
                List<Participant> participants = new ArrayList<>();
                participants.add(returnParticipant);
                printParticipantInformation(participants);
            }
        });
    }

    //EFFECTS: performs action when user clicks on remove participants, opens frame to remove the participant
    private class RemoveParticipants extends AbstractAction {

        RemoveParticipants() {
            super("Remove Participants");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            createFrameRemoveParticipants();
        }
    }

    //MODIFIES: this
    //EFFECTS: creates frame to remove a participant with textbox to enter participant's name,
    // removes the participant with given name
    public void createFrameRemoveParticipants() {
        JFrame frame = new JFrame();
        frame.setSize(300, 360);
        frame.setResizable(true);
        JTextField textField = new JTextField();
        textField.setSize(100,100);
        JLabel question = new JLabel("What is the participant's name?");
        frame.add(textField);
        frame.add(question);
        frame.setVisible(true);

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textField.getText();
                studies.removeSpecificParticipant(name);
            }
        });
    }

    //EFFECTS: creates a new frame with all the names of participants in participants
    private void printListOfParticipants(List<Participant> participants) {
        JFrame printFrame = new JFrame();
        printFrame.setSize(300, 360);
        printFrame.setResizable(true);
        DefaultListModel<String> modelNames = new DefaultListModel<>();
        for (Participant p : participants) {
            String name = p.getName();
            modelNames.addElement(name);
        }

        JList list = new JList((ListModel) modelNames);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        printFrame.add(list);
        printFrame.setVisible(true);
    }

    //EFFECTS: creates a new frame with all the information of all the participants in participants
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void printParticipantInformation(List<Participant> participants) {
        JFrame printFrame = new JFrame();
        printFrame.setSize(300, 360);
        printFrame.setResizable(true);
        DefaultListModel<String> modelParticipantInfo = new DefaultListModel<>();
        for (Participant p : participants) {
            String participantInfo = "Participant name is " + p.getName() + ". Their age is " + p.getAge()
                    + " years old. " + "Their ID is " + p.getId() + ". The Study that they participated in is "
                    + p.getStudy() + ". Their study condition is " + p.getCondition();
            if (p.getCompleted()) {
                participantInfo = participantInfo + ". They completed the study. ";
            } else {
                participantInfo = participantInfo + ". They did not complete the study. ";
            }
            if (p.getKeepable()) {
                participantInfo = participantInfo + "Their results are keepable.";
            } else {
                participantInfo = participantInfo + "Their results are not keepable.";
            }
            modelParticipantInfo.addElement(participantInfo);
        }
        JList list = new JList((ListModel) modelParticipantInfo);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setLayoutOrientation(JList.VERTICAL_WRAP);
        printFrame.add(list);
        printFrame.setVisible(true);
    }


    // MODIFIES: this
    // EFFECTS: adds participant to specific study depending on study name in constructor
    private void addToSpecificStudy(Participant p) {
//        if (p.getStudy().equals("Memory Study")) {
//            memoryStudy.addParticipant(p);
//        } else if (p.getStudy().equals("Stress Study")) {
//            stressStudy.addParticipant(p);
//        } else {
//            cognitiveProcessingStudy.addParticipant(p);
//        }

        studies.addParticipant(p);
    }


    // EFFECTS: saves the studies current state to file, when new participant is added, it saves it, pops an image if
    // FileNotFoundException is thrown
    private void saveStudies() {
        try {
            jsonWriter.open();
            jsonWriter.write(studies);
            jsonWriter.close();
            imageFrame("save");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Unable to save file",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads studies from file, pops an image if FileNotFoundException is thrown
    private void loadStudies() {
        try {
            studies = jsonReader.read();
            imageFrame("load");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Unable to load from file",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // EFFECTS: prints list of all participants in dataset from studies from file
    private void printStudies() {
        List<Participant> participants = studies.getParticipants();
        printParticipantInformation(participants);
    }

    // EFFECTS: sets imageFrame to loaded.png if s is loaded, and sets imageFrame to saved.png otherwise
    private void imageFrame(String s) {
        JFrame imageFrame = new JFrame();
        imageFrame.setSize(300, 360);
        ImageIcon load = new ImageIcon("./data/images/loaded.png");
        ImageIcon saved = new ImageIcon("./data/images/saved.png");
        if (s == "load") {
            JLabel label = new JLabel(load);
            imageFrame.add(label);
        } else  {
            JLabel label = new JLabel(saved);
            imageFrame.add(label);
        }
        imageFrame.setResizable(true);
        imageFrame.setVisible(true);
    }

}