# Car Park Coding Exercise
## Requirements
Create a simulation of a car park. The program will record vehicles entering and exiting the car park, 
as well as the total balance of parking fees paid. 

The program can also be asked for a balance report 
that will show how many vehicles have entered and exited, the number of spaces available and how much 
money has been paid so far.

## Example
```
Program: How many spaces does the car park have? 
User: 10 
User: ENTER CAR 
User: ENTER TRUCK 
User: REPORT 

Program: 
Cars Entered: 1 
Trucks Entered: 1 
Cars Exited: 0 
Trucks Exited: 0 
Parking Cars: 1 
Parking Trucks: 1 
Spaces available: 7 
Fees paid: $0 

User: QUIT
```

##Design Notes

### Structure
The central class is the CarParkImpl, which manages a List of Vehicles that have used the car park.
I originally used a Vehicle super class, with a Car and Truck subclass, but replaced it with a VehicleType
enum that encapsulated the size of the vehicle.

The Command design pattern is used to encapsulate the various commands input by the user. 
I think the pattern is overkill for a small program like this. 

### Features 
1. Simplified package structure. Since there aren't many classes, I chose to use only two packages, with a 
'command' sub-package housing the Command pattern classes.

2. Command pattern. Use of the standard Command pattern to house program commands. 
I think its overkill for a program of this size, but it demonstrates usage of the pattern.

3. Help command. I though it might be useful.

4. Vehicle ID. This wasn't listed as a requirement, but I added a vehicle ID that is assigned upon entry. 
This ID is used to identify a vehicle exiting the car park. 
This grants the CarPark the ability to track individual vehicles and their parking times.
However, it requires an extra 'ID' parameter for the "EXIT" command.
This feature was not strictly needed and can be removed if needed.

5. Timestamped reporting.

6. Error handling. Once the CarPark has been successfully initialised, all Exceptions are trapped in the 
main loop and print an error message. The user still has to manually "QUIT" the program to exit.

7. Commands are case insensitive.

8. There is provision in the code to modify the hourly rates.

9. Good unit test coverage. At last count, test coverage was 90% LoC.