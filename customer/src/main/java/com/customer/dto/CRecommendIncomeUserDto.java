package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by Administrator on 2017/7/8 0008.
 */
@ApiModel(value = "CRecommendIncomeUserDto",description = "用户推荐奖励列表")
public class CRecommendIncomeUserDto {

    @ApiModelProperty(value = "商家名称")
    private String bName;

    @ApiModelProperty(value = "变动收益")
    private String bdsz;

    @ApiModelProperty(value = "变动时间")
    private Date bdsj;

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getBdsz() {
        return bdsz;
    }

    public void setBdsz(String bdsz) {
        this.bdsz = bdsz;
    }

    public Date getBdsj() {
        return bdsj;
    }

    public void setBdsj(Date bdsj) {
        this.bdsj = bdsj;
    }
}
