package org.axonframework.samples.trader.app.query;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.samples.trader.app.api.tradeitem.TradeItemCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jettro Coenradie
 */
@Component
public class TradeItemListener {
    private MongoHelper mongo;


    @EventHandler
    public void handleTradeItemCreatedEvent(TradeItemCreatedEvent event) {
        DBObject tradeItemEntry = BasicDBObjectBuilder.start()
                .add("identifier", event.getTradeItemIdentifier().toString())
                .add("name", event.getTradeItemName())
                .add("value", event.getTradeItemValue())
                .add("amountOfShares", event.getAmountOfShares())
                .add("tradeStarted", true)
                .get();
        mongo.tradeItems().insert(tradeItemEntry);
    }

    @Autowired
    public void setMongoHelper(MongoHelper mongoHelper) {
        this.mongo = mongoHelper;
    }
}
