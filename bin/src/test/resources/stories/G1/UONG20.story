Title: International Student Talk

Narrative:
As a visitor
I want to find out where the concert hall is 
So I can attend the talk for international students

Acceptance Criteria: 

Scenario 1: Mobile App "Getting Around" feature
Given As a visitor
Given that I have logged in to the mobile app 
When I click on the "Getting Around" option from the home page
Then the I can find the Concert hall by following the map

Scenario 2: Mobile App "Getting Around" feature - UL Buildings fail to load
Given that I have logged in to the mobile app 
When I click on the "Getting Around" option from the home page
And the map loads without any of UL's buildings showing
Then I cannot see where the Concert Hall is on the map

Scenario 3: Using the "About UL" webpage
Given that the About UL webpage has loaded 
When I click on the UL Campus Map link
Then I can see where on the campus the Concert Hall is

Scenario 4: Using the "Contact Us" webpage
Given that the Contact Us webpage has loaded 
When I click on the contact number for Information Services
Then I can ask someone where the concert hall is in UL











