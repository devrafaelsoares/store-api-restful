package br.devrafaelsoares.storeapirestful.domain.order;

public enum OrderStatus {
    WAITING_PAYMENT,
    PAID,
    SHIPPED,
    DELIVERED,
    CANCELED
}
