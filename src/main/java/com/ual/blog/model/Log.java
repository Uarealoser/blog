package com.ual.blog.model;

import com.ual.blog.plugin.CreateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ual.blog.plugin.CreateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name="t_log")
public class Log {

    @Id
    private Long id;

    private String method;

    private String operator;

    private String descr;

    @CreateTime
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
