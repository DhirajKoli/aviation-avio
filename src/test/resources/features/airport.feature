Feature: Airport feature

  Scenario: A user searchers for a Airport
    Given the airport id APT-FO61-WBTO
    When the user requests airport data
    Then airport data is returned