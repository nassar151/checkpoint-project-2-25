To run the application, follow these steps:  

Set up the database:  
Log in to MySQL.
Run the script located in the file script database. This script will create the student, course, and enrollment tables.

Check the server port:  
Open the file checkpointApplication/src/main/resources/application.yml.
Check the value of server.port. For example, if it is set to 6060, you will use this port in your Postman calls.


Run the application:  
Use the command ./mvnw spring-boot:run in your IDE (IntelliJ IDEA) or your local terminal.


Set up Postman:
Copy the Postman collection from the file admin-student-login request.postman_collection.json.
Import the collection into Postman by using Import -> Raw text -> Paste.
Change the port in any request to match the port specified in application.yml (e.g., 6060).
For example, update the URL to http://localhost:6060/admin/allStudents.


