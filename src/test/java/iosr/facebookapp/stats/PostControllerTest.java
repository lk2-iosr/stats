package iosr.facebookapp.stats;

import iosr.facebookapp.stats.controller.PostController;
import iosr.facebookapp.stats.entity.Post;
import iosr.facebookapp.stats.repository.PostRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.mockito.Matchers.any;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PostController.class, secure = false)
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostRepository postRepository;

    private static final List<Post> mockPosts;

    private static final String expected = "[" +
            "{\"" +
            "postId\":\"e\",\"" +
            "likes\":5,\"" +
            "comments\":55,\"" +
            "shares\":555,\"" +
            "link\":\"http://e.com\",\"" +
            "createdTime\":1513338545251" +
            "}," +
            "{\"" +
            "postId\":\"d\",\"" +
            "likes\":4,\"" +
            "comments\":44,\"" +
            "shares\":444,\"" +
            "link\":\"http://d.com\",\"" +
            "createdTime\":1513338545251" +
            "}," +
            "{\"" +
            "postId\":\"c\",\"" +
            "likes\":3,\"" +
            "comments\":33,\"" +
            "shares\":333,\"" +
            "link\":\"http://c.com\",\"" +
            "createdTime\":1513338545251" +
            "}" +
            "]\n";

    static {
        Date date;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(1513338545251L);
        date = calendar.getTime();
        mockPosts = new ArrayList<>();
        mockPosts.add(new Post("e",5,55,555,"http://e.com", date));
        mockPosts.add(new Post("d",4,44,444,"http://d.com", date));
        mockPosts.add(new Post("c",3,33,333,"http://c.com", date));
    }

    @Test
    public void getPostsRankingByLikes() throws Exception{
        Mockito.when(postRepository.findTop10ByCreatedTimeAfterOrderByLikesDesc(any(Date.class))).thenReturn(mockPosts);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/posts/topByLikes").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();



        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void getPostsRankingByShares() throws Exception{
        Mockito.when(postRepository.findTop10ByCreatedTimeAfterOrderBySharesDesc(any(Date.class))).thenReturn(mockPosts);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/posts/topByShares").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void getPostsRankingByComments() throws Exception{
        Mockito.when(postRepository.findTop10ByCreatedTimeAfterOrderByCommentsDesc(any(Date.class))).thenReturn(mockPosts);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/posts/topByComments").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }
}
