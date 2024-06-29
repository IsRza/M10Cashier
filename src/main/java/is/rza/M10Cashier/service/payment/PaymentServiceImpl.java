package is.rza.M10Cashier.service.payment;

import is.rza.M10Cashier.dao.entity.TransactionEntity;
import is.rza.M10Cashier.dao.repository.TOTPSessionRepository;
import is.rza.M10Cashier.dao.repository.TransactionRepository;
import is.rza.M10Cashier.dao.repository.UserRepository;
import is.rza.M10Cashier.exception.NotEnoughBalance;
import is.rza.M10Cashier.exception.NotFound;
import is.rza.M10Cashier.exception.UnauthorizedTransaction;
import is.rza.M10Cashier.mapper.TransactionMapper;
import is.rza.M10Cashier.model.dto.PaymentRequestDTO;
import is.rza.M10Cashier.model.dto.TransactionDTO;
import is.rza.M10Cashier.model.enums.EntryType;
import is.rza.M10Cashier.service.totp.TOTPService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final TOTPSessionRepository totpSessionRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final UserRepository userRepository;
    private final TOTPService totpService;

    @Override
    @Transactional
    public TransactionDTO processPayment(PaymentRequestDTO params) {
        var session = totpSessionRepository
                .findByPayerUserId(params.userId())
                .orElseThrow(NotFound::new);

        var user = session.getPayerUser();

        var isValid = totpService.validateTOTP(session.getSecret(), params.otp());
        if (!isValid) {
            throw new UnauthorizedTransaction();
        }

        if (user.getBalance().compareTo(params.amount()) < 0) {
            throw new NotEnoughBalance();
        }

        user.decreaseAmount(params.amount());

        var transaction = TransactionEntity
                .builder()
                .payerUser(user)
                .amount(params.amount())
                .entryType(EntryType.CREDIT)
                .build();

        userRepository.save(user);

        transaction = transactionRepository.save(transaction);

        return transactionMapper.mapToDTO(transaction);
    }
}
