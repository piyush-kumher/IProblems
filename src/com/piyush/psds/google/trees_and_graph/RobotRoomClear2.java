package com.piyush.psds.google.trees_and_graph;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.Set;


public class RobotRoomClear2 {

    Robot robot;

    // it's always turn right .. in a single direction
    int[][] directions = {{-1,0}, {0,1}, {1,0}, {0,-1}};
    Set<Pair<Integer, Integer>> visited = new HashSet<>();

    public void cleanRoom(Robot robot) {
        this.robot = robot;
        backtrack(0, 0, 0);
    }

    private void backtrack(int row, int col, int direction) {
        visited.add(new Pair(row, col));
        robot.clean();
        // move into all four direction by doing turn right.. with respect to current direction
        for(int i=0; i < 4; i++) {
            int newD = (direction + i) % 4;
            int newR = directions[newD][0] + row;
            int newC = directions[newD][1] + col;
            if(!visited.contains(new Pair(newR, newC)) && robot.move()) {
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
