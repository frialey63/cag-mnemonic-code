package org.pjp.cag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

public final class Order {

    private static final Logger LOGGER = LoggerFactory.getLogger(Order.class);

    public static Order create(boolean query, String orderNumberStr, String addressStr, String modifierStr) {

        try {
            OrderNumber orderNumber = OrderNumber.valueOf(orderNumberStr);

            if (addressStr == null) {
                return new Order(query, orderNumber);
            } else {
                int address = Integer.parseInt(addressStr);          // will parse because matched to number in the regex

                if (modifierStr == null) {
                    return new Order(query, orderNumber, address);
                } else {
                    int modifier = Integer.parseInt(modifierStr);   // will parse because matched to number in the regex

                    return new Order(query, orderNumber, address, modifier);
                }
            }
        } catch (IllegalArgumentException e) {
            LOGGER.error("Caught IllegalArgumentException while attempting to look-up the OrderNumber by value", e);
            throw e;
        }
    }

    // CHECKSTYLE:OFF encapsulation

    final boolean query;

    final OrderNumber orderNumber;

    final int address;  // 0 - 999

    final int modifier; // 0 - 9

    // CHECKSTYLE:ON

    private Order(boolean query, OrderNumber orderNumber, int address, int modifier) {
        super();
        this.query = query;
        this.orderNumber = Preconditions.checkNotNull(orderNumber, "orderNumber cannot be null");
        this.address = address;
        this.modifier = modifier;
    }

    private Order(boolean query, OrderNumber orderNumber, int address) {
        this(query, orderNumber, address, 0);
    }

    private Order(boolean query, OrderNumber orderNumber) {
        this(query, orderNumber, 0, 0);
    }

    @Override
    public String toString() {
        return "Order [query=" + query + ", orderNumber=" + orderNumber + ", address=" + address + ", modifier=" + modifier + "]";
    }

    // CHECKSTYLE:OFF auto-generated

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + address;
        result = prime * result + modifier;
        result = prime * result + ((orderNumber == null) ? 0 : orderNumber.hashCode());
        result = prime * result + (query ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Order other = (Order) obj;
        if (address != other.address)
            return false;
        if (modifier != other.modifier)
            return false;
        if (orderNumber != other.orderNumber)
            return false;
        if (query != other.query)
            return false;
        return true;
    }

    // CHECKSTYLE:ON

}
