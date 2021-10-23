package order.manager.impl;

import order.manager.OrderManager;

import java.util.Map;

public class SellOrderManager extends OrderManager {

    public SellOrderManager() {
        super(999999999999999999L);
    }

    @Override
    public Map.Entry<Long, Integer>  getBestPriceEntry(int instrumentId) {
        return orderRepo.get(instrumentId).firstEntry();
    }

}
