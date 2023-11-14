package com.mongo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "accounts")
public class Account {
    @Id
    private String id;

    @NotNull(message = "to do can not be null")
    private String username;

    @NotNull(message = "password can not be null")
    @Length(min = 6, max = 32, message = "password is from 6-32 characters")
    private String password;

    private List<Integer> roleId;
}
