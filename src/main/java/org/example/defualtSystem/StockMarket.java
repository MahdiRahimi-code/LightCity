package org.example.defualtSystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class StockMarket {
    private Map<String, Double> users;

    public StockMarket() {
        users = new HashMap<>();
    }

    public synchronized void depositCapital(String userName, double amount) {
        if (users.containsKey(userName)) {
            double currentCapital = users.get(userName);
            users.put(userName, currentCapital + amount);
        } else {
            users.put(userName, amount);
        }
    }

    public synchronized void registerUser(String userName, double initialCapital) {
        if (!users.containsKey(userName)) {
            users.put(userName, initialCapital);
        }
    }

    public synchronized double getDetail(String userName) {
        if (users.containsKey(userName)) {
            return users.get(userName);
        } else {
            return -1;
        }
    }

    public synchronized double withdrawCapital(String userName, double amount) {
        if (users.containsKey(userName)) {
            users.put(userName, users.get(userName)-amount);
            return amount;
        } else {
            return -1;
        }
    }

    public void startMarketSimulation() {
        Thread marketThread = new Thread(() -> {
            Random random = new Random();
            while (true) {
                double change = (random.nextDouble() * 0.06) - 0.03;
                for (String userName : users.keySet()) {
                    double capital = users.get(userName);
                    double newCapital = capital + capital * change;
                    if (newCapital < 0) {
                        newCapital = 0;
                    }
                    users.put(userName, newCapital);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        marketThread.start();
    }
}
