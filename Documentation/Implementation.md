# Implementation document

## 1. Class Maze

Generates a nm (where n = number of columns, m = number of rows) maze full of walls for the two algorithms to carve passages through. Data structure is a char-table. Two methods are provided, one for carving in given position, one for printing the maze. Works in O(nm) time and o(nm) space.

## 2. Class EllersAlgo

Generates a perfect maze (n²m) using Eller’s algorithm. The algorithm generates one row at a time keeeping track of sets of cells to avoid loops and isolated areas. It only needs to keep the current row in memory making the space complexity O(n) where n is the number of cells in the row. Data structures are array tables.

Constructor:
EllersAlgo(rows, column)

### Methods:
1. generateRow():
* assigning cells without a set to their own unique set (reusing eliminated set => amount of sets stays the same throughout the whole process). Time complexity O(n)
* adds right-walls between adjacent cells of same set to avoid loops. Otherwise randomly decides whether to put up a right-wall or not by calling method random(). If not putting a wall, it calls joinSets()-method to join sets of the two adjacent cells without a wall between them. joinSets() method merges all cells from the two sets into one set in O(n)-time. generateRow() works thus in O(n²).

2. addBottoms():
* randomly adds bottom-walls following given rules. First iterates over the row initiating information tables. Iterates over the row once more adding and removing walls. Time O(n).

3. completeRow()
* carves the made passages in the maze “template” created by the Maze class. Time O(n).

4. prepareNextRow()
* using the generated row to prepare the next row following the rules of Eller. Removes right-walls, removes cells with bottom-walls from their sets, removes bottom-walls. Calls generateRow(). Time O(n).

5. completeMaze()
* At the final row maze is completed by merging all sets into one. Time O(n)

The proceedure is repeated m times, where m is the amount of rows in the maze.

## 3. Class WilsonAlgo

Generates a perfect maze using Wilson's algorithm based on looperased random walks. Starts by randomly picking a cell on the grid and marking it visited. Rnadomly chooses another cell and starts a loop-erased random walk. Once arrivind at a visited cell carves the path of the walk marking cells on the path as visited. Time O(n³), space O(nm).

Constructor:
WilsonsAlgo(rows, columns)

### Methods:
1. pickACell()
* randomly chooses an unvisited cell from the grid. Takes advantage of the IndexTree class to find the next free cell in O(log n) time.

2. randomWalk()
* Makes a loop-erased random walk until meeting a visited cell. The earliest walks will take the longest time to find the few visited cells. The later walk will be significantly faster. Because of the random factor of this method the time complexity is very difficult to estimate. In theory it can make infinitely many loops which it erases before the walk is over, covering much of the grid. As the maximum size of the grid is approximately 110x110 because of memory restrictions the probability that it will find the visited area before covering the whole grid n times grows as the visited area expands. Ruff complexity limit O(n³) time and O(nm) space. 
* Calls on methods chooseDir(), which calls on method ifBorder() both working in constant time.

3. carvePath()
* Carves the path in the Maze template and marks the cells from the path visited. Time during the whole maze generation O(nm)


## 4. Class TremauxSolve

Solves the generated maze by Tremaux solving method in O(nm) time and O(nm) space. Chooses an available direction at random and marks the once. If a dead end is encountered the algorithm backtracks until finding an unmarked passage it can take marking the dead-end path twice. Since the mazes generated in this application are perfect (without loops and isolated areas) the solving algorithm does not actually need to be concerned about encountering a path it has marked once before. When reaching the finish coordinates the path that has been marked only once is the solution path. The algorithm print the solution to the screen.
