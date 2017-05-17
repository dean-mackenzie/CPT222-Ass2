CPT222 - Assignment 2

Student Number: s3338283
Student Name: Dean Mackenzie

Note: the dice images used were public domain.

Known Issues
------------
1. Multiple clients are not supported.  Only a single client can communicate with the server.

2. Updating the points value for the player in the UI are not working.  I do not know why.

3. Closing the client doesn't close the callbacks, which causes problems when a new client connects.  I had no idea on how to clean up the callback on the server side when a connection was lost.

4. Game history was not implemented.  Spent so much time on server connection and attempting threading I didn't get round to it.