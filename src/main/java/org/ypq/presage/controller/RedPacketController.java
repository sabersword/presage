package org.ypq.presage.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedPacketController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedPacketController.class);

    @RequestMapping("getOneRedPacket")
    public String getOneRedPacket() {
        return "aaabcd";
    }



}
