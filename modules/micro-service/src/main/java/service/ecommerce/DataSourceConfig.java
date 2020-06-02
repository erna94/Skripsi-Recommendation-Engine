package service.ecommerce;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourceConfig {
     
    @Bean
    public DataSource getDataSource() {
    	DriverManagerDataSource dataSource = new DriverManagerDataSource();
    	 
    	// mengkofigurasi database untuk mengenali letak database kita yang akan
    	// dihubungi
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setUrl(
          "jdbc:mysql://localhost:3306/db_product"); 
         
        return dataSource;
    }
    
  }
