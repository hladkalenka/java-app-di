package cz.mendelu.ja.di.app;

import java.util.stream.Collectors;

@MyClassAnnotation
public class CarController {

    @MyAtributeAnnotation
    private CarService carService;

    @MyAtributeAnnotation
    private CarDao carDao;

    public String post(String spz, String znacka) {
        return carService.create(spz, znacka).toString();
    }

    public String getAll() {
        return carDao.findAll()
                .stream()
                .map(Car::toString)
                .collect(Collectors.joining());
    }
}
