package cz.mendelu.ja.di.app;

@MyClassAnnotation
public class CarService {

    @MyAtributeAnnotation
    private CarDao carDao;
    @MyAtributeAnnotation
    private ZnackaService znackaService;

    public Car create(String spz, String znacka) {
        if (!znackaService.isValid(znacka)) {
            throw new IllegalArgumentException("Neni Skoda");
        }
        var car = new Car(spz, znacka);
        var saved = carDao.save(car);
        return saved;
    }
}
