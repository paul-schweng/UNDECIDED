Feature: Rate
  as a User
  I want to rate a product and upload a picture

  Background:
    Given is logged in
    Given rate is clicked

  Scenario: rate with picture
    When upload button is clicked
    Then open filebrowser
    And choose picture
    When enter product field is focused
    Then enter product
    When enter rating field is focused
    Then enter rating
    When post rating button is clicked
    Then post rating

  Scenario: rate without picture and tag friends
    When enter product field is focused
    Then enter product
    When enter rating field is focused
    Then enter rating
    When tag friends button is clicked
    Then choose friend to tag
    When post rating button is clicked
    Then post rating

  Scenario: enter product that is not in DB
    When enter product field is focused
    Then enter product
    When product is not found
    Then create new product in DB
    When enter rating field is focused
    Then enter enter rating
    When post rating button is clicked
    Then post rating