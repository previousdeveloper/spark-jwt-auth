import Bootstrapper.AppModule;
import Controller.AuthController;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Server {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AppModule());
        AuthController authController = injector.getInstance(AuthController.class);
    }
}
