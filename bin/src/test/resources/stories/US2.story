Narrative:
As a frequent traveler
I want to search for tickets, providing locations and dates for a multi-destination trip
So that I can obtain information about rates and flight times


Scenario: Multi-destination searching
Given As a frequent traveler
Given I go to the page “Searching Flights”
When I choose “Multi-destinations”
And I type “Paris” and choose “Paris, Charles de Gaulle” in the field “Departure”
And I type “Rio de Janeiro” in the field “Destination”
And I choose “15/02/17” in the field “Departure Date”
And I choose “20/02/17” in the field “Return Date”
And I type “Rio de Janeiro” in the field “Departure”
And I type “Porto Alegre” in the field “Destination”
And I choose “17/02/17” in the field “Departure Date”
And I choose “19/02/17” in the field “Return Date”
And I click on “Search”
Then will be displayed the list of available flights