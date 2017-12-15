package iosr.facebookapp.stats;

import iosr.facebookapp.stats.entity.Post;
import iosr.facebookapp.stats.repository.PostRepository;
import iosr.facebookapp.stats.sqs.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatsApplicationTests {

	@MockBean
	private PostRepository postRepository;

	@Autowired
	private MessageService messageService;

	@Test
	public void savePostToRepository() throws IOException, ParseException {
		given(postRepository.findOne("266358044374_10155983854704375")).willReturn(null);
		String message = "{\n" +
				"  \"Type\" : \"Notification\",\n" +
				"  \"MessageId\" : \"815fdee2-4127-5105-897e-8c4506eacbed\",\n" +
				"  \"TopicArn\" : \"arn:aws:sns:eu-west-1:1234:fetched-posts\",\n" +
				"  \"Message\" : \"{\\\"id\\\":\\\"266358044374_10155983854704375\\\",\\\"message\\\":\\\"Myślicie, że to pomoże? :O\\\",\\\"link\\\":\\\"https://www.fakt.pl/kobieta/plotki/patrycja-markowska-o-terapii-dla-par/7m7nzct?utm_source=fpfakt&utm_medium=social&utm_campaign=fanpage\\\",\\\"shares\\\":0,\\\"likes\\\":41,\\\"comments\\\":6,\\\"createdTime\\\":\\\"2017-12-10T14:00:00Z\\\"}\",\n" +
				"  \"Timestamp\" : \"2017-12-10T14:32:45.497Z\",\n" +
				"  \"SignatureVersion\" : \"1\",\n" +
				"  \"Signature\" : \"abcd\",\n" +
				"  \"SigningCertURL\" : \"https://xxx.pem\",\n" +
				"  \"UnsubscribeURL\" : \"https://xxx\"\n" +
				"}";
		messageService.updatePost(message);

		Mockito.verify(postRepository).save(Matchers.any(Post.class));
	}


}
