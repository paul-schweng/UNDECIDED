# Inhalt
- [Inhalt](#inhalt)
- [1. Introduction](#1-introduction)
  - [1.1 Purpose](#11-purpose)
  - [1.2 Scope](#12-scope)
  - [1.3 Definitions, Acronyms & Abbreviations](#13-definitions-acronyms--abbreviations)
  - [1.4 References](#14-references)
  - [1.5 Overview](#15-overview)
- [2. Overall Description](#2-overall-description)
  - [2.1 Product Perspective](#21-product-perspective)
  - [2.2 Product Functions](#22-product-functions)
  - [2.3 User Characteristics](#23-user-characteristics)
  - [2.4 Assumptions and Dependencies](#24-assumptions-and-dependencies)
- [3. Specific Requirements](#3-specific-requirements)
  - [3.1 Functionality](#31-functionality)
  - [3.2 Usability](#32-usability)
  - [3.3 Reliability](#33-reliability)
  - [3.4 Performance](#34-performance)
  - [3.5 Supportability](#35-supportability)
  - [3.6 Design Constraints](#36-design-constraints)
  - [3.7 Online User Documentation & Help System Requirements](#37-online-user-documentation--help-system-requirements)
  - [3.8 Purchased Components](#38-purchased-components)
  - [3.9 Interfaces](#39-interfaces)
  - [3.10 Licensing Requirements](#310-licensing-requirements)
  - [3.11 Legal, Copyright & Other Notices](#311-legal-copyright--other-notices)
  - [3.12 Applicable Standards](#312-applicable-standards)
- [4. Supporting Information](#4-supporting-information)

---
# 1. Introduction

## 1.1 Purpose 
	
This Software Requirements Specification (SRS) describes all specifications for the application "UNDECIDED" and creates an overview about this project including its vision, detailed information about the planned features as well as functions and boundary conditions of the development process.	
		
## 1.2 Scope 
	
UNDECIDED will be realised as a webapp and its actors will be private users, business users or administrators. 

Subsystems:

  - Authentication:  
In our authentification process a guest can register to become a user. During the registration process a guest is able to choose whether they are a normal or business user. After that a user can login to get access to the website. A user can also logout or delete their account. If a user forgets their password they can reset it.  

  - Ratings:  
A user can create and post ratings. These ratings can be edited or deleted by the user or upvoted and commented on by other users. Those comments can again be edited by the user who posted the comment. When creating a rating a user tag their friends in it.

  - Profile:  
A distinction is made between a user profile and a business user profile. A normal user can add and edit their profile data including a profile picture. Further it is possible to view the own ratings that were made. A profile also has a list of friends which were added. In contrast to a user a business user can only add profile data and edit it.

  - Business Functions:  
As a business user they have access to business-only functions. They can insert their own products with product pictures and also delete them. They can use tags for their product like 'vegan' or 'glutenfree'. After they insert the product they can also change it. They can request verification so the users know that they are the original restaurant/shop.

  - Admin Panel:  
An admin can use their admin panel to block (business) users and administrate the website meaning they are able to delete ratings or comments.
			
## 1.3 Definitions, Acronyms & Abbreviations
	
| Abbrevation     | Explanation                         |
|-----------------|-------------------------------------|
| SRS             | Software Requirements Specification |
| UC	          | Use Case                            |
| n/a	          | not applicable                      |
| tbd	          | to be determined                    |
| UCD	          | Use Case Diagram                    |
| FAQ	          | Frequently asked Questions          |
| CI              | continuous integration              |
| CD              | continuous deployment               |
| IDE             | integrated development environment  |
| DB              | database				|
	

## 1.4 References
| Title							                                  | Date	  | Publishing organization   |
| --------------------------------------------------------------------------------------- | ------------- | ------------------------- |
| [UNDECIDED Blog](https://uundecided.wordpress.com)			  | 21.10.2021	  | UNDECIDED Team	      | 
| [GitHub](https://github.com/paul-schweng/UNDECIDED)					  | 21.10.2021    | UNDECIDED Team            |
| [Jira](https://diesdasundso.atlassian.net/jira/software/c/projects/NDCDD/boards/4?atlOrigin=eyJpIjoiNjIzYmEwNzk3YzNiNDI5ZjkyZjMzNTI1NTJmOTZkNDciLCJwIjoiaiJ9) | 21.10.2021 | UNDECIDED Team |
	
## 1.5 Overview
	
The next chapters provide information about our vision based on the UCD as well as a more detailed requirements specification in terms of functionality, usability and design.

---
[nach oben](#inhalt)

---
---
		
# 2. Overall Description	

## 2.1 Product Perspective 
	
The goal of our project UNDECIDED is to build a webapp solution that allows people to rate food and drinks they have consumed in a restaurant or have bought in the supermarket in order to help others decide what to order or buy. With that the users will be able to help each other deciding and to make better decisions themselves. 

>For further information on our vision see our [first blogpost](https://uundecided.wordpress.com/2021/10/10/thebeginning/).
	 
## 2.2 Product Functions 
	
![UCD](./UseCaseDiagram.jpg)
		
## 2.3 User Characteristics 

Generally there are two types of users for UNDECIDED. People all over the world that are searching for a way to improve their decisions concerining food and drinks and businesses that would like to insert pictures of their products. Further than that there are no restrictions to the target groups but it is realistic that the younger generation will most probably use the application more than older generations that might not be as familiar with modern technology.
	
## 2.4 Assumptions and Dependencies 
	
Backend: Spring Framework
Frontend: Angular
Database: Maria DB
IDE: IntelliJ
Version Control: Git
Repository Platform: GitHub
CI/CD: Docker & Jenkins 
Mockups: Zeplin & Figma 
Scrum-Platform: JIRA

---
[nach oben](#inhalt)

---	
---
# 3. Specific Requirements

## 3.1 Functionality
	
- [3.1.1 register](#311-register)
- [3.1.2 login](#312-login)
- [3.1.3 delete account](#313-delete-account)
- [3.1.4 logout](#314-logout)
- [3.1.5 add profile picture](#315-add-profile-picture)
- [3.1.6 edit profile](#316-edit-profile)
- [3.1.7 view my ratings](#317-view-my-ratings)
- [3.1.8 show friends](#318-show-friends)
- [3.1.9 show history](#319-show-history)
- [3.1.10 rate](#3110-rate)
- [3.1.11 delete rating](#3111-delete-rating)
- [3.1.12 comment rating](#3112-comment-rating)
- [3.1.13 upvote rating](#3113-upvote-rating)
- [3.1.14 edit comment](#3114-edit-comment)
- [3.1.15 tag friends](#3115-tag-friends)
- [3.1.16 insert product](#3116-insert-product)
- [3.1.17 delete products](#3117-delete-products)
- [3.1.18 verify](#3118-verify)

### 3.1.1 register
In the authentification process a guest and a business user have to register to get acces to the website. A guest becomes a user by creating a account.

### 3.1.2 login
The website will provide the possibility to log in. This will also make the usability easier when a user wants to manage his sessions, post or join a session because they don't have to enter their mail address every time.

### 3.1.3 delete account
A user and a business user is able to delete his account and thus the ability to delete their data. 

### 3.1.4 logout
In case you share your phone, have multiple accounts or just want to be cautius about your privacy you should be able to manually log out.

### 3.1.5 add profile picture
A (business) user can add/delete a profile picture.

### 3.1.6 edit profile
A (business) user can edit their profile.

### 3.1.7 view my ratings
A user can see a list of their posted ratings.

### 3.1.8 show friends
A user can see a list of who they are following/who follows them.

### 3.1.9 show history
A user can see their statistics, e.g. number of friends/posted ratings/...

### 3.1.10 rate
A user can post a rating on a drink/meal/restaurant/...

### 3.1.11 delete rating 
A user can delete their rating.

### 3.1.12 comment rating
A user can comment a rating.

### 3.1.13 upvote rating
A user can upvote a rating.

### 3.1.14 edit comment
A user can edit their comment on a rating.

### 3.1.15 tag friends 
A user can tag their friends in a rating.

### 3.1.16 insert product 
As a business user you can add products to your account.

### 3.1.17 delete products 
As a business user you can delete your inserted products.

### 3.1.18 verify
A business user can request a verification for his account, which gives them a blue verification badge. The verification request must be approved by an admin.
		
## 3.2 Usability
	
We will build the user interface intuitive, so that a new user does not necessarily need an explanation. If questions arise our interface provides a comprehensive FAQ. If the user doesn't know the principle of flashcards and a system to learn with them, the user interface provides a manual how to learn with flashcards as well.
	
## 3.3 Reliability
tbd
### 3.3.1 Availability
### 3.3.2 Defect Rate
### 3.3.3 MTBF, MTTR
### 3.3.3 Accuracy
### 3.3.4 Bug classes

## 3.4 Performance
In general, we try to keep to user experience fluent and response times low. 
tbd
### 3.4.1 Response time
### 3.4.2 Throughput
### 3.4.3 Capacity
### 3.4.4 Resource utilization

## 3.5 Supportability
Bsp: Our frontend, backend and each functionality will be clearly separated and we try to stick to naming conventions which are common in the used technologies. Furthermore we aim to keep our code clean which we can't guarantee though. Thereby we make it easy to understand our infrastructure and avoid possible confusion when one needs to edit older parts of the application.
CODING STANDARDS
TESTING STRATEGY
tbd

## 3.6 Design Constraints
We are focused on building a modern-looking web-application using modern technologies. Of course there are other smaller libraries and frameworks used than the ones that are listed, but they represent just a small fraction of the whole project and aren't worth mentioning. In order to meet the requirements and wished of our team we conducted a survey which shows the following results.

![Word Cloud](./word_cloud.png)

## 3.7 Online User Documentation & Help System Requirements
	
The user interface will be intuitive and simple so that the user can navigate easily between the different sections. Futhermore it is planned that there will be a FAQ and a help page, where the basic steps for using the webapp will be presented and explained step-by-step. 
	
## 3.8 Purchased Components 
	
N\A
	
## 3.9 Interfaces 
tbd
### 3.1.9 User Interfaces 
### 3.9.2 Hardware Interfaces 
### 3.9.3 Software Interfaces 
### 3.9.4 Communication Interfaces 

## 3.10 Licensing Requirements
tbd

## 3.11 Legal, Copyright & Other Notices 
The logo is licensed to the UNDECIDED Team and is only allowed to use for the application. We do not take responsibilty for any incorrect data or errors in the application.

## 3.12 Applicable Standards   
tbd
---
[nach oben](#inhalt)

---
---
# 4. Supporting Information  	
For any further information you can contact the UNDECIDED team or check our [UNDECIDED Blog](https://uundecided.wordpress.com). 
The Team Members are:
- Elisa Schäfer
- Paul Schweng
- Krystian Schmidt
- Christian Geier
