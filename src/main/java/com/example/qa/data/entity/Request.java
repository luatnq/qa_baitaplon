package com.example.qa.data.entity;


import com.example.qa.dto.RequestDTO;
import com.example.qa.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "requests")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Proxy(lazy=false)
public class Request extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username_request")
    private String usernameReq;

    @Column(name = "expire_date")
    @DateTimeFormat(pattern="yyyy.MM.dd hh:mm:ss")
    private Date expireDate;

    @Column(name = "status")
    private int status;

    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "class_name")
    private String className;

    @Column(name = "username_approve")
    private String usernameApprove;

    public Request(RequestDTO request, Date expireDate){
        this.usernameReq = request.getUsernameReq();
        this.expireDate = expireDate;
        this.className = request.getClassName();
        this.subjectName = request.getSubjectName();
        this.status = request.getStatus();
        super.setCreatedDate(DateUtils.now());
        super.setLastUpdatedDate(DateUtils.now());
    }
}
