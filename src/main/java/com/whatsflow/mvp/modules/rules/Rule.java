package com.whatsflow.mvp.modules.rules;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rule {

    private UUID id;
    private String pattern;
    private String response;
    private Integer priority;
    private Boolean active;
}