# SYSC3110-Scrabble-G14

Overview:
This Implementation of Scrabble is a text-based, 4-player version of the game containing a 15x15 board and a bag of 100 seperate tiles. Each tile contains its own assigned score. Players are assigned 4 letters to start. Players take turns placing words on the board while attempting to form the largest word they possibly can with the previously placed tiles on the board. The ultimate goal is gaining as many points as possible. 

Build Status: 
As of 2022-10-25, the primary functionality of the game is up. Players can exchange, pass, shuffle, play or forfeit when their turn is reached. A board and the player's hand is printed each turn. For calculating points, the current implementation simply sums up the most recent played word and stores it with each individual player. The first tile can be placed where ever but afterwards, tiles must connect and placed tiles must all form words with all adjacent tiles. The game ends once there is only one player left. Below is a list of improvements to be made in later milestones. 
- Focus of Board implementation was functionality. Improvements to cohesion, process and efficiency will be made for the next milestone. 
- Focus of Game implementation was functionality. Improvements to cohesion, process and efficiency will be made for the next milestone.
- Cohesion of classes PlayMove, Player, InHand will be improved for next milestones. 
- Implementation for specific square points (multipliers) will be accounted for in later milestones. 
- Certain conditions for ending the game have not been set (board is unplayable with current state of all players' hands for example). These cases will be addressed in later milestones. 
- Possible bug detected when trying to play two blank tiles in the same word input. 
- Unable to get a dictionary API. Storing all valid words in a local text file which is read during program runtime. 
- JUnit tests to be added in later milestones. 
- User can only enter capitals when specifying which tiles to use to make a word. To be changed in later miletones. 
- Player does not need to start at middle of board, will be implemented in later milestones. 

Code Style: 
Primarily coded in Java v.17. Coded and Designed using fundamental Object-Oriented Programming concepts and Design Patterns and Forms discussed in SYSC3110. 

Tests:
For this milestone, testing was done manually by running through the game and attempting to enter as many different branching paths as possible to identify any and all bugs. JUnit tests to be added in later milestones.  

How to Use: 
Running the main method in Game class will bring up the following:

Welcome to Scrabble!
Type number of players (2, 3, or 4):

The user then enters the number of players and then is prompted to type 'Start' and the following is displayed:

"
------------------------------------------------------------------------------------------------
|    |    A|    B|    C|    D|    E|    F|    G|    H|    I|    J|    K|    L|    M|    N|    O|
------------------------------------------------------------------------------------------------
|   1|     |     |     |     |     |     |     |     |     |     |     |     |     |     |     |
------------------------------------------------------------------------------------------------
|   2|     |     |     |     |     |     |     |     |     |     |     |     |     |     |     |
------------------------------------------------------------------------------------------------
|   3|     |     |     |     |     |     |     |     |     |     |     |     |     |     |     |
------------------------------------------------------------------------------------------------
|   4|     |     |     |     |     |     |     |     |     |     |     |     |     |     |     |
------------------------------------------------------------------------------------------------
|   5|     |     |     |     |     |     |     |     |     |     |     |     |     |     |     |
------------------------------------------------------------------------------------------------
|   6|     |     |     |     |     |     |     |     |     |     |     |     |     |     |     |
------------------------------------------------------------------------------------------------
|   7|     |     |     |     |     |     |     |     |     |     |     |     |     |     |     |
------------------------------------------------------------------------------------------------
|   8|     |     |     |     |     |     |     |     |     |     |     |     |     |     |     |
------------------------------------------------------------------------------------------------
|   9|     |     |     |     |     |     |     |     |     |     |     |     |     |     |     |
------------------------------------------------------------------------------------------------
|  10|     |     |     |     |     |     |     |     |     |     |     |     |     |     |     |
------------------------------------------------------------------------------------------------
|  11|     |     |     |     |     |     |     |     |     |     |     |     |     |     |     |
------------------------------------------------------------------------------------------------
|  12|     |     |     |     |     |     |     |     |     |     |     |     |     |     |     |
------------------------------------------------------------------------------------------------
|  13|     |     |     |     |     |     |     |     |     |     |     |     |     |     |     |
------------------------------------------------------------------------------------------------
|  14|     |     |     |     |     |     |     |     |     |     |     |     |     |     |     |
------------------------------------------------------------------------------------------------
|  15|     |     |     |     |     |     |     |     |     |     |     |     |     |     |     |
------------------------------------------------------------------------------------------------

Color Legend
DL: Double Letter Score
TL: Triple Letter Score
DW: Double Word Score
TW: Triple Word Score

It is Player 1's turn
Current Player Points: 0
I, 1 | P, 3 | A, 1 | V, 4 | M, 3 | T, 1 | S, 1 | 

What would you like to do? (play, exchange, shuffle, pass, forfeit)
"

The board is displayed and is colour coded. The legend below it indicates the colour and the value of its respective multiplier. 
The player number, score and hand are then displayed. The user is then prompted to enter commands. Below are examples of valid commands:
"shuffle, pass, forfeit, play MAP 8H, play SAP H8, exchange MTS"

Credits: 
Vashisht, Akshay
Kaddour, Mohamed
Haghighi Saed, Jaydon
Ameli, Mahtab

Despite focusing on specific classes, each member contributed equally to the overall functionailty of the program.

License: 
Copyright 2022, G14-Scrabble for SYSC 3110 Project Carleton University, released with no intent of distribution and with heavy influence from the official scrabble board game. 
