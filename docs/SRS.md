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
  - [---](#---)
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
	
    This Software Requirements Specification (SRS) describes all specifications for the application "UNDECIDED" and creates an overview 
    about this project including its vision, detailed information about the planned features as well as functions and boundary conditions of the development process.	
		
## 1.2 Scope 
	
    UNDECIDED will be realised as a webapp and its actors will be private users, business users or administrators. 
    
    Subsystems:
    
        - Authentication
        - Ratings
        - Profile
        - Business Functions
		
	
## 1.3 Definitions, Acronyms & Abbreviations
	
    Welche Abkürzungen / allgemein fremde Begriffe verwenden wir? -> definition hier  
	
	
## 1.4 References
	
    //hier bloglink
    //hier github link
	
## 1.5 Overview
	
    The next chapters provide information about our vision based on the overall use case diagram (OUCD) as well as a more detailed requirements specification in terms of functionality, usability and design.

---
[nach oben](#inhalt)

---
---
		
# 2. Overall Description	

## 2.1 Product Perspective 
	
    The goal of our project UNDECIDED is to build a webapp solution that allows people to rate food and drinks they have consumed in a restaurant or have bought in the supermarket in order to help others decide what to order or buy. With that the users will be able to help each other deciding and to make better decisions themselves. 

>For further information on our vision see our first blogpost: https://uundecided.wordpress.com/2021/10/10/thebeginning/
	 
## 2.2 Product Functions 
	
		HIER USE CASE DIAGRAM einfügen 
		
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
	
    3.1.1 register
    3.1.2 login
    3.1.3 delete account
    3.1.4 logout
    3.1.5 add profile picture
    3.1.6 edit profile
    3.1.7 view my ratings
    3.1.8 show friends
    3.1.9 show history
    3.1.10 rate
    3.1.11 delete rating 
    3.1.12 comment rating
    3.1.13 upvote rating
    3.1.14 edit comment
    3.1.15 tag friends 
    3.1.16 insert product 
    3.1.17 delete products 
    3.1.18 verify
    
    3.1.1 register
    3.1.2 login
    3.1.3 delete account
    3.1.4 logout
    3.1.5 add profile picture
    3.1.6 edit profile
    3.1.7 view my ratings
    3.1.8 show friends
    3.1.9 show history
    3.1.10 rate
    3.1.11 delete rating 
    3.1.12 comment rating
    3.1.13 upvote rating
    3.1.14 edit comment
    3.1.15 tag friends 
    3.1.16 insert product 
    3.1.17 delete products 
    3.1.18 verify
		
## 3.2 Usability
	
    We will build the user interface intuitive, so that a new user does not necessarily need an explanation. If questions arise our interface provides a comprehensive FAQ. If the user doesn't know the principle of flashcards and a system to learn with them, the user interface provides a manual how to learn with flashcards as well.
	
## 3.3 Reliability
    3.3.1 Availability
    3.3.2 Defect Rate
    3.3.3 MTBF, MTTR
    3.3.3 Accuracy
    3.3.4 Bug classes

## 3.4 Performance
    In general, we try to keep to user experience fluent and response times low. 
    3.4.1 Response time
    3.4.2 Throughput
    3.4.3 Capacity
    3.4.4 Resource utilization

## 3.5 Supportability
    Bsp: Our frontend, backend and each functionality will be clearly separated and we try to stick to naming conventions which are common in the used technologies. Furthermore we aim to keep our code clean which we can't guarantee though. Thereby we make it easy to understand our infrastructure and avoid possible confusion when one needs to edit older parts of the application.
    CODING STANDARDS
    TESTING STRATEGY

## 3.6 Design Constraints
	Bsp: We are focused on building a modern-looking application using modern technologies. Of course there are other smaller libraries and frameworks used than the ones that are listed, but they represent just a small fraction of the whole project and aren't worth mentioning. Especially in ReactJS one does add a lot of external modules.
		
## 3.7 Online User Documentation & Help System Requirements
	
    The user interface will be intuitive and simple so that the user can navigate easily between the different sections. Futhermore it is planned that there will be a FAQ and a help page, where the basic steps for using the webapp will be presented and explained step-by-step. 
	
## 3.8 Purchased Components 
	
	N\A
	
## 3.9 Interfaces 
    3.1.9 User Interfaces 
    3.9.2 Hardware Interfaces 
    3.9.3 Software Interfaces 
    3.9.4 Communication Interfaces 

## 3.10 Licensing Requirements
## 3.11 Legal, Copyright & Other Notices 
## 3.12 Applicable Standards 

---
[nach oben](#inhalt)

---
---
# 4. Supporting Information 	
