import com.flipkart.service.LowCostRentingService;
import com.flipkart.service.RentingService;
import org.junit.Test;

public class ApplicationTest {
    private RentingService service = new LowCostRentingService();

    @Test
    public void test_cases()
    {
        service.addBranch("koramangala");
        service.set_vehicle("koramangala", "suv", 1, 12);
        service.set_vehicle("koramangala", "sedan", 3, 10);

        //service.set_vehicle("abc", "sedan", 3, 10);

        service.rent_vehicle("car", "20th Feb 10:00 PM", 2);

        service.addBranch("jayanagar");
        service.set_vehicle("jayanagar", "suv", 1, 15);
        service.set_vehicle("jayanagar", "sedan", 3, 11);
        service.set_vehicle("jayanagar", "bike", 3, 30);
        service.set_vehicle("jayanagar", "bike", 5, 50);
        service.set_vehicle("jayanagar", "hatchback", 4, 8);
        service.set_vehicle("jayanagar", "hatchback", 4, 9);
        service.rent_vehicle("suv", "20th Feb 10:00 PM", 2);
        service.rent_vehicle("suv", "20th Feb 10:00 PM", 2);
        service.rent_vehicle("suv", "20th Feb 10:00 PM", 2);
        service.system_view_for_time_slot("20th Feb 08:00 PM","20th Feb 09:00 PM");
        System.out.println("-------");
        service.system_view_for_time_slot("20th Feb 10:00 PM","20th Feb 12:00 PM");

    }
}
