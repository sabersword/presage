package org.ypq.presage.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ypq.presage.dao.RedPacketDao;

import java.util.Timer;
import java.util.TimerTask;

@RestController
@Transactional
public class RedPacketController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedPacketController.class);
    @Autowired
    private RedPacketDao redPacketDao;

    private Timer timer = new Timer();
    private int quota = MAX_QUOTA;

    private static int MAX_QUOTA = 2;
    private static int INTERVAL = 120 * 1000;

    @RequestMapping("getOneRedPacket")
    public String getOneRedPacket(Integer score) {
        synchronized (RedPacketController.class) {
            if (quota >= MAX_QUOTA) {
                timer.schedule(new TimerTask(){
                    @Override
                    public void run() {
                        synchronized (RedPacketController.class) {
                            quota = MAX_QUOTA;
                        }
                    }
                }, INTERVAL);
            }
            quota--;
        }
        System.err.println("此时的count值" + quota);
        if (quota <= -1) {
            return "403";
        }

        String token = redPacketDao.queryRedPacket();
        if (token == null) {
            return "none";
        }

        redPacketDao.consumeRedPacket(token, score);
        return token;
    }

}
