package model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Time;
import java.sql.Timestamp;

@Getter
@ToString
public class Player {
    private int id;
    private int teamId;
    private String name;
    private  String position;
    private Timestamp createdAt;

    @Builder
    public Player(int id, int teamId, String name, String position, Timestamp createdAt){
        this.id = id;
        this.teamId = teamId;
        this.name = name;
        this.position = position;
        this.createdAt = createdAt;
    }
}
