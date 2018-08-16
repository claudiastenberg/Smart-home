package se.claudiastenberg.MyHome.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.claudiastenberg.MyHome.model.User;
import se.claudiastenberg.MyHome.repository.UserRepository;

import javax.ws.rs.NotFoundException;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

@Service
public class UserService {

    private final String USER_AGENT = "Mozilla/5.0";

    @Autowired
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User createUser(User user){
        return repository.save(user);
    }
    /*FindByAccountName*/
   public User getIdFromUser(Long accountName, int controller_channel, int status) throws Exception {
     Optional <User> userOptional = repository.findByAccountName(accountName);
        if (userOptional.isPresent()){

            UserService http = new UserService(repository);

            System.out.println(controller_channel);

            http.sendingPostRequest(userOptional.get().getControllerId(), controller_channel, status);

            return userOptional.get();
            }
        throw new NotFoundException();
    }

    private void sendingPostRequest(int controller_id, int controller_channel, int status) throws Exception {

    String url = "http://84.217.194.147:4000/post";
    URL obj = new URL(url);
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();

    // Setting basic post request
    con.setRequestMethod("POST");
    con.setRequestProperty("User-Agent", USER_AGENT);
    con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
    con.setRequestProperty("Content-Type","application/json");

    String postJsonData = String.format("{\"controller_id\":%d, \"controller_channel\":%d, \"status\":%d }", controller_id, controller_channel, status);
    System.out.println(postJsonData);

    // Send post request
    con.setDoOutput(true);
    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
    wr.writeBytes(postJsonData);
    wr.flush();
    wr.close();

    int responseCode = con.getResponseCode();
    System.out.println("nSending 'POST' request to URL : " + url);
    System.out.println("Post Data : " + postJsonData);
    System.out.println("Response Code : " + responseCode);

    BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream()));
    String output;
    StringBuffer response = new StringBuffer();

    while ((output = in.readLine()) != null) {
        response.append(output);
    }
    in.close();

    //printing result from response
    System.out.println(response.toString());
    }
}
