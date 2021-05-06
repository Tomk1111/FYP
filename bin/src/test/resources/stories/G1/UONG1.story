Title: Course List 

Narrative: As a current leaving certificate student I want to see what courses are available at UL to help me decide what I should study at third level.

Acceptance Criteria: 

Scenario 1: Course List
Given As a current leaving certificate student
Given that the University Limerick homepage is displayed 
When I click on courses
Then the webpage with a list of courses available at UL is displayed 

Scenario 2: Course Keyword Search 
Given that the webpage with a list of courses is displayed
And I enter a word in the search bar 
And courses with this word in their title are taught at UL
When I click search 
Then only the courses with that word in the title will be displayed 

Scenario 3: No Course with Keyword Search  
Given that the webpage with a list of courses is displayed
And I enter a word in the search bar
And no course with that word in the title is taught at UL 
When I click search 
Then the message "No Courses Found" will be displayed 

Scenario 4: Course Information 
Given that the webpage with a list of courses is displayed
When I click on the title of the course on the list 
Then the course homepage will be displayed

Scenario 4: Department Information 
Given that the webpage with a list of courses is displayed
When I click on the department which facilitates a course
Then the department homepage will be displayed

Scenario 5:Filtering By Department 
Given that the webpage with a list of courses is displayed
And that there are courses taught by the department of omputer Science and Information Systems
And the "filter by department" dropdown list is selected
And I check "Computer Science and Information Systems"
When I click Enter 
Then course taught by the Computer Science and Information Systems department will be displayed

Scenario 6:Filtering By Course Level
Given that the webpage with a list of courses is displayed
And that there are courses taught at FETAC level 8 
And the "filter by course" dropdown list is selected
And I check "Level 8"
When I click Enter 
Then courses which are FETAC level 8 will be displayed

Scenario 7: Filtering By Department and Course Level
Given that the webpage with a list of courses is displayed
And the "filter by department" dropdown list is selected
And I check "Computer Science and Information Systems"
And the "filter by course" dropdown list is selected
And I check "Level 8"
When I click Enter 
Then courses which are FETAC level 8 and taught by the Computer Science and Information Systems department will be displayed

Scenario 7: Non-Filtering By Department and Course Level
Given that the webpage with a list of courses is displayed
And there is no level 6 course taught by the department of Computer Science and Information Systems
And the "filter by department" dropdown list is selected
And I check "Computer Science and Information Systems"
When I click select the "filter by level" dropdown list
Then there will be no checkbox for level 6 






