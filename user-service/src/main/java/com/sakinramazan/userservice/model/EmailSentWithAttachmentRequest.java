package com.sakinramazan.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailSentWithAttachmentRequest extends EmailSentRequest {
    private String attachmentName;
    private String attachmentPath;
}
