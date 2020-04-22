package com.gomawa.gomawa.repository;

import com.gomawa.gomawa.entity.QShareItem;
import com.gomawa.gomawa.entity.ShareItem;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

/**
 * querydsl을 사용하기위한 레파지토리 클래스
 */
@Repository
public class ShareItemRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;
    private final int num = 20;

    public ShareItemRepositorySupport(JPAQueryFactory queryFactory) {
        super(ShareItem.class);
        this.queryFactory = queryFactory;
    }

    /**
     * 모든 shareItem 목록을 가져오기
     * @return
     */
    public QueryResults<ShareItem> findAll(int page) {
        QShareItem shareItem = QShareItem.shareItem;
        int offset = page * num;
        int limit = num;
        System.out.println("limit = " + limit + ", offset = " + offset);
        return queryFactory.selectFrom(shareItem)
                .orderBy(shareItem.regDate.desc())
                .offset(offset)
                .limit(limit)
                .fetchResults();
    }
}
