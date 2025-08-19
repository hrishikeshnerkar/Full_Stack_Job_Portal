package com.example.jobportal.controller;

import com.example.jobportal.entity.RecruiterProfile;
import com.example.jobportal.entity.Users;
import com.example.jobportal.repository.UsersRepo;
import com.example.jobportal.services.RecruiterProfileService;
import com.example.jobportal.util.FileUploadUtil;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("recruiter-profile")
public class RecruiterProfileController {

    private final UsersRepo usersRepo;

    private final RecruiterProfileService recruiterProfileService;

    public RecruiterProfileController(UsersRepo usersRepo, RecruiterProfileService recruiterProfileService) {
        this.usersRepo = usersRepo;
        this.recruiterProfileService = recruiterProfileService;
    }

    @GetMapping("/")
    public String recruiterProfile(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            String currentUsername = authentication.getName();
            Users users = usersRepo.findByEmail(currentUsername).orElseThrow(() -> new UsernameNotFoundException("Could not found" + "User"));
            Optional<RecruiterProfile> recruiterProfile = recruiterProfileService.getId(users.getUserId());

            if(!recruiterProfile.isEmpty()){
                model.addAttribute("profile", recruiterProfile.get());
            }
        }

        return "recruiter_profile";
    }


    @PostMapping("/addNew")
    public String addNew(RecruiterProfile recruiterProfile, @RequestParam("image")MultipartFile multipartFile, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            String currentUsername = authentication.getName();
            Users users = usersRepo.findByEmail(currentUsername).orElseThrow(()-> new UsernameNotFoundException("Could not found" + "User"));
            recruiterProfile.setUserId(users);
            recruiterProfile.setUserAccountId(users.getUserId());
        }
        model.addAttribute("profile", recruiterProfile);
        String fileName="";
        if(!multipartFile.getOriginalFilename().equals("")){
            fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            recruiterProfile.setProfilePhoto(fileName);
        }
        RecruiterProfile savedUser = recruiterProfileService.addNew(recruiterProfile);

        String uploadDir = "photos/recruiter/"+savedUser.getUserAccountId();

        try{
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "redirect:/dashboard/";
    }
}
