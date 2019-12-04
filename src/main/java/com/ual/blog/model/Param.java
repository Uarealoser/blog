package com.ual.blog.model;

import com.ual.blog.plugin.CreateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ual.blog.plugin.CreateTime;
import com.ual.blog.plugin.UpdateTime;
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
@Table(name="t_param")
public class Param {

    @Id
    private Integer id;

    private String paramName;

    private String paramValue;

    private String descr;

    private Integer sort;

    private Integer type;

    @CreateTime
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @UpdateTime
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
