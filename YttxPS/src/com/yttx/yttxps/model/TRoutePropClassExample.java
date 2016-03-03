package com.yttx.yttxps.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TRoutePropClassExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TRoutePropClassExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        public Criteria andFiClassIsNull() {
            addCriterion("FI_CLASS is null");
            return (Criteria) this;
        }

        public Criteria andFiClassIsNotNull() {
            addCriterion("FI_CLASS is not null");
            return (Criteria) this;
        }

        public Criteria andFiClassEqualTo(BigDecimal value) {
            addCriterion("FI_CLASS =", value, "fiClass");
            return (Criteria) this;
        }

        public Criteria andFiClassNotEqualTo(BigDecimal value) {
            addCriterion("FI_CLASS <>", value, "fiClass");
            return (Criteria) this;
        }

        public Criteria andFiClassGreaterThan(BigDecimal value) {
            addCriterion("FI_CLASS >", value, "fiClass");
            return (Criteria) this;
        }

        public Criteria andFiClassGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("FI_CLASS >=", value, "fiClass");
            return (Criteria) this;
        }

        public Criteria andFiClassLessThan(BigDecimal value) {
            addCriterion("FI_CLASS <", value, "fiClass");
            return (Criteria) this;
        }

        public Criteria andFiClassLessThanOrEqualTo(BigDecimal value) {
            addCriterion("FI_CLASS <=", value, "fiClass");
            return (Criteria) this;
        }

        public Criteria andFiClassIn(List<BigDecimal> values) {
            addCriterion("FI_CLASS in", values, "fiClass");
            return (Criteria) this;
        }

        public Criteria andFiClassNotIn(List<BigDecimal> values) {
            addCriterion("FI_CLASS not in", values, "fiClass");
            return (Criteria) this;
        }

        public Criteria andFiClassBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FI_CLASS between", value1, value2, "fiClass");
            return (Criteria) this;
        }

        public Criteria andFiClassNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FI_CLASS not between", value1, value2, "fiClass");
            return (Criteria) this;
        }

        public Criteria andFiClasstypeIsNull() {
            addCriterion("FI_CLASSTYPE is null");
            return (Criteria) this;
        }

        public Criteria andFiClasstypeIsNotNull() {
            addCriterion("FI_CLASSTYPE is not null");
            return (Criteria) this;
        }

        public Criteria andFiClasstypeEqualTo(BigDecimal value) {
            addCriterion("FI_CLASSTYPE =", value, "fiClasstype");
            return (Criteria) this;
        }

        public Criteria andFiClasstypeNotEqualTo(BigDecimal value) {
            addCriterion("FI_CLASSTYPE <>", value, "fiClasstype");
            return (Criteria) this;
        }

        public Criteria andFiClasstypeGreaterThan(BigDecimal value) {
            addCriterion("FI_CLASSTYPE >", value, "fiClasstype");
            return (Criteria) this;
        }

        public Criteria andFiClasstypeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("FI_CLASSTYPE >=", value, "fiClasstype");
            return (Criteria) this;
        }

        public Criteria andFiClasstypeLessThan(BigDecimal value) {
            addCriterion("FI_CLASSTYPE <", value, "fiClasstype");
            return (Criteria) this;
        }

        public Criteria andFiClasstypeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("FI_CLASSTYPE <=", value, "fiClasstype");
            return (Criteria) this;
        }

        public Criteria andFiClasstypeIn(List<BigDecimal> values) {
            addCriterion("FI_CLASSTYPE in", values, "fiClasstype");
            return (Criteria) this;
        }

        public Criteria andFiClasstypeNotIn(List<BigDecimal> values) {
            addCriterion("FI_CLASSTYPE not in", values, "fiClasstype");
            return (Criteria) this;
        }

        public Criteria andFiClasstypeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FI_CLASSTYPE between", value1, value2, "fiClasstype");
            return (Criteria) this;
        }

        public Criteria andFiClasstypeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FI_CLASSTYPE not between", value1, value2, "fiClasstype");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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