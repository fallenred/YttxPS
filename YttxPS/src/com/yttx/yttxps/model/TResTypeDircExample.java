package com.yttx.yttxps.model;

import java.util.ArrayList;
import java.util.List;

public class TResTypeDircExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TResTypeDircExample() {
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

        public Criteria andFsResnameIsNull() {
            addCriterion("FS_RESNAME is null");
            return (Criteria) this;
        }

        public Criteria andFsResnameIsNotNull() {
            addCriterion("FS_RESNAME is not null");
            return (Criteria) this;
        }

        public Criteria andFsResnameEqualTo(String value) {
            addCriterion("FS_RESNAME =", value, "fsResname");
            return (Criteria) this;
        }

        public Criteria andFsResnameNotEqualTo(String value) {
            addCriterion("FS_RESNAME <>", value, "fsResname");
            return (Criteria) this;
        }

        public Criteria andFsResnameGreaterThan(String value) {
            addCriterion("FS_RESNAME >", value, "fsResname");
            return (Criteria) this;
        }

        public Criteria andFsResnameGreaterThanOrEqualTo(String value) {
            addCriterion("FS_RESNAME >=", value, "fsResname");
            return (Criteria) this;
        }

        public Criteria andFsResnameLessThan(String value) {
            addCriterion("FS_RESNAME <", value, "fsResname");
            return (Criteria) this;
        }

        public Criteria andFsResnameLessThanOrEqualTo(String value) {
            addCriterion("FS_RESNAME <=", value, "fsResname");
            return (Criteria) this;
        }

        public Criteria andFsResnameLike(String value) {
            addCriterion("FS_RESNAME like", value, "fsResname");
            return (Criteria) this;
        }

        public Criteria andFsResnameNotLike(String value) {
            addCriterion("FS_RESNAME not like", value, "fsResname");
            return (Criteria) this;
        }

        public Criteria andFsResnameIn(List<String> values) {
            addCriterion("FS_RESNAME in", values, "fsResname");
            return (Criteria) this;
        }

        public Criteria andFsResnameNotIn(List<String> values) {
            addCriterion("FS_RESNAME not in", values, "fsResname");
            return (Criteria) this;
        }

        public Criteria andFsResnameBetween(String value1, String value2) {
            addCriterion("FS_RESNAME between", value1, value2, "fsResname");
            return (Criteria) this;
        }

        public Criteria andFsResnameNotBetween(String value1, String value2) {
            addCriterion("FS_RESNAME not between", value1, value2, "fsResname");
            return (Criteria) this;
        }

        public Criteria andFsRespropIsNull() {
            addCriterion("FS_RESPROP is null");
            return (Criteria) this;
        }

        public Criteria andFsRespropIsNotNull() {
            addCriterion("FS_RESPROP is not null");
            return (Criteria) this;
        }

        public Criteria andFsRespropEqualTo(String value) {
            addCriterion("FS_RESPROP =", value, "fsResprop");
            return (Criteria) this;
        }

        public Criteria andFsRespropNotEqualTo(String value) {
            addCriterion("FS_RESPROP <>", value, "fsResprop");
            return (Criteria) this;
        }

        public Criteria andFsRespropGreaterThan(String value) {
            addCriterion("FS_RESPROP >", value, "fsResprop");
            return (Criteria) this;
        }

        public Criteria andFsRespropGreaterThanOrEqualTo(String value) {
            addCriterion("FS_RESPROP >=", value, "fsResprop");
            return (Criteria) this;
        }

        public Criteria andFsRespropLessThan(String value) {
            addCriterion("FS_RESPROP <", value, "fsResprop");
            return (Criteria) this;
        }

        public Criteria andFsRespropLessThanOrEqualTo(String value) {
            addCriterion("FS_RESPROP <=", value, "fsResprop");
            return (Criteria) this;
        }

        public Criteria andFsRespropLike(String value) {
            addCriterion("FS_RESPROP like", value, "fsResprop");
            return (Criteria) this;
        }

        public Criteria andFsRespropNotLike(String value) {
            addCriterion("FS_RESPROP not like", value, "fsResprop");
            return (Criteria) this;
        }

        public Criteria andFsRespropIn(List<String> values) {
            addCriterion("FS_RESPROP in", values, "fsResprop");
            return (Criteria) this;
        }

        public Criteria andFsRespropNotIn(List<String> values) {
            addCriterion("FS_RESPROP not in", values, "fsResprop");
            return (Criteria) this;
        }

        public Criteria andFsRespropBetween(String value1, String value2) {
            addCriterion("FS_RESPROP between", value1, value2, "fsResprop");
            return (Criteria) this;
        }

        public Criteria andFsRespropNotBetween(String value1, String value2) {
            addCriterion("FS_RESPROP not between", value1, value2, "fsResprop");
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