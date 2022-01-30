package org.pjp.cag;

import com.google.common.base.Preconditions;

public class Order {

    public static Order create(boolean query, String orderNumberStr, String addressStr, String modifierStr) {

        try {
            OrderNumber orderNumber = OrderNumber.valueOf(orderNumberStr);

            if (addressStr == null) {
                return new Order(query, orderNumber);
            } else {
                int address = Integer.parseInt(addressStr);

                if (modifierStr == null) {
                    return new Order(query, orderNumber, address);
                } else {
                    int modifier = Integer.parseInt(modifierStr);

                    return new Order(query, orderNumber, address, modifier);
                }
            }
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e;
        }
    }

    // CHECKSTYLE:OFF encapsulation

    final boolean query;

    final OrderNumber orderNumber;

    final int address;  // 0 - 999

    final int modifier; // 0 - 9

    // CHECKSTYLE:ON

    public Order(boolean query, OrderNumber orderNumber, int address, int modifier) {
        super();
        this.query = query;
        this.orderNumber = Preconditions.checkNotNull(orderNumber, "orderNumber cannot be null");
        this.address = address;
        this.modifier = modifier;
    }

    public Order(boolean query, OrderNumber orderNumber, int address) {
        this(query, orderNumber, address, 0);
    }

    public Order(boolean query, OrderNumber orderNumber) {
        this(query, orderNumber, 0, 0);
    }

    @Override
    public String toString() {
        return "Order [query=" + query + ", orderNumber=" + orderNumber + ", address=" + address + ", modifier=" + modifier + "]";
    }

}
