package rest.client.controller;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import rest.client.model.Person;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rest.client.model.ResponseWrapper;

public class PersonController {

    private static Logger logger = LoggerFactory.getLogger(PersonController.class);

    public Person findPersonById(long id) throws Exception {
        logger.info("Method [findPersonById] with parameter id [" + id + "] invoked.");

        ClientResponse response = establishConnection("http://localhost:8070/people/" + id);
        verifyResponseStatus(response);

        String responseString = response.getEntity(String.class);

        Gson gson = new Gson();
        Person person = gson.fromJson(responseString,Person.class);

        logger.info(person.toString());

        return person;
    }

    public List<Person> listAllPeople() throws Exception {
        logger.info("Method [listAllPeople] invoked.");

        ClientResponse response = establishConnection("http://localhost:8070/people");
        verifyResponseStatus(response);

        String responseString = response.getEntity(String.class);

        Gson gson = new Gson();
        ResponseWrapper peopleWrapper = gson.fromJson(responseString, ResponseWrapper.class);
        List<Person> people = peopleWrapper.get_embedded().getPeople();

        logger.info("Number of subscribed people: " + people.size());

        for (Person person : people) {
            logger.info(person.toString());
        }

        return people;
    }

    private ClientResponse establishConnection(String uri) {
        Client client = Client.create();

        WebResource webResource = client
                .resource(uri);

        return webResource.accept("application/json")
                .get(ClientResponse.class);
    }

    private void verifyResponseStatus(ClientResponse response) {
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }
    }

}
