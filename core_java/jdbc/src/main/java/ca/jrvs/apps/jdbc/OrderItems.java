package ca.jrvs.apps.jdbc;

import ca.jrvs.apps.jdbc.util.DataTransferObject;

public class OrderItems implements DataTransferObject {
    private long order_item_id;
    private long order_id;
    private long product_id;
    private int quantity;

    @Override
    public long getID() {
        return order_id;
    }
}
