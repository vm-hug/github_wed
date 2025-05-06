package com.demo.account.controllers;


import com.demo.account.config.FileUploadUtil;
import com.demo.account.models.User;
import com.demo.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/showNewUser")
    public String showNewUser(Model model){
        User user = new User();
        model.addAttribute("user" , user);
        return "new_user";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user ,
                           @RequestParam("image")MultipartFile multipartFile) throws Exception {
        if(!multipartFile.isEmpty()){ // trường hợp có ảnh mới được chọn
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            user.setPhotos(fileName);
            User saveUser = userService.saveUser(user);

            String uploadDir = "user-photos/" + saveUser.getId();
            FileUploadUtil.saveFile(uploadDir , fileName , multipartFile);
        }else { //trường hợp nếu không có ảnh mới , giữ nguyên ảnh cũ
            User existingUser = userService.getUserById(user.getId());
            user.setPhotos(existingUser.getPhotos());
            userService.saveUser(user);
        }
        return "redirect:/users";
    }

    @GetMapping("/editUser/{id}")
    public String editUser(@PathVariable(value = "id") long id , Model model){
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "edit_user";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(value = "id") long id){
        this.userService.deleteUserById(id);
        return "redirect:/users";
    }

}
