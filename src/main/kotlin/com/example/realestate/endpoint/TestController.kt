package com.example.realestate.endpoint

import com.example.realestate.domain.DistanceCalculator
import com.example.realestate.domain.NewOtodomAdvertisementScrapper
import com.example.realestate.domain.advertisement.Advertisement
import com.example.realestate.domain.advertisement.repository.AdvertisementRepository
import com.example.realestate.domain.point_of_interest.PointOfInterest
import com.example.realestate.domain.point_of_interest.repository.PointOfInterestRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
@RequestMapping("/api/v1/test")
class TestController(
    private val scrapper: NewOtodomAdvertisementScrapper, private val advertisementRepository: AdvertisementRepository,
    private val distanceCalculator: DistanceCalculator,
    private val pointOfInterestRepository: PointOfInterestRepository
) {

    @GetMapping(path = ["/polo.jpg"])
    fun jpg(){
        println("aaaa")
    }


    @GetMapping
    fun test() {
        scrapper.scrap();
    }

//    @GetMapping(path = ["/create"])
//    fun create() {
//        val testEntity = Advertisement("ad", BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE);
//        advertisementRepository.save(testEntity);
//
//    }

    @GetMapping(path = ["/get"])
    fun get(): MutableList<Advertisement> {
        val a: String? = null;
        return advertisementRepository.findAll();
    }

    @GetMapping(path = ["/distance"])
    fun distance(): Double {
        return distanceCalculator.distance(52.1451814, 21.0413588, 52.1495426, 21.0443367)
    }

    @GetMapping(path = ["metro"])
    fun loadMetro() {
        val metro = mutableListOf<PointOfInterest>();
        //m1
        metro.add(PointOfInterest("Metro Kabaty", "METRO", BigDecimal(52.131190), BigDecimal(21.065734)));
        metro.add(PointOfInterest("Metro Natolin", "METRO", BigDecimal(52.139875), BigDecimal(21.057650)));
        metro.add(PointOfInterest("Metro Imielin", "METRO", BigDecimal(52.149582), BigDecimal(21.045389)));
        metro.add(PointOfInterest("Metro Stokłosy", "METRO", BigDecimal(52.156003), BigDecimal(21.034602)));
        metro.add(PointOfInterest("Metro Ursynów", "METRO", BigDecimal(52.161873), BigDecimal(21.027615)));
        metro.add(PointOfInterest("Metro Służew", "METRO", BigDecimal(52.172715), BigDecimal(21.026292)));
        metro.add(PointOfInterest("Metro Wilanowska", "METRO", BigDecimal(52.181589), BigDecimal(21.022884)));
        metro.add(PointOfInterest("Metro Wierzbno", "METRO", BigDecimal(52.190010), BigDecimal(21.016892)));
        metro.add(PointOfInterest("Metro Racławicka", "METRO", BigDecimal(52.199479), BigDecimal(21.012039)));
        metro.add(PointOfInterest("Metro Pole Mokotowskie", "METRO", BigDecimal(52.209397), BigDecimal(21.007373)));
        metro.add(PointOfInterest("Metro Politechnika", "METRO", BigDecimal(52.217470), BigDecimal(21.014543)));
        metro.add(PointOfInterest("Metro Centrum", "METRO", BigDecimal(52.230361), BigDecimal(21.010894)));
        metro.add(PointOfInterest("Metro Świętokrzyska", "METRO", BigDecimal(52.235197), BigDecimal(21.008447)));
        metro.add(PointOfInterest("Metro Ratusz Arsenał", "METRO", BigDecimal(52.244840), BigDecimal(21.001082)));
        metro.add(PointOfInterest("Metro Dworzec Gdański", "METRO", BigDecimal(52.257959), BigDecimal(20.994603)));
        metro.add(PointOfInterest("Metro Plac Wilsona", "METRO", BigDecimal(52.268773), BigDecimal(20.985208)));
        metro.add(PointOfInterest("Metro Marymont", "METRO", BigDecimal(52.271984), BigDecimal(20.971212)));
        metro.add(PointOfInterest("Metro Słodowiec", "METRO", BigDecimal(52.276922), BigDecimal(20.960023)));
        metro.add(PointOfInterest("Metro Stare Bielany", "METRO", BigDecimal(52.281740), BigDecimal(20.949284)));
        metro.add(PointOfInterest("Metro Wawrzyszew", "METRO", BigDecimal(52.286491), BigDecimal(20.938504)));
        metro.add(PointOfInterest("Metro Młociny", "METRO", BigDecimal(52.290528), BigDecimal(20.930408)));

        //m2
        metro.add(PointOfInterest("Metro Bemowo", "METRO", BigDecimal(52.239233), BigDecimal(20.915019)))
        metro.add(PointOfInterest("Metro Ulrychów", "METRO", BigDecimal(52.240445), BigDecimal(20.930046)))
        metro.add(PointOfInterest("Metro Księcia Janusza", "METRO", BigDecimal(52.239266), BigDecimal(20.942503)))
        metro.add(PointOfInterest("Metro Młynów", "METRO", BigDecimal(52.237635), BigDecimal(20.960610)))
        metro.add(PointOfInterest("Metro Płocka", "METRO", BigDecimal(52.232990), BigDecimal(20.966106)))
        metro.add(PointOfInterest("Metro Rondo Daszyńskiego", "METRO", BigDecimal(52.230246), BigDecimal(20.983494)))
        metro.add(PointOfInterest("Metro Rondo ONZ", "METRO", BigDecimal(52.232998), BigDecimal(20.998095)));
        metro.add(PointOfInterest("Metro Świętokrzyska", "METRO", BigDecimal(52.235217), BigDecimal(21.008421)));
        metro.add(
            PointOfInterest(
                "Metro Nowy Świat-Uniwersytet", "METRO", BigDecimal(52.236848), BigDecimal(21.017276)
            )
        );
        metro.add(
            PointOfInterest(
                "Metro Centrum Nauki Kopernik",
                "METRO",
                BigDecimal(52.239560),
                BigDecimal(21.030811)
            )
        );
        metro.add(PointOfInterest("Metro Stadion Narodowy", "METRO", BigDecimal(52.246953), BigDecimal(21.043326)));
        metro.add(PointOfInterest("Metro Wileński", "METRO", BigDecimal(52.254147), BigDecimal(21.035187)));
        metro.add(PointOfInterest("Metro Szwedzka", "METRO", BigDecimal(52.262889), BigDecimal(21.043863)));
        metro.add(
            PointOfInterest(
                "Metro Targówek Mieszkaniowy",
                "METRO",
                BigDecimal(52.269495),
                BigDecimal(21.051388)
            )
        );
        metro.add(PointOfInterest("Metro Targówek Trocka", "METRO", BigDecimal(52.275299), BigDecimal(21.055384)));
        metro.add(PointOfInterest("Metro Zacisze", "METRO", BigDecimal(52.284234), BigDecimal(21.061983)));
        metro.add(PointOfInterest("Metro Kondratowicza", "METRO", BigDecimal(52.292053), BigDecimal(21.050196)));
        metro.add(PointOfInterest("Metro Bródno", "METRO", BigDecimal(52.293485), BigDecimal(21.029050)));

        pointOfInterestRepository.saveAll(metro);

    }



}