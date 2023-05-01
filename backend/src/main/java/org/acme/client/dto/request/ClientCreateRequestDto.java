package org.acme.client.dto.request;


import lombok.Data;

import javax.validation.constraints.*;


@Data
public class ClientCreateRequestDto {
    @NotNull
    @NotBlank
    @Size(min = 3)
    private String name;

    @NotNull
    @NotBlank
    @org.hibernate.validator.constraints.br.CPF
    private String CPF;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^\\([1-9]{2}\\) (?:[2-8]|9[1-9])[0-9]{3}\\-[0-9]{4}$")
    private String phone;

    @NotNull
    @NotBlank
    @Email
    private String email;

}
