# 1 Use-Case Name
[Edit Profile](../SRS.md#316-edit-profile)

## 1.1 Brief Description
A user is able to make changes to his profile attributes, like his name or email etc.
# 2 Flow of Events
## 2.1 Basic Flow

- User clicks on "edit profile" button
- User changes either 
    - his email
    - his description
    - his name
    - his new password
    - his profile picture
    - nothing
- User clicks on "save changes" button or "cancel" button 

### 2.1.1 Activity Diagram
![Organization Application Activity Diagram](editProfile.svg)

### 2.1.2 Mock-up
![](EDIT-PROFILE.png)

### 2.1.3 Narrative
```gherkin
Feature: Edit Profile
as a User
I want to be able to change my profile attributes.

	Background:
		Given The User is logged in
		Given The User is on his profile page
		Given The button for "edit profile" is clicked 

	Scenario: successfully changed profile attributes
		When changes description
		Then enter new description
		When changes name
		Then enter new name
		When change profile picture
		Then enter new profile picture
		When change banner picture
		Then enter new banner picture
		Then save changes
		And back to profile page

	Scenario: no profile attribute changes
		When the user presses cancel button
		Then back to profile page

	Scenario: invalid profile attribute changes
		When name is empty
		Then enter new name
		Then save changes
		And back to profile page
```

## 2.2 Alternative Flows
(n/a)

# 3 Special Requirements
(n/a)

# 4 Preconditions
## 4.1 Login
The user has to be logged in to the system.

## 4.2 Profile Page
The user has to be on his profile page.

# 5 Postconditions
(n/a)

# 6 Extension Points
(n/a)
