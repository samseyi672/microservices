package com.activedge.dto;

import com.activedge.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportDto {

    @JsonProperty("id")
    private String id ;  //this will enable us to track report for every user in the db.It is an owning side

    @JsonProperty("info")
    private String reportInfo ;

}
