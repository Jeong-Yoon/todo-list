package my.examples.todolist.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import my.examples.todolist.util.PriorityType;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "task")
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (nullable = false)
    private String title;
    @Lob
    private String content;
    @Column(name = "reg_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private Date regDate;
    @Column(name = "end_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private Date endDate;
    @Column
    @Enumerated(EnumType.STRING)
    private PriorityType priority;
    @Column(columnDefinition="tinyint(1) default 0")
    private boolean complete;

    public Task() {
        regDate = new Date();
        complete = false;
    }

    public Task(Date endDate) {
        regDate = new Date();
        endDate = endDate;
        complete = false;
    }
}
