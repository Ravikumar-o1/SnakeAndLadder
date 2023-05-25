package models;

import enums.ObjectType;

public abstract class Entity {
    private ObjectType type;
    private int start;
    private int end;

    public Entity(ObjectType objType, int startValue, int endValue){
        type = objType;
        start = startValue;
        end = endValue;
    }

    public int getStart(){
        return start;
    }

    public int getEnd(){
        return end;
    }

    public ObjectType getType(){
        return type;
    }
}
