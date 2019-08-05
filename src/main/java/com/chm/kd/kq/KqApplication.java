package com.chm.kd.kq;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author caihongming
 */
@SpringBootApplication
@MapperScan("com.chm.kd.kq.mapper")
@EnableTransactionManagement
public class KqApplication {

  /**
   * 分页插件
   */
  @Bean
  public PaginationInterceptor paginationInterceptor() {
    return new PaginationInterceptor();
  }

  /**
   * SQL执行效率插件
   */
  @Bean
  public PerformanceInterceptor performanceInterceptor() {
    return new PerformanceInterceptor();
  }

  public static void main(String[] args) {
    SpringApplication.run(KqApplication.class, args);
  }
}
