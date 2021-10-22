# Welcome to Rocket Elevators!

This program simulates a commercial elevator controller that can be set up in a building with any number of batteries, columns, elevators or floors.

In the scenarios provided, we use a building of 66 floors including 6 basements served by 4 columns of 5 cages each. The floors are separated amongst the columns in the following way: B6 to B1, 2 to 20, 21 to 40, 41 to 60.

All the columns serve the 1st floor (Lobby). 

There are no floor buttons inside the elevators. Instead, there is a panel at the Lobby with which the users select where they want to go.
The client is then sent to the column that serves the requested floor, they enter the elevator that was selected for them and the algorithm makes sure that they reach their destination.

Since there are no floor buttons inside the elevators, users that are on a floor cannot select where they want to go and must imperatively go back to the Lobby.

---------------RUN YOUR OWN TEST!---------------

To try the program for yourself, uncomment line 396 of the file "Main.java" and the following scenario you wish to test.
