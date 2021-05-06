Narrative:
As a researcher
I want to be able to search air tickets for my business trips, providing destinations and dates
So that I can obtain information about rates and times of the flights.



Scenario: Successful Roundtrip Tickets Search
Proceed to Login
Reach the Travel Planet Search Page
Given As a researcher
Given I go to "Flight Search"
When I select "Round Trip"
And I inform "Toulouse" and choose "Toulouse, Blagnac (TLS)" in the field "Departure"
When I inform "Paris" and choose "Paris, Charles-de-Gaulle (CDG)" in the field "Destination"
And I set "Sam, Déc 1, 2018" in the field "Departure Date"
When I set "Lun, Déc 10, 2018" in the field "Arrival Date"
And I submit "Search"
Then will be displayed "2. Sélectionner un voyage"


Scenario: Successful Roundtrip Tickets Search With Full Options
Proceed to Login
Reach the Travel Planet Search Page
Given I go to "Flight Search"
When I inform "Toulouse" and choose "Toulouse, Blagnac (TLS)" in the field "Departure"
And I inform "Paris" and choose "Paris, Charles-de-Gaulle (CDG)" in the field "Destination"
When I set "Sam, Déc 1, 2018" in the field "Departure Date"
And I set "08:00" in the field "Departure Time Frame"
When I choose "Round Trip"
And I set "Lun, Déc 10, 2018" in the field "Arrival Date"
When I set "10:00" in the field "Arrival Time Frame"
And I choose the option of value "2" in the field "Number of Passengers"
When I set "6" in the field "Timeframe"
And I select "Direct Flights Only"
When I choose the option of value "Economique" in the field "Flight Class"
And I set "Air France" in the field "Company 1"
When I submit "Search"
Then will be displayed "2. Sélectionner un voyage"



Scenario: Successful One-way Tickets Search
Proceed to Login
Reach the Travel Planet Search Page
Given I go to "Flight Search"
When I inform "Toulouse" and choose "Toulouse, Blagnac (TLS)" in the field "Departure"
And I inform "Paris" and choose "Paris, Charles-de-Gaulle (CDG)" in the field "Destination"
When I set "Sam, Déc 1, 2018" in the field "Departure Date"
And I choose "One-way / Multidestination"
When I submit "Search"
Then will be displayed "2. Sélectionner un voyage"


Scenario: Successful Multidestination Tickets Search
Proceed to Login
Reach the Travel Planet Search Page
Given I go to "Flight Search"
When I choose "One-way / Multidestination"
And I inform "Toulouse" and choose "Toulouse, Blagnac (TLS)" in the field "Departure"
When I inform "Paris" and choose "Paris, Charles-de-Gaulle (CDG)" in the field "Destination"
And I set "Sam, Déc 1, 2018" in the field "Departure Date"
When I inform "Paris" and choose "Paris, Charles-de-Gaulle (CDG)" in the field "Departure"
And I inform "Nice" and choose "Nice, Côte D'Azur (NCE)" in the field "Destination"
When I set "Lun, Déc 10, 2018" in the field "Departure Date"
And I submit "Search"
Then will be displayed "2. Sélectionner un voyage"

Scenario: Search for Flights More Than One Year in Advance
Proceed to Login
Reach the Travel Planet Search Page
Given I go to "Flight Search"
When I inform "Toulouse" and choose "Toulouse, Blagnac (TLS)" in the field "Departure"
And I inform "Paris" and choose "Paris, Charles-de-Gaulle (CDG)" in the field "Destination"
When I set "Dim, Déc 1, 2019" in the field "Departure Date"
And I choose "One-way / Multidestination"
When I submit "Search"
Then will be displayed "Erreur : Vous devez choisir une date de départ ultérieure comprise entre 4 heures et 11 mois. Veuillez sélectionner une autre date. (10032)"


Scenario: Search for a Return Flight Before a Departure Flight
Proceed to Login
Reach the Travel Planet Search Page
Given I go to "Flight Search"
When I inform "Toulouse" and choose "Toulouse, Blagnac (TLS)" in the field "Departure"
And I inform "Paris" and choose "Paris, Charles-de-Gaulle (CDG)" in the field "Destination"
When I set "Lun, Déc 10, 2018" in the field "Departure Date"
And I choose "Round Trip"
When I set "Sam, Déc 1, 2018" in the field "Arrival Date"
And I submit "Search"
Then will be displayed "Erreur : La date de retour ne peut pas être antérieure à la date de départ."
