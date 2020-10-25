Study programme: Bachelor's Programme in Computer Science

Language: English

# Project Specification
Aim of this project is to create a labyrinth generator implementing Wilson’s and Eller’s Algorithms. The program will be based in a command line interface where the user can generate a maze of wanted size by any of the two methods. There will be a maze solver algorithm implemented as well. I was thinking of Trémaux's algorithm and maybe one more. Both algorithms create perfect mazes, that is a uniform spanning tree, so I was thinking that the user could also decide where the entrance and exit of the labyrinth should be since all points of the maze are interconnected.

## Algorithms and data structures
A char-table filled with walls works as a maze template for both generation algorithms. Both algorithms generate mazes by carving passages through the walls.

## Wilson’s Algorithm
Wilson’s algorithm is based on the idea of loop-erased random walks, which means that as it goes, if the path it is forming happens to intersect with itself and form a loop, it erases that loop before continuing on. The algorithm starts by randomly choosing a point on the grid and marking it visited. Then it chooses any unvisited cell in the grid and does a loop-erased random walk until it encounters a visited cell. At that point it goes back to the chosen starting cell and carves the path it has made by adding it to the maze, marking each cell along the path as visited. The process repeats until all the cells in the grid have been visited.

## Segment tree
Wilson's Algo takes advantage of an index segment tree data structure for finding the next available starting cell for the walk in O(log n) time. The maze generation works in O(n³) time and O(n*m) space, but generates relatively small mazes compared to Eller's method due to memory limitation. In this way the generating of the maze does not get to feel slow.

## Eller's Algorithm
Eller's algorithm creates the Maze one row at a time, where once a row has been generated, the algorithm no longer looks at it. Each cell in a row is contained in a set, where two cells are in the same set if there's a path between them through the part of the Maze that's been made so far. This information allows passages to be carved in the current row without creating loops or isolations. 

Because the algorithm generates the maze one row at a time it only needs to keep the current row in memory making the space complexity O(n) where n is the number of cells in the row. Time complexity is O(n²m) where m is the amount of rows in the maze.

## Trémaux's solving algorithm
Is a maze solving method that will find a solution for all types of mazes. Based on the technique a real person would use inside the maze. Walkes through the maze marking the path once. When encountering a crossing passage chooses the path that is unmarked or goes back marking the path twice. When arriving at the finish coordinates the solution itś the path that has been marked only once. Visits each cell at most twice, if even: working in time and space O(n*m).

## Sources
[Think Labyrinthm](http://www.astrolog.org/labyrnth/algrithm.htm)

[Wilson's algorithm](http://people.cs.ksu.edu/~ashley78/wiki.ashleycoleman.me/index.php/Wilson's_Algorithm.html)

[James Buck blog on Wilson's algorithm](http://weblog.jamisbuck.org/2011/1/20/maze-generation-wilson-s-algorithm.html)

[Neocomputer on Eller's Alforithm](http://www.neocomputer.org/projects/eller.html)

[Tremaux Labyrinth solving algorithm, wikipedia](https://en.wikipedia.org/wiki/Maze_solving_algorithm#Tr%C3%A9maux's_algorithm)
