package model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@ToString
public class Player {
    private int id;
    private String name;
    private String position;
    private Timestamp createdAt;


    @Builder
    public Player(int id, String name, String position, Timestamp createdAt) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.createdAt = createdAt;
    }
}
