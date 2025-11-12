package java10x.devnoah.desafiocinereview.repository;

import java10x.devnoah.desafiocinereview.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {
    List<Review> findByAutor(String autor);
}
