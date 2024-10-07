package org.example.expert.domain.comment.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.comment.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.example.expert.domain.comment.entity.QComment.comment;

@Repository
@RequiredArgsConstructor
public class CommentQueryRepositoryImpl implements CommentQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Comment> findByTodoIdWithUsers(Long todoId) {
        return queryFactory
                .selectFrom(comment)
                .join(comment.user).fetchJoin()
                .where(comment.todo.id.eq(todoId))
                .fetch();
    }
}
