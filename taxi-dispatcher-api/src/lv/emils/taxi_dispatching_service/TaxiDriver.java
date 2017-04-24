package lv.emils.taxi_dispatching_service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Emils on 24.04.2017.
 */
class TaxiDriver {
    private long driverId;
    private Set<TaxiOrder> taxiDriverOrders;

    TaxiDispatchingService taxiDispatchingService = new TaxiDispatchingService();

    public TaxiDriver() {
        this.driverId = DriverIdGenerator.nextId();
    }

    public synchronized void acceptOrder(TaxiOrder order) {
        if (taxiDriverOrders.equals(null)) {
            taxiDriverOrders = new HashSet<>();
        }
        taxiDriverOrders.add(order);
        order.setAccepted(true);
    }

    public synchronized Set<TaxiOrder> getAvailableOrders() {
        for (TaxiOrder order : taxiDispatchingService.getActiveTaxiOrders()) {
            if (!order.isAccepted()) {
                taxiDispatchingService.getActiveTaxiOrders().add(order);
            }
        }
        return taxiDispatchingService.getActiveTaxiOrders();
    }

    // left in for 'future developments'
    public synchronized void returnOrders(TaxiOrder... orders) {
        for (TaxiOrder order : orders) {
            if (order.isCompleted()) {
                getTaxiDriverOrders().remove(order);
            } else {
                order.setAccepted(false);
                getTaxiDriverOrders().remove(order);
            }
            taxiDispatchingService.getActiveTaxiOrders().remove(order);
        }
    }

    public long getDriverId() {
        return driverId;
    }

    public void setDriverId(long driverId) {
        this.driverId = driverId;
    }

    public Set<TaxiOrder> getTaxiDriverOrders() {
        return taxiDriverOrders;
    }

    public void setTaxiDriverOrders(Set<TaxiOrder> taxiDriverOrders) {
        this.taxiDriverOrders = taxiDriverOrders;
    }
}

class DriverIdGenerator {
    private static AtomicLong id = new AtomicLong(0);

    public static long nextId() {
        long next = id.incrementAndGet();
        return next;
    }
}