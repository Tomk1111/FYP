User Story: Confirm Flight Selection

Narrative:
As a IRIT researcher
I want to get all the required data to confirm my flights
So that I can check the information, the fare rules and then finalize my booking.

Scenario: Confirm a Flight Selection

Select a Return Flight Searched Without Full Options
Given "Confirmation Page" is displayed
When I choose "J'accepte les Conditions d'achat concernant le(s) tarif(s) aérien(s)."
And I click on "Finalize the trip"
Then will be displayed "Votre voyage a été confirmé!"

Scenario: Confirm a Flight Selection (Full Version)

Select a Return Flight Searched With Full Options
Given "Confirmation Page" is displayed
When I choose "I accept the General Terms and Conditions."
And I click on "Finalize the trip"
Then will be displayed "Votre voyage a été confirmé!"

Scenario: Confirm a Flight Selection for a One-Way Trip

Select a One-way Flight
Given "Confirmation Page" is displayed
When I choose "J'accepte les Conditions d'achat concernant le(s) tarif(s) aérien(s)."
And I click on "Finalize the trip"
Then will be displayed "Votre voyage a été confirmé!"

Scenario: Confirm a Flight Selection for a Multidestination Trip

Select a Multidestination Flight
Given "Confirmation Page" is displayed
When I choose "J'accepte les Conditions d'achat concernant le(s) tarif(s) aérien(s)."
And I click on "Finalize the trip"
Then will be displayed "Votre voyage a été confirmé!"

Scenario: Decline a Flight Selection

Select a One-way Flight
Given "Confirmation Page" is displayed
When I choose "J'accepte les Conditions d'achat concernant le(s) tarif(s) aérien(s)."
And I click on "Decline the trip"
Then will be displayed "Votre voyage a été annulé!"