Title: Workload and Contact Hours query

Narrative:
As a prospective mature student,
I would like to find out the contact hours and workload for each course
so that I can plan how I might juggle college and a job

Scenario 1: Course found by department
Given as a prospective mature student
Given that I am on the desktop website
When I click on courses
And select the relevant department 
And select the course of interest to me
And the contact hours and workload are known for this course
Then the course information page will be displayed
And this info will include course contact hours and average, estimated workload

Scenario 2: Course found alphabetically
Given that I am on the desktop website
When I click on the alphabetical list of course
And select the course of interest to me
And the contact hours and workload are known for this course
Then the known course information page will be displayed
And this info will include course contact hours and average, estimated workload

Scenario 3: New course, listed on site but contact hours or workload are unknown
Given that I am on the desktop website
When I click on the course of interest to me
Then the known course information page will be displayed
And I will be directed to contact the course director for further information on the as yet unknown details

Scenario 4: New course, not found in the system
Given that I am on the desktop website
When I click on courses
And select the relevant department 
Then a contact person for that department will be listed at the bottom of the listed courses

