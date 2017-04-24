package lv.emils.taxi_dispatching_service;

import java.util.Set;

public class TaxiDispatchingService {

    private static Set<TaxiOrder> activeTaxiOrders;
    private static Set<TaxiOrder> availableOrders;
    private static Set<TaxiDriver> taxiDrivers;
    private static Set<TaxiUser> taxiUsers;

    public synchronized static Set<TaxiDriver> getTaxiDrivers() {
        return taxiDrivers;
    }

    public synchronized static void setTaxiDrivers(Set<TaxiDriver> taxiDrivers) {
        TaxiDispatchingService.taxiDrivers = taxiDrivers;
    }

    public synchronized static Set<TaxiUser> getTaxiUsers() {
        return taxiUsers;
    }

    public synchronized static void setTaxiUsers(Set<TaxiUser> taxiUsers) {
        TaxiDispatchingService.taxiUsers = taxiUsers;
    }

    public static Set<TaxiOrder> getAvailableOrders() {
        return availableOrders;
    }

    public static void setAvailableOrders(Set<TaxiOrder> availableOrders) {
        TaxiDispatchingService.availableOrders = availableOrders;
    }

    public synchronized Set<TaxiOrder> getActiveTaxiOrders() {
        return activeTaxiOrders;
    }

    public synchronized void setActiveTaxiOrders(Set<TaxiOrder> taxiOrders) {
        this.activeTaxiOrders = taxiOrders;
    }
}
