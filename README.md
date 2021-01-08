# NCAA March Madness Bracket Predictor
The NCAA March Madness Bracket Predictor was designed to predict a completed tournament style bracket of 64 teams in the NCAA March Madness Tournament. This program takes in data from the over 2000 March Madness tournament games played since 1985, along with the current season data for the participating teams to predict the winner of each individual game in the tournament. The current data sets that are provided help to predict the bracket for the 2017 tournament. 

# Instructions
The program is run from the "Predictor" class. Once the program is run, there will be multiple outputs stating the winners of each individual game in the ***East***, ***West***, ***Midwest***, and ***South*** regions. The outputs of each game should be in the order that they appear on an actual March Madness bracket, so fill out your bracket accordingly.

# Current Implementations and Future Plans
Currently, this project is implemented with a 65 separate data sets (one historical data set and one data set for each of the 64 teams) that are stored on my local disk. In the future, I plan to use a database to store and retrieve all of this information to that it can be used without having to change any source code.
The current version also uses a basic point system that considers average points per game, win/loss ratio, the seed and other metrics such as a randomness factor to determine the winner of a game. In future versions, I plan to implement basic artificial intelligence to better predict outcomes.
