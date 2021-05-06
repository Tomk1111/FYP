Title: Sports Scholarship information for prospective students

Narrative:
As a high level sports player,
I wish to know about sports scholarships for my sport
so that I may apply.

Scenario 1: Desktop user interested in text information
Given As a high level sports player
Given that I am on the "Study At UL" page of the desktop website
When I click on the "Learn more about our sports facilities and scholarships" link
Then the "Sports facilities and scholarships" page is displayed directly

Scenario 2: Desktop user interested in video overview
Given that I am on the "Study At UL" page of the desktop website
When I click on the "Vid 1" video link
Then I am taken to a short video showcasing the facilities and the scholarships available

Scenario 3: User interested in an available scholarship, found by browsing
Given that the "Sports facilities and scholarships" page is displayed
And a scholarship is available for my chosen sport
When I click on that sport
Then the details of the scholarship available are displayed

Scenario 4: User interested in an available scholarship, found by searching
Given that the "Sports facilities and scholarships" page is displayed
And I enter a sport for which there is a scholarship ("Karate") in the search bar
When I click search
Then the details of the scholarship available are displayed

Scenario 5: User searching for a sport for which no scholarship is available
Given that the "Sports facilities and scholarships" page is displayed
And I enter a sport for which there is no scholarship ("darts") in the search bar
When I click search
Then "Sorry, no scholarships were found for your search term" is displayed

