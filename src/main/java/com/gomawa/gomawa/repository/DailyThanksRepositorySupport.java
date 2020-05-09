package com.gomawa.gomawa.repository;

import com.gomawa.gomawa.entity.DailyThanks;
import com.gomawa.gomawa.entity.Member;
import com.gomawa.gomawa.entity.QDailyThanks;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Repository
public class DailyThanksRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public DailyThanksRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(DailyThanks.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    /**
     * 오늘 작성한 DailyThanks 가져오기
     */
    public DailyThanks getDailyThanks(Member member) {
        QDailyThanks dailyThanks = QDailyThanks.dailyThanks;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        String todayStr = format.format(new Date());

        DailyThanks result = jpaQueryFactory.selectFrom(dailyThanks)
                .where(dailyThanks.regDate.stringValue().like(todayStr + "%"))
                .fetchOne();

        return result;
    }
}
