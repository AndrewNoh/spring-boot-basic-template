package andrew.backend.app.domain.main.account.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.geo.Point;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AddressEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    private String address1;

    private String address2;

    private String city;

    private String town;

    private String village;

    private String postCode;

    private Point point;

}

