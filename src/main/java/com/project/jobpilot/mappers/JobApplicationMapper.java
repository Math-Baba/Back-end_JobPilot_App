package com.project.jobpilot.mappers;
import com.project.jobpilot.dtos.JobApplicationDTO;
import com.project.jobpilot.entities.JobApplication;
import com.project.jobpilot.requests.JobApplicationRequest;
import com.project.jobpilot.responses.JobApplicationResponse;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface JobApplicationMapper {

    JobApplicationDTO jobApplicationToJobApplicationDTO(JobApplication jobApplication);

    JobApplication jobApplicationRequestToJobApplication(JobApplicationRequest jobApplicationRequest);

    @Mapping(target = "companyName", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "jobTitle", ignore = true)
    @Mapping(target = "sector", ignore = true)
    @Mapping(target = "status", ignore=true)
    @Mapping(target = "positionType", ignore=true)
    @Mapping(target = "companyAdress", ignore = true)
    @Mapping(target = "applicationDate", ignore = true)
    @Mapping(target = "companyType", ignore = true)
    JobApplicationResponse jobApplicationToJobApplicationResponse(JobApplication jobApplication);

    @AfterMapping
    default void jobApplicationToJobApplicationResponse(@MappingTarget JobApplicationResponse.JobApplicationResponseBuilder target, JobApplication source){
        target
                .companyName(source.getJobCompanyInfo().getName())
                .email(source.getJobCompanyInfo().getEmail())
                .jobTitle(source.getJobPositionInfo().getJobTitle())
                .sector(source.getJobCompanyInfo().getSector())
                .status(source.getJobPositionInfo().getStatus())
                .positionType(source.getJobPositionInfo().getPositionType())
                .companyAdress(source.getJobCompanyInfo().getAdress())
                .applicationDate(source.getJobPositionInfo().getApplicationDate())
                .companyType(source.getJobCompanyInfo().getCompanyType());
    }
}
