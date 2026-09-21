package za.ac.cput.communitystoreplatform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.communitystoreplatform.domain.Payment;
import za.ac.cput.communitystoreplatform.repository.PaymentRepository;
import za.ac.cput.communitystoreplatform.service.IPaymentService;

import java.util.List;

@Service
public class PaymentServiceImpl implements IPaymentService {

    private final PaymentRepository repository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Payment create(Payment payment) {
        return this.repository.save(payment);
    }

    @Override
    public Payment read(Integer paymentId) {
        return this.repository.findById(paymentId).orElse(null);
    }

    @Override
    public Payment update(Payment payment) {
        if (this.repository.existsById(payment.getPaymentId())) {
            return this.repository.save(payment);
        }
        return null;
    }

    @Override
    public boolean delete(Integer paymentId) {
        this.repository.deleteById(paymentId);
        return true;
    }

    @Override
    public List<Payment> getAll() {
        return this.repository.findAll();
    }

    @Override
    public List<Payment> getPaymentsByOrder(int orderId) {
        return this.repository.findByOrderId(orderId);
    }
}
