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
    	
    	//setting aws
    	// username: ernbtpl/Goldenstick1
    	// host: skripsi.cw2h8gl6vykn.ap-southeast-1.rds.amazonaws.com:3306
    	
    	// setting local
    	// username: root/root
    	// host:localhost:3306
    	
    	String URL_HOST = "skripsi.cw2h8gl6vykn.ap-southeast-1.rds.amazonaws.com:3306";
    	
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("ernbtpl");
        dataSource.setPassword("Goldenstick1");
        dataSource.setUrl(
          "jdbc:mysql://" + URL_HOST + "/db_product"); 
         
        System.out.println("Connecting to URL HOST: >>>>>> " + URL_HOST);
        
        return dataSource;
    }
  }
