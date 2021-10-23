package order;

public class Order {
    private String userId;
    private String clorderId;
    private Action action;
    private int instrumentId;
    private char side;
    private long price;
    private int amount;
    private int amount_rest;

    public enum Action {
        POST,
        REMOVE,
        EXECUTE;

        public static Action getActionByIndex(int index) {
            for (Action act : Action.values()) {
                if (act.ordinal() == index)
                    return act;
            }
            throw new IllegalArgumentException("Action not found");
        }
    }

    public Order(String userId, String clorderId, Action action, int instrumentId, char side, long price, int amount, int amount_rest) {
        this.userId = userId;
        this.clorderId = clorderId;
        this.action = action;
        this.instrumentId = instrumentId;
        this.side = side;
        this.price = price;
        this.amount = amount;
        this.amount_rest = amount_rest;
    }

    public Action getAction() {
        return action;
    }

    public int getInstrumentId() {
        return instrumentId;
    }

    public char getSide() {
        return side;
    }

    public long getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public static Order parseOrder(String orderDesc) {
        String[] orderDescArr = orderDesc.split(";");

        return new Order(orderDescArr[0],
                         orderDescArr[1],
                         Action.getActionByIndex(Integer.parseInt(orderDescArr[2])),
                         Integer.parseInt(orderDescArr[3]),
                         orderDescArr[4].charAt(0),
                         Long.parseLong(orderDescArr[5]),
                         Integer.parseInt(orderDescArr[6]),
                         Integer.parseInt(orderDescArr[7]));
    }
}

