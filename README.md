# Search-Algorithm-State
Poject for experimenting search algorithms

## Game Background
The Game has 4 letters on the board, which are A,B,C and D. The goal of this game is to find the path to reach the goal state of the chess board. 



## Search Methods
### BFS
Breath first search is implemented using queue, the path it finds is always optimal.

### DFS
Depth first search is implemented using stack, the path it finds is not necessarily optimal, however it general takes less time to find a path.

### A* 
A star serach is implemented with a heuristic, in this project the heursistic used is manhattan heuristic, which calculates the distance between the start state and target state.

### IDS
Iterative Deepening search is implemented with stack, however it has depth limit which limits the maximum depth the search can reach.
