package com.puck.intelrecom.domain;

public class BsDict extends BsDictKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bs_dict.code_value
     *
     * @mbggenerated Tue Oct 25 14:53:01 CST 2016
     */
    private String codeValue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bs_dict.seqence
     *
     * @mbggenerated Tue Oct 25 14:53:01 CST 2016
     */
    private Integer seqence;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bs_dict.code_value
     *
     * @return the value of bs_dict.code_value
     *
     * @mbggenerated Tue Oct 25 14:53:01 CST 2016
     */
    public String getCodeValue() {
        return codeValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bs_dict.code_value
     *
     * @param codeValue the value for bs_dict.code_value
     *
     * @mbggenerated Tue Oct 25 14:53:01 CST 2016
     */
    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue == null ? null : codeValue.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bs_dict.seqence
     *
     * @return the value of bs_dict.seqence
     *
     * @mbggenerated Tue Oct 25 14:53:01 CST 2016
     */
    public Integer getSeqence() {
        return seqence;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bs_dict.seqence
     *
     * @param seqence the value for bs_dict.seqence
     *
     * @mbggenerated Tue Oct 25 14:53:01 CST 2016
     */
    public void setSeqence(Integer seqence) {
        this.seqence = seqence;
    }
}