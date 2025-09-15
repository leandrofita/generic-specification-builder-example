package com.leandrofita.generic_specification_builder_example.dto;

import lombok.Data;

@Data
public class PlayerProfileResponseDTO {

    private Long id;
    private String nickname;
    private String email;
    private String platform;

}
