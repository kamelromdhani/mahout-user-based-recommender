package com.example;

import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mysql.cj.jdbc.MysqlDataSource;

@SpringBootApplication
public class MahoutUserBasedRecommenderApplication {

	public static void main(String[] args) throws IOException, TasteException {
		SpringApplication.run(MahoutUserBasedRecommenderApplication.class, args);
		
		try {
		
			MysqlDataSource dataSource = new MysqlDataSource();
	        dataSource.setServerName("localhost");
	        dataSource.setUser("root");
	        dataSource.setPassword("root");
	        dataSource.setDatabaseName("rec");
	        
	        JDBCDataModel dm = new MySQLJDBCDataModel(dataSource,"ratings","userid","itemid","rating","");
	        
	        UserSimilarity similarity = new PearsonCorrelationSimilarity(dm);
	        UserNeighborhood neighbor = new NearestNUserNeighborhood(2,similarity, dm);
	        
	        GenericUserBasedRecommender recommender = new GenericUserBasedRecommender(dm, neighbor, similarity);
	        List<RecommendedItem> list = recommender.recommend(2, 3);// recommend
	                                                                 // one item
	                                                                 // to user
	                                                                 // 1
	        
	        for (RecommendedItem ri : list) {
	            System.out.println(ri);
	        }
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		

	}

}
