package com.techprimers.mongodb.springbootmongodbexample.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techprimers.mongodb.springbootmongodbexample.document.Users;
import com.techprimers.mongodb.springbootmongodbexample.repository.UserRepository;

@RestController
@RequestMapping("/rest/users")
public class UsersResource {

	@Autowired
    MongoTemplate mongoTemplate;
    private UserRepository userRepository;

    public UsersResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/all")
    public List<Users> getAll() {
        return mongoTemplate.findAll(Users.class);
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Users test(@RequestBody Users user){
    	mongoTemplate.save(user);
    	return user;
    }
    
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public Users getUser(@RequestParam int id){
    	Users test = mongoTemplate.findById(id, Users.class);
    	return test;
    }
    
    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
    public void deleteUser(@RequestParam int id){
    	mongoTemplate.remove(new Query(Criteria.where("id").is(id)), Users.class);
    	
    }
    @RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
    public boolean updateUser(@RequestParam int id, @RequestParam String name){
    	Query query = new Query(Criteria.where("id").is(id));
    	mongoTemplate.updateFirst(/*new Query(Criteria.where("id").is(id))*/ query ,Update.update("name", name),Users.class);
    	//If the update "testa" is not available in the document, it will create as a field.
    	// If "testa" is available in the document, it will update the testa field
    	/*Users users = getUser(id);
    	users.setName(name);
    	mongoTemplate.save(users);*/
    	return true;
    }
    
}
