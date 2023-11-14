package com.mongo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "todos")
public class TodoDTO {
    @Id
    private String id;

    @NotNull(message = "to do can not be null")
    private String todo;

    @NotNull(message = "description can not be null")
    private String description;

    @NotNull(message = "isDone can not be null")
    private Boolean isDone;

    private Date createAt;

    private Date updateAt;
}
