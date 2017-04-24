package lv.emils.taxi_dispatching_service;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Emils on 24.04.2017.
 */
public class TaxiOrder {
    private long orderId;
    private String addressTo;
    private String addressFrom;
    private boolean accepted;
    private boolean completed;

    public TaxiOrder(String addressTo, String addressFrom) {
        this.orderId = OrderIdGenerator.nextId();
        this.addressTo = addressTo;
        this.addressFrom = addressFrom;
    }

    @Override
    public String toString() {
        return "TaxiOrder{" +
                "orderId=" + orderId +
                ", addressTo='" + addressTo + '\'' +
                ", addressFrom='" + addressFrom + '\'' +
                ", accepted=" + accepted +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaxiOrder taxiOrder = (TaxiOrder) o;

        if (orderId != taxiOrder.orderId) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + addressTo.hashCode();
        result = 31 * result + addressFrom.hashCode();
        result = 31 * result + (accepted ? 1 : 0);
        result = 31 * result + (completed ? 1 : 0);
        return result;
    }

    public String getAddressTo() {
        return addressTo;
    }

    public void setAddressTo(String addressTo) {
        this.addressTo = addressTo;
    }

    public String getAddressFrom() {
        return addressFrom;
    }

    public void setAddressFrom(String addressFrom) {
        this.addressFrom = addressFrom;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}

class OrderIdGenerator {
    private static AtomicLong id = new AtomicLong(0);

    public static long nextId() {
        long next = id.incrementAndGet();
        return next;
    }
}