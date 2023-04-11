package andrew.backend.app.domain.main.account.model.entity;

import andrew.backend.app.domain.admin.term.model.entity.AdminTermConditionsEntity;
import andrew.backend.app.domain.admin.term.model.repository.TermConditionsRepo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserTermEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ut_id")
    private Long utId;

    @ManyToOne
    @JoinColumn(name = "terms_code")
    private AdminTermConditionsEntity termCondition;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserInfoEntity userInfo;

    @Column(name = "is_agree")
    private Boolean isAgree;

    @Column(name = "agree_date")
    private LocalDateTime agreeDate;

    public void setTermConditionById(Long id, TermConditionsRepo termConditionsRepo) {
        this.termCondition = termConditionsRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Terms Not Found"));
    }
}

