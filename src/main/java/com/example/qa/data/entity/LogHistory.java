package com.example.qa.data.entity;

import com.example.qa.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "logs")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LogHistory extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column( name = "action")
    private String action;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public LogHistory(String action, User user){
        this.action = action;
        this.user = user;
        super.setLastUpdatedDate(DateUtils.now());
    }
}
