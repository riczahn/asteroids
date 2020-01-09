# asteroid-scanner
Tool to check for asteroids approaching earth so that we can send up Bruce Willis to blow it up.
It uses Nasa's public 'Near Earth Object' API - found on https://api.nasa.gov
First an initial query is done on the 'Neo - Feed' API to get a list of IDs and names of asteroids near to earth. 
Secondly full information is retrieved for all the asteroid IDs retreived, in separate rest queries (Neo - Lookup).

There's two application classes - App doing main initialization and the first Feed query, which it gives to the ApprachDetector.
This one iterates through the list and retrieves fly-by data (distance, date, speed etc). 
The 10 closest fly-byes are shown in the terminal.


