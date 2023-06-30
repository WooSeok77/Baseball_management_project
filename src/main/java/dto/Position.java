package dto;



public enum Position {
    FIRST_BASE("1루수"),
    SECOND_BASE("2루수"),
    THIRD_BASE("3루수"),
    CATCHER("포수"),
    PITCHER("투수"),
    SHORTSTOP("유격수"),
    LEFT_FIELD("좌익수"),
    CENTER_FIELD("중견수"),
    RIGHT_FIELD("우익수");

    private final String displayName;

    Position(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
