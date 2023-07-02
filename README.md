# DataPixie: Unleash Knowledge, Uncover Opportunities.
This repository stores my first ever project using Java. It is a desktop based GUI, backed with Web scrapping algorithm, that scrapes the courses from youtube and udemy (as of now) and sorts them, based on user preference and stores in SQL as well as show case them on a GUI.

# How to use it:
1. Pre-requisite is that the java files are already present in local repo, and config (pom.xml dependencies etc) are set up.
2. Set up and load the SQL server via JDBC connector. (Here MySQL 8.0 is used)
3. You would also need to register to API client 2.0 Affiliate API Program by Udemy and a reliable Youtube API. Registration will get you the public key to access
the courses and their details.
4. Some files are therfore hidden from other developers, you will have to create the requests and add appropriate headers to the requests and use them.
5. We are good to try the App.

# SOP:
1. First query has to be given in the search bar.
2. Press the search button (it is a custom Jbutton class, doesn't give look and feel of a button, I know!)
3. Then Apply the sorting feature. (Optional)
4. Results will start displaying using the "Next" and "Previous" options in the content panel.

# Upcoming features:
1. Websites other than Youtube and Udemy to be incorporated in next release. 
2. Java Multithreading to be added for processing the requests parallelly and also visit all pages of a website.
3. Front end GUI to be made more aesthetic and good looking. JavaFX with Scenebuilder to be used.


Adding one snapshot for the viewers for high level understanding of the project!
![image](https://github.com/abhayjohri23/SkillScraper/assets/124622368/33853f6b-8ea0-4c60-b433-9f39671ec677)

Feedbacks will always be apreciated!
