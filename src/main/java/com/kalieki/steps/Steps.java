package com.kalieki.steps;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by kalieki on 11/12/16.
 */

@Entity
public class Steps {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private int steps;

    @Column
    private Date logTime;


}
