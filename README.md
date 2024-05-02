Cab Booking System
Description
The Cab Booking System is a command-line application that allows users to book rides on a route. It provides features for users to register, drivers to onboard with vehicle details, and users to search and select from multiple available rides based on their source and destination.

Features
•	User registration
•	Driver onboarding with vehicle details
•	Searching and selecting available rides based on source and destination
•	Choosing a ride from the available options
•	Requirements
•	Java 8 or higher
•	Maven (for building the project)

Installation Clone this repository to your local machine.
bash Copy code git clone https://github.com/karanmiglani/CabBookingSystemProject.git

Sample Test Cases:
Onboard 3 users
add_user(“Abhishek, M, 23”); 
add_user(“Rahul , M, 29”); 
add_user(“Nandini, F, 22”) ;

Onboard 3 driver to the application
add_driver(“Driver1, M, 22”,“Swift, KA-01-12345”,(10,1))
add_driver(“Driver2, M, 29”,“Swift, KA-01-12345”,(11,10))
add_driver(“Driver3, M, 24”,“Swift, KA-01-12345”,(5,3))
	
User trying to get a ride 
find_ride(“Abhishek” ,(0,0),(20,1))
		Output : No ride found [Since all the driver are more than 5 units away from user]
find_ride(“Rahul” ,(10,0),(15,3))
		Output : Driver1 [Available]
find_ride(“Nandini”,(15,6),(20,4))
Output : No ride found [Driver one in set to not available]



