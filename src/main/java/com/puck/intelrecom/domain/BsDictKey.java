package com.puck.intelrecom.domain;

public class BsDictKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bs_dict.name
     *
     * @mbggenerated Tue Oct 25 14:53:01 CST 2016
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bs_dict.code_key
     *
     * @mbggenerated Tue Oct 25 14:53:01 CST 2016
     */
    private String codeKey;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bs_dict.name
     *
     * @return the value of bs_dict.name
     *
     * @mbggenerated Tue Oct 25 14:53:01 CST 2016
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bs_dict.name
     *
     * @param name the value for bs_dict.name
     *
     * @mbggenerated Tue Oct 25 14:53:01 CST 2016
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bs_dict.code_key
     *
     * @return the value of bs_dict.code_key
     *
     * @mbggenerated Tue Oct 25 14:53:01 CST 2016
     */
    public String getCodeKey() {
        return codeKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bs_dict.code_key
     *
     * @param codeKey the value for bs_dict.code_key
     *
     * @mbggenerated Tue Oct 25 14:53:01 CST 2016
     */
    public void setCodeKey(String codeKey) {
        this.codeKey = codeKey == null ? null : codeKey.trim();
    }
}