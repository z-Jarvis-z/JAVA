package com.wh.bank.vo;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AccountVo {
    @NotBlank(message = "银行账户不能为空")
    @Length(min = 3, max = 16, message = "银行账户长度必须在16到19之间")
    private String accountId;

    @NotBlank
    @Min(value = 3, message = "密码不能过短")
    private String password;

    @NotBlank(message = "姓名不能为空")
    @Size(min = 2, max = 4, message = "姓名长度必须在2到4之间")
    private String identityName;

    @Pattern(regexp = "^1[3,4,5,6,7,8,9][0-9]{9}$", message = "手机号码格式不正确")
    private String mobile;
}
