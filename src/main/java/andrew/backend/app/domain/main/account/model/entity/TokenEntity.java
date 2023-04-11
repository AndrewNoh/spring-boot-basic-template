package andrew.backend.app.domain.main.account.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserInfoEntity user;

    private String token;

    private boolean expired;

    public TokenEntity(UserInfoEntity user, String token, boolean expired) {
        this.user = user;
        this.token = token;
        this.expired = expired;
    }
}
