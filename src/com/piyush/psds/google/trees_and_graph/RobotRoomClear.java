package com.piyush.psds.google.trees_and_graph;

import java.util.HashSet;
import java.util.Set;


// This is the robot's control interface.
// You should not implement it, or speculate about its implementation
interface Robot {
    // Returns true if the cell in front is open and robot moves into the cell.
    // Returns false if the cell in front is blocked and robot stays in the current cell.
    public boolean move();
    // Robot will stay in the same cell after calling turnLeft/turnRight.
    // Each turn will be 90 degrees.
    public void turnLeft();
    public void turnRight();
    // Clean the current cell.
    public void clean();
}

public class RobotRoomClear {

    Robot robot;

    // it's always turn right .. in a single direction
    int[][] directions = {{-1,0}, {0,1}, {1,0}, {0,-1}};
    Set<String> visited = new HashSet<>();

    public void cleanRoom(Robot robot) {
        this.robot = robot;
        backtrack(0, 0, 0);
    }

    private void backtrack(int row, int col, int direction) {
        String node = Integer.toString(row) + "#" + Integer.toString(col);
        visited.add(node);
        robot.clean();
        // move into all four direction by doing turn right.. with respect to current direction
        for(int i=0; i < 4; i++) {
            int newD = (direction + i) % 4;
            int newR = directions[newD][0] + row;
            int newC = directions[newD][1] + col;
            String newNode = Integer.toString(newR) + "#" + Integer.toString(newC);
            if(!visited.contains(newNode) && robot.move()) {
                backtrack(newR, newC, newD);
                goBack();
            }
            robot.turnRight();
        }
    }

    private void goBack() {
        // getting the complete opposite direction of the move
        this.robot.turnRight();
        this.robot.turnRight();

        // getting back at the same position
        this.robot.move();

        // getting in the original direction
        this.robot.turnRight();
        this.robot.turnRight();
    }
}
