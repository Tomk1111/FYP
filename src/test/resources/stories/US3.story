Narrative:
As a travel manager
I want to check travel authorizations
So that I can ensure the confirmed bookings



Scenario: Listing travel authorizations
Given As a travel manager
Given I go to the tab “Travel Authorization”
When I type the “Booking Reference”
And I check if the request is well registered
Then at this time, I can know for sure (or not) the request has been taken into account
And it’s shown a tab: authorized / non-authorized