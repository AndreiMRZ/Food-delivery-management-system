package BusinessLogic;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

public class Order implements Serializable, Comparable<Order> {

    private int orderId;
    private int clientId;
    private Calendar orderDate;

    public Order(int orderId, int clientId, Calendar orderDate){
        this.orderId = orderId;
        this.clientId = clientId;
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getClientId() {
        return clientId;
    }

    public Calendar getOrderDate() {
        return orderDate;
    }

    @Override
    public int compareTo(Order o) {
        return Integer.compare(o.getOrderId(), orderId);
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setOrderDate(Calendar orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        if(order.getOrderId() == this.orderId && order.getOrderDate().equals(this.orderDate) && order.getClientId() == this.clientId)
        return true;
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, clientId, orderDate);
    }
}
