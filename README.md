# FileProcessor-Web
Web app for processing uploaded csv files and saves in MySQL db
* To run this web app go to application.yml in the resources folder and update the db password with your MySQL login details:
*  Set the db url, username and password as follows:
- url: jdbc:mysql://localhost:3306/tulaa
- username: root
- password: r00t
* NB: Make sure to create tulaa db in the database
* To run the project in the command line execute: mvn spring-boot:run . This assumes that you already have maven installed in your computer
* Alternatively import the project using Eclipse IDE, right click on the project and in the context menu, run as spring boot app
* By default this project runs in port 8080. You can change the port in application.yml in server : port: property
