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
        log.info(sqsMessage.getMessage());
        PostMessage postMessage = PostMessage.fromJSON(sqsMessage.getMessage());
        processMessage(postMessage);
    }

    private void processMessage(PostMessage postMessage) throws ParseException {
        Post post = postFromSqsMessage(postMessage);
        postRepository.save(post);
    }

    private Post postFromSqsMessage(PostMessage postMessage) throws ParseException {
        return new Post(postMessage.getId(), postMessage.getLikes(), postMessage.getComments(), postMessage.getShares(), postMessage.getLink(), postMessage.getCreatedTimeDate());
    }
}
