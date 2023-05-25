package models;

import enums.ObjectType;

public class Ladder extends Entity{

    public Ladder(int start, int end){
        super(ObjectType.LADDER, start, end);
    }

}