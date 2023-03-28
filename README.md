# Skin_Consultation_Manager


In the console a menu, containing the following management actions from which the user can select one.

• Add a new doctor in the system. It should be possible to add a new doctor, with all the relevant
  information(centre can allocate a maximum of 10 doctors).
• Delete a doctor from the system, selecting the medical licence number. 
  Displays a message with the information of the doctor that has been deleted and the total number of doctors in the centre.
• Print the list of the doctors in the consultation centre. 
  For each doctor, stored information can be printed on the console. 
  The list are ordered alphabetically according to the doctor surname.
• Save in a file all the information entered by the user so far. 
  The next time the application starts it can read back all the information saved in the file and continue to use the system.


Graphical User Interface (GUI).

Graphical User Interface (GUI) is opened from the menu console.

The GUI has the following functionalities:

• The user can visualise the list of doctors with relative information. 
  The user can sort the list alphabetically.
• The user can select a doctor and add a consultation with that specific doctor. 
• The user can check the availability of the doctor in specific date/time and can book a consultation for a patient if the doctor is available. 
  If the doctor is not available automatically another doctor will be allocated, who is available in that specific date/time. 
  The choice of the doctor is done randomly among all available doctors.
  
For each consultation the user has to:
o Add patient information (add all the attributes defined above - name, surname, date of birth, mobile number, id).
o Enter and save the cost for the consultation. Considered that each consultation is £25 per hour and the first consultation is £15 per hour.
o Add some notes (this could be textual information or the user could upload some images of the skin). 
This information is encrypted in order to preserver data privacy.

o Once the consultation has been saved in the system, the user can select it and visualise all the stored information.
