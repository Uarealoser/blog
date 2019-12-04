package com.ual.blog.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ual.blog.plugin.CreateTime;
import com.ual.blog.plugin.UpdateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * @ClassName Category
 * @Description TODO
 * @Author ual
 */

@Getter
@Setter
@NoArgsConstructor
@ToString
@Accessors(chain = true)
@Table(name="t_category")
public class Category {
    @Id
    private Integer id;
    @NotEmpty(message = "名字不能为空")
    private String name;

    private String descr;

    private String color;

    private String imgUrl;
    @CreateTime
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @UpdateTime
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
