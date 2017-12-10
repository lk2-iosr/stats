package iosr.facebookapp.stats.sqs;

import iosr.facebookapp.stats.entity.Post;
import iosr.facebookapp.stats.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;

@Service
public class MessageService {

    private Logger log = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    PostRepository postRepository;

    @JmsListener(destination = "${aws.sqs.queue.name}")
    public void updatePost(String requestJSON) throws IOException, ParseException {
        log.info("Received: \n" + requestJSON);
        SqsMessage sqsMessage = SqsMessage.fromJSON(requestJSON);
        processMessage(sqsMessage);
    }

    private void processMessage(SqsMessage sqsMessage) throws ParseException {
        Post post = postFromSqsMessage(sqsMessage);
        postRepository.save(post);
    }

    private Post postFromSqsMessage(SqsMessage sqsMessage) throws ParseException {
        return new Post(sqsMessage.getId(), sqsMessage.getLikes(), sqsMessage.getComments(), sqsMessage.getShares(), sqsMessage.getLink(), sqsMessage.getCreatedTimeDate());
    }
}
