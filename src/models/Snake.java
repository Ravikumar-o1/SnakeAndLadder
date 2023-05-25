package models;

import enums.ObjectType;

public class Snake extends Entity{

    public Snake(int start, int end){
        super(ObjectType.SNAKE, start, end);
    }
}
