package is.rza.M10Cashier.service.payment;

import is.rza.M10Cashier.model.dto.PaymentRequestDTO;
import is.rza.M10Cashier.model.dto.TransactionDTO;

public interface PaymentService {
    TransactionDTO processPayment(PaymentRequestDTO params);
}
