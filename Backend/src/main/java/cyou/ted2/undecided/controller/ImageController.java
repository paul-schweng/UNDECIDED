package cyou.ted2.undecided.controller;

import cyou.ted2.undecided.models.Rating;
import cyou.ted2.undecided.repository.RatingRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/img")
public class ImageController {

    private final RatingRepository ratingRepository;
    private static final String PATH_TO_IMAGES = "/var/opt/pictures/";

    public ImageController(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @PostMapping("/user")
    public ResponseEntity postUserImage(@RequestBody() MultipartFile file) throws IOException {
        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        File imageFile = new File(PATH_TO_IMAGES + "users/" + userId + ".jpg");
        FileUtils.writeByteArrayToFile(imageFile, file.getBytes(), false);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/rating")
    public ResponseEntity postRatingImages(@RequestBody MultipartFile file, @RequestParam("rating") String ratingId, @RequestParam("index") String index) throws IOException{
        Rating rating = ratingRepository.findRatingById(ratingId);
        try {
            int imageIndex = Integer.parseInt(index);
            if (imageIndex > 4) {
                return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
            }
        }catch(NumberFormatException e){
            return  new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        File imageFile = new File(PATH_TO_IMAGES + "ratings/" + ratingId + "-" + index + ".jpg");
        if(!imageFile.exists()){
            rating.setImageNum(rating.getImageNum() + 1);
            ratingRepository.save(rating);
        }

        FileUtils.writeByteArrayToFile(imageFile, file.getBytes());
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
