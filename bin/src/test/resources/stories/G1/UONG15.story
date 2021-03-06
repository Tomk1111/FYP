Title: Parents Seminar

Narrative:
As a parent
I want to find the room where the parents seminar will be held 
So I can get some general information about UL 

Acceptance Criteria: 

Scenario 1: Mobile App "Event Buddy" feature
Given as a parent
Given that I have logged in to the mobile app 
When I click on the "Event Buddy" option from the home page
And enter my details into the requested fields
Then I will be able to find the parents seminar event and see where it is being held

Scenario 2: Mobile App "Getting Around" feature
Given that I know the room number of the parents seminar
When I click on the "Getting Around" option from the home screen
Then I will be able to find the room using the "Search room" field

Scenario 3: Mobile App "Getting Around" feature - User enters room number in small caps
Given that I know the room number of the parents seminar
When I click on the "Getting Around" option from the home screen
And enter the room number using small caps
Then I will get an error saying "Room not found"

Scenario 4: Virtual Open Day Webpage
Given that the Virtual Open Day webpage has loaded 
When I click on the "Talks and Q&A" option from the webpage
Then I will be able to find details about the parents seminar and where it is being held

Scenario 5: Virtual Open Day Webpage - No information found
Given that the Virtual Open Day webpage has loaded 
When I click on the "Talks and Q&A" option from the webpage
And the Talks and Q&A page has no details about a parents seminar
And there is no "Need help?" option
Then I cannot find the parents seminar details using the webpage