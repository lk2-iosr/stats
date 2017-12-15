package iosr.facebookapp.stats.repository;

import iosr.facebookapp.stats.entity.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface PostRepository extends CrudRepository<Post, String> {

    List<Post> findTop10ByCreatedTimeAfterOrderByLikesDesc(Date date);

    List<Post> findTop10ByCreatedTimeAfterOrderBySharesDesc(Date date);

    List<Post> findTop10ByCreatedTimeAfterOrderByCommentsDesc(Date date);
}
