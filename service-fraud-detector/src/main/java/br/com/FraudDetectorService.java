package br.com;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class FraudDetectorService {

    public static void main(String[] args){
        var fraudService = new FraudDetectorService();
        try(var service = new KafkaService<>(FraudDetectorService.class.getName(), "ECOMMERCE_NEW_ORDER", fraudService::parse, Order.class, new HashMap<>())){
            service.run();
        }
    }

    private final KafkaDispatcher<Order> orderKafkaDispatcher = new KafkaDispatcher<>();

    private void parse(ConsumerRecord<String, Order> record) throws ExecutionException, InterruptedException {
        System.out.println("---------------------------------------");
        System.out.println("Processing new order, checking fraud");
        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println(record.partition());
        System.out.println(record.offset());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        var order = record.value();
        System.out.println(order);
        System.out.println(order.getAmount());

        System.out.println("Pre check");
        if(isFraud(order)){
            System.out.println("Order is fraud!!! order: " + order);
            orderKafkaDispatcher.send("ECOMMERCE_ORDER_REJECTED", order.getUserID(), order);
        } else {
            System.out.println("Order aproved! order: " + order);
            orderKafkaDispatcher.send("ECOMMERCE_ORDER_APPROVED", order.getUserID(), order);
        }

    }

    private boolean isFraud(Order order) {
        return order.getAmount().compareTo(new BigDecimal("4500")) >= 0;
    }
}
