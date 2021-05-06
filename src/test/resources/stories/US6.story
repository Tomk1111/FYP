Narrative:
As a researcher
I want to get a list of compatible flights (including their rates and times) in accordance with my search criteria
So that I can select a suitable flight based on my needs.

Scenario: Select a Return Flight Searched Without Full Options

Successful Roundtrip Tickets Search
Given As a researcher
Given "Availability Page" is displayed
When I click on "No Bag" referring to "Air France 7519"
And I click on "No Bag" referring to "Air France 7522"
When I click on "Book"
Then will be displayed "J'accepte les Conditions d'achat concernant le(s) tarif(s) aérien(s)."


Scenario: Select a Return Flight Searched With Full Options

Successful Roundtrip Tickets Search With Full Options
Given "Availability Page" is displayed
When I click on "No Bag" referring to "Air France 7519"
And I click on "No Bag" referring to "Air France 7522"
When I click on "Book"
Then will be displayed "J'accepte les Conditions d'achat concernant le(s) tarif(s) aérien(s)."


Scenario: Select a One-way Flight

Successful One-way Tickets Search
Given "Availability Page" is displayed
When I click on "No Bag" referring to "Air France 7519"
And I click on "Book"
Then will be displayed "J'accepte les Conditions d'achat concernant le(s) tarif(s) aérien(s)."


Scenario: Select a Multidestination Flight

Successful Multidestination Tickets Search
Given "Availability Page" is displayed
When I click on "No Bag" referring to "Air France 7519"
!-- And I click on "No Bag" referrizng to "Air France 7700"
And I click on "Book"
Then will be displayed "J'accepte les Conditions d'achat concernant le(s) tarif(s) aérien(s)."
