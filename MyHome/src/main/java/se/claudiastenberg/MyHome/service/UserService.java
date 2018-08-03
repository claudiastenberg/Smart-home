package se.claudiastenberg.MyHome.service;

import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.claudiastenberg.MyHome.model.User;
import se.claudiastenberg.MyHome.repository.UserRepository;

import javax.ws.rs.NotFoundException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

@Service
public class UserService {



    @Autowired
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User createUser(User user){
        return repository.save(user);
    }

    public User getIdFromUser(Long id){
     Optional <User> userOptional = repository.findById(id);
        if (userOptional.isPresent()){

            /*Kolla lösenordet innan i databasen*/
            // Sending post request
            // HttpURLConnectionExample http = new HttpURLConnectionExample();

            // int AnvändarID = id från databasen
            //http.sendingPostRequest(AnvändarID);

            return userOptional.get();
            }
        throw new NotFoundException();
    }

/**Här ska metod för post till rasberrypi ligga med en input från databasen
 *
 *
 *  // om jag får ett post anrop
 // ta lösenordet, kolla efter användarID i databasen
 // kör metoden sendingPostRequest(användarID)
 // HTTP Post request
 private void sendingPostRequest(int AnvändarID) throws Exception {

 String url = "http://84.217.194.147:4000/post";
 URL obj = new URL(url);
 HttpURLConnection con = (HttpURLConnection) obj.openConnection();

 // Setting basic post request
 con.setRequestMethod("POST");
 con.setRequestProperty("User-Agent", USER_AGENT);
 con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
 con.setRequestProperty("Content-Type","application/json");

 String postJsonData = String.Format("{\"password\":\"{0}\"}", AnvändarID);
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
 * **/



}
