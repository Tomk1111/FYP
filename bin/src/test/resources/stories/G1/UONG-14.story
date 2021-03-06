Title: Hear from UL alumni

Narrative:
As a prospective undergraduate student
I want to hear stories from alumni about their time at UL 
So that I can decide if college is the right path for me to take 

Acceptance Criteria: 

Scenario 1: Mobile App "Book a Meeting" feature
Given as a prospective undergraduate student
Given that I have logged in to the mobile app 
When I click on the "Book a Meeting" option from the home page
And I choose the option for UL Alumni
Then I will be to book a slot with a former UL student 

Scenario 2: Website "Alumni" page
Given that the Virtual Open Day webpage has loaded
When I click on the "Alumni" option from the webpage
Then I will be able to find see short pieces from UL alumni

Scenario 3: Mobile App "Book a Meeting" feature - slot not available
Given that I have logged in to the mobile app 
When I click on the "Book a Meeting" option from the home page
And I choose the option for UL Alumni
And I choose a free slot
Then I get an error message saying "Slot already taken"