package com.demo.account.api;

import com.demo.account.config.FileUploadUtil;
import com.demo.account.models.User;
import com.demo.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/users") // Đây là base path cho API
public class UserRestController {

    @Autowired
    private UserService userService;

    // Lấy tất cả user
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Lấy user theo id
    @GetMapping("/{id}")
    public User getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    // Thêm user mới
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // Cập nhật user
    @PutMapping(value = "/{id}" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public User updateUser(
            @PathVariable long id,
            @RequestPart("user") User updatedUser,
            @RequestPart(value = "image" , required = false) MultipartFile imageFile
    ) throws IOException {
        User existingUser = userService.getUserById(id);

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPhone(updatedUser.getPhone());
        existingUser.setAddress(updatedUser.getAddress());

        if(imageFile != null && !imageFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
            String uploadDir = "user-photos/" + id;
            FileUploadUtil.saveFile(uploadDir , fileName , imageFile);
            existingUser.setPhotos(fileName);
        }
        return userService.saveUser(existingUser);
    }

    // Xóa user
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUserById(id);
    }
}
