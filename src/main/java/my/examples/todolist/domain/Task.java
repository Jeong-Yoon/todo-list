package my.examples.todolist.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

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
    private String title;
    @Lob
    private String content;
    @Column(name = "reg_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private Date regDate;
    @Column(name = "end_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private Date endDate;
    @Column(columnDefinition="tinyint(1) default 0")
    private boolean complete;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "priorityId")
    private Priority priority;

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
