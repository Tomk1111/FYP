Title: Editing Course Info

Narrative: As a course coordinator, I wish to be able to edit the information on the course homepage so that I can keep it up to date.

Acceptance Criteria: 

Scenario 1: Course Coordinator Changing information
Given as a course coordinator
Given that I have the appropriate administrative permissions to edit the webpage 
And that I am logged into my administrative account
And the edit webpage form is loaded 
And I have altered some information 
When I click save 
Then the course homepage will be updated. 

Scenario 2: Lecturer changing information 
Given that I do not have administrative permission to edit the course webpage
And I am logged into my UL account
And the edit webpage form is loaded 
And I have altered some information 
When I click save 
Then a message will be displayed saying "Edits will be sent to admin account for review"

Scenario 3: Course coordinator leaving form blank
Given that I have the appropriate administrative permissions to edit the webpage 
And that I am logged into my administrative account
And the edit webpage form is loaded 
And I have deleted the information from the fields
When I click save 
Then an error message reading "Error : Please fill in blank fields" will be displayed. 
And the course webpage will not be updated 

Scenario 4: Incorrect form inputs 
Given that I have the appropriate administrative permissions to edit the webpage 
And that I am logged into my administrative account
And the edit webpage form is loaded 
And I have deleted the information from the fields
And I enter numerical types into the field "Course Coordinator"
When I click save 
Then an error message reading "Error : Course Coordinator can not be a number" will be displayed. 
And the course webpage will not be updated

