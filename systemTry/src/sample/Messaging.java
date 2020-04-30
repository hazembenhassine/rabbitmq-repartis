package sample;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Messaging {
    ConnectionFactory factory = new ConnectionFactory();
    public void receive(TextArea textArea, int n){
        try {

            Connection connection = factory.newConnection();
            Channel channel = null;
            channel = connection.createChannel();
            channel.exchangeDeclare("Exchange1", "direct");
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, "Exchange1", Integer.toString(n));

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");

                textArea.setText(message);
            };

            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });


        } catch (IOException e) {
            System.out.println("Receiving IO Problem");
        } catch (TimeoutException e) {
            System.out.println("Receiving Problem TimeOut");
        }
    }

    public void send(String message, int n){
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare("Exchange1", "direct");

            channel.basicPublish("Exchange1", Integer.toString(n), null, message.getBytes("UTF-8"));

        }catch (Exception e) {
            System.out.println("exception caught ");
        }
    }

}
