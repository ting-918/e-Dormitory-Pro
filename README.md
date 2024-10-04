# e-Dormitory-Pro


## Overall Design
e-Dormitory-Pro contains four user roles: students, property management, dormitory managers, and community administrators. 

### 1. Student Users
- **Login Page**<br> Students can log in by entering their student number and password to access the student interface.
- **Accommodation Fee Payment**<br> View past payment records, including year, amount, and payment status. If the payment is incomplete, the access card becomes invalid. Students can pay outstanding amounts.
- **Utility Bill Payment**<br> View past water and electricity bills, including quarterly breakdowns of water and electricity fees, and their payment status. Students can pay outstanding bills. This feature is only available to residents.
- **Maintenance Requests**<br> View past maintenance records for dormitory equipment, including project details, reporting date, repair date, and status. Students can submit maintenance requests for their dormitory's equipment. This feature is only available to residents.
- **Late Return Records**: Students can view their own past late return records.
- **Check-in Application**: Students can submit check-in applications to the community center. This feature is only available to non-residents.
- **Check-out Application**: Students can submit check-out applications to the community center. This feature is only available to residents.
- **Logout Interface**: Allows students to log out.

### 2. Property Management Users
- **Login Page**: Property personnel can log in by entering their username and password to access the property management interface.
- **Maintenance Records**: View all maintenance request records, including project details, dormitory, repair date, and status, to facilitate record-keeping and progress tracking.
- **Utility Bill Payment Notifications**: View all utility records for the current quarter, including dates, dormitories, detailed charges, and payment status. If utility payment notifications have not been issued to all occupied dormitories, the system will remind property personnel. Property managers can issue payment notifications to dormitories that have not yet paid their utility bills.

### 3. Dormitory Management Users
- **Login Page**: Dormitory managers can log in by entering the building number and password to access the dormitory management interface.
- **Dormitory Utility Management**: View all utility records for the current quarter, including dates, dormitories, detailed charges, and payment status.
- **Dormitory Maintenance Management**: View all maintenance records for the building, including project details, dormitory, repair date, and status, to facilitate coordination between students and property management.
- **Dormitory Late Return Management**: View all student information for the building, including names, genders, student numbers, and dormitory numbers, to verify identities. During curfew hours (23:00-05:00), dormitory managers can register late returns for specific students.

### 4. Community Management Users
- **Login Page**: Community administrators can log in by entering their username and password to access the community management interface.
- **New Student Check-in Management**: Administrators can download an Excel template to upload new student information in a standardized format. The system will process this information, assign dormitories, create access cards, issue accommodation fee notifications, and create check-in records. After processing, the system returns the assignment list and exports the final Excel file for local download.
- **Check-in Application Management**: View all pending check-in applications and approve or reject them. Approved applications will trigger dormitory assignment and processing of all check-in procedures, including access card creation, fee notification, and record updates.
- **Check-out Application Management**: View all pending check-out applications and approve or reject them. Approved applications will automatically update the student’s status and complete the check-out procedures, including access card deletion and dormitory vacancy updates.
- **Dormitory Transfer Management**: Community administrators can view all resident information, including names, genders, student numbers, and dormitory numbers, to verify identities. They can process dormitory transfers for selected residents. The system will automatically validate the dormitory number to ensure its validity, including whether the dormitory exists, has enough beds, and is compatible with the resident's gender. Only valid requests can be submitted for processing.
- **Payment Information Management**: Community administrators can view all annual payment records, including year, student numbers, names, classes, amounts, and payment status. If annual payment notifications have not been issued to all residents, the system will remind administrators. Administrators can issue payment notifications for specific amounts to students who haven't paid their annual fees. Once payment is overdue, the student's access card will be deactivated to encourage timely payment. Administrators can export a list of "unpaid" students as an Excel file for download.
- **Building Information Management**: Community administrators can view all building information, including building numbers and dormitory manager login passwords. They can change the passwords for specific buildings. Administrators can delete unoccupied buildings, but the system will alert them if there are still residents, preventing the submission of a deletion request for buildings with occupants. Administrators can also add new buildings, and the system will validate the input data before processing. Once a new building is added, the system will automatically create corresponding dormitory records matching the number of floors, rooms, and beds based on the input data.
