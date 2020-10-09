# Implementation document

## 1. Class Maze

Generates a n*m (where n = number of columns, m = number of rows) maze full of walls for the two algorithms to carve passages through. Data structure is a char-table. Two methods are provided, 
* printMaze()
* carve(x, y) 
* The initiation of the maze works in O(n*m)-time. 
* The print-method works in O(n*m)-time.

## 2. Class EllersAlgo

Generates a perfect maze (n*m) using Eller’s algorithm. The algorithm generates one row at a time keeeping track of sets of cells to avoid loops and isolated areas. It only needs to keep the current row in memory making the space complexity O(n) where n is the number of cells in the row. Data structures are array tables.

Methods:
1. generateRow():
* assigning cells without a set to their own unique set (reusing eliminated set => amount of sets stays the same throughout the whole process). Time complexity O(n)
* adds right-walls between adjacent cells of same set avoiding loops (this could be a method of its own probably). Otherwise randomly decides whether to put up a right-wall or not. If not, it calls upon the joinSets()-method to join sets of the two cells without a wall between them. Each join runs in O(n)-time which make the time complexity of this method O(n²) (but this is worst case and quite unlikely). 

2. addBottoms():
* randomly adds bottom-walls following the rules above. First iterates over the row initiating information tables. Iterates over the row once more adding and removing walls. O(2n) → O(n).

3. completeRow()
* carves the made passages, that is since this class uses the maze “template” created by the Maze class this method carves the given row in that maze. Time O(n).

4. prepareNextRow()
* using the generated row it uses it to prepare the next row following the rules of Eller. Removes right-walls, removes cells with bottom-walls from their sets, removes bottom-walls. Calls generateRow() for the next row Time O(n).

5. completeMaze()
*completes the maze once the final row has arrived, following the given rules. Prints maze (for now). Time O(n)

6. joinSets() already described. Time O(n)

7. Has a random-numbers generating method (System call nano time)

## 3. Class WilsonAlgo

Generates a perfect maze based on Wilson's algorithm.


