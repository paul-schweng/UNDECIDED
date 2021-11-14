Feature: Edit Profile
as a User
I want to be able to change my profile attributes.

	Background:
		Given following attributes:
			| attribute       | required |
			| profile picture | false    |
			| banner picture  | true     |
			| description     | false    |
			| name            | true     |
		Given user is logged in
		And user is on their profile page
		And the button for "edit profile" is clicked


	Scenario: successfully changed profile attributes
		When user changes any attributes
		And user saves changes
		Then changes are sent to server
		And DB is updated
		And back to profile page
		Then wait 1 second
		And request user details from server
		And update the user details in the client

	Scenario: no profile attribute changes
		When user presses cancel button
		Then discard any entered changes
		And back to profile page

	Scenario: invalid profile attribute changes
		When user leaves a required attribute empty
		Then save changes button is disabled
		And the corresponding input field is highlighted
