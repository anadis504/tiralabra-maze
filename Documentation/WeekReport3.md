# Weekly progress

Code specification


Eller's Algorithm:
Creates one row at a time - therefore it is memory efficient. But has to somehow keep track of the maze it generates in order to solve it later.
What is the best data structure for the maze? Is it going to be a graph? 
What does it need? 
* It needs to keep track of the different sets the cells belong to during the creation.
* It needs to decide whether to build a wall or union two different sets.
* It needs to of course follow the rules for wall and floor building and unioning two sets
* It needs to keep track of the sets created during the current row and the sets allready existing. (Is that necessary? There can never be more sets in one row than the size of the row (1), some sets will be eliminated during set union (2). Will the sets be "reused" after elimination so there is a fixed amount of sets during the whole procedure? (Think about these. Is this at all relevant?)
So once one row is finnished it is added to the maze and that row is used for the creation of the next one.
