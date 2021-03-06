package com.yttx.yttxps.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TOrderCustomListExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table TORDERCUSTOMLIST
     *
     * @mbggenerated Sun Mar 20 16:44:11 CST 2016
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table TORDERCUSTOMLIST
     *
     * @mbggenerated Sun Mar 20 16:44:11 CST 2016
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table TORDERCUSTOMLIST
     *
     * @mbggenerated Sun Mar 20 16:44:11 CST 2016
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TORDERCUSTOMLIST
     *
     * @mbggenerated Sun Mar 20 16:44:11 CST 2016
     */
    public TOrderCustomListExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TORDERCUSTOMLIST
     *
     * @mbggenerated Sun Mar 20 16:44:11 CST 2016
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TORDERCUSTOMLIST
     *
     * @mbggenerated Sun Mar 20 16:44:11 CST 2016
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TORDERCUSTOMLIST
     *
     * @mbggenerated Sun Mar 20 16:44:11 CST 2016
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TORDERCUSTOMLIST
     *
     * @mbggenerated Sun Mar 20 16:44:11 CST 2016
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TORDERCUSTOMLIST
     *
     * @mbggenerated Sun Mar 20 16:44:11 CST 2016
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TORDERCUSTOMLIST
     *
     * @mbggenerated Sun Mar 20 16:44:11 CST 2016
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TORDERCUSTOMLIST
     *
     * @mbggenerated Sun Mar 20 16:44:11 CST 2016
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TORDERCUSTOMLIST
     *
     * @mbggenerated Sun Mar 20 16:44:11 CST 2016
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TORDERCUSTOMLIST
     *
     * @mbggenerated Sun Mar 20 16:44:11 CST 2016
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TORDERCUSTOMLIST
     *
     * @mbggenerated Sun Mar 20 16:44:11 CST 2016
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table TORDERCUSTOMLIST
     *
     * @mbggenerated Sun Mar 20 16:44:11 CST 2016
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andFiCustomIdIsNull() {
            addCriterion("FI_CUSTOM_ID is null");
            return (Criteria) this;
        }

        public Criteria andFiCustomIdIsNotNull() {
            addCriterion("FI_CUSTOM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFiCustomIdEqualTo(BigDecimal value) {
            addCriterion("FI_CUSTOM_ID =", value, "fiCustomId");
            return (Criteria) this;
        }

        public Criteria andFiCustomIdNotEqualTo(BigDecimal value) {
            addCriterion("FI_CUSTOM_ID <>", value, "fiCustomId");
            return (Criteria) this;
        }

        public Criteria andFiCustomIdGreaterThan(BigDecimal value) {
            addCriterion("FI_CUSTOM_ID >", value, "fiCustomId");
            return (Criteria) this;
        }

        public Criteria andFiCustomIdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("FI_CUSTOM_ID >=", value, "fiCustomId");
            return (Criteria) this;
        }

        public Criteria andFiCustomIdLessThan(BigDecimal value) {
            addCriterion("FI_CUSTOM_ID <", value, "fiCustomId");
            return (Criteria) this;
        }

        public Criteria andFiCustomIdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("FI_CUSTOM_ID <=", value, "fiCustomId");
            return (Criteria) this;
        }

        public Criteria andFiCustomIdIn(List<BigDecimal> values) {
            addCriterion("FI_CUSTOM_ID in", values, "fiCustomId");
            return (Criteria) this;
        }

        public Criteria andFiCustomIdNotIn(List<BigDecimal> values) {
            addCriterion("FI_CUSTOM_ID not in", values, "fiCustomId");
            return (Criteria) this;
        }

        public Criteria andFiCustomIdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FI_CUSTOM_ID between", value1, value2, "fiCustomId");
            return (Criteria) this;
        }

        public Criteria andFiCustomIdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FI_CUSTOM_ID not between", value1, value2, "fiCustomId");
            return (Criteria) this;
        }

        public Criteria andFiSeqIsNull() {
            addCriterion("FI_SEQ is null");
            return (Criteria) this;
        }

        public Criteria andFiSeqIsNotNull() {
            addCriterion("FI_SEQ is not null");
            return (Criteria) this;
        }

        public Criteria andFiSeqEqualTo(BigDecimal value) {
            addCriterion("FI_SEQ =", value, "fiSeq");
            return (Criteria) this;
        }

        public Criteria andFiSeqNotEqualTo(BigDecimal value) {
            addCriterion("FI_SEQ <>", value, "fiSeq");
            return (Criteria) this;
        }

        public Criteria andFiSeqGreaterThan(BigDecimal value) {
            addCriterion("FI_SEQ >", value, "fiSeq");
            return (Criteria) this;
        }

        public Criteria andFiSeqGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("FI_SEQ >=", value, "fiSeq");
            return (Criteria) this;
        }

        public Criteria andFiSeqLessThan(BigDecimal value) {
            addCriterion("FI_SEQ <", value, "fiSeq");
            return (Criteria) this;
        }

        public Criteria andFiSeqLessThanOrEqualTo(BigDecimal value) {
            addCriterion("FI_SEQ <=", value, "fiSeq");
            return (Criteria) this;
        }

        public Criteria andFiSeqIn(List<BigDecimal> values) {
            addCriterion("FI_SEQ in", values, "fiSeq");
            return (Criteria) this;
        }

        public Criteria andFiSeqNotIn(List<BigDecimal> values) {
            addCriterion("FI_SEQ not in", values, "fiSeq");
            return (Criteria) this;
        }

        public Criteria andFiSeqBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FI_SEQ between", value1, value2, "fiSeq");
            return (Criteria) this;
        }

        public Criteria andFiSeqNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FI_SEQ not between", value1, value2, "fiSeq");
            return (Criteria) this;
        }

        public Criteria andFsNameIsNull() {
            addCriterion("FS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFsNameIsNotNull() {
            addCriterion("FS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFsNameEqualTo(String value) {
            addCriterion("FS_NAME =", value, "fsName");
            return (Criteria) this;
        }

        public Criteria andFsNameNotEqualTo(String value) {
            addCriterion("FS_NAME <>", value, "fsName");
            return (Criteria) this;
        }

        public Criteria andFsNameGreaterThan(String value) {
            addCriterion("FS_NAME >", value, "fsName");
            return (Criteria) this;
        }

        public Criteria andFsNameGreaterThanOrEqualTo(String value) {
            addCriterion("FS_NAME >=", value, "fsName");
            return (Criteria) this;
        }

        public Criteria andFsNameLessThan(String value) {
            addCriterion("FS_NAME <", value, "fsName");
            return (Criteria) this;
        }

        public Criteria andFsNameLessThanOrEqualTo(String value) {
            addCriterion("FS_NAME <=", value, "fsName");
            return (Criteria) this;
        }

        public Criteria andFsNameLike(String value) {
            addCriterion("FS_NAME like", value, "fsName");
            return (Criteria) this;
        }

        public Criteria andFsNameNotLike(String value) {
            addCriterion("FS_NAME not like", value, "fsName");
            return (Criteria) this;
        }

        public Criteria andFsNameIn(List<String> values) {
            addCriterion("FS_NAME in", values, "fsName");
            return (Criteria) this;
        }

        public Criteria andFsNameNotIn(List<String> values) {
            addCriterion("FS_NAME not in", values, "fsName");
            return (Criteria) this;
        }

        public Criteria andFsNameBetween(String value1, String value2) {
            addCriterion("FS_NAME between", value1, value2, "fsName");
            return (Criteria) this;
        }

        public Criteria andFsNameNotBetween(String value1, String value2) {
            addCriterion("FS_NAME not between", value1, value2, "fsName");
            return (Criteria) this;
        }

        public Criteria andFsIdtypeIsNull() {
            addCriterion("FS_IDTYPE is null");
            return (Criteria) this;
        }

        public Criteria andFsIdtypeIsNotNull() {
            addCriterion("FS_IDTYPE is not null");
            return (Criteria) this;
        }

        public Criteria andFsIdtypeEqualTo(String value) {
            addCriterion("FS_IDTYPE =", value, "fsIdtype");
            return (Criteria) this;
        }

        public Criteria andFsIdtypeNotEqualTo(String value) {
            addCriterion("FS_IDTYPE <>", value, "fsIdtype");
            return (Criteria) this;
        }

        public Criteria andFsIdtypeGreaterThan(String value) {
            addCriterion("FS_IDTYPE >", value, "fsIdtype");
            return (Criteria) this;
        }

        public Criteria andFsIdtypeGreaterThanOrEqualTo(String value) {
            addCriterion("FS_IDTYPE >=", value, "fsIdtype");
            return (Criteria) this;
        }

        public Criteria andFsIdtypeLessThan(String value) {
            addCriterion("FS_IDTYPE <", value, "fsIdtype");
            return (Criteria) this;
        }

        public Criteria andFsIdtypeLessThanOrEqualTo(String value) {
            addCriterion("FS_IDTYPE <=", value, "fsIdtype");
            return (Criteria) this;
        }

        public Criteria andFsIdtypeLike(String value) {
            addCriterion("FS_IDTYPE like", value, "fsIdtype");
            return (Criteria) this;
        }

        public Criteria andFsIdtypeNotLike(String value) {
            addCriterion("FS_IDTYPE not like", value, "fsIdtype");
            return (Criteria) this;
        }

        public Criteria andFsIdtypeIn(List<String> values) {
            addCriterion("FS_IDTYPE in", values, "fsIdtype");
            return (Criteria) this;
        }

        public Criteria andFsIdtypeNotIn(List<String> values) {
            addCriterion("FS_IDTYPE not in", values, "fsIdtype");
            return (Criteria) this;
        }

        public Criteria andFsIdtypeBetween(String value1, String value2) {
            addCriterion("FS_IDTYPE between", value1, value2, "fsIdtype");
            return (Criteria) this;
        }

        public Criteria andFsIdtypeNotBetween(String value1, String value2) {
            addCriterion("FS_IDTYPE not between", value1, value2, "fsIdtype");
            return (Criteria) this;
        }

        public Criteria andFsIdIsNull() {
            addCriterion("FS_ID is null");
            return (Criteria) this;
        }

        public Criteria andFsIdIsNotNull() {
            addCriterion("FS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFsIdEqualTo(String value) {
            addCriterion("FS_ID =", value, "fsId");
            return (Criteria) this;
        }

        public Criteria andFsIdNotEqualTo(String value) {
            addCriterion("FS_ID <>", value, "fsId");
            return (Criteria) this;
        }

        public Criteria andFsIdGreaterThan(String value) {
            addCriterion("FS_ID >", value, "fsId");
            return (Criteria) this;
        }

        public Criteria andFsIdGreaterThanOrEqualTo(String value) {
            addCriterion("FS_ID >=", value, "fsId");
            return (Criteria) this;
        }

        public Criteria andFsIdLessThan(String value) {
            addCriterion("FS_ID <", value, "fsId");
            return (Criteria) this;
        }

        public Criteria andFsIdLessThanOrEqualTo(String value) {
            addCriterion("FS_ID <=", value, "fsId");
            return (Criteria) this;
        }

        public Criteria andFsIdLike(String value) {
            addCriterion("FS_ID like", value, "fsId");
            return (Criteria) this;
        }

        public Criteria andFsIdNotLike(String value) {
            addCriterion("FS_ID not like", value, "fsId");
            return (Criteria) this;
        }

        public Criteria andFsIdIn(List<String> values) {
            addCriterion("FS_ID in", values, "fsId");
            return (Criteria) this;
        }

        public Criteria andFsIdNotIn(List<String> values) {
            addCriterion("FS_ID not in", values, "fsId");
            return (Criteria) this;
        }

        public Criteria andFsIdBetween(String value1, String value2) {
            addCriterion("FS_ID between", value1, value2, "fsId");
            return (Criteria) this;
        }

        public Criteria andFsIdNotBetween(String value1, String value2) {
            addCriterion("FS_ID not between", value1, value2, "fsId");
            return (Criteria) this;
        }

        public Criteria andFsTelIsNull() {
            addCriterion("FS_TEL is null");
            return (Criteria) this;
        }

        public Criteria andFsTelIsNotNull() {
            addCriterion("FS_TEL is not null");
            return (Criteria) this;
        }

        public Criteria andFsTelEqualTo(String value) {
            addCriterion("FS_TEL =", value, "fsTel");
            return (Criteria) this;
        }

        public Criteria andFsTelNotEqualTo(String value) {
            addCriterion("FS_TEL <>", value, "fsTel");
            return (Criteria) this;
        }

        public Criteria andFsTelGreaterThan(String value) {
            addCriterion("FS_TEL >", value, "fsTel");
            return (Criteria) this;
        }

        public Criteria andFsTelGreaterThanOrEqualTo(String value) {
            addCriterion("FS_TEL >=", value, "fsTel");
            return (Criteria) this;
        }

        public Criteria andFsTelLessThan(String value) {
            addCriterion("FS_TEL <", value, "fsTel");
            return (Criteria) this;
        }

        public Criteria andFsTelLessThanOrEqualTo(String value) {
            addCriterion("FS_TEL <=", value, "fsTel");
            return (Criteria) this;
        }

        public Criteria andFsTelLike(String value) {
            addCriterion("FS_TEL like", value, "fsTel");
            return (Criteria) this;
        }

        public Criteria andFsTelNotLike(String value) {
            addCriterion("FS_TEL not like", value, "fsTel");
            return (Criteria) this;
        }

        public Criteria andFsTelIn(List<String> values) {
            addCriterion("FS_TEL in", values, "fsTel");
            return (Criteria) this;
        }

        public Criteria andFsTelNotIn(List<String> values) {
            addCriterion("FS_TEL not in", values, "fsTel");
            return (Criteria) this;
        }

        public Criteria andFsTelBetween(String value1, String value2) {
            addCriterion("FS_TEL between", value1, value2, "fsTel");
            return (Criteria) this;
        }

        public Criteria andFsTelNotBetween(String value1, String value2) {
            addCriterion("FS_TEL not between", value1, value2, "fsTel");
            return (Criteria) this;
        }

        public Criteria andFsMarkIsNull() {
            addCriterion("FS_MARK is null");
            return (Criteria) this;
        }

        public Criteria andFsMarkIsNotNull() {
            addCriterion("FS_MARK is not null");
            return (Criteria) this;
        }

        public Criteria andFsMarkEqualTo(String value) {
            addCriterion("FS_MARK =", value, "fsMark");
            return (Criteria) this;
        }

        public Criteria andFsMarkNotEqualTo(String value) {
            addCriterion("FS_MARK <>", value, "fsMark");
            return (Criteria) this;
        }

        public Criteria andFsMarkGreaterThan(String value) {
            addCriterion("FS_MARK >", value, "fsMark");
            return (Criteria) this;
        }

        public Criteria andFsMarkGreaterThanOrEqualTo(String value) {
            addCriterion("FS_MARK >=", value, "fsMark");
            return (Criteria) this;
        }

        public Criteria andFsMarkLessThan(String value) {
            addCriterion("FS_MARK <", value, "fsMark");
            return (Criteria) this;
        }

        public Criteria andFsMarkLessThanOrEqualTo(String value) {
            addCriterion("FS_MARK <=", value, "fsMark");
            return (Criteria) this;
        }

        public Criteria andFsMarkLike(String value) {
            addCriterion("FS_MARK like", value, "fsMark");
            return (Criteria) this;
        }

        public Criteria andFsMarkNotLike(String value) {
            addCriterion("FS_MARK not like", value, "fsMark");
            return (Criteria) this;
        }

        public Criteria andFsMarkIn(List<String> values) {
            addCriterion("FS_MARK in", values, "fsMark");
            return (Criteria) this;
        }

        public Criteria andFsMarkNotIn(List<String> values) {
            addCriterion("FS_MARK not in", values, "fsMark");
            return (Criteria) this;
        }

        public Criteria andFsMarkBetween(String value1, String value2) {
            addCriterion("FS_MARK between", value1, value2, "fsMark");
            return (Criteria) this;
        }

        public Criteria andFsMarkNotBetween(String value1, String value2) {
            addCriterion("FS_MARK not between", value1, value2, "fsMark");
            return (Criteria) this;
        }

        public Criteria andFiStatIsNull() {
            addCriterion("FI_STAT is null");
            return (Criteria) this;
        }

        public Criteria andFiStatIsNotNull() {
            addCriterion("FI_STAT is not null");
            return (Criteria) this;
        }

        public Criteria andFiStatEqualTo(BigDecimal value) {
            addCriterion("FI_STAT =", value, "fiStat");
            return (Criteria) this;
        }

        public Criteria andFiStatNotEqualTo(BigDecimal value) {
            addCriterion("FI_STAT <>", value, "fiStat");
            return (Criteria) this;
        }

        public Criteria andFiStatGreaterThan(BigDecimal value) {
            addCriterion("FI_STAT >", value, "fiStat");
            return (Criteria) this;
        }

        public Criteria andFiStatGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("FI_STAT >=", value, "fiStat");
            return (Criteria) this;
        }

        public Criteria andFiStatLessThan(BigDecimal value) {
            addCriterion("FI_STAT <", value, "fiStat");
            return (Criteria) this;
        }

        public Criteria andFiStatLessThanOrEqualTo(BigDecimal value) {
            addCriterion("FI_STAT <=", value, "fiStat");
            return (Criteria) this;
        }

        public Criteria andFiStatIn(List<BigDecimal> values) {
            addCriterion("FI_STAT in", values, "fiStat");
            return (Criteria) this;
        }

        public Criteria andFiStatNotIn(List<BigDecimal> values) {
            addCriterion("FI_STAT not in", values, "fiStat");
            return (Criteria) this;
        }

        public Criteria andFiStatBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FI_STAT between", value1, value2, "fiStat");
            return (Criteria) this;
        }

        public Criteria andFiStatNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FI_STAT not between", value1, value2, "fiStat");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table TORDERCUSTOMLIST
     *
     * @mbggenerated do_not_delete_during_merge Sun Mar 20 16:44:11 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table TORDERCUSTOMLIST
     *
     * @mbggenerated Sun Mar 20 16:44:11 CST 2016
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}