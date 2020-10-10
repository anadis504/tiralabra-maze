# Implementation document

## 1. Class Maze

Generates a n*m (where n = number of columns, m = number of rows) maze full of walls for the two algorithms to carve passages through. Data structure is a char-table. Two methods are provided, 
* printMaze()
* carve(x, y) 
* The initiation of the maze works in O(n*m)-time. 
* The print-method works in O(n*m)-time.

## 2. Class EllersAlgo

Generates a perfect maze (n*m) using Eller’s algorithm. The algorithm generates one row at a time keeeping track of sets of cells to avoid loops and isolated areas. It only needs to keep the current row in memory making the space complexity O(n) where n is the number of cells in the row. Data structures are array tables.

Constructor:
EllersAlgo(rows, column)

Methods:
1. generateRow():
* assigning cells without a set to their own unique set (reusing eliminated set => amount of sets stays the same throughout the whole process). Time complexity O(n)
* adds right-walls between adjacent cells of same set avoiding loops (this could be a method of its own probably). Otherwise randomly decides whether to put up a right-wall or not. If not, it calls upon the joinSets()-method to join sets of the two cells without a wall between them. Each join call runs in O(n)-time which make the time complexity of this method O(n²).

2. addBottoms():
* randomly adds bottom-walls following given rules. First iterates over the row initiating information tables. Iterates over the row once more adding and removing walls. Time O(n).

3. completeRow()
* carves the made passages in the maze “template” created by the Maze class. Time O(n).

4. prepareNextRow()
* using the generated row to prepare the next row following the rules of Eller. Removes right-walls, removes cells with bottom-walls from their sets, removes bottom-walls. Calls generateRow(). Time O(n).

5. completeMaze()
* At the final row maze is completed by merging all sets into one. Time O(n)

6. joinSets() time O(n)

7. Has a random-numbers generating method (System call nano time)

## 3. Class WilsonAlgo

Generates a perfect maze using Wilson's algorithm based on looperased random walks. Starts by randomly picking a cell on the grid and marking it visited. Rnadomly chooses another cell and starts a loop-erased random walk. Once arrivind at a visited cell carves the path of the walk marking cells on the path as visited.

Constructor:
WilsonsAlgo(rows, columns)

Methods:
1. 

## 4. Class TremauxSolve

Solves the generated maze by Tremaux solving method
