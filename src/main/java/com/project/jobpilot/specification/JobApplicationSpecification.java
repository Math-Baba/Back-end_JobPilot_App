package com.project.jobpilot.specification;

import com.project.jobpilot.entities.JobApplication;
import com.project.jobpilot.entities.JobCompanyInfo;
import com.project.jobpilot.entities.JobPositionInfo;
import com.project.jobpilot.enums.CompanyType;
import com.project.jobpilot.enums.PositionType;
import com.project.jobpilot.enums.Sector;
import com.project.jobpilot.enums.Status;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.List;

public class JobApplicationSpecification {
    public static Specification<JobApplication> hasPositionType(List<PositionType> positionTypes){
        return (root, query, criteriaBuilder) -> {
            query.distinct(true); // On évite les doublons
            if(positionTypes == null || positionTypes.isEmpty()){
                return null;
            }
            Join<JobApplication, JobPositionInfo> positionTypeJoin = root.join("jobPositionInfo");
            return positionTypeJoin.get("positionType").in(positionTypes);
        };
    }

    public static Specification<JobApplication> hasStatus(List<Status> statuses){
        return (root, query, criteriaBuilder) -> {
            query.distinct(true);
            if(statuses == null || statuses.isEmpty()){
                return null;
            }
            Join<JobApplication, JobPositionInfo> statusJoin = root.join("jobPositionInfo");
            return statusJoin.get("status").in(statuses);
        };
    }

    public static Specification<JobApplication> hasApplicationDate(List<LocalDate> applicationsDates){
        return (root, query, criteriaBuilder) -> {
            query.distinct(true);
            if(applicationsDates == null || applicationsDates.isEmpty()){
                return null;
            }
            Join<JobApplication, JobPositionInfo> applicationDateJoin = root.join("jobPositionInfo");
            return applicationDateJoin.get("applicationDate").in(applicationsDates);
        };
    }

    public static Specification<JobApplication> hasSector(List<Sector> sectors){
        return (root, query, criteriaBuilder) -> {
            query.distinct(true);
            if(sectors == null || sectors.isEmpty()){
                return null;
            }
            Join<JobApplication, JobCompanyInfo> sectorJoin = root.join("jobCompanyInfo");
            return sectorJoin.get("sector").in(sectors);
        };
    }

    public static Specification<JobApplication> hasCompanyType(List<CompanyType> companyTypes){
        return (root, query, criteriaBuilder) -> {
            query.distinct(true);
            if(companyTypes == null || companyTypes.isEmpty()){
                return null;
            }
            Join<JobApplication, JobCompanyInfo> companyTypeJoin = root.join("jobCompanyInfo");
            return companyTypeJoin.get("companyType").in(companyTypes);
        };
    }
}
