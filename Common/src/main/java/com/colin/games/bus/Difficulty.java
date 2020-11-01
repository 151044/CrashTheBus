package com.colin.games.bus;

public enum Difficulty {
    EASY(100),MEDIUM(200),HARD(400),INSANE(800);
    private int points;
    Difficulty(int points){
        this.points = points;
    }
    public int getPoints(){
        return points;
    }
}
