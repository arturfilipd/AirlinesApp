package com.AirlinesApp;

import com.AirlinesApp.Model.Airport;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { AirportJpaConfig.class },
        loader = AnnotationConfigContextLoader.class)
@Transactional
public class InMemoryDBTest {

    //@Autowired
    //private AirportRepository repository;

    @Test
    public void givenStudent_whenSave_thenGetOk() {
        Airport ap = new Airport("Testport", "TEST", "Testville");
       //repository.save(ap);

        //Airport student2 = repository.findOneById(0);
        Assert.assertEquals("Testport", ap.getAirportName());
    }
}