package cyou.ted2.undecided.controller;

import cyou.ted2.undecided.models.Rating;
import cyou.ted2.undecided.models.User;
import cyou.ted2.undecided.repository.RatingRepository;
import cyou.ted2.undecided.repository.UserRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/api/img")
public class ImageController {

    private final UserRepository userRepository;
    private final RatingRepository ratingRepository;

    public ImageController(UserRepository userRepository, RatingRepository ratingRepository) {
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
    }

    User postUserImage(@RequestParam("image") MultipartFile file) throws IOException {
        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user = userRepository.findUserById(userId);
        File tempFile = File.createTempFile("Undecided-", ".tmp");
        FileUtils.writeByteArrayToFile(tempFile, file.getBytes());
        tempFile.deleteOnExit();
        byte[] fileContent = FileUtils.readFileToByteArray(tempFile);
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        user.setProfileImage(encodedString);
        return userRepository.save(user);
    }

    Rating postRatingImages(@RequestBody String ratingId, @RequestParam("image") MultipartFile file) throws IOException{
        Rating rating = ratingRepository.findRatingById(ratingId);
        File tempFile = File.createTempFile("Undecided-", ".tmp");
        FileUtils.writeByteArrayToFile(tempFile, file.getBytes());
        tempFile.deleteOnExit();
        byte[] fileContent = FileUtils.readFileToByteArray(tempFile);
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        //rating.setImages(rating.getImages().add(encodedString););
        return ratingRepository.save(rating);
    }



}
