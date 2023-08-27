# Pacman Game

The Pacman Game project is a captivating implementation of the classic arcade game developed using Java and object-oriented programming principles. Inspired by the iconic Pacman game from the 1980s, this project showcases the power of Java's object-oriented features and provides an engaging gaming experience.

With a focus on modularity and extensibility, this project demonstrates the fundamental concepts of object-oriented programming, including encapsulation, inheritance, and polymorphism. By employing these principles, the codebase ensures maintainability and allows for easy future enhancements and additions.

- Click [here](https://youtu.be/RBWuVX7_XUY) to see a video presentation of the project. 

## Game Description

PAC-MAN is a classic arcade game, where a pac-man moves through a maze consuming the dots and avoiding the coloured ghosts. The player has a limited number of lives and loses one whenever a ghost catches them. The player gets points for eating the dots, power pellets (these give special powers to pac-man) and ghosts. The player can get another life every time their score goes over a multiple of 10,000.

## Movement

Pac-man will always move in the direction that he is pointed. He cannot speed up or slow down and when the game starts, he will begin moving. In the maze pac-man can only turn a direction when the way is open, he
cannot turn towards a wall.

- The only buttons used in this game are the **directional arrows**.

## Gameplay Elements

In the game there is the following:

- A maze that pac man must move through
- Dots that pac-man must eat
- Four power pellets, that make pacman invincible for a short amount of time during which he can eat the ghosts (they come back to life a short time later)
- Four Ghosts that come out and chase after pac-man
- Fruit that randomly appears for a short amount of time and can be eaten for extra points

## Points/Scoring

Pacman gets points for eating each of the following

- Dots - 10 points each
- Power pellets - 50 points each
- Fruit - 500 points each
- Ghosts - 200 points for the first, 400 points for the second, 800 points for the third and 1600 points for the fourth (count is reset each time you eat a power pellet)

## Ghosts
The four ghosts are named Blinky, Pinky, Inky and Clyde and are coloured Red, Pink, Cyan and Orange. The ghosts differ in speed **slightly** and are listed previously from the slowest to the fastest. In the first level, only one ghost is released and chases after the player, with each increase in level another ghost is added until all of the ghosts are chasing player.
When multiple ghosts are released, there should be a constant interval of time between the release of each ghost (15 to 30 seconds). This delay should also be applied to the return of the ghosts after the player has eaten them. When ghosts are returning, they should appear at the original spawning location.
When the player eats a power pellet the ghosts should all change colour to blue. At this time there should also be a change in behaviour, the ghosts should try and run away from the player instead of chasing it.

## Levels
This game is based on the completion of levels, whenever a level is complete the player is returned to the beginning to play again. Though some details such as the number of ghosts to be released, their movement speed and the delay between their release may be changed. A level is complete when all of the **dots** and **power pellets** are eaten.

## Difficulty
As mentioned above, some variables should change to increase difficulty. An example of the way that these changes could be made is given in Table. The idea behind this is that the speed of the ghosts is increased at a relatively slow pace (increasing about 5% each level) while at the same time there are more ghosts and a shorter delay between the release of ghosts from the spawn point.

| Level | Number of Ghosts | Delay Between Ghosts | Movement Speedup |
| :---: | :--------------: | :------------------: | :--------------: |
|   1   |        1         |          20          |       1.00       |
|   2   |        2         |          20          |       1.05       |
|   3   |        3         |          20          |       1.10       |
|   4   |        4         |          20          |       1.15       |
|   5   |        4         |          18          |       1.20       |
|   6   |        4         |          16          |       1.25       |
|   7   |        4         |          14          |       1.30       |
|   8   |        4         |          12          |       1.35       |
|   9   |        4         |          10          |       1.40       |
|  10   |        4         |          8           |       1.45       |
|  11   |        4         |          6           |       1.50       |
|  12   |        4         |          5           |       1.55       |
|  13   |        4         |          5           |       1.60       |
|  14   |        4         |          5           |       1.60       |

## Level Layout
The version of the game you are creating will allow for the layout of the maze to be defined in a text file. This fill can then be loaded in the game and used to play. The different parts of the maze should be shown using the following characters

- `W` represents that this location is a wall the the player and ghosts may not pass through
- `P` represents the location where pac-man spawns at the beginning of the level or when the player dies
- `.` represents that there is a dot in this location
- `*` represents that there is a power pellet at this location
- `F` represents a location where fruit will occasionally appear here (there is only ever one location on a map)
- `G` represents the location where ghosts spawn here at the beginning and after they die (it is usually surrounded
  by empty spaces)

- `-`represents an empty square that ghosts may pass through, but the player may not

Each maze design will be stored as a single text file containing the whole maze. The text file is laid out like a two dimensional grid where each line is the next part of the maze. An example of the format is given in
Figure.

![image-20230704215533264](E:\Code\COMP2011J-Object-Oriented-Programming-Assignment\maze.jpg)

The maximum width of any level will 20 and the maximum height (number of lines) will be 24. I will be testing with files that I have created myself, so your code must be able to generate levels based on them.

## Example

To get an example of the how the pac-man game plays, go to the http://pacman.platzh1rsch.ch/ and play the game there. Note that only the motion of the characters is required, animation is optional.
