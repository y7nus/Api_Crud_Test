package StepImplementations;

import base.BaseApi;
import client.ApiClient;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import io.restassured.response.Response;
import org.junit.Assert;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PetStepImplementations extends BaseApi {
    public static long petId;
    public static Response response;
    ApiClient client = new ApiClient();

    @Step("Pet için temel ayarları yap")
    public void init() {
        setupBase(); // url ayarlarını yükler
        petId = (long) (Math.random() * 100000); // başlangıç için geçici bir id
    }

    @Step("Table ile Pet oluştur ve gerçek ID'yi sakla <table>")
    public void createPetWithTable(Table table) throws Exception {
        for (TableRow row : table.getTableRows()) {
            String name = row.getCell("name");
            String status = row.getCell("status");

            //Dosyayı okur
            String jsonTemplate = new String(Files.readAllBytes(Paths.get("src/test/resources/pet.json")));

            // Verileri düzenler
            String finalBody = jsonTemplate
                    .replace("PET_ID", String.valueOf(petId))
                    .replace("DOG_NAME", name)
                    .replace("STATUS_VALUE", status);

            response = client.post("/pet", finalBody);

            // ** Swaggerın verdiği gerçek ıd değişkene yazılır
            petId = response.jsonPath().getLong("id");
            System.out.println("Sistem onayı: Gerçek ıd kaydedildi -> " + petId);
        }
    }

    @Step("Oluşturulan ID ile peti getir")
    public void getPet() throws InterruptedException {
        System.out.println("Swagger'dan veri bekleniyor (3 sn)...");
        Thread.sleep(3000);

        response = client.get("/pet/" + petId);

        // Eğer veri hala gelmediyse (404), 2 saniye daha bekleyip bir ker daha sorulur
        if (response.statusCode() == 404) {
            System.out.println(">>> Veri henüz hazır değil, 2 sn daha bekleniyor...");
            Thread.sleep(2000);
            response = client.get("/pet/" + petId);

        }
        System.out.println(">>> Sorgu atılan URL: /pet/" + petId);
        System.out.println(">>> API'den dönen Status Code: " + response.statusCode());

        System.out.println("-----------------------------------------------------");
        System.out.println(">>> Sunucudan gelen pet detayları: ");
        System.out.println(response.asPrettyString());
        System.out.println("-----------------------------------------------------");
    }

    @Step("Table ile Pet bilgilerini güncelle <table>")
    public void updatePetWithTable(Table table) throws Exception {
        for (TableRow row : table.getTableRows()) {
            String newName = row.getCell("newName");
            String newStatus = row.getCell("newStatus");

        String jsonTemplate = new String(Files.readAllBytes(Paths.get("src/test/resources/pet.json")));

            String finalBody = jsonTemplate
                .replace("PET_ID", String.valueOf(petId))
                .replace("DOG_NAME",newName)
                .replace("STATUS_VALUE", newStatus);

        response = client.put("/pet", finalBody);

        System.out.println(">>> Güncelleme yapıldı, sistemin kendine gelmesi bekleniyor...");
        Thread.sleep(5000);
    } }

    @Step("Peti sistemden sil")
    public void deletePet() {
        try {
            response = client.delete("/pet/" + petId);
            System.out.println(">>> Silme denendi. Kod: " + response.statusCode());
        } catch (Exception e) {
            System.out.println(">>> Pet zaten yerinde yok veya silindi.");
        }
    }

    @Step("Pet'in silindiğini doğrula")
    public void verifyDeleted() throws InterruptedException {

        System.out.println(">>> Silinme sonrası veritabanı temizliği bekleniyor (3 sn)...");
        Thread.sleep(3000);

        try {
            // GET isteğini atıyoruz
            response = client.get("/pet/" + petId);

            int actualStatusCode = response.statusCode();
            System.out.println(">>> Silinme Kontrolü Yapılıyor. Gelen Kod: " + actualStatusCode);
            Assert.assertEquals("Hata: Pet hala silinmemiş!", 404, actualStatusCode);

        } catch (Exception e) {
            if (e.getMessage().contains("404")) {
                System.out.println(">>> Onay: Pet bulunamadı (404). Silme işlemi başarılı!");
            } else {

                Assert.fail("Silinme doğrulanırken beklenmedik bir hata oluştu: " + e.getMessage());
            }
        }
    }
}
