package lv.emils.taxi_dispatching_service;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Emils on 24.04.2017.
 */
public class TaxiUser {
    private long userId;
    private TaxiOrder taxiOrder;

    TaxiDispatchingService taxiDispatchingService = new TaxiDispatchingService();

    public TaxiUser() {
        this.userId = UserIdGenerator.nextId();
    }

    public synchronized void createTaxiOrder(String addressTo, String addressFrom) {
        setTaxiOrder(new TaxiOrder(addressTo, addressFrom));
        taxiDispatchingService.getActiveTaxiOrders().add(getTaxiOrder());
    }

    public synchronized boolean cancelTaxiOrder() {
        if (getTaxiOrder().isAccepted()) {
            System.out.println("Cannot cancel - the order has already been accepted by a taxi driver.");
            return false;
        }
        taxiDispatchingService.getActiveTaxiOrders().remove(getTaxiOrder());
        return true;
    }

    public TaxiOrder getTaxiOrder() {
        return taxiOrder;
    }

    public void setTaxiOrder(TaxiOrder taxiOrder) {
        this.taxiOrder = taxiOrder;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}

class UserIdGenerator {
    private static AtomicLong id = new AtomicLong(0);

    public static long nextId() {
        long next = id.incrementAndGet();
        return next;
    }
}
