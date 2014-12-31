package ru.spb.iac.service.mongo;

import com.mongodb.*;
import lombok.extern.log4j.*;
import org.springframework.data.mongodb.core.*;

import javax.annotation.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by manaev on 15.11.14.
 */
@Log4j
public class CommonMongoService {

    private String host;
    private Integer port;
    private List<String> dbNames;

    private ConcurrentHashMap<String, MongoOperations> operationsMap = new ConcurrentHashMap<String, MongoOperations>();

    public MongoOperations getMongoOperator(String dbName){
        return operationsMap.get(dbName);
    }

    @PostConstruct
    public void initOperationsMap() {
        try {
            MongoClient mongoClient = new MongoClient(host, port);
            for (String db : dbNames) {
                operationsMap.put(db, new MongoTemplate(new SimpleMongoDbFactory(mongoClient, db)));
            }
        } catch (UnknownHostException e) {
            log.error(e.getMessage(), e);
        }
    }

    public List<String> getDbNames() {
        return dbNames;
    }

    public void setDbNames(List<String> dbNames) {
        this.dbNames = dbNames;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
