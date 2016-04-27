package com.mongodb;

import com.mongodb.gridfs.GridFSDBFile;
import org.junit.FixMethodOrder;
import org.junit.Test;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * @Title: MongodbGridFsTest
 * @Description:
 * @author: xian jie
 * @date: 2016-4-27 11:53
 * 杭州尚尚签网络科技有限公司
 * @version: 2.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MongodbGridFsTest {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    /**
     * 写入
     */
    @Test
    public void teat_a_store() {

        DBObject metaData = new BasicDBObject();
        metaData.put("extra1", "anything 1");
        metaData.put("extra2", "anything 2");

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("f:/java_tmp/AsianTest.pdf");
            gridFsTemplate.store(inputStream, "AsianTest1.pdf", "application/pdf", metaData);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Done");
    }

    /**
     * 读取
     */
    @Test
    public void test_b_read() {

        List<GridFSDBFile> result = gridFsTemplate.find(new Query().addCriteria(Criteria.where("filename").is("AsianTest1.pdf")));

        for (GridFSDBFile file : result) {
            try {
                System.out.println(file.getFilename());
                System.out.println(file.getContentType());

                //save as another image
                file.writeTo("f:/java_tmp/AsianTest1.pdf");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Done");
    }


}
