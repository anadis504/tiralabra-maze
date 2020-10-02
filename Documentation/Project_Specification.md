Study programme: Bachelor's Programme in Computer Science

Language: Java

# Project Specification
Aim of this project is to create a labyrinth generator implementing Wilson’s and Eller’s Algorithms. The program will be based in a command line interface where the user can generate a maze of wanted size by any of the two methods. There will be a maze solver algorithm implemented as well. I was thinking of Trémaux's algorithm and maybe one more. Both algorithms create perfect mazes, that is a uniform spanning tree, so I was thinking that the user could also decide where the entrance and exit of the labyrinth should be since all points of the maze are interconnected.

## Algorithms and data structures
A char-table filled with walls works as a maze template for both generation algorithms. Both algorithms generate mazes by carving passages through the walls.

## Wilson’s Algorithm
Wilson’s algorithm is based on the idea of loop-erased random walks, which means that as it goes, if the path it is forming happens to intersect with itself and form a loop, it erases that loop before continuing on. The algorithm starts by randomly choosing a point on the grid and marking it visited. Then it chooses any unvisited cell in the grid and does a loop-erased random walk until it encounters a visited cell. At that point it goes back to the chosen starting cell and carves the path it has made by adding it to the maze, marking each cell along the path as visited. The process repeats until all the cells in the grid have been visited.

The space complexity of the algorithm is the whole maze. Actually I don't quite know how to estimate the time complexity because of the factor of randomness int the algorithm, is there any advice on this?

## Eller's Algorithm
Eller's algorithm creates the Maze one row at a time, where once a row has been generated, the algorithm no longer looks at it. Each cell in a row is contained in a set, where two cells are in the same set if there's a path between them through the part of the Maze that's been made so far. This information allows passages to be carved in the current row without creating loops or isolations. 

Because the algorithm generates the maze one row at a time it only needs to keep the current row in memory making the space complexity O(n) where n is the number of cells in the row. Time complexity is O(n²m) where m is the amount of rows in the maze.
