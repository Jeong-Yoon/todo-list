package my.examples.todolist.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IdDTO {
    @NotNull
    private Long id;
}
