package andrew.backend.app.domain.main.account.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CountryCodeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codeId;

    private String code;

    private String country;

    private String continent;

    private Boolean isService;

}

