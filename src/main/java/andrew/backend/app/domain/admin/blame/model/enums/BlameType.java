package andrew.backend.app.domain.admin.blame.model.enums;

public enum BlameType {
    FEED_BLAME_1("스팸 및 홍보성 게시물"),
    FEED_BLAME_2("비욕어 및 욕설"),
    FEED_BLAME_3("음란 및 유해한 게시물"),
    FEED_BLAME_4("개인정보가 노출된 게시물"),
    PLACE_BLAME_1("불쾌한 내용이 포함되어 있음"),
    PLACE_BLAME_2("존재하지 않는 장소"),
    PLACE_BLAME_3("폐업한 장소"),
    PLACE_BLAME_4("중복된 장소"),
    PLACE_BLAME_5("다른 곳으로 이전한 장소"),
    PLACE_BLAME_6("카테고리가 실제 업종과 다른 장소"),
    PLACE_BLAME_7("부적절한 장소명을 사용하는 장소"),
    USER_BLAME_1("적합하지 않은 게시물을 게시"),
    USER_BLAME_2("타인을 사칭하는 계정"),
    COMMENT_BLAME_1("스팸 및 홍보"),
    COMMENT_BLAME_2("비욕어 및 욕설"),
    COMMENT_BLAME_3("음란 및 유해"),
    COMMENT_BLAME_4("잘못된 정보"),
    ETC("기타");

    private final String type;

    BlameType(String status) {
        this.type = status;
    }

    public String getStatus() {
        return type;
    }
}
