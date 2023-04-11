package andrew.backend.app.domain.main.account.model.enums;

public enum Roles {
    ROLE_ADMIN("관리자"),
    ROLE_USER("일반유저"),
    ROLE_SUSPENDED("회원정지"),
    ROLE_WITHDRAWN("회원탈퇴");

    private final String roleName;

    Roles(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public static Roles fromRoleName(String roleName) {
        for (Roles role : values()) {
            if (role.getRoleName().equals(roleName)) {
                return role;
            }
        }
        throw new IllegalArgumentException("No such role with roleName: " + roleName);
    }
}