Feature: Rate
  as a User
  I want to rate a product and upload a picture

  Background:
    Given following rating attributes:
      | attribute     | required |
      | image         | false    |
      | product name  | true     |
      | product brand | false    |
      | product type  | true     |
      | product tags  | false    |
      | description   | false    |
      | stars         | true     |
      | friends       | false    |
    Given is logged in
    And new rating is clicked

  Scenario: post rating
    When user enters attributes
    And all required attributes are set
    Then post rating

  Scenario: discard rating
    When user clicks
    Then enter rating
    Then tag friends
    Then post rating

  Scenario: temporarily save rating
    When user clicks on dialog's backdrop
    Then save the created rating
    But do not post it
    And close the popup

  Scenario: post rating which you previously worked on
    When saved rating is found
    Then load the popup with these changes to the input fields

  Scenario: invalid rating attribute changes
    When user leaves a required attribute empty
    Then post button is disabled
    And the corresponding input field is highlighted
