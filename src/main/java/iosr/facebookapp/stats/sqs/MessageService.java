package iosr.facebookapp.stats.sqs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;

@Service
public class MessageService {

    private Logger log = LoggerFactory.getLogger(MessageService.class);

    @JmsListener(destination = "${aws.sqs.queue.name}")
    public void createPost(String requestJSON) throws JMSException {
        log.info("Received ");
        log.info(requestJSON);
    }

}
