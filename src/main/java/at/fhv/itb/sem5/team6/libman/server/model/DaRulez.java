package at.fhv.itb.sem5.team6.libman.server.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Getter
@Setter

@Document(collection = "daRulez")
public class DaRulez {
    @Id
    private String id;

    private int maxExtensions;
    private long maxLendingDurationInMilliseconds;
    private long maxReservationDuration;

    private float annualFee;
    private float overdueFine;
}
