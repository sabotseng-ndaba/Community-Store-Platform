package za.ac.cput.communitystoreplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.ac.cput.communitystoreplatform.domain.Payment;
import za.ac.cput.communitystoreplatform.service.IPaymentService;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final IPaymentService service;

    @Autowired
    public PaymentController(IPaymentService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Payment create(@RequestBody Payment payment) {
        return service.create(payment);
    }

    @GetMapping("/read/{paymentId}")
    public Payment read(@PathVariable int paymentId) {
        return service.read(paymentId);
    }

    @PutMapping("/update")
    public Payment update(@RequestBody Payment payment) {
        return service.update(payment);
    }

    @DeleteMapping("/delete/{paymentId}")
    public boolean delete(@PathVariable int paymentId) {
        return service.delete(paymentId);
    }

    @GetMapping("/getAll")
    public List<Payment> getAll() {
        return service.getAll();
    }

    @GetMapping("/getByOrder/{orderId}")
    public List<Payment> getByOrder(@PathVariable int orderId) {
        return service.getPaymentsByOrder(orderId);
    }
}
