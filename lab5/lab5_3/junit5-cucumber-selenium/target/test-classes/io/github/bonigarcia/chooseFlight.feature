Feature: Choose a flight on BlazeDemo website

  Scenario: Navigate to website and choose departure and destination
    When I navigate to "https://blazedemo.com/" website
    And I choose "San Diego" as the departure city
    And I choose "London" as the destination city
    And I click on "Find Flights" button
    Then I should see the "Flights from San Diego to London:" page
    Then I choose the flight option number 2
    And I should see "Your flight from TLV to SFO has been reserved." on my screen
