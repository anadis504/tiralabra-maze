# Weekly progress 3

Code specification

Eller's algorithm creates a perfect maze with no loops or isolations. It generates one row at a time. 
1. Starting with only the outmost two walls and all cells not belonging to any set yet.
2. It designates every cell not belonging to a set to its own (unique) set. 
3. Then starting from left to right it takes every pair of adjacent cells and randomly decides whether to union two different sets into one or to put up a wall between them following these rules:
    * If two adjacent cells belong to the same set there need to be a wall put between them.
    * If the wall is not added then the two sets need to be joined into one.
4. Randomly adding floors (bottom walls) from left to right. Rules:
    * Every set has to have at least one opening down (one cell without a floor)
    * If a set consists of only one cell there can be no floor, or if the cell is the last one of the set and the set still doesn't have an opening down it must have an opening (this follows the upper requirement)
5. Decide to keep adding rows, or stop and complete the maze (From here on down to the end of algoritm sepcification it is copy/paste from internet)
  
   A) If you decide to add another row:
     * Output the current row
     * Remove all right walls
     * Remove cells with a bottom-wall from their set
     * Remove all bottom walls
     * Continue from Step 2

   B) If you decide to complete the maze
     * Add a bottom wall to every cell
     * Moving from left to right:
     * If the current cell and the cell to the right are members of a different set:
     * Remove the right wall
     * Union the sets to which the current cell and cell to the right are members.
     * Output the final row
     
  (Source material: http://www.neocomputer.org/projects/eller.html)    
  

Eller's Algorithm:
Creates one row at a time - therefore it is memory efficient. But has to somehow keep track of the maze it generates in order to solve it later.
What is the best data structure for the maze? Is it going to be a graph? 
What does it need? 
* It needs to keep track of the different sets the cells belong to during the creation.
* It needs to decide whether to build a wall or union two different sets.
* It needs to of course follow the rules for wall and floor building and unioning two sets
* It needs to keep track of the sets created during the current row and the sets allready existing. (Is that necessary? There can never be more sets in one row than the size of the row (1), some sets will be eliminated during set union (2). Will the sets be "reused" after elimination so there is a fixed amount of sets during the whole procedure? (Think about these. Is this at all relevant?)
So once one row is finnished it is added to the maze and that row is used for the creation of the next one.
* For the later solving of the maze, what is the best way for presentation of the maze. Is it again a graph, and if it is, what kind of...
* Also there needs to be randomization function to randomly celect where to put up walls and floors. This is also needed in Wilson's algo.


Wilson's Algorithm:
Starts with a grid full of walls and then carves the paths through them. This too creates a perfect algorithm. A spanning tree. What does it need:
* Needs to keep track of the random path it is walking and eliminate the loops it creates if it crosses its own path. (Keeping track of the current cells parent, maybe. Tracking down the path by the list of the parents. Oncearriving back to the same spot - deleting the loop and continue as if nothing happened)
* How to represent the maze? Will a 3d table be good? (To store the cells connections, information of being visited or not)


Documentation

1. Class Maze

Generates a n*m (where n = number of columns, m = number of rows) maze full of walls for the two algorithms to carve passages through. Data structure is a char-table. Two methods are provided, 
* printMaze(), and
* carve() (in position). 
* The initiation of the maze works in O(n*m)-time. 
* The print-method works in O(n*m)-time.

2. Class EllersAlgo

Generates a perfect maze (n*m) using Eller’s algorithm. Data structures are all tables (is this correct? How is a normal table called in java, do I have to mention int[] or boolean[] or things like this?).

Follows the description of Eller’s algorithm described above step-by-step. Generates one row at a time, as expected.

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

7. Has a random numbers generating method (I followed this course’s guidelines)

Time complexity of generating one row is O(n²) and it does this m times making the time complexity of this generator O(n²m). There are no restrictions for the size of the maze (as in Wilson’s, my (so far) implementation restricts the size to n<=100 m<=100)
