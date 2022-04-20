package cyou.ted2.undecided.controller;

import cyou.ted2.undecided.models.Following;
import cyou.ted2.undecided.models.FollowingPK;
import cyou.ted2.undecided.models.Rating;
import cyou.ted2.undecided.models.User;
import cyou.ted2.undecided.repository.FollowerRepository;
import cyou.ted2.undecided.repository.RatingRepository;
import cyou.ted2.undecided.repository.UserRepository;
import cyou.ted2.undecided.tools.PasswordHashing;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
class UserController {

    private final UserRepository repository;
    private final FollowerRepository followerRepository;

    private final static int MAX_LOAD_USER = 5;


    UserController(UserRepository repository, FollowerRepository followerRepository) {
        this.repository = repository;
        this.followerRepository = followerRepository;
    }

    @PutMapping("/email")
    @ResponseBody
    User postNewEmail(@RequestBody String currentEmail, String newEmail) {
        User user = repository.findUserByEmail(currentEmail);
        user.setEmail(newEmail);
        return repository.save(user);
    }

    @PutMapping("/password")
    @ResponseBody
    User putNewPassword(@RequestBody String currentPw, String newPw) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user = repository.findUserById(userId);
        if (PasswordHashing.hashPassword(currentPw).equals(user.getPassword())){
            user.setPassword(PasswordHashing.hashPassword(newPw));
            return repository.save(user);
        }
        return null; //later replace with exception
    }

    @GetMapping()
    @ResponseBody
    User getUser(){
        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return repository.findUserById(userId);
    }

    @GetMapping(value="/u/{username}")
    @ResponseBody
    User getUser(@PathVariable String username){
        return repository.findUserByUsername(username);
    }

    @PutMapping()
    @ResponseBody
    User updateUser(@RequestBody User u) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user = repository.findUserById(userId);
        user.update(u);
        return repository.save(user);
    }

    @DeleteMapping
    public void deleteUser(){
        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User deletedUser = new User();
        deletedUser.setId(userId);
        // TODO delete profile pictures
        repository.save(deletedUser);
    }

    @GetMapping("/follow")
    ResponseEntity followUser(@RequestParam String id){
        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Following follow = new Following();
        User user = repository.getById(userId);
        User following = repository.getById(id);
        follow.setUser(user);
        follow.setFollowing(following);
        follow.setFollowDate(ZonedDateTime.now());

        user.setFollowingNum(user.getFollowingNum() + 1);
        following.setFollowerNum(following.getFollowerNum() + 1);

        try {
            if(userId.equals(id))
                throw new Exception();

            followerRepository.save(follow);
            repository.save(user);
            repository.save(following);

            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/following")
    Map<String, Boolean> isFollowing(@RequestParam String id){
        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        boolean exists = followerRepository.existsById(new FollowingPK(userId, id));
        Map<String, Boolean> map = new HashMap<>();
        map.put("isFollowing", exists);
        return map;
    }

    @DeleteMapping("/unfollow")
    ResponseEntity unfollowUser(@RequestParam String id){
        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

        try {
            followerRepository.deleteById(new FollowingPK(userId, id));

            User user = repository.getById(userId);
            User following = repository.getById(id);
            user.setFollowingNum(user.getFollowingNum() - 1);
            following.setFollowerNum(following.getFollowerNum() - 1);
            
            repository.save(following);
            repository.save(user);

            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/myFollower")
    ResponseEntity<?> getFollower(@RequestBody PartialLoadFollow body){
        if(body.userid == null)
            body.userid = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

        List<User> followerList = followerRepository.getAllUsingFollowing_Id(body.userid, body.timestamp, PageRequest.of(0, MAX_LOAD_USER));

        return ResponseEntity.accepted().body(followerList);
    }

    @PostMapping("/myFollowing")
    ResponseEntity<?> getFollowing(@RequestBody PartialLoadFollow body){

        System.out.println("\n \n \n \n");
        System.out.println(body.userid);
        System.out.println("\n \n \n \n");


        if(body.userid == null)
            body.userid = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

        List<User> followingList = followerRepository.getAllUsingFollower_Id(body.userid, body.timestamp,PageRequest.of(0, MAX_LOAD_USER));

        return ResponseEntity.accepted().body(followingList);
    }

}

class PartialLoadFollow{
    ZonedDateTime timestamp;
    String userid;

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}


