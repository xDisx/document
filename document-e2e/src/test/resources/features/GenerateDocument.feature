Feature: Generate a document

  Scenario: Generate new document
    When I send a request to generate a new document
    Then I receive the generated document