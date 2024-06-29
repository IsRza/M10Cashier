package is.rza.M10Cashier.dao.entity;

import is.rza.M10Cashier.model.enums.EntryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@Table(name = "transaction")
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payer_user_id", nullable = false)
    private UserEntity payerUser;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "entry_type")
    @Enumerated(EnumType.STRING)
    private EntryType entryType;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
