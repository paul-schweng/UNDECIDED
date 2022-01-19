package cyou.ted2.undecided.controller;

import cyou.ted2.undecided.repository.RatingRepository;
import cyou.ted2.undecided.repository.UserRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/img")
public class ImageController {

    private final UserRepository userRepository;
    private final RatingRepository ratingRepository;

    public ImageController(UserRepository userRepository, RatingRepository ratingRepository) {
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
    }

    public void postUserImage(@RequestBody() MultipartFile file) throws IOException {
        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        File imageFile = new File("/var/opt/pictures/users/Undecided-" + userId + ".jpg");
        FileUtils.writeByteArrayToFile(imageFile, file.getBytes(), false);
    }

    public ResponseEntity postRatingImages(@RequestBody MultipartFile file, @RequestParam("rating") String ratingId, @RequestParam("index") String index) throws IOException{
        int imageIndex = Integer.parseInt(index);
        if (imageIndex > 4) {
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        File imageFile = new File("/var/opt/pictures/ratings/Undecided-" + ratingId + "-" + imageIndex + ".jpg");
        if(!imageFile.exists()){

        }
        FileUtils.writeByteArrayToFile(imageFile, file.getBytes());

        return new ResponseEntity(HttpStatus.CREATED);
    }
}
