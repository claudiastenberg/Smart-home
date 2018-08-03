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

        try {
            URL url = new URL("http://84.217.194.147:4000/post");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            String contentType = con.getHeaderField("Content-Type");
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        int account = userOptional.get().getAccountName();
        boolean status = userOptional.get().isStatus();
        int message = account;

        if (userOptional.isPresent()){



                return userOptional.get();
            }
        throw new NotFoundException();
    }





}
