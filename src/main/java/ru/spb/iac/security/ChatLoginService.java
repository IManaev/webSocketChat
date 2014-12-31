package ru.spb.iac.security;

import com.google.common.base.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;
import ru.spb.iac.security.user.*;
import ru.spb.iac.service.mongo.*;

/**
 * Created by manaev on 25.12.14.
 */
@Service("chatLoginService")
public class ChatLoginService implements UserDetailsService {
    @Autowired
    @Qualifier("commonMongoService")
    private CommonMongoService mongoService;

    @Value("${mongo.db}")
    private String mongoDbName;

    @Override
    public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException {
        if(!Strings.isNullOrEmpty(username)){
            ChatUser user = mongoService.getMongoOperator(mongoDbName).findOne(new Query(Criteria.where("username").is(username)), ChatUser.class, ChatUser.COLLECTION_NAME);
            if(user == null){
                return null;
            }else{
                return user;
            }
        }else{
            return null;
        }
    }
}
