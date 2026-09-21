package za.ac.cput.communitystoreplatform.service;

import za.ac.cput.communitystoreplatform.domain.Payment;

import java.util.List;

public interface IPaymentService extends IService<Payment, Integer> {

    List<Payment> getAll();

    List<Payment> getPaymentsByOrder(int orderId);
}
