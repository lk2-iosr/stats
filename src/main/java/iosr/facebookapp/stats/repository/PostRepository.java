package iosr.facebookapp.stats.repository;

import iosr.facebookapp.stats.entity.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Integer> {
}
