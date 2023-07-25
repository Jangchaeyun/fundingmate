package com.fund.fundingmate.domain.banner.repository;

import com.fund.fundingmate.domain.banner.entity.Banner;
import com.fund.fundingmate.domain.banner.entity.QBanner;
import com.fund.fundingmate.domain.investment.entity.Investment;
import com.fund.fundingmate.global.file.entity.QFile;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.beans.Expression;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Repository
public class BannerRepository{
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    public List<Tuple> findBannerAll() throws ParseException {
        QBanner banner = QBanner.banner;
        QFile file = QFile.file;

        return jpaQueryFactory.select(banner,file)
                .from(banner)
                .join(file)
                .on(banner.bn_imgno.eq(file.fileId))
                .where(Expressions.currentDate().between(banner.bn_startdate, banner.bn_enddate))
                .orderBy(banner.bnShowno.asc())
                .fetch();
    }
}
