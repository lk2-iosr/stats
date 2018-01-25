package iosr.facebookapp.stats.sqs;

import com.fasterxml.jackson.databind.ObjectMapper;
import iosr.facebookapp.stats.entity.Post;
import iosr.facebookapp.stats.logging.UpdatePostTimeLog;
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

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    PostRepository postRepository;

    @JmsListener(destination = "${aws.sqs.queue.name}")
    public void updatePost(String requestJSON) throws IOException, ParseException {
        long start = System.currentTimeMillis();
        SqsMessage sqsMessage = SqsMessage.fromJSON(requestJSON);
        PostMessage postMessage = PostMessage.fromJSON(sqsMessage.getMessage());
        processMessage(postMessage);
        long end = System.currentTimeMillis();
        UpdatePostTimeLog updatePostTimeLog = new UpdatePostTimeLog(end - start);
        log.info(OBJECT_MAPPER.writeValueAsString(updatePostTimeLog));
    }

    private void processMessage(PostMessage postMessage) throws ParseException {
        Post post = postFromSqsMessage(postMessage);
        postRepository.save(post);
    }

    private Post postFromSqsMessage(PostMessage postMessage) throws ParseException {
        return new Post(postMessage.getId(), postMessage.getLikes(), postMessage.getComments(), postMessage.getShares(), postMessage.getLink(), postMessage.getCreatedTimeDate());
    }
}
