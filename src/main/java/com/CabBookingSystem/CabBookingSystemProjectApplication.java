package com.CabBookingSystem;

import java.util.List;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.CabBookingSystem.Exceptions.DriverRegistrationException;
import com.CabBookingSystem.Exceptions.RideNotFoundException;
import com.CabBookingSystem.Exceptions.UserRegistrationException;
import com.CabBookingSystem.Services.Impl.DriverServiceImpl;
import com.CabBookingSystem.Services.Impl.RideServiceImpl;
import com.CabBookingSystem.Services.Impl.UserServiceImpl;
import com.CabBookingSystem.models.Driver;
import com.CabBookingSystem.models.Ride;
import com.CabBookingSystem.models.User;

@SpringBootApplication
public class CabBookingSystemProjectApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CabBookingSystemProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Instantiate services
        UserServiceImpl userService = new UserServiceImpl();
        DriverServiceImpl driverService = new DriverServiceImpl();
        RideServiceImpl rideService = new RideServiceImpl();

        try (Scanner scanner = new Scanner(System.in)) {
            // Prompt user for input until they choose to exit
            String input;
            do {
                // Display options to the user
                System.out.println("Select an option:");
                System.out.println("1. Add User");
                System.out.println("2. Add Driver");
                System.out.println("3. Find Ride");
                System.out.println("0. Exit");

                // Read user input
                System.out.print("Enter your choice: ");
                input = scanner.nextLine();

                // Perform operations based on user input
                switch (input) {
                    case "1":
                        // Add user
                        try {
                            System.out.print("Enter username: ");
                            String username = scanner.nextLine();
                            System.out.print("Enter gender: ");
                            String gender = scanner.nextLine();
                            System.out.print("Enter age: ");
                            int age = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            userService.add_user(new User(username, gender, age));
                        } catch (UserRegistrationException e) {
                            System.out.println("Failed to add user: " + e.getMessage());
                        }
                        break;
                    case "2":
                        // Add driver
                        try {
                            System.out.print("Enter driver name: ");
                            String driverName = scanner.nextLine();
                            System.out.print("Enter gender: ");
                            String driverGender = scanner.nextLine();
                            System.out.print("Enter age: ");
                            int driverAge = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            System.out.print("Enter vehicle details: ");
                            String vehicleDetails = scanner.nextLine();
                            double x=0, y=0;
                            boolean validLocation = false;
                            do {
                                try {
                                    System.out.print("Enter current location (x y): ");
                                    String[] locationInput = scanner.nextLine().split("\\s+");
                                    if (locationInput.length != 2) {
                                        throw new IllegalArgumentException("Invalid location format. Please enter coordinates in the format 'x y'.");
                                    }
                                    x = Double.parseDouble(locationInput[0]);
                                    y = Double.parseDouble(locationInput[1]);
                                    validLocation = true;
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid coordinates. Please enter valid numerical values.");
                                } catch (IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                                }
                            } while (!validLocation);
                            driverService.add_driver(new Driver(driverName, driverGender, driverAge, vehicleDetails, new double[]{x, y}));
                        } catch (DriverRegistrationException e) {
                            System.out.println("Failed to add driver: " + e.getMessage());
                        }
                        break;
                    case "3":
                        // Find ride and choose ride
                        try {
                            System.out.print("Enter username: ");
                            String usernameForRide = scanner.nextLine();
                            System.out.print("Enter source location (x y): ");
                            double sourceX = scanner.nextDouble();
                            double sourceY = scanner.nextDouble();
                            scanner.nextLine(); // Consume newline
                            System.out.print("Enter destination location (x y (separated by space)): ");
                            double destX = scanner.nextDouble();
                            double destY = scanner.nextDouble();
                            scanner.nextLine(); // Consume newline
                            
                            List<Ride> availableRides = rideService.find_ride(usernameForRide, new double[]{sourceX, sourceY}, new double[]{destX, destY});
                            
                            if (availableRides.size() > 0) {
                                System.out.println("Available rides:");
                                for (int i = 0; i < availableRides.size(); i++) {
                                    Ride ride = availableRides.get(i);
                                    System.out.println((i + 1) + ". " + ride.getDriverName().getName());
                                }
                                
                                System.out.print("Enter the number of the ride you want to choose: ");
                                int rideChoice = scanner.nextInt();
                                scanner.nextLine(); // Consume newline
                                
                                if (rideChoice >= 1 && rideChoice <= availableRides.size()) {
                                    Ride chosenRide = availableRides.get(rideChoice - 1);
                                    rideService.chooseRide(usernameForRide, chosenRide.getDriverName());
                                    System.out.println("Ride chosen: " + chosenRide.getDriverName().getName());
                                } else {
                                    throw new IllegalArgumentException("Invalid ride choice.");
                                }
                            } else {
                                throw new RideNotFoundException("No ride found.");
                            }
                        } catch (RideNotFoundException | IllegalArgumentException e) {
                            System.out.println("Failed to find/choose ride: " + e.getMessage());
                        }
                        break;

                    case "0":
                        // Exit
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } while (!input.equals("0")); // Continue until user chooses to exit
        } catch (Exception e) {
            // Handle any exceptions that occur during the execution of commands
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
