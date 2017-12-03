package iosr.facebookapp.stats.controller;

import iosr.facebookapp.stats.controller.dto.PostDTO;
import iosr.facebookapp.stats.entity.Post;
import iosr.facebookapp.stats.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public ResponseEntity<?> addPost(@RequestBody PostDTO post) {
        Post created = new Post();

        created.setLikesCount(post.getLikesCount());
        created.setCommentsCount(post.getCommentsCount());
        created.setSharesCount(post.getSharesCount());
        created = postRepository.save(created);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value="/all")
    public @ResponseBody Iterable<Post> getAllPosts() {
        return postRepository.findAll();
    }

}
