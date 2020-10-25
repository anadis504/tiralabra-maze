# Testing document

The classes are covered by unit tests. Test coverage:

![Pit-report](https://github.com/anadis504/tiralabra-maze/blob/master/Documentation/graph/pit-report202010101951.png)

## Performance testing
The performance tests can be run from the user interface. The tests are run for maze size up to 1000x1000 (n = 1000 000). Wilson's algorithm's maximum capacity is 180X180 before the stack runs out of memory and throws StackOverFlow exception.

![Performance on arrays of gradual increase of x10](https://github.com/anadis504/tiralabra-maze/blob/master/Documentation/graph/performance-1000000.png)


#### A more nuanced graph adding the maximum limitation of the Wilson's algorithm at array size 32400 (180x180)

![Algorithm performance on array size up to 1000 000](https://github.com/anadis504/tiralabra-maze/blob/master/Documentation/graph/performance.png)

#### The results illustrated for mazes up to size 180x180 (maximum size of Wilson's generation algorithm) for a closer comparison

![Performance on array up to 180x180](https://github.com/anadis504/tiralabra-maze/blob/master/Documentation/graph/performance_small.png)

The Wilson's algorithm is slower because of the loop erased random walk the algorithm is based on. In the start the algorithm covers a lot of the grid in vain before it encounters the "visited" area. But the real strength of Eller's algorithm is the space complexity from generating the maze one row at a time allowing it to generate mazes of very big size. For the given application a maze of size 180x180 is enogh concidering that the maze is printed to the command line. If the application was to expand to contain graphical interface and more complex features then the Wilson's algorithm should undergo some development to solve the space limitations it is suffering from at this point.
