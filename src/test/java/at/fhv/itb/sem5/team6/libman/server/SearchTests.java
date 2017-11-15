package at.fhv.itb.sem5.team6.libman.server;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class SearchTests extends EmbeddedMongoUnitTest {

    @Before
    public void setUp() {
        media();
        customer();
        physicalMedia(50);
        reservation(10);
        lending(10);
    }

    @Test
    @Ignore
    public void mediaSearchTest() {
        //libraryController.findMedias("", null, MediaType.BOOK, null);
        //int i;
    }
}
