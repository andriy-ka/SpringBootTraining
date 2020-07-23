package andriy.kachur.controllers;

import andriy.kachur.domain.Message;
import andriy.kachur.reposetories.MessageRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
    public class Main {
    @Autowired
    private MessageRepos messageRepos;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Message> messages = messageRepos.findAll();
        model.put("messages", messages);
        return "main";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        Message message = new Message(text, tag);
        messageRepos.save(message);
        Iterable<Message> messages = messageRepos.findAll();
        model.put("messages", messages);
        return "main";
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Message> messages;
        if (filter != null && !filter.isEmpty()) {
            messages = messageRepos.findByTag(filter);
        } else {
            messages = messageRepos.findAll();
        }
        model.put("messages", messages);
        return "main";
    }

}
