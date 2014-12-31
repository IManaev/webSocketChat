package ru.spb.iac.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.*;
import org.springframework.scheduling.*;
import org.springframework.scheduling.concurrent.*;
import org.springframework.security.access.prepost.*;
import org.springframework.stereotype.*;
import ru.spb.iac.model.*;

import javax.annotation.*;
import java.security.*;
import java.util.*;

/**
 * Created by manaev on 24.12.14.
 */
@Controller
public class TestController {

    @Autowired private SimpMessagingTemplate template;

    private TaskScheduler scheduler = new ConcurrentTaskScheduler();
    private List<Stock> stockPrices = new ArrayList<Stock>();
    private Random rand = new Random(System.currentTimeMillis());

    /**
     * Iterates stock list, update the price by randomly choosing a positive
     * or negative percentage, then broadcast it to all subscribing clients
     */
    private void updatePriceAndBroadcast() {
        for(Stock stock : stockPrices) {
            double chgPct = rand.nextDouble() * 5.0;
            if(rand.nextInt(2) == 1) chgPct = -chgPct;
            stock.setPrice(stock.getPrice() + (chgPct / 100.0 * stock.getPrice()));
            stock.setTime(new Date());
        }
        template.convertAndSend("/topic/price", stockPrices);
    }

    /**
     * Invoked after bean creation is complete, this method will schedule
     * updatePriceAndBroacast every 1 second
     */
    @PostConstruct
    private void broadcastTimePeriodically() {
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override public void run() {
                updatePriceAndBroadcast();
            }
        }, 10000);
    }

    /**
     * Handler to add one stock
     */
    @PreAuthorize("hasRole('ROLE_USER')")
    @MessageMapping("/addStock")
    public void addStock(Stock stock,Principal principal) throws Exception {
        stockPrices.add(stock);
        updatePriceAndBroadcast();
    }

    /**
     * Handler to remove all stocks
     */
    @PreAuthorize("hasRole('ROLE_USER')")
    @MessageMapping("/removeAllStocks")
    public void removeAllStocks(Principal principal) {
        stockPrices.clear();
        updatePriceAndBroadcast();
    }
}
