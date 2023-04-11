package andrew.backend.app.domain.admin.blame.model.enums;

public enum TargetType {
    POST("게시글"),
    USER("계정"),
    PLACE("장소"),
    COMMENT("댓글");
    private final String type;

    TargetType(String status) {
        this.type = status;
    }

    public String getStatus() {
        return type;
    }
}

