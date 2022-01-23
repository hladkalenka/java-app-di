package cz.mendelu.ja.di.app;

@MyClassAnnotation
public class ZnackaService {

    public boolean isValid(String znacka) {
        return "Skoda".equals(znacka);
    }
}
