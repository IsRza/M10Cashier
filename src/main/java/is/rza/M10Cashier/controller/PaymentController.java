package is.rza.M10Cashier.controller;

import is.rza.M10Cashier.model.dto.PaymentRequestDTO;
import is.rza.M10Cashier.model.dto.TransactionDTO;
import is.rza.M10Cashier.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    TransactionDTO makePayment(@RequestBody PaymentRequestDTO params) {
        return paymentService.processPayment(params);
    }
}
