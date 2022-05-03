package cyou.ted2.undecided.controller;

import cyou.ted2.undecided.models.Rating;
import cyou.ted2.undecided.models.User;
import cyou.ted2.undecided.repository.RatingRepository;
import cyou.ted2.undecided.repository.UserRepository;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/img")
public class ImageController {

    private final RatingRepository ratingRepository;
    private static final String PATH_TO_IMAGES = "/var/opt/pictures/";
    private static final int MAX_NUM_IMAGES = 4;

    public ImageController(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }


    @PostMapping("/user")
    public ResponseEntity<?> postUserImage(@RequestParam("image") MultipartFile file) throws IOException {
        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        File imageFile = new File(PATH_TO_IMAGES + "users/" + userId + ".jpg");
        FileUtils.writeByteArrayToFile(imageFile, file.getBytes(), false);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/rating")
    public ResponseEntity<?> postRatingImages(@RequestParam("image") MultipartFile file,
                                           @RequestParam("rating") String ratingId,
                                           @RequestParam("index") String index) throws IOException{
        Rating rating = ratingRepository.findRatingById(ratingId);
        try {
            int imageIndex = Integer.parseInt(index);
            if (imageIndex > MAX_NUM_IMAGES) {
                return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
            }
        }catch(NumberFormatException e){
            return  new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        File imageFile = new File(PATH_TO_IMAGES + "ratings/" + ratingId + "-" + index + ".jpg");
        boolean existsFile = imageFile.exists();

        FileUtils.writeByteArrayToFile(imageFile, file.getBytes());

        if(!existsFile){
            rating.setImageNum(rating.getImageNum() + 1);
            ratingRepository.save(rating);
        }


        return ResponseEntity.accepted().body("{\"imageNum\": " + rating.getImageNum() + "}");
    }


    @GetMapping(
            value = "/rating/{id}/{idx}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody ResponseEntity<?> getRatingImage(@PathVariable String id, @PathVariable int idx) throws IOException {
        File file = new File(PATH_TO_IMAGES + "ratings/" + id + "-" + idx + ".jpg");

        while(!file.exists() && idx <= MAX_NUM_IMAGES) {
            idx++;
            file = new File(PATH_TO_IMAGES + "ratings/" + id + "-" + idx + ".jpg");
        }

        if(!file.exists())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        InputStream in = new FileInputStream(file);
        byte[] byteArr = IOUtils.toByteArray(in);
        in.close();
        return ResponseEntity.accepted().body(byteArr);
    }


    @GetMapping(
            value = "/user/{userid}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody ResponseEntity<?> getUserImage(@PathVariable String userid) throws IOException {
        File file = new File(PATH_TO_IMAGES + "users/" + userid + ".jpg");
        if(!file.exists())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        InputStream in = new FileInputStream(file);
        byte[] byteArr = IOUtils.toByteArray(in);
        in.close();
        return ResponseEntity.accepted().body(byteArr);
    }


    @DeleteMapping("/user")
    public ResponseEntity<?> deleteUserImage() {

        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        File file = new File(PATH_TO_IMAGES + "users/" + userId + ".jpg");
        if (!file.exists())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        try {
            Files.delete(Paths.get(PATH_TO_IMAGES + "users/" + userId + ".jpg"));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    /*
    @DeleteMapping("/rating")
    public ResponseEntity<?> deleteRatingImage(@RequestParam String id) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

        if(!userId.equals(rating.getUser().getId()))
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        return new ResponseEntity<>(HttpStatus.OK);
    }
     */
}
