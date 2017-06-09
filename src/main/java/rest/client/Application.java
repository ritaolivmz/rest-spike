package rest.client;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import rest.client.controller.PersonController;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Exception {
        PersonController personController = new PersonController();
        personController.findPersonById(1);
        personController.listAllPeople();
    }
}