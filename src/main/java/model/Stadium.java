package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@ToString
@Getter
public class Stadium {
    private int id;
    private String name;
    private Timestamp createdAt;

    @Builder
    public Stadium(int id, String name, Timestamp timestamp) {
        this.id = id;
        this.name = name;
        this.createdAt = timestamp;
    }
}
