import order.Order;
import order.manager.impl.BuyOrderManager;
import order.manager.OrderManager;
import order.manager.impl.SellOrderManager;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TopOfTheBook {

    public static void main(String[] args) throws IOException {
        OrderManager buyOrderManager = new BuyOrderManager();
        OrderManager sellOrderManager = new SellOrderManager();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while (!(line = reader.readLine()).equals("exit")) {
                Order order = Order.parseOrder(line);
                OrderManager orderManager = (order.getSide() == 'B') ? buyOrderManager : sellOrderManager;
                orderManager.handleOrder(order);
            }
        }
    }
}

