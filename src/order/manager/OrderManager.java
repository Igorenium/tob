package order.manager;

import order.Order;

import java.util.*;

public abstract class OrderManager {
    protected Map<Integer, NavigableMap<Long, Integer>> orderRepo;
    private long dummyPrice;

    public OrderManager(long dummyPrice) {
        this.dummyPrice = dummyPrice;
        orderRepo = new HashMap<>();
    }

    public void handleOrder(Order order) {
        Map.Entry<Long, Integer>  initBestPriceEntry = getBestPriceEntryClone(order.getInstrumentId());

        switch (order.getAction()) {
            case POST:
                addOrderToRepo(order);
                break;
            default:
                removeOrderFromRepo(order);
                break;
        }

        Map.Entry<Long, Integer>  finalBestPriceEntry = getBestPriceEntryClone(order.getInstrumentId());

        if (!initBestPriceEntry.equals(finalBestPriceEntry))
            printBestPrice(order, finalBestPriceEntry);
    }

    public Map.Entry<Long, Integer> getBestPriceEntryClone(int instrumentId) {
        long key = dummyPrice;
        int value = 0;
        if (orderRepo.containsKey(instrumentId) && !orderRepo.get(instrumentId).isEmpty()) {
            Map.Entry<Long, Integer> bestPriceEntry = getBestPriceEntry(instrumentId);
            key = bestPriceEntry.getKey();
            value = bestPriceEntry.getValue();
        }
        return new AbstractMap.SimpleEntry<>(key, value);
    }

    public abstract Map.Entry<Long, Integer> getBestPriceEntry(int instrumentId);

    private void addOrderToRepo(Order order) {
        if (!orderRepo.containsKey(order.getInstrumentId()))
            orderRepo.put(order.getInstrumentId(), new TreeMap<>());

        int amount = order.getAmount();
        Map<Long, Integer> prices = orderRepo.get(order.getInstrumentId());
        if (prices.containsKey(order.getPrice()))
            amount += prices.get(order.getPrice());

        prices.put(order.getPrice(), amount);
    }

    private void removeOrderFromRepo(Order order) {
        Map<Long, Integer> prices = orderRepo.get(order.getInstrumentId());

        if (prices.get(order.getPrice()) != order.getAmount())
            prices.put(order.getPrice(), prices.get(order.getPrice()) - order.getAmount());
        else
            prices.remove(order.getPrice());
    }

    private void printBestPrice(Order order, Map.Entry<Long, Integer>  bestPriceEntry) {
        System.out.printf("%d;%c;%d;%d%n", order.getInstrumentId(), order.getSide(), bestPriceEntry.getKey(), bestPriceEntry.getValue());
    }
}
