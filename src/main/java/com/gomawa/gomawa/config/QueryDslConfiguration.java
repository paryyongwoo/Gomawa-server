package com.gomawa.gomawa.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * querydsl에서 쿼리에 사용되는 JPAQueryFactory를 빈으로 생성해주는 설정
 */
@Configuration
public class QueryDslConfiguration {

    @PersistenceContext
    private EntityManager em;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(em);
    }
}
