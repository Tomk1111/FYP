Title: Accessibility 

Narrative: As a wheelchair user, I wish to find out which talks will be accessible to me so I can attend them.


Acceptance Criteria: 

Scenario 1: Extra Open Day Event Information
Given as a wheelchair user
Given that the timetable of open day events is loaded 
When I hover over an event 
Then extra information about the event should be shown

Scenario 2: Open Day Event Information
Given that the timetable of open day events is loaded 
When I click on an event
Then event information page should be loaded

Scenario 3: Map Extras 
Given that a map of the college is loaded 
When I check the box labelled "Show accessibility"
Then a wheelchair icon should appear on all of the buildings which have wheelchair access

Scenario 4: Open Day Timetable
Given that the timetable of Friday open day events is loaded 
And that there are no events on Saturday
When I toggle the calendar to the next day
Then the calendar will be blank 
And the message "No events scheduled" will be displayed. 

Scenario 5: Booking Events 
Given that I have enrolled to attend the in person open day 
And that as part of the enrolment form I checked the "wheelchair user" box 
And that I am logged in to the app 
And the webpage of an event with no wheelchair access is loaded 
When I click book event 
Then a warning message reading "Unfortunately this event is not wheelchair accessible, Would you like to continue" is displayed. 
