package co.com.bancolombia.smokeTest.learning_day_ia.features;
import com.intuit.karate.junit5.Karate;

public class Learning_day_iaRunner {
    @Karate.Test
    Karate massive() {
        return Karate.run("learning_day_ia").relativeTo(getClass());
    }
}