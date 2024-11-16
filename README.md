# Travel Agency System (TAS) README
## Project Overview
The Travel Agency System (TAS) is designed to streamline booking reservations for flights and hotels while offering comprehensive customer service capabilities. Customers can easily book, cancel, and manage their flight and hotel reservations through the assistance of agency employees. The system stores customer profiles, flight details, hotel reservations, and payment histories in different storage locations. Customers accumulate points with each booking, which can be redeemed for future transactions. Employees can book and cancel reservations for customers and manage their own reservations with unique benefits.

This project leverages the Model-View-Controller (MVC) design paradigm and aims to provide a user-friendly experience for both customers and employees.

##Design Paradigm
Model-View-Controller (MVC)
Model: Represents data, such as customer profiles, flight details, booking statuses, etc.
View: Provides user interfaces to interact with the system (e.g., booking forms, search results).
Controller: Manages logic and user input, processing requests, updating the view, and interacting with the model.

##Expected Features and Outcomes
###Flights

Search for Flights

Action: Users can search for available flights based on departure city, destination city, date, and class of service (economy/business).
Expected Output: A list of flights matching the criteria, with details such as:
Flight number
Airline name
Departure and arrival times
Price per seat
Additional Output: If no flights match the criteria, a message appears: "No flights available for the selected criteria."

Book a Flight

Action: Agents select a flight and book a seat by entering passenger details (name, age, passport number, phone number, and email).
Expected Output: Confirmation of booking with assigned ticket details, including:
Booking number
Flight details
Passenger details
Total cost

Cancel a Flight

Action: Agents cancel an existing booking by providing the booking reference.
Expected Output: Confirmation message: "Booking successfully canceled." The system updates the list of booked and canceled tickets.
Additional Output: If invalid booking reference is provided, message appears: "Booking not found. Please check your booking number."

View Booking Details

Action: Users can view existing booking details using their booking number.
Expected Output: Display of booking details, passenger information, flight details, and total price. If booking is not found, an error message is displayed.

Payment and Invoice Generation

Action: Employees handle payment using credit card details. Payment is simulated.
Expected Output: Generation of an invoice with total amount, payment method, and ticket details.

View Flight Information

Action: Users or agents view general flight information, including upcoming departures and flight status.
Expected Output: Display of flight information without booking.

Error Handling

Expected Output: Specific error messages for invalid inputs (e.g., wrong dates, incomplete passenger details, invalid booking numbers).

###Hotels
Search for Available Rooms

Action: Users search for rooms based on availability.
Expected Output: List of available rooms matching the criteria.

Reserve a Room

Action: Agents book a room by entering client details (name, age, phone number, and email).
Expected Output: Confirmation with booking details:
Room number
Booking details (nights, price, meals, arrival and departure times)
Client details
Total cost

Cancel a Hotel Booking

Action: Agents cancel a booking using the room number and client name.
Expected Output: Confirmation message: "Booking successfully canceled." The system updates booked and available rooms.

View Booking Details

Action: Users view booking details.
Expected Output: Display of all relevant details.

Payment and Invoice Generation

Action: Employees handle payment using credit card details.
Expected Output: Generation of invoice with payment information.

View Hotel Information

Action: Users view hotel information without booking.
Expected Output: Display of hotel information.

Error Handling

Expected Output: Specific error messages for invalid inputs (e.g., wrong dates, incomplete client details, invalid user IDs).

##Additional System Behavior
Admin-Specific Features: Restricted to certain users with password protection.
Data Integrity: Prevents overbooking, double bookings, and incorrect flight/hotel details.
Responsive User Interface: Provides clear, concise feedback for actions and errors.

##Expected Results Summary
Search for Flights and Rooms
Displays a list of matching results or a "not found" message.
Book a Flight or Room
Confirms booking with detailed information.
Cancel a Booking
Confirms cancellation and updates lists.
Payment Processing and Invoice Generation
Generates an invoice with payment details.
View Booking Information
Displays booking, flight, and hotel information.
Error Handling
Provides detailed error messages for invalid inputs.

##Conclusion
The Travel Agency System (TAS) will streamline booking, cancellation, and customer service processes with an easy-to-use interface while maintaining data integrity and security. This system will enhance customer experience and simplify agency operations.
