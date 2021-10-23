package order.manager.impl;

import order.manager.OrderManager;

import java.util.Map;

public class BuyOrderManager extends OrderManager {

    public BuyOrderManager() {
        super(0L);
    }

    @Override
    public Map.Entry<Long, Integer>  getBestPriceEntry(int instrumentId) {
        return orderRepo.get(instrumentId).lastEntry();
    }

}
