package com.puck.intelrecom.domain;

public class RuRefRulesKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ru_ref_rules.recommended
     *
     * @mbggenerated Thu Oct 27 14:23:56 CST 2016
     */
    private String recommended;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ru_ref_rules.recom_col
     *
     * @mbggenerated Thu Oct 27 14:23:56 CST 2016
     */
    private String recomCol;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ru_ref_rules.recom_source
     *
     * @mbggenerated Thu Oct 27 14:23:56 CST 2016
     */
    private String recomSource;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ru_ref_rules.recom_source_col
     *
     * @mbggenerated Thu Oct 27 14:23:56 CST 2016
     */
    private String recomSourceCol;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ru_ref_rules.recommended
     *
     * @return the value of ru_ref_rules.recommended
     *
     * @mbggenerated Thu Oct 27 14:23:56 CST 2016
     */
    public String getRecommended() {
        return recommended;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ru_ref_rules.recommended
     *
     * @param recommended the value for ru_ref_rules.recommended
     *
     * @mbggenerated Thu Oct 27 14:23:56 CST 2016
     */
    public void setRecommended(String recommended) {
        this.recommended = recommended == null ? null : recommended.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ru_ref_rules.recom_col
     *
     * @return the value of ru_ref_rules.recom_col
     *
     * @mbggenerated Thu Oct 27 14:23:56 CST 2016
     */
    public String getRecomCol() {
        return recomCol;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ru_ref_rules.recom_col
     *
     * @param recomCol the value for ru_ref_rules.recom_col
     *
     * @mbggenerated Thu Oct 27 14:23:56 CST 2016
     */
    public void setRecomCol(String recomCol) {
        this.recomCol = recomCol == null ? null : recomCol.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ru_ref_rules.recom_source
     *
     * @return the value of ru_ref_rules.recom_source
     *
     * @mbggenerated Thu Oct 27 14:23:56 CST 2016
     */
    public String getRecomSource() {
        return recomSource;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ru_ref_rules.recom_source
     *
     * @param recomSource the value for ru_ref_rules.recom_source
     *
     * @mbggenerated Thu Oct 27 14:23:56 CST 2016
     */
    public void setRecomSource(String recomSource) {
        this.recomSource = recomSource == null ? null : recomSource.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ru_ref_rules.recom_source_col
     *
     * @return the value of ru_ref_rules.recom_source_col
     *
     * @mbggenerated Thu Oct 27 14:23:56 CST 2016
     */
    public String getRecomSourceCol() {
        return recomSourceCol;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ru_ref_rules.recom_source_col
     *
     * @param recomSourceCol the value for ru_ref_rules.recom_source_col
     *
     * @mbggenerated Thu Oct 27 14:23:56 CST 2016
     */
    public void setRecomSourceCol(String recomSourceCol) {
        this.recomSourceCol = recomSourceCol == null ? null : recomSourceCol.trim();
    }
}