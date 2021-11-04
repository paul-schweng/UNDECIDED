# 1 Use-Case Name
[Rate](../SRS.md#3110-rate)

## 1.1 Brief Description
A user is able to rate a product by writing a review and uploading an optional picture of the product.

# 2 Flow of Events
## 2.1 Basic Flow
- User clicks on "rate" button
- User can upload an optional picture
- User chooses product they want to rate
- User enters their rating
- User is able to tag a friend if he wants to

### 2.1.1 Activity Diagram
![UC Activity Diagram](Rate.svg)

### 2.1.2 Mock-up
![](ADD-POST.png)

### 2.1.3 Narrative
```gherkin
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
```

## 2.2 Alternative Flows
(n/a)

# 3 Special Requirements
(n/a)

# 4 Preconditions
## 4.1 Login
The user has to be logged in to the system.

# 5 Postconditions
(n/a)

# 6 Extension Points
(n/a)
