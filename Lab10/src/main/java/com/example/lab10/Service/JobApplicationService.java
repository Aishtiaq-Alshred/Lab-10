package com.example.lab10.Service;

import com.example.lab10.Model.JobApplication;
import com.example.lab10.Model.JobPost;
import com.example.lab10.Model.User;
import com.example.lab10.Repository.JobPostRepository;
import com.example.lab10.Repository.JobApplicationRepository;
import com.example.lab10.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {

    private final JobApplicationRepository jopApplicationRepository;
    private final JobPostRepository jobPostRepository;
    private final UserRepository userRepository;


    public List<JobApplication> getJopApplication(){
        return jopApplicationRepository.findAll();
    }

    public Boolean ApplyForJob(JobApplication jopApplication){
        List<User>users=userRepository.findAll();
        List<JobPost> jobPosts =jobPostRepository.findAll();
        for( User user: users){
            if(user.getId()==jopApplication.getUserid()){
                for(JobPost jobPost:jobPosts){
                    if(jobPost.getId()==jopApplication.getJobPostId()){
                        jopApplicationRepository.save(jopApplication);
                        return true;
                    }
                }
            }
        }

        return false;

    }


    public Boolean WithdrawJobApplication(Integer id){

        JobApplication jopApplication = jopApplicationRepository.getById(id);
        if(jopApplication==null){

            return false;
        }

        jopApplicationRepository.delete(jopApplication);
        return true;
    }


}
