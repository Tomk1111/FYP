Title: Book a meeting slot

Narrative:
As a prospective student
I want to book a meeting slot with someone from the ECE department
So I can find out the different modules and learning outcomes

Acceptance Criteria: 

Scenario 1: Mobile App "Book A Meeting" feature
Given As a prospective student
Given that I have logged in to the mobile app 
When I click on the "Book a meeting" option from the home page
Then I will be able to book a meeting slot with a lecturer/alumni

Scenario 2: Select who to book a meeting with (success)
Given that I have logged in to the mobile app 
When I click on the "Book a meeting" option from the home page
And the list of available meeting slots is shown
Then I will be able to choose who to book a meeting with

Scenario 3: Select who to book a meeting with (fail)
Given that I have logged in to the mobile app 
When I click on the "Book a meeting" option from the home page
And the next screen fails to load
Then I cannot see who I can book a meeting with

Scenario 4: Website "Book A Meeting" feature
Given that the Virtual Open Day webpage is displayed 
When I click on the "Book a meeting" option from the webpage
Then I will be able to book a meeting slot with a lecturer/alumni