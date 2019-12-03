package com.example;


import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastIDSet;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MahoutUserBasedRecommenderApplication {

	public static void main(String[] args) throws IOException, TasteException {
		SpringApplication.run(MahoutUserBasedRecommenderApplication.class, args);
		
		try {
		
			DataModel model = new FileDataModel(new File("C:\\Users\\kromdhani\\Documents\\workspace-spring-tool-suite-4-4.4.0.RELEASE\\mahout-user-based-recommender\\src\\main\\resources\\dataset.csv"));
			
			
			/*In this example, we want to create a user-based recommender. 
			 * The idea behind this approach is that when we want to compute recommendations for a particular users, we look for other users with a similar taste and pick the recommendations from their items.
			 * For finding similar users, we have to compare their interactions.
			 * There are several methods for doing this. One popular method is to compute the correlation coefficient between their interactions.
			 * The range of values for the correlation coefficient is -1.0 to 1.0
			 * In Mahout, you use this method as follows: */
			
			UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
			UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.4, similarity, model); // correlation between 2 users equal 0.1
			
			UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
			
			List<RecommendedItem> recommendations = recommender.recommend(2, 3); //get 3 items recommended for the user with userID 2
			
			for (RecommendedItem recommendation : recommendations) {
			  System.out.println(recommendation);
			  
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		

	}

}
