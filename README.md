//Group Name And ID:-                          Git_account
//        1. AMANUEL ABATE --- 2122/14  ----   amanabate
//        2. AYANA FILE --- 2226/14     ----    ayu10up
//        3. ASDESACH ASFAW --- 2193/14  -----  Asdesac
//        4. BANA DAWIT ---- 2245/14    -----  banadawit
//        5. BEFIKADU TILAHUN --- 2268/14  --- atwil  

Dormitory Management System:- 
  
  This Dormitory Management System is designed to efficiently 
  manage dorm-related tasks, including student assignments,
  building details, and proctor responsibilities

Based on the project provided by our group, 
it appears to be a Java program related to managing buildings 
and zones in a dormitory. It includes functionality for adding buildings, adding zones, assigning proctors to buildings, and displaying information about buildings and proctors.
      
#Features of programme:
Here is a breakdown of the files and their functionalities:

#Admin_class: The Admin class in the Dormitory Management System represents 
an administrative user responsible for managing students, proctors, building details. 
The class provides functionality for registering new students, adding proctors, 
removing students, displaying student lists, and interacting with building-related details.

Buildings: This file contains the main logic for managing buildings, 
   zones, and proctors. It includes methods for adding buildings (both for males and females), 
   adding zones, displaying buildings, displaying buildings with assigned proctors,
   and other utility methods. The class also includes a navbar() method 
   that provides a menu for navigating through the program's functionalities.

#DormAccess_class:- The Dorm_Room class in the Dormitory Management System 
represents dormitory rooms and provides functionalities for managing dorm rooms, 
adding students to rooms, and handling related database operations.

#Constants_class: This file likely contains constant values used in the program. 
   Unfortunately, the content of this file has been truncated and is not available.
   However, based on the usage of the Constants interface in the tryout class, 
   it can be inferred that it may include constants related to building types 
   (e.g., "BUILDING_F" and "BUILDING_M").

#DormMaterial_class: The DormMaterial class in the Dormitory Management System extends the Dorm_Room class 
and provides functionalities related to dormitory materials. It includes methods for managing 
dorm materials such as beds, lockers, and tables, and handles corresponding database operations.

tryout: This file contains a main method where various methods
   from the Buildings class are called. 
   It initializes the necessary database tables, adds buildings, creates zones, 
   assigns proctors to buildings, and displays building details

Proctor: The Proctor class represents the Proctor entity in the Dormitory Management System.
Proctors are responsible for managing dormitory-related tasks.

#Student_class: The Student class represents the Student entity in the Dormitory Management System. 
Students are individuals residing in dormitories.

#Home_class: The Home class serves as the entry point for the Dormitory Management System.
It provides a user interface for selecting the type of user (Admin, Proctor, or Student) 
and redirects the user to the corresponding functionality.


