# Weekly progress

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


Eller's Algorithm:
Creates one row at a time - therefore it is memory efficient. But has to somehow keep track of the maze it generates in order to solve it later.
What is the best data structure for the maze? Is it going to be a graph? 
What does it need? 
* It needs to keep track of the different sets the cells belong to during the creation.
* It needs to decide whether to build a wall or union two different sets.
* It needs to of course follow the rules for wall and floor building and unioning two sets
* It needs to keep track of the sets created during the current row and the sets allready existing. (Is that necessary? There can never be more sets in one row than the size of the row (1), some sets will be eliminated during set union (2). Will the sets be "reused" after elimination so there is a fixed amount of sets during the whole procedure? (Think about these. Is this at all relevant?)
So once one row is finnished it is added to the maze and that row is used for the creation of the next one.
