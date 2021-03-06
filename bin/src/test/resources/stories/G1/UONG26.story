Title: Restaurants and Cafe information 

Narrative: As a physical attendee, I wish to be able to able to easily find cafes and restaurants near me so that I can eat/drink and discuss the open day with friends.

Acceptance Criteria: 

Scenario 1: Restaurants by map
Given As a physical attendee
Given that I have the app open on my phone 
And that the map page is loaded 
When I check the show cafes/restaurants box 
Then a knife and fork symbol will appear on the map on any building that has a cafe/restaurant

Scenario 2: Restaurants by web list 
Given that the visiting UL webpage is loaded 
And I click on where to eat 
Then list of restaurants and cafes on campus will be loaded

Scenario 3: More Restaurant info by web list 
Given that a list of restaurants and cafes on campus is loaded 
And I click on a restaurant/cafe 
Then a webpage with more information about the cafe will be loaded

Scenario 4: App restaurant information 
Given that the I have the app open on my phone 
And I the map page is loaded 
And I have checked the show cafes/restaurants box
When I tap a knife and fork symbol on the map 
Then information about the cafe/restaurant will be shown 

Scenario 5: Web List Search
Given that that the list of cafes and restaurants is loaded 
And I enter the name of a cafe in the search bar at the top 
When I click search 
Then the list of cafes will change 
And only cafes with the word I searched in their name will appear 

Scenario 6: Wrong Web List Search
Given that that the list of cafes and restaurants is loaded 
And I enter the name of a cafe in the search bar at the top 
And there are no cafes with this name on campus
When I click search 
Then a message reading "There are no cafes/restaurants with this name" will be displayed.

Scenario 7: Refresh cafe list page 
Given that that the list of cafes and restaurants is loaded
And I enter the name of a cafe in the search bar at the top  
And I click search 
And the list of cafes is filtered 
When I refresh the page 
Then the original full list of cafes/restaurants will load






