package com.project.jobpilot.controllers;

import com.project.jobpilot.dtos.JobApplicationDTO;
import com.project.jobpilot.requests.JobApplicationFilterRequest;
import com.project.jobpilot.requests.JobApplicationRequest;
import com.project.jobpilot.responses.JobApplicationResponse;
import com.project.jobpilot.services.JobApplicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST qui gère les opérations liées aux requêtes HTTP
 * Traitement de la requête via un service puis envoie d'une réponse
 */
@RestController
@RequestMapping("/job")
@CrossOrigin("*")
@Validated
public class JobApplicationController {

    @Autowired
    private JobApplicationService jobApplicationService;

    @PostMapping()
    public ResponseEntity<String> create(@Valid @RequestBody JobApplicationRequest request){
        try {
            jobApplicationService.createJobApplication(request);
            return ResponseEntity.ok("Candidature créée avec succès");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Erreur :" + e.getMessage());
        }
    }

    @PutMapping()
    public ResponseEntity<String> update(@Valid @RequestBody JobApplicationRequest request){
        try {
            jobApplicationService.updateJobApplication(request);
            return ResponseEntity.ok("Candidature modifiée avec succès");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Erreur :" + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable long id) {
        jobApplicationService.deleteJobApplication(id);
        return ResponseEntity.ok("La candidature a bien été supprimée avec succès");
    }

    @GetMapping()
    public ResponseEntity<List<JobApplicationResponse>> getAll(){
        return ResponseEntity.ok(jobApplicationService.getAllJobApplications());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobApplicationDTO> getById(@PathVariable long id){
        return ResponseEntity.ok(jobApplicationService.getJobApplicationById(id));
    }

    @GetMapping("/search/{query}")
    public ResponseEntity<List<JobApplicationResponse>> search(@PathVariable String query) {
        return ResponseEntity.ok(jobApplicationService.search(query));
    }

    @PostMapping("/filter")
    public ResponseEntity<List<JobApplicationResponse>> filter(@RequestBody JobApplicationFilterRequest filterRequest){
        return ResponseEntity.ok(jobApplicationService.filter(filterRequest));
    }
}
