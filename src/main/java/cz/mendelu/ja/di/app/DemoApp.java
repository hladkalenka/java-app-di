package cz.mendelu.ja.di.app;

@MyClassAnnotation
public class DemoApp {

    @MyAtributeAnnotation
    private CarController carController;

    public static void main(String[] args) {
        var app = IOC.create(DemoApp.class);

        System.out.println("Inversion of Control");
        System.out.println("Jmeno controleru: " + app.carController +". ");
        System.out.println(app.carController.post("ABC-1234", "Skoda"));
        System.out.println(app.carController.post("010-1010", "Skoda"));

        System.out.println("Výpis všech vložených aut.");
        System.out.println(app.carController.getAll());
    }
}
