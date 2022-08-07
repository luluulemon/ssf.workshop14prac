package com.visa.workshop14prac.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.visa.workshop14prac.Model.Contact;

@Service
public class ContactsRedis {
    private static final Logger logger = LoggerFactory.getLogger(ContactsRedis.class);

    @Autowired
    private RedisTemplate<String, Object> template;

    public void saveContact(Contact contact){
        template.opsForValue().set(contact.getId(), contact);
    }


    public Contact getContact(String id){
        Contact contact = (Contact) template.opsForValue().get(id);
        logger.info(">>> " + contact.getEmail());
        return contact;
    }
}
