package com.pltfhs.car.repository;

import com.pltfhs.car.entity.Comments;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {

    Comments findByUserIdAndCommentId(long userId, long commentId);

    List<Comments> findByEntityIdAndEntityType_typeId(long entityId, int typeId);
}
