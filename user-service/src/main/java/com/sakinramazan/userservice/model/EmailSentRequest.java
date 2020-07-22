package com.sakinramazan.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailSentRequest {
    private String mailUser;
    private String emailHeadline;
    private String mailDetails;

}
