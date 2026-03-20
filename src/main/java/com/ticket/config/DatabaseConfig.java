package com.ticket.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * MyBatis 설정 클래스
 *
 * @MapperScan 범위를 실제 MyBatis Mapper 인터페이스가 위치한 패키지로만 한정합니다.
 * 와일드카드(com.ticket.*)로 지정하면 JPA Repository 인터페이스까지 스캔 대상에 포함되어
 * Spring Boot 3.x 에서 빈 등록 충돌이 발생합니다.
 *
 * MyBatis Mapper 패키지 목록:
 *   - com.ticket.booking.dao   (BookingMapper)
 *   - com.ticket.pay.dao       (PayMapper)
 *   - com.ticket.review.mapper (ReviewMapper)
 *   - com.ticket.showList.dao  (ShowListMapper)
 *   - com.ticket.test.dao      (TestMapper)
 *
 *
 *   기존에는 basePackages를 com.ticket.*으로 설정
 */
@MapperScan(basePackages = {
        "com.ticket.booking.dao",
        "com.ticket.pay.dao",
        "com.ticket.review.mapper",
        "com.ticket.showList.dao",
        "com.ticket.test.dao"
})
@Configuration
public class DatabaseConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        Resource[] res = new PathMatchingResourcePatternResolver()
                .getResources("classpath:mappers/*Mapper.xml");
        sessionFactory.setMapperLocations(res);

        return sessionFactory.getObject();
    }
}
