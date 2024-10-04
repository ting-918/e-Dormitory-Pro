# e-Dormitory-Pro


## Overall Design
e-Dormitory-Pro contains four user roles: students, property management, dormitory managers, and community administrators. 

### 1. Student Users
- **Login Page**<br> Students can log in by entering their student number and password to access the student interface.
- **Accommodation Fee Payment**<br>
  - Students can view their payment history of accommodation fees, including year, amount, and payment status.
  - Students can settle outstanding accommodation fees that are not overdue.
- **Utility Fee Payment**<br>
  - Students can view their payment history of utility fees, including monthly breakdowns of water and electricity fees, and payment status.
  - Students can settle outstanding utility fees.
  - This function is only available to residents.
- **Maintenance Requests**<br>
  - Students can view their past maintenance records for dormitory equipment, including project details, reporting date, repair date, and status.
  - Students can submit maintenance requests for their dormitory's equipment.
  - This function is only available to residents.
- **Late Return Records**<br> Students can view their past late return records.
- **Check-in Application**<br>
  - Students can submit check-in applications to the community center.
  - This function is only available to non-residents.
- **Check-out Application**<br>
  - Students can submit check-out applications to the community center.
  - This function is only available to residents.

### 2. Property Management Users
- **Login Page**<br> Property personnel can log in by entering their username and password to access the property management interface.
- **Maintenance Requests Management**<br>
  - Property personnel can view all maintenance requests, including project details, dormitory, repair date, and status, to facilitate record keeping and progress tracking.
  - Property personnel can update the status of pending requests to "finished" while recording the repair date.
- **Utility Bill Payment Notifications**<br>
  - Property personnel can view all utility bills for the current month, including dates, dormitories, detailed charges, and payment status.
  - Property personnel has the authority to issue payment notifications to occupied dormitories which should pay utility fees for the current month.
  - If monthly utility payment notifications have not been issued to all occupied dormitories, the system will automatically prompt a reminder.

### 3. Dormitory Management Users
- **Login Page**<br> Dormitory managers can log in by entering the building number and password to access the dormitory management interface.
- **Utility Bill Management**<br> Dormitory managers can view all utility records for the current quarter, including dates, dormitories, detailed charges, and payment status.
- **Dormitory Maintenance Management**<br> Dormitory managers can view all maintenance records for the building, including project details, dormitory, repair date, and status, to facilitate coordination between students and property management.
- **Late Return Management**<br>
  - Dormitory managers can view all student information for the building, including names, genders, student numbers, and dormitory numbers, to verify identities.
  - During curfew hours (23:00-05:00), dormitory managers can register late returns for specific students.

### 4. Community Administrators
- **Login Page**<br> Community administrators can log in by entering their username and password to access the community administration interface.
- **New Freshmen Check-in Management**<br>
  - Community administrators can download an Excel template and upload freshmen informations in a standardized format for batch processing.
  - The system will automatically validate each piece of data in the Excel file.
  - All valid entries will trigger dormitory allocation and the check-in procedures, including the following steps:<br> (1) Updating student information<br> (2) Occupying a bed in the target room<br> (3) Creating access card (without activation)<br> (4) Issuing accommodation fee notifications<br> (5) Creating check-in data
  - After batch processing, the system exports the allocation report as an Excel file for local download.
  - In the event of exceptions, such as incorrect file format or insufficient dormitories, the system will halt the task and present a helpful prompt.
- **Check-in Application Management**<br>
  - Community administrators can view all pending check-in applications, and approve/reject them .
  - Approved applications will trigger dormitory allocation and the check-in procedures, including the following steps:<br> (1) Updating student information<br> (2) Occupying a bed in the target room<br> (3) Creating access card (without activation)<br> (4) Issuing accommodation fee notifications<br> (5) Creating check-in data
- **Check-out Application Management**<br>
  - Community administrators can view all pending check-out applications, and approve/reject them .
  - Approved applications will automatically complete the check-out procedures, including the following steps:<br>(1) Freeing up beds in the original room<br> (2) Deleting check-in data <br> (3) Updating student information <br> (4) Deleting history records of the original room (maintenance requests and utility bills) if the student was the last occupant<br> (5) Deleting access card
- **Dormitory Transfer Management**<br>
  - To verify identities, community administrators have access to all resident information, including names, genders, student numbers, and dormitory numbers.
  - Community administrators can process dormitory transfers for selected residents.
  - The system will automatically validate the dormitory number to ensure its validity, including whether it exists, has enough beds, and is compatible with the resident's gender.
  - Only valid requests can be submitted for processing.
  - Each dormitory transfer process involves the following steps:<br>(1) Freeing up beds in the original room<br> (2) Modifying target room information (occupying a bed) <br> (3) Updating student information <br> (4) Deleting history records of the original room (maintenance requests and utility bills) if the student was the last occupant<br> (5) Updating access card information
    
- **Accommodation Fee Management**<br>
  - Community administrators can view all accommodation payment records, including year, student numbers, names, classes, amounts, and payment status.
  - Community administrators have the authority to issue payment notifications to students who should pay annual accommodation fees.
  - If annual payment notifications have not been issued to all residents, the system will automatically prompt a reminder.
  - In the event of overdue payment, the student's access card will be deactivated automatically.
  - Community administrators can export a list of "unpaid" students as an Excel file for download.
  
- **Building Information Management**<br>
  - Community administrators can view all building information, including building numbers and login passwords for the dormitory manager.
  - Community administrators has the authority to change the passwords for specific buildings.
  - Community administrators can delete unoccupied buildings, but the system will alert them if there are still residents, preventing the submission of a deletion request for buildings with occupants.
  - Community administrators can also add new buildings, and the system will validate the input data before processing.
  - Once a new building is added, the system automatically creates dormitory records, matching the number of floors, rooms, and beds based on the input data.
