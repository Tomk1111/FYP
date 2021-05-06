# Automated Assessment of Domain Models using BDD Story Parsing

## Thomas Kiely - 17185203

This project has been developed as part of my final year project with the objective of identifying the best heuristics for extracting domain models from NL requirements texts in the BDD (Behaviour Driven Development) User story notation. This tool aims to implement these identified heuristics to assess the consistensy of BDD User scenarios with created Class Diagrams.

## How to Run
The MyTest class is where the application is executed. This is a jUnit Test class, which executes a set of BDD User Story Step Scenarios using `JBehave`.
In `testAllStories()` the stories to be tested are added to the application, and the Behave Context executes `run()` to begin executing the steps.

In MySteps a generic `givenWhenThen()` method is defined with generic @Given, @When and @Then annotations. This allows for the single method to receive every BDD scenario automatically, without configuring the domain specific step definitions.

The `MyParser` class is where the scenario is passed to, and the extraction methods and heuristics are applied. This class makes use of the `StanfordCoreUtils` and `WordNetUtils` classes which use functionality from the `Stanford Core NLP` libraries and MIT's `WordNet` library. These provide extensive functionality for analysing natural texts.

The `Scenario` class is used to maintain information about the current scenario, including its dependencies and extractions throughout the analysis of the scenario.

The `SearchModel` class is used to search the defined class diagrams and assess the consistency of the extractions with the elements present in the diagrams.

Class Diagrams for the system being searched are to be designed using `PlantUML` which allows for the text based modelling of these diagrams. These files must be stored in the `/src/test/resources/classmodels` folder. 


**Note This project relies on the explicit definition of the User role within the story scenario and not just the narrative. This allows for further extractions and analysis when encountering personal pronouns such (I, my, etc.)
This can be done by providing a top level @Given annotation containing the "As a [ROLE]" definition.
Scenarios should always imply a specific user role like user stories in Agile, as different applications will contain domani specific user roles. 




## Research
This project is part of a broader approach aimed at ensuring the consistency between requirements and the domain models of a software system.
