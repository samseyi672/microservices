package com.activedge.dto;


import com.activedge.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Data
@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BalanceDTO {

    private User user ;
    @JsonProperty("accountname")
    private String accountname ;

    @JsonProperty("accountBalance")
    private BigDecimal accountBalance ;
}
