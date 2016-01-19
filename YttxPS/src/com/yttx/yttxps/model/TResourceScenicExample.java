package com.yttx.yttxps.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TResourceScenicExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table TRESOURCESCENIC
     *
     * @mbggenerated Sat Jan 16 11:00:29 CST 2016
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table TRESOURCESCENIC
     *
     * @mbggenerated Sat Jan 16 11:00:29 CST 2016
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table TRESOURCESCENIC
     *
     * @mbggenerated Sat Jan 16 11:00:29 CST 2016
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TRESOURCESCENIC
     *
     * @mbggenerated Sat Jan 16 11:00:29 CST 2016
     */
    public TResourceScenicExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TRESOURCESCENIC
     *
     * @mbggenerated Sat Jan 16 11:00:29 CST 2016
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TRESOURCESCENIC
     *
     * @mbggenerated Sat Jan 16 11:00:29 CST 2016
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TRESOURCESCENIC
     *
     * @mbggenerated Sat Jan 16 11:00:29 CST 2016
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TRESOURCESCENIC
     *
     * @mbggenerated Sat Jan 16 11:00:29 CST 2016
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TRESOURCESCENIC
     *
     * @mbggenerated Sat Jan 16 11:00:29 CST 2016
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TRESOURCESCENIC
     *
     * @mbggenerated Sat Jan 16 11:00:29 CST 2016
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TRESOURCESCENIC
     *
     * @mbggenerated Sat Jan 16 11:00:29 CST 2016
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TRESOURCESCENIC
     *
     * @mbggenerated Sat Jan 16 11:00:29 CST 2016
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
     * This method corresponds to the database table TRESOURCESCENIC
     *
     * @mbggenerated Sat Jan 16 11:00:29 CST 2016
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TRESOURCESCENIC
     *
     * @mbggenerated Sat Jan 16 11:00:29 CST 2016
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table TRESOURCESCENIC
     *
     * @mbggenerated Sat Jan 16 11:00:29 CST 2016
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

        public Criteria andFiIndexIsNull() {
            addCriterion("FI_INDEX is null");
            return (Criteria) this;
        }

        public Criteria andFiIndexIsNotNull() {
            addCriterion("FI_INDEX is not null");
            return (Criteria) this;
        }

        public Criteria andFiIndexEqualTo(BigDecimal value) {
            addCriterion("FI_INDEX =", value, "fiIndex");
            return (Criteria) this;
        }

        public Criteria andFiIndexNotEqualTo(BigDecimal value) {
            addCriterion("FI_INDEX <>", value, "fiIndex");
            return (Criteria) this;
        }

        public Criteria andFiIndexGreaterThan(BigDecimal value) {
            addCriterion("FI_INDEX >", value, "fiIndex");
            return (Criteria) this;
        }

        public Criteria andFiIndexGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("FI_INDEX >=", value, "fiIndex");
            return (Criteria) this;
        }

        public Criteria andFiIndexLessThan(BigDecimal value) {
            addCriterion("FI_INDEX <", value, "fiIndex");
            return (Criteria) this;
        }

        public Criteria andFiIndexLessThanOrEqualTo(BigDecimal value) {
            addCriterion("FI_INDEX <=", value, "fiIndex");
            return (Criteria) this;
        }

        public Criteria andFiIndexIn(List<BigDecimal> values) {
            addCriterion("FI_INDEX in", values, "fiIndex");
            return (Criteria) this;
        }

        public Criteria andFiIndexNotIn(List<BigDecimal> values) {
            addCriterion("FI_INDEX not in", values, "fiIndex");
            return (Criteria) this;
        }

        public Criteria andFiIndexBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FI_INDEX between", value1, value2, "fiIndex");
            return (Criteria) this;
        }

        public Criteria andFiIndexNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FI_INDEX not between", value1, value2, "fiIndex");
            return (Criteria) this;
        }

        public Criteria andFsScenicnoIsNull() {
            addCriterion("FS_SCENICNO is null");
            return (Criteria) this;
        }

        public Criteria andFsScenicnoIsNotNull() {
            addCriterion("FS_SCENICNO is not null");
            return (Criteria) this;
        }

        public Criteria andFsScenicnoEqualTo(String value) {
            addCriterion("FS_SCENICNO =", value, "fsScenicno");
            return (Criteria) this;
        }

        public Criteria andFsScenicnoNotEqualTo(String value) {
            addCriterion("FS_SCENICNO <>", value, "fsScenicno");
            return (Criteria) this;
        }

        public Criteria andFsScenicnoGreaterThan(String value) {
            addCriterion("FS_SCENICNO >", value, "fsScenicno");
            return (Criteria) this;
        }

        public Criteria andFsScenicnoGreaterThanOrEqualTo(String value) {
            addCriterion("FS_SCENICNO >=", value, "fsScenicno");
            return (Criteria) this;
        }

        public Criteria andFsScenicnoLessThan(String value) {
            addCriterion("FS_SCENICNO <", value, "fsScenicno");
            return (Criteria) this;
        }

        public Criteria andFsScenicnoLessThanOrEqualTo(String value) {
            addCriterion("FS_SCENICNO <=", value, "fsScenicno");
            return (Criteria) this;
        }

        public Criteria andFsScenicnoLike(String value) {
            addCriterion("FS_SCENICNO like", value, "fsScenicno");
            return (Criteria) this;
        }

        public Criteria andFsScenicnoNotLike(String value) {
            addCriterion("FS_SCENICNO not like", value, "fsScenicno");
            return (Criteria) this;
        }

        public Criteria andFsScenicnoIn(List<String> values) {
            addCriterion("FS_SCENICNO in", values, "fsScenicno");
            return (Criteria) this;
        }

        public Criteria andFsScenicnoNotIn(List<String> values) {
            addCriterion("FS_SCENICNO not in", values, "fsScenicno");
            return (Criteria) this;
        }

        public Criteria andFsScenicnoBetween(String value1, String value2) {
            addCriterion("FS_SCENICNO between", value1, value2, "fsScenicno");
            return (Criteria) this;
        }

        public Criteria andFsScenicnoNotBetween(String value1, String value2) {
            addCriterion("FS_SCENICNO not between", value1, value2, "fsScenicno");
            return (Criteria) this;
        }

        public Criteria andFsRestypeIsNull() {
            addCriterion("FS_RESTYPE is null");
            return (Criteria) this;
        }

        public Criteria andFsRestypeIsNotNull() {
            addCriterion("FS_RESTYPE is not null");
            return (Criteria) this;
        }

        public Criteria andFsRestypeEqualTo(String value) {
            addCriterion("FS_RESTYPE =", value, "fsRestype");
            return (Criteria) this;
        }

        public Criteria andFsRestypeNotEqualTo(String value) {
            addCriterion("FS_RESTYPE <>", value, "fsRestype");
            return (Criteria) this;
        }

        public Criteria andFsRestypeGreaterThan(String value) {
            addCriterion("FS_RESTYPE >", value, "fsRestype");
            return (Criteria) this;
        }

        public Criteria andFsRestypeGreaterThanOrEqualTo(String value) {
            addCriterion("FS_RESTYPE >=", value, "fsRestype");
            return (Criteria) this;
        }

        public Criteria andFsRestypeLessThan(String value) {
            addCriterion("FS_RESTYPE <", value, "fsRestype");
            return (Criteria) this;
        }

        public Criteria andFsRestypeLessThanOrEqualTo(String value) {
            addCriterion("FS_RESTYPE <=", value, "fsRestype");
            return (Criteria) this;
        }

        public Criteria andFsRestypeLike(String value) {
            addCriterion("FS_RESTYPE like", value, "fsRestype");
            return (Criteria) this;
        }

        public Criteria andFsRestypeNotLike(String value) {
            addCriterion("FS_RESTYPE not like", value, "fsRestype");
            return (Criteria) this;
        }

        public Criteria andFsRestypeIn(List<String> values) {
            addCriterion("FS_RESTYPE in", values, "fsRestype");
            return (Criteria) this;
        }

        public Criteria andFsRestypeNotIn(List<String> values) {
            addCriterion("FS_RESTYPE not in", values, "fsRestype");
            return (Criteria) this;
        }

        public Criteria andFsRestypeBetween(String value1, String value2) {
            addCriterion("FS_RESTYPE between", value1, value2, "fsRestype");
            return (Criteria) this;
        }

        public Criteria andFsRestypeNotBetween(String value1, String value2) {
            addCriterion("FS_RESTYPE not between", value1, value2, "fsRestype");
            return (Criteria) this;
        }

        public Criteria andFsResnoIsNull() {
            addCriterion("FS_RESNO is null");
            return (Criteria) this;
        }

        public Criteria andFsResnoIsNotNull() {
            addCriterion("FS_RESNO is not null");
            return (Criteria) this;
        }

        public Criteria andFsResnoEqualTo(String value) {
            addCriterion("FS_RESNO =", value, "fsResno");
            return (Criteria) this;
        }

        public Criteria andFsResnoNotEqualTo(String value) {
            addCriterion("FS_RESNO <>", value, "fsResno");
            return (Criteria) this;
        }

        public Criteria andFsResnoGreaterThan(String value) {
            addCriterion("FS_RESNO >", value, "fsResno");
            return (Criteria) this;
        }

        public Criteria andFsResnoGreaterThanOrEqualTo(String value) {
            addCriterion("FS_RESNO >=", value, "fsResno");
            return (Criteria) this;
        }

        public Criteria andFsResnoLessThan(String value) {
            addCriterion("FS_RESNO <", value, "fsResno");
            return (Criteria) this;
        }

        public Criteria andFsResnoLessThanOrEqualTo(String value) {
            addCriterion("FS_RESNO <=", value, "fsResno");
            return (Criteria) this;
        }

        public Criteria andFsResnoLike(String value) {
            addCriterion("FS_RESNO like", value, "fsResno");
            return (Criteria) this;
        }

        public Criteria andFsResnoNotLike(String value) {
            addCriterion("FS_RESNO not like", value, "fsResno");
            return (Criteria) this;
        }

        public Criteria andFsResnoIn(List<String> values) {
            addCriterion("FS_RESNO in", values, "fsResno");
            return (Criteria) this;
        }

        public Criteria andFsResnoNotIn(List<String> values) {
            addCriterion("FS_RESNO not in", values, "fsResno");
            return (Criteria) this;
        }

        public Criteria andFsResnoBetween(String value1, String value2) {
            addCriterion("FS_RESNO between", value1, value2, "fsResno");
            return (Criteria) this;
        }

        public Criteria andFsResnoNotBetween(String value1, String value2) {
            addCriterion("FS_RESNO not between", value1, value2, "fsResno");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table TRESOURCESCENIC
     *
     * @mbggenerated do_not_delete_during_merge Sat Jan 16 11:00:29 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table TRESOURCESCENIC
     *
     * @mbggenerated Sat Jan 16 11:00:29 CST 2016
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