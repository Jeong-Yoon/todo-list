package my.examples.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import my.examples.todolist.util.PriorityType;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private Long id;
    @NotNull
    private String title;
    @Lob
    private String content;
    @Column(name = "end_date")
    private Date endDate;
    @NotNull
    private PriorityType priority;
}
