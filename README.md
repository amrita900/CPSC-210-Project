# My Personal Project

## Creating an application for Psychology research studies 

### Phase 0:

**Introduction to my project idea:**

What will the application do?
- The application will be able to store important information about participants in a psychology study. Information like the participant ID, name, age, contact information, and study results can be stored for each participant. The participants can then also be sorted according to different factors like their age or other demographic details and their results. This would make it easier to group participants and sort through a lot of data for statistical analysis.

Who will use it?
- Psychology researchers can use the application. Each time a participant comes to do a study, their information and results from the research study can be put in the application. Psychology researchers often recruit a lot of participants and require an organized system to store all the data so that they can perform statistical and data analysis on the results of the study.

Why is this project of interest to you?
- I am currently working as a Research Assistant in psychology labs. As an RA, when doing data analysis or other important lab work relating to participants, we often refer to spreadsheets or databases that help organize information. Consequently, I developing an application that would help organize such information, and the type of coding required to create organize psychology data is interesting to me.

**User Stories:**

- As a user, I want to be able to add a new participant to the dataset.

- As a user, I want to be able to view the list of all the participants that have completed the study so far, and another list for those that are yet to complete the study.

- As a user, I want to be able to see a participant whose data may not be used for the study due to some procedure issues.

- As a user, I want to be able to remove participants that have cancelled their participation in the study.

- As a user, I want to see how many participants have been recruited for the different conditions of the study.

- As a user, I want to be able to select a participant and be able to see all their data that has been collected.

- As a user, I want to be able to add the participant’s name, age, participant ID, contact information, the studies the participants has participated in and the study results for each of the studies.

- As a user, I want to be able to have the option to save the new participant added and the current state of the file 

- As a user, I want to be able to have the option to load the state of the file the last time it was used and use it for filtering

# Intrustructions for Grader 


You can generate the first required action related to the user story "adding multiple Xs to a Y" by clicking on "Add participants" from the Participants menu 
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by using either the Participant menu to find or remove a participant, or the Studies menu to filter for specific studies and conditions
- You can locate my visual component by clicking on load or save buttons 
- You can save the state of my application by clicking on the save button 
- You can reload the state of my application by clicking on the load button 
- You can find the list of all Xs added to Ys by clicking on "Print all participants"

#Phase 4: Task 3 
- One way I could refactor my code is to make participants the Observable class and the different studies the observer. This way, each time a new participant was added, the study could be updated with the participant and add it accordingly. 
- I could have also created a bi-directional relationship between participant and studies so that each participant could be assigned the study that they participated in and the study would also be aware of the participant. 
