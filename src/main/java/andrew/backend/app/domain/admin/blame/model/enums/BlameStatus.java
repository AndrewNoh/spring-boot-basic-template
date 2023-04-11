package andrew.backend.app.domain.admin.blame.model.enums;

public enum BlameStatus {
    UNCOMPLETED("미처리"),
    COMPLETED_USER_BLOCK("처리/계정 블럭"),
    COMPLETED_USER_RESIGN("처리/계정 탈퇴"),
    COMPLETED_DELETE("처리/삭제"),
    COMPLETED_HIDE("처리/숨김");

    private final String type;

    BlameStatus(String status) {
        this.type = status;
    }

    public String getStatus() {
        return type;
    }
}
