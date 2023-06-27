package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerRespDTO {
    private int id;
    private String name;
    private int age;
    private String position;
    private int teamId;

    public PlayerRespDTO(int id, String name, int age, String position, int teamId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.position = position;
        this.teamId = teamId;
    }
}
