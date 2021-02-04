package com.stefura.mentorsplatform.repositories;

import com.stefura.mentorsplatform.models.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {
}
