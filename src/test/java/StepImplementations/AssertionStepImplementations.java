package StepImplementations;

import base.BaseApi;
import com.thoughtworks.gauge.Step;
import org.junit.Assert;

import static StepImplementations.PetStepImplementations.response;

public class AssertionStepImplementations extends BaseApi {

     @Step("status code <statusCode> olmalı")
    public void checkStatusCode(int statusCode) {
         Assert.assertEquals("Status code beklenenle eşleşmedi!",
                 statusCode, response.statusCode());
     }

    @Step("<jsonKey> contains <expectedValues>")
    public void checkJsonContains(String jsonKey, String expectedValues) {

        Object actualValue = PetStepImplementations.response.jsonPath().get(jsonKey);

        System.out.println("KONTROL EDİLİYOR -> API'den Gelen: [" + actualValue + "] | Beklenen Kelime: [" + expectedValues + "]");

        Assert.assertTrue("Hata: " + jsonKey + "alanı '" + expectedValues + "'içermiyor",
                actualValue != null &&
                actualValue.toString().toLowerCase().contains(expectedValues.toLowerCase()));

    }
}
