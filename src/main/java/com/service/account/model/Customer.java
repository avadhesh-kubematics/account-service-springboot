package com.service.account.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
public class Customer {
    private Integer id;
    private String firstName;
    private String surname;
}
