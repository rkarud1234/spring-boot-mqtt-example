package com.example.mqtt.config;

import org.eclipse.paho.client.mqttv3.*;

public class Mqtt {
    private static final String MQTT_CLIENT_ID = MqttAsyncClient.generateClientId();
    private static final String MQTT_BROKER_URL = "tcp://127.0.0.1:1883";

    private static IMqttClient instance;

    public static IMqttClient getInstance() {
        try {
            if (instance == null) {
                instance = new MqttClient(MQTT_BROKER_URL, MQTT_CLIENT_ID);
            }

            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);

            if (!instance.isConnected()) {
                instance.connect(options);
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }

        return instance;
    }

    private Mqtt() {

    }
}
