package cyou.ted2.undecided.controller;

import cyou.ted2.undecided.models.Rating;
import cyou.ted2.undecided.repository.RatingRepository;
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
        //TODO hier prüfung auf groeße
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
        FileUtils.writeByteArrayToFile(imageFile, file.getBytes());

        if(!imageFile.exists()){
            rating.setImageNum(rating.getImageNum() + 1);
            ratingRepository.save(rating);
        }


        return new ResponseEntity<>(HttpStatus.CREATED);
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
        return ResponseEntity.accepted().body(IOUtils.toByteArray(in));
    }


    //TODO this here
    @GetMapping(
            value = "/user/{imgPath}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody ResponseEntity<?> getUserImage(@PathVariable String imgPath) throws IOException {
        File file = new File(PATH_TO_IMAGES + "users/");
        if(!file.exists())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        InputStream in = new FileInputStream(file);
        return ResponseEntity.accepted().body(IOUtils.toByteArray(in));
    }


}
