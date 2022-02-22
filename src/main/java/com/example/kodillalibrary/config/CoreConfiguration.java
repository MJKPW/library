package com.example.kodillalibrary.config;

import com.example.kodillalibrary.mapping.BorrowedMapper;
import com.example.kodillalibrary.mapping.CopyMapper;
import com.example.kodillalibrary.mapping.ReaderMapper;
import com.example.kodillalibrary.mapping.TitleMapper;
import com.example.kodillalibrary.service.DbService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class CoreConfiguration {

    @Bean
    @Scope("singleton")
    public DbService createDbService() {
        return new DbService();
    }

    @Bean
    @Scope("singleton")
    public BorrowedMapper createBorrowedMapper() {
        return new BorrowedMapper();
    }

    @Bean
    @Scope("singleton")
    public CopyMapper createCopyMapper() {
        return new CopyMapper();
    }

    @Bean
    @Scope("singleton")
    public ReaderMapper createReaderMapper() {
        return new ReaderMapper();
    }

    @Bean
    @Scope("singleton")
    public TitleMapper createTitleMapper() {
        return new TitleMapper();
    }

}
