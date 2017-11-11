package at.fhv.itb.sem5.team6.libman.server;

import at.fhv.itb.sem5.team6.libman.server.config.FongoConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {FongoConfiguration.class})
public abstract class EmbeddedMongoUnitTest {

}
