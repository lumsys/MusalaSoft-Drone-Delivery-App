# MusalaSoft-Drone-Delivery-App

## Introduction
There is a major new technology that is destined to be a disruptive force in the field of transportation: the drone. Just as the mobile phone allowed developing countries to leapfrog older technologies for personal communication, the drone has the potential to leapfrog traditional transportation infrastructure.

Useful drone functions include delivery of small items that are (urgently) needed in locations with difficult access.

Task description
We have a fleet of 10 drones. A drone is capable of carrying devices, other than cameras, and capable of delivering small loads. For our use case the load is medications.

A Drone has:

serial number (100 characters max);
model (Lightweight, Middleweight, Cruiserweight, Heavyweight);
weight limit (500gr max);
battery capacity (percentage);
state (IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING).
Each Medication has:

name (allowed only letters, numbers, ‘-‘, ‘_’);
weight;
code (allowed only upper case letters, underscore and numbers);
image (picture of the medication case).
Develop a service via REST API that allows clients to communicate with the drones (i.e. dispatch controller). The specific communicaiton with the drone is outside the scope of this task.

The service should allow:

registering a drone;
loading a drone with medication items;
checking loaded medication items for a given drone;
checking available drones for loading;
check drone battery level for a given drone;
Feel free to make assumptions for the design approach.

Requirements
While implementing your solution please take care of the following requirements:

Functional requirements
There is no need for UI;
Prevent the drone from being loaded with more weight that it can carry;
Prevent the drone from being in LOADING state if the battery level is below 25%;
Introduce a periodic task to check drones battery levels and create history/audit event log for this.
Non-functional requirements
Input/output data must be in JSON format;
Your project must be buildable and runnable;
Your project must have a README file with build/run/test instructions (use DB that can be run locally, e.g. in-memory, via container);
Required data must be preloaded in the database.
JUnit tests are optional but advisable (if you have time);
Advice: Show us how you work through your commit history.
How to build
Requirements
Java 18
Java IDE (Intellij)
POSTGRESQL databse
Postman

## To replicate this app on local:
Clone the from the link git clone https://github.com/lumsys/MusalaSoft-Drone-Delivery-App.git

Go to pom.xml file to update the dependencies then build the project and run

## Testing the app API

Please note that medicine loaded to a specific drone cannot be load to another drone at the same time.

Open Postman For testing purpose the API is secured and you will have to specify the Authorization in the headers as Basic Auth

Username:admin
Password:password 

Note: the ContentType is application/json

## App Authorization

![Authorization](https://user-images.githubusercontent.com/56724384/190123953-d008d688-8e6f-4bef-87c9-ff091181c12c.PNG)

## Registering a drone localhost:8080/api/drone/register. The request should be in json format and below it is the response.
![Register a Drone](https://user-images.githubusercontent.com/56724384/190114275-17d5b525-5816-4973-b90b-d285a2205ba7.PNG)

## Check drone avaliability
# We need the drone available to be loaded with medicine
![Check drone availability](https://user-images.githubusercontent.com/56724384/190117433-bbe49072-f6a9-46b3-9595-ceefd247e391.PNG)

We wil be loading drone with medicine items;
localhost:8080/api/drone/load

## Below is the payload for this endpoint

serialNumber: is the unique serial for the drone being loaded
code: id the unique code for the medication load being loaded to the drone
source: is the loading point
destination: Location where the medicine will be deliver to

Medicine items to be loaded for testing are code : OGE123455, OGE123456, OGE123457, OGE123458, OGE123459, OGE123451, OGE123452, OGE123453, OGE123454, OGE123450

The serialNumber is the unique serialNumber a drone that you register

![Load Medicine into drone](https://user-images.githubusercontent.com/56724384/190117516-8ec6986f-a93d-4fa3-8f10-9c738b422d34.PNG)


## Here we will be checking for specific drone that have successfully loaded medicine and the medicine loaded
localhost:8080/api/drone/details/P1256TY7691

![Check loaded medicine for a drone](https://user-images.githubusercontent.com/56724384/190122952-d305d55d-37ea-48da-a2fc-06a6b28ecb55.PNG)

## Here we will be checking the drone battery level
localhost:8080/api/drone/battery

![Check drone battery level](https://user-images.githubusercontent.com/56724384/190117463-9e86afc8-2bef-4750-9073-20c0dc1852fc.PNG)

## Here we will check a specific drone medicine successfull delivery
localhost:8080/api/drone/deliver

When the drone delivers the item it call this end-point and its status is change drop loaded to delivering then delivered

![Check drone medicine delivery](https://user-images.githubusercontent.com/56724384/190117486-41558616-842c-47b6-bb5d-ca876dd153c1.PNG)
