package com.ArtsCom.ARTSCOM.controllers;

import com.ArtsCom.ARTSCOM.models.TokenModel;
import com.ArtsCom.ARTSCOM.models.User;
import com.ArtsCom.ARTSCOM.repos.TokenRepo;
import com.ArtsCom.ARTSCOM.repos.UserRepo;
import com.ArtsCom.ARTSCOM.services.EmailSenderService;
import com.ArtsCom.ARTSCOM.services.PostService;
import com.ArtsCom.ARTSCOM.services.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
@AllArgsConstructor
public class userController {
    // Required variables
    private final UserService userService;
    private final UserRepo userRepo;
    private final PostService postService;
    private final EmailSenderService senderService;
    private final TokenRepo tokenRepo;

    //just to try user another logger
    private final Logger logger = LoggerFactory.getLogger(userController.class);

    @GetMapping("/login")
    public String createUser(){
        return "loginPage";
    }

    @GetMapping("/registration")
    public String registUser(){
        return "registPage";
    }

    @PostMapping("/registration")
    public String createUserPost(User user , Model model){
        if(!userService.createUser(user)) {
            model.addAttribute("errorMassage", "User with email:" + user.getEmail() + " already exists");
            return "registPage";
        }
        model.addAttribute("email",user.getEmail());

        String str = "Hi %s this is ARTS.com administration\n" +
                "please confirm your registration by clickin this link http://localhost:8080/confirm-account?token=%s";

        TokenModel tokenModel = tokenRepo.findByUserId(user.getId());

        senderService.sendEmail(user.getEmail(),
               String.format(str,user.getName(),tokenModel.getConfirmationToken()),"Complete Registration");
        return "confirmBlock";
    }

    @GetMapping("/confirm-account")
    public String getConfirmed(@RequestParam(name = "token") String token){
        TokenModel tokenModel = tokenRepo.findByConfirmationToken(token);
        if(tokenModel != null){
            User user = tokenModel.getUser();
            user.setActive(true);
            userRepo.save(user);
            tokenRepo.deleteById(tokenModel.getId());
        }else{
            return "errorPage";
        }

        return "redirect:/login";
    }


    @GetMapping("/profile/{id}")
    public String homePage(Model model, Principal principal, @PathVariable(name = "id") Long id){
        model.addAttribute("user",postService.getUserByPrincipal(principal));
        User user = userRepo.findById(id).orElse(null);

        if(user != null){
            model.addAttribute("userInfo",user);
        }

        return "homePage";
    }

    @GetMapping("/home")
    public String privateControllPage(Principal principal,Model model){
        User user = postService.getUserByPrincipal(principal);
        model.addAttribute("userInfo",user);
        model.addAttribute("user",user);
        return "profileP";
    }

    @PostMapping("/setImageAvatar")
    public String setImageAv(@RequestParam(name = "avatar") MultipartFile file, Principal principal) throws IOException {
        User user = postService.getUserByPrincipal(principal);

        userService.addIconImage(user,file);

        return "redirect:/home";
    }

    @GetMapping("/editProfilePage")
    public String editPage(Model model,Principal principal){
        User user = postService.getUserByPrincipal(principal);
            model.addAttribute("userInfo",user);
            model.addAttribute("user",user);

            return "editProfilePage";
    }

    @PostMapping("/editProfile")
    public String editPagePost(Principal principal,Model model, @RequestParam(name = "name") String name){
        User user = postService.getUserByPrincipal(principal);
        user.setName(name);
        userRepo.save(user);
        logger.info("Set new name");
        return "redirect:/editProfilePage";
    }
}
