# Food App
This is the documentation for the food ordering application, which includes a backend, a database, and a basic frontend. The application is implemented 
using Spring Boot and runs on the Tomcat server.
# System Requirements
Before running the application on Docker, make sure your system meets the following requirements:
Docker: https://www.docker.com/
# Running the Application on Docker
1. Clone the Repository
2. Build Docker Image<br>
docker build -t food_app .
3. Run the Container<br>
docker run --name food_app -p 8190:8190  food_app  
5. Open the Application in Your Browser 
http://localhost:8190/foodApp
# Logging into the Application
On the login page, enter your username and password to log in.<br><br>
Customers:<br><br>
Username: customerJohn <br>
Password: test <br><br>
Username: customerEmily <br>
Password: test <br><br>
Restaurant Owners:<br><br>
Username: ownerJames <br>
Password: test <br><br>
Username: ownerOlivia <br>
Password: test <br><br>
Username: ownerSophia <br>
Password: test <br><br>
Username: ownerWilliam <br>
Password: test <br><br>
Username: ownerAva <br>
Password: test <br><br>
Username: ownerJoseph <br>
Password: test <br><br>

# Swagger API Documentation
The application provides an interactive Swagger API, which allows browsing and testing the available endpoints. After launching the application, the Swagger API will be accessible at: http://localhost:8190/foodApp/swagger-ui/index.html

# External API Usage
In this application, we use an external API to download recipes. Before launching the application, make sure that you are connected to the Internet and that the external API is available and properly configured. Api link: https://rapidapi.com/rapihub-rapihub-default/api/the-mexican-food-db/
# Overview
The food ordering application allows customers to browse and order food from various restaurants. It provides features for user authentication and account management.
Customers can create an account, log in, browse restaurants, place food orders, and cancel orders. Owners of the restaurants can log in, manage their menus, and fulfill 
and edit orders. The application ensures the security of user data and implements basic access control.
