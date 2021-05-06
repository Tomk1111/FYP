Narrative:
As a guest
I want airline tickets with defined time and flights
So that I can book tickets



Scenario: Searching demanded tickets
Given As a guest
Given I go to the site "SIMBAB/TRAVEL"
When I choose "the demanded flight (destination and times, TOULOUSE/PARIS, departure 7 a.m., return 7 p.m. at the same day)"
And I choose "type of traveler Guest"
And I search
Then several propositions
And I choose the desired flights

When I inform the data concerning the traveler (name, given name, birthdate, phone, mail), and eventually the loyalty card (Flying Blue and Season Ticket)
Then ticket waiting for validation
