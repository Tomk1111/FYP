Title: Virtual Open Day Enrolment

Narrative:
As a prospective student
I want to find out how to enrol for the virtual open day 
so that I can attend it.

Acceptance Criteria:

Scenario1: Desktop website - Virtual Open Day Enrolment
Given as a prospective student
Given that I am on the Virtual Open Day desktop site 
When I click on the Enrol link
Then I am brought to form to book a place for the key events and receive digital information.

Scenario2: Desktop website - Virtual Open Day Enrolment - successful enrolment
Given that I am on Enrol form page
When I supply my name, email address and year of graduation
And my year of graduation is the next academic year
Then I am informed that my place for Event X, Y, Z at times A, B, C was successfully reserved

Scenario3: Desktop website - Virtual Open Day Enrolment - unsuccessful enrolment
Given that I am on Enrol form page
When I supply my name, email address and year of graduation
And my year of graduation is NOT in the next academic year
Then I am informed that the open day is specifically for prospective students in the coming academic year
And asked to return next year.

Scenario4: Desktop website - Virtual Open Day Enrolment - optional events following successful enrolment
Given that I have successfully submitted my enrolment form
If I click on "Reserve other optional events"
Then I am brought to a page where I can select optional reservations for faculty specific events
