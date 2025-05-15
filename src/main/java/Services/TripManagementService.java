package Services;

import models.Dto.CreateRezervimetDto;
import models.Dto.CreateUdhetimeDto;
import models.Rezervimet;
import models.Udhetime;

import java.time.LocalDate;
import java.util.List;
public class TripManagementService {

    private final RezervimetService rezervimetService;
    private final UdhetimetService udhetimetService;

    public TripManagementService() {
        this.rezervimetService = new RezervimetService();
        this.udhetimetService = new UdhetimetService();
    }

    // rezervo udhetimin
    public Rezervimet bookTrip(int perdoruesId, int orariId, LocalDate dataUdhetimit, int nrBiletave) throws Exception {
        // validate
        if (nrBiletave <= 0) {
            throw new Exception("Numri i biletave duhet te jete me i madh se zero.");
        }

        // a ekziston udhetimi per kete orar
        Udhetime existingTrip = findTripByScheduleAndDate(orariId, dataUdhetimit);

        // nese nuk ekziston, krijoje nje te ri
        if (existingTrip == null) {
            CreateUdhetimeDto createTrip = new CreateUdhetimeDto(orariId, dataUdhetimit, 0, "Active");
            existingTrip = udhetimetService.createUdhetim(createTrip);
        }

        //krijo rezervim
        CreateRezervimetDto rezervimDto = new CreateRezervimetDto(perdoruesId, orariId, dataUdhetimit, nrBiletave);
        Rezervimet rezervimi = rezervimetService.createRezervim(rezervimDto);

        return rezervimi;
    }

    // gjej nje udhetim permes orarit
    private Udhetime findTripByScheduleAndDate(int orariId, LocalDate date) {
        List<Udhetime> trips = udhetimetService.getAllUdhetimet();
        for (Udhetime t : trips) {
            if (t.getOrariId() == orariId && t.getDataudhetimit().isEqual(date)) {
                return t;
            }
        }
        return null;
    }

    // fshij rezervimin
    public boolean cancelReservation(int rezervimId) throws Exception {
        return rezervimetService.deleteRezervim(rezervimId);
    }
}
