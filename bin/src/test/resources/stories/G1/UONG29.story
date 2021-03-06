Title: Sports Scholarships 

Narrative: As a high level sports player, I wish to know about sports scholarships for my sport so that I may apply.

Acceptance Criteria: 

Scenario 1: Navigating to Scholarship Information
Given as a high level sports player
Given that the UL homepage is loaded 
When I click the Scholarships link
Then the webpage with information about scholarships is loaded 

Scenario 2: More Scholarship information 
Given that the Scholarship webpage information page is loaded 
When I click on a listed scholarship 
Then a webpage with more detailed information about the scholarship is loaded 

Scenario 3: Contact Information 
Given that a webpage detailing a sports scholarship is loaded 
When I click find out more 
Then a message with "Please contact the sport scholarship department" pops up 
And a webpage with the contact information of the sports scholarship admissions office is loaded

Scenario 4: Open Scholarships 
Given that the Scholarship webpage information page is loaded 
When I check the box "Currently accepting applications"
Then only scholarships which are currently accepting applications are shown 

Scenario 5: No Open Scholarships 
Given that the Scholarship webpage information page is loaded 
And no scholarships are currently taking applications
When I check the box "Currently accepting applications"
Then the message "No Scholarships are currently taking applications" is displayed. 





