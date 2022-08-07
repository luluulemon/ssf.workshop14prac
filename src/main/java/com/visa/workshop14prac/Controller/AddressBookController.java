package com.visa.workshop14prac.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.visa.workshop14prac.Model.Contact;
import com.visa.workshop14prac.Service.ContactsRedis;
import com.visa.workshop14prac.Service.Util;

@Controller
public class AddressBookController {
    
    @Autowired
    ContactsRedis service;
    
    @Autowired      // autowire Args to get dataDir path
    ApplicationArguments args;
    

    @RequestMapping("/saved")
    public String saveDetails(@ModelAttribute Contact contact, Model model){       

        String dataDirectory = args.getOptionValues("dataDir").get(0);

        contact.generateId();
        String fileName = dataDirectory + "/" + contact.getId();

        Util.saveContact(fileName, contact);
        model.addAttribute("Contact", contact);

        Boolean existing = false;
        model.addAttribute("Existing", existing);

        return "saveConfirmation";
    }


    @GetMapping("contacts/{id}")
    public String getDetails(@PathVariable String id, Model model){
        
        String dataDirectory = args.getOptionValues("dataDir").get(0);
        Contact contact = new Contact();
        contact.setId(id);

        String fileName = dataDirectory + "/" + id;
        Util.loadContact(fileName, contact);

        model.addAttribute("Contact", contact);
        Boolean existing = true;
        model.addAttribute("Existing", existing);

        return "saveConfirmation";

    }



    @GetMapping("/redis")
    public String getRedis(Model model){
        model.addAttribute("Contact", new Contact());
        return "Redis";
    }


    @PostMapping("/saveToRedis")
    public String savetoRedis(@ModelAttribute Contact contact, Model model){       

        contact.generateId();   // give new Contact an Id
        Boolean existing = false;

        service.saveContact(contact);
        model.addAttribute("Contact", contact);
        model.addAttribute("Existing", existing);

        return "saveConfirmation";
    }


    @GetMapping("/redis/{id}")
    public String GetFromRedis(@PathVariable String id, Model model){       

        Boolean existing = true;

        Contact contact = service.getContact(id);
        model.addAttribute("Contact", contact);
        model.addAttribute("Existing", existing);

        return "saveConfirmation";
    }



}
