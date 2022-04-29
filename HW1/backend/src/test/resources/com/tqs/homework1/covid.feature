Feature: Check statistics of a country
  Scenario: Navigate to the website and check the most recent statistics for the country Portugal
    When I navigate to "http://localhost:3000/"
    And I choose "Portugal" as the country I want to see
    Then I should see the "Statistics" modal
    And I should see the COVID data for the country "Portugal"

  Scenario: Navigate to the website and check the history data of the day 2022-04-15 for the country Australia
    When I navigate to "http://localhost:3000/"
    And I choose "Australia" as the country I want to see
    And I enter the From date as "2022-04-15"
    And I enter the To date as "2022-04-15"
    And I click the "See History" button
    Then I should see the "History" modal
    And I should see the COVID data for the country "Australia"
    And I should see the day "2022-04-15"

  Scenario: Navigate to the website and check the history data of Spain between 2022-02-07 and 2022-02-08
    When I navigate to "http://localhost:3000/"
    And I choose "Spain" as the country I want to see
    And I enter the From date as "2022-02-07"
    And I enter the To date as "2022-02-08"
    And I click the "See History" button
    Then I should see the "History" modal
    And I should see the COVID data for the country "Spain"
    And I should see the day "2022-02-07"
    And I should see the day "2022-02-08"