package uz.controlstudentserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String number;

    private int course_number;

    @ManyToOne(fetch = FetchType.EAGER)
    private Direction direction;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> teachers;
}
