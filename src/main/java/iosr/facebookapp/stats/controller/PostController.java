package iosr.facebookapp.stats.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import iosr.facebookapp.stats.controller.dto.PostDTO;
import iosr.facebookapp.stats.entity.Post;
import iosr.facebookapp.stats.logging.Log;
import iosr.facebookapp.stats.logging.TimeLog;
import iosr.facebookapp.stats.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path="/posts")
public class PostController {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private Logger log = LoggerFactory.getLogger(PostController.class);

    private static final int HOURS_RANGE = 8;

    @Autowired
    private PostRepository postRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public ResponseEntity<?> addPost(@RequestBody PostDTO post) throws JsonProcessingException {
        Post created = new Post(post.getPostId(), post.getLikes(), post.getComments(), post.getShares(), post.getLink(), post.getCreatedTime());
        long start = System.currentTimeMillis();
        created = postRepository.save(created);
        long end = System.currentTimeMillis();
        log.info(Log.getLog(new TimeLog("savePost", end - start)));
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value="/all")
    public @ResponseBody Iterable<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/topByLikes")
    public ResponseEntity<?> getTopPostsByLikes() throws JsonProcessingException {
        long start = System.currentTimeMillis();
        List<Post> ranking = postRepository.findTop10ByCreatedTimeAfterOrderByLikesDesc(getRestrainingDate());
        long end = System.currentTimeMillis();
        log.info(Log.getLog(new TimeLog("getRankingByLikes", end - start)));
        return new ResponseEntity<>(ranking, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/topByShares")
    public ResponseEntity<?> getTopPostsByShares() throws JsonProcessingException {
        long start = System.currentTimeMillis();
        List<Post> ranking = postRepository.findTop10ByCreatedTimeAfterOrderBySharesDesc(getRestrainingDate());
        long end = System.currentTimeMillis();
        log.info(Log.getLog(new TimeLog("getRankingByShares", end - start)));
        return new ResponseEntity<>(ranking, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/topByComments")
    public ResponseEntity<?> getTopPostsByComments() throws JsonProcessingException {
        long start = System.currentTimeMillis();
        List<Post> ranking = postRepository.findTop10ByCreatedTimeAfterOrderByCommentsDesc(getRestrainingDate());
        long end = System.currentTimeMillis();
        log.info(Log.getLog(new TimeLog("getRankingByComments", end - start)));
        return new ResponseEntity<>(ranking, HttpStatus.OK);
    }

    private Date getRestrainingDate() {
        Date currentDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.HOUR, -1 * HOURS_RANGE);
        return cal.getTime();
    }
}
