#Weekly report 2

This week I have been looking at and reading about different maze generating algorithms. I will pick two of them to implement and compare. The chosen algorithms are:
* Eller's algorithm 
* Wilson's algorithm

Both algorithms create a perfect maze, having no loops or isolated areas and only one path from any point to another in the maze. In other words creating a spanning tree.

Wilson's algorithm generates labyrinths at random using loop-erased random walks. By this all the possible mazes are generated with the same probability. The algorithm selects a random starting point on the grid and adds it to the visited list. Next it chooses another random point to start the random walk from. During this walk the algorithm needs to keep track of the direction it is walking. If it by any point crosses its own path the loop is deleted and the walk continues from that point as if had arrived there from its starting point – until the walk bumps into a cell that is marked “visited”. This path is then marked as visited and a new walk starts from a random point on the grid (that is not marked as “visited”), until the whole grid is “visited”.

Eller’s algorithm generates the maze one row at a time. Does not need memory space for the whole labyrinth – only one row at a time. Uses data structure set to keep track of different paths.

I don’t yet have the exact vision of the actual implementation or the exact data structures needed. 

Time spent 7 h.
