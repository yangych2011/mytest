package com.puck.intelrecom.domain;

import java.util.Date;

public class RuDilatationEnmu {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ru_dilatation_enmu.id
     *
     * @mbggenerated Thu Oct 27 14:23:56 CST 2016
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ru_dilatation_enmu.dilatation_id
     *
     * @mbggenerated Thu Oct 27 14:23:56 CST 2016
     */
    private Integer dilatationId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ru_dilatation_enmu.enmu_value
     *
     * @mbggenerated Thu Oct 27 14:23:56 CST 2016
     */
    private String enmuValue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ru_dilatation_enmu.status
     *
     * @mbggenerated Thu Oct 27 14:23:56 CST 2016
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ru_dilatation_enmu.create_time
     *
     * @mbggenerated Thu Oct 27 14:23:56 CST 2016
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ru_dilatation_enmu.id
     *
     * @return the value of ru_dilatation_enmu.id
     *
     * @mbggenerated Thu Oct 27 14:23:56 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ru_dilatation_enmu.id
     *
     * @param id the value for ru_dilatation_enmu.id
     *
     * @mbggenerated Thu Oct 27 14:23:56 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ru_dilatation_enmu.dilatation_id
     *
     * @return the value of ru_dilatation_enmu.dilatation_id
     *
     * @mbggenerated Thu Oct 27 14:23:56 CST 2016
     */
    public Integer getDilatationId() {
        return dilatationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ru_dilatation_enmu.dilatation_id
     *
     * @param dilatationId the value for ru_dilatation_enmu.dilatation_id
     *
     * @mbggenerated Thu Oct 27 14:23:56 CST 2016
     */
    public void setDilatationId(Integer dilatationId) {
        this.dilatationId = dilatationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ru_dilatation_enmu.enmu_value
     *
     * @return the value of ru_dilatation_enmu.enmu_value
     *
     * @mbggenerated Thu Oct 27 14:23:56 CST 2016
     */
    public String getEnmuValue() {
        return enmuValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ru_dilatation_enmu.enmu_value
     *
     * @param enmuValue the value for ru_dilatation_enmu.enmu_value
     *
     * @mbggenerated Thu Oct 27 14:23:56 CST 2016
     */
    public void setEnmuValue(String enmuValue) {
        this.enmuValue = enmuValue == null ? null : enmuValue.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ru_dilatation_enmu.status
     *
     * @return the value of ru_dilatation_enmu.status
     *
     * @mbggenerated Thu Oct 27 14:23:56 CST 2016
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ru_dilatation_enmu.status
     *
     * @param status the value for ru_dilatation_enmu.status
     *
     * @mbggenerated Thu Oct 27 14:23:56 CST 2016
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ru_dilatation_enmu.create_time
     *
     * @return the value of ru_dilatation_enmu.create_time
     *
     * @mbggenerated Thu Oct 27 14:23:56 CST 2016
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ru_dilatation_enmu.create_time
     *
     * @param createTime the value for ru_dilatation_enmu.create_time
     *
     * @mbggenerated Thu Oct 27 14:23:56 CST 2016
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}