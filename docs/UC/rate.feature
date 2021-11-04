Feature: Rate
  as a User
  I want to rate a product and upload a picture

  Background:
    Given is logged in
    Given rate is clicked

  Scenario: rate with picture
    Then upload picture
    Then enter product
    Then enter rating
    Then post rating

  Scenario: rate without picture and tag friends
    Then enter product
    Then enter rating
    Then tag friends
    Then post rating