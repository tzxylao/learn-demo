package com.tzxylao.java8.valid;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author laoliangliang
 * @date 2019/10/21 16:44
 */
@Data
@Accessors(chain = true)
public class Order {

    @NotNull(message = "id不能为空",groups = Update.class)
    private Long id;

    @NotEmpty(message = "name is not null",groups = Insert.class)
    private String name;

    @Future(message = "必须之后的时间")
    private Date createDate;

    @IdCardValid(message = "idcard 不合法")
    private String idcard;
}
