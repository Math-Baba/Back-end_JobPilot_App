package com.example.jobpilot.mappers;
import com.example.jobpilot.dtos.JobApplicationDTO;
import com.example.jobpilot.entities.JobApplication;
import com.example.jobpilot.requests.JobApplicationRequest;
import com.example.jobpilot.responses.JobApplicationResponse;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface JobApplicationMapper {

    JobApplicationDTO jobApplicationToJobApplicationDTO(JobApplication jobApplication);

    JobApplication jobApplicationRequestToJobApplication(JobApplicationRequest jobApplicationRequest);

    @Mapping(target = "companyName", ignore = true)
    @Mapping(target = "jobTitle", ignore = true)
    @Mapping(target = "sector", ignore = true)
    @Mapping(target = "companyAdress", ignore = true)
    @Mapping(target = "applicationDate", ignore = true)
    @Mapping(target = "companyType", ignore = true)
    JobApplicationResponse jobApplicationToJobApplicationResponse(JobApplication jobApplication);

    @AfterMapping
    default void jobApplicationToJobApplicationResponse(@MappingTarget JobApplicationResponse.JobApplicationResponseBuilder target, JobApplication source){
        target
                .companyName(source.getJobCompanyInfo().getName())
                .jobTitle(source.getJobPositionInfo().getJobTitle())
                .sector(source.getJobCompanyInfo().getSector())
                .companyAdress(source.getJobCompanyInfo().getAdress())
                .applicationDate(source.getJobPositionInfo().getApplicationDate())
                .companyType(source.getJobCompanyInfo().getCompanyType());
    }
}
