
Requirements:

For building and running the application you need:
JDK 1.8
Maven 3

Running the application locally:

There are several ways to run a Spring Boot application on your local machine.
One way is to execute the main method in the \drones\DronesApplication.java class from your IDE.

Alternatively you can use the Spring Boot Maven plugin like so:
mvn spring-boot:run
navigate your browser to http://localhost:8080/

Instructions:

mvn package

To create a docker image packaging an existing jar
mvn package
docker build -f Dockerfile -t drones .  


To run the docker image
docker run -p 8080:8080 drones

Test APIs URL Samples:

Register Drone: 
  URL: http://localhost:8080/api/registerDrone
  
  Type: POST
  
  Body: 
   {
  "id":1,
  "serialNumber": 1,
  "model": "test",
  "weightLimit":500,
  "batteryCapacity":0.6,
  "state": "U"
 }
 
------------------------------------
Get Drone By ID:

  URL:http://localhost:8080/api/getDroneById/1
  
  Type: GET

------------------------------------
Update Drone:

 URL:http://localhost:8080/api/updateDrone/1
 
 Type:PUT
 
 Body: 
 {
  "serialNumber": 1,
  "model": "test",
  "weightLimit":500,
  "batteryCapacity":0.6,
  "state": "U"
 }

-------------------------------------
Get Drone Battery:

 URL: http://localhost:8080/api/getbatteryDrone/1
 
-------------------------------------
Load Drone:
   URL:http://localhost:8080/api/loadDrone/1
   Type: PUT
   Body: 
     [
    {
      "id": 1,
      "name": "test1",
      "weight": 100,
      "code":"test1",
      "image":"test1",
       "droneId": 1    
    },
    {
      "id": 2,
      "name": "test2",
      "weight": 100,
      "code":"test2",
      "image":"test2",
      "droneId": 1
    },
    {
      "id": 3,
      "name": "test3",
      "weight": 100,
      "code":"test3",
      "image":"test3",
      "droneId": 1
    }
]

------------------------------------------------------------------------
