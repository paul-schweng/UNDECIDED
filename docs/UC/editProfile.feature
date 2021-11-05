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
