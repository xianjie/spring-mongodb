package com.mongodb;

import com.mongodb.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Title: MongodbDeleteTest
 * @Description:
 * @author: xian jie
 * @date: 2016-4-27 11:48
 * 杭州尚尚签网络科技有限公司
 * @version: 2.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring.xml")
public class MongodbDeleteTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void delete() {
        // insert 6 users for testing
        List<User> users = new ArrayList<>();

        User user1 = new User("1001", "ant", 10, new Date());
        User user2 = new User("1002", "bird", 20, new Date());
        User user3 = new User("1003", "cat", 30, new Date());
        User user4 = new User("1004", "dog", 40, new Date());
        User user5 = new User("1005", "elephant", 50, new Date());
        User user6 = new User("1006", "frog", 60, new Date());
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        mongoTemplate.insert(users, User.class);

        Query query1 = new Query();
        query1.addCriteria(Criteria.where("name").exists(true)
                .orOperator(Criteria.where("name").is("frog"), Criteria.where("name").is("dog")));
        mongoTemplate.remove(query1, User.class);

        Query query2 = new Query();
        query2.addCriteria(Criteria.where("name").is("bird"));
        User userTest2 = mongoTemplate.findOne(query2, User.class);
        mongoTemplate.remove(userTest2);

        // The first document that matches the query is returned and also
        // removed from the collection in the
        // database.
        Query query3 = new Query();
        query3.addCriteria(Criteria.where("name").is("ant"));
        User userTest3 = mongoTemplate.findAndRemove(query3, User.class);
        System.out.println("Deleted document : " + userTest3);

        // either cat or elephant is deleted, common mistake, dun use for batch
        // delete.
		/*
		 * Query query4 = new Query(); query4.addCriteria(Criteria
		 * .where("name") .exists(true)
		 * .orOperator(Criteria.where("name").is("cat"),
		 * Criteria.where("name").is("elephant")));
		 * mongoTemplate.findAndRemove(query1, User.class);
		 * System.out.println("Deleted document : " + userTest4);
		 */

        System.out.println("\nAll users : ");
        List<User> allUsers = mongoTemplate.findAll(User.class);
        allUsers.forEach(System.out::println);

        mongoTemplate.dropCollection(User.class);
    }
}
