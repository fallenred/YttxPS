package com.yttx.yttxps.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RoomExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RoomExample() {
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

        public Criteria andFsRoomnoIsNull() {
            addCriterion("FS_ROOMNO is null");
            return (Criteria) this;
        }

        public Criteria andFsRoomnoIsNotNull() {
            addCriterion("FS_ROOMNO is not null");
            return (Criteria) this;
        }

        public Criteria andFsRoomnoEqualTo(String value) {
            addCriterion("FS_ROOMNO =", value, "fsRoomno");
            return (Criteria) this;
        }

        public Criteria andFsRoomnoNotEqualTo(String value) {
            addCriterion("FS_ROOMNO <>", value, "fsRoomno");
            return (Criteria) this;
        }

        public Criteria andFsRoomnoGreaterThan(String value) {
            addCriterion("FS_ROOMNO >", value, "fsRoomno");
            return (Criteria) this;
        }

        public Criteria andFsRoomnoGreaterThanOrEqualTo(String value) {
            addCriterion("FS_ROOMNO >=", value, "fsRoomno");
            return (Criteria) this;
        }

        public Criteria andFsRoomnoLessThan(String value) {
            addCriterion("FS_ROOMNO <", value, "fsRoomno");
            return (Criteria) this;
        }

        public Criteria andFsRoomnoLessThanOrEqualTo(String value) {
            addCriterion("FS_ROOMNO <=", value, "fsRoomno");
            return (Criteria) this;
        }

        public Criteria andFsRoomnoLike(String value) {
            addCriterion("FS_ROOMNO like", value, "fsRoomno");
            return (Criteria) this;
        }

        public Criteria andFsRoomnoNotLike(String value) {
            addCriterion("FS_ROOMNO not like", value, "fsRoomno");
            return (Criteria) this;
        }

        public Criteria andFsRoomnoIn(List<String> values) {
            addCriterion("FS_ROOMNO in", values, "fsRoomno");
            return (Criteria) this;
        }

        public Criteria andFsRoomnoNotIn(List<String> values) {
            addCriterion("FS_ROOMNO not in", values, "fsRoomno");
            return (Criteria) this;
        }

        public Criteria andFsRoomnoBetween(String value1, String value2) {
            addCriterion("FS_ROOMNO between", value1, value2, "fsRoomno");
            return (Criteria) this;
        }

        public Criteria andFsRoomnoNotBetween(String value1, String value2) {
            addCriterion("FS_ROOMNO not between", value1, value2, "fsRoomno");
            return (Criteria) this;
        }

        public Criteria andFsAccomnoIsNull() {
            addCriterion("FS_ACCOMNO is null");
            return (Criteria) this;
        }

        public Criteria andFsAccomnoIsNotNull() {
            addCriterion("FS_ACCOMNO is not null");
            return (Criteria) this;
        }

        public Criteria andFsAccomnoEqualTo(String value) {
            addCriterion("FS_ACCOMNO =", value, "fsAccomno");
            return (Criteria) this;
        }

        public Criteria andFsAccomnoNotEqualTo(String value) {
            addCriterion("FS_ACCOMNO <>", value, "fsAccomno");
            return (Criteria) this;
        }

        public Criteria andFsAccomnoGreaterThan(String value) {
            addCriterion("FS_ACCOMNO >", value, "fsAccomno");
            return (Criteria) this;
        }

        public Criteria andFsAccomnoGreaterThanOrEqualTo(String value) {
            addCriterion("FS_ACCOMNO >=", value, "fsAccomno");
            return (Criteria) this;
        }

        public Criteria andFsAccomnoLessThan(String value) {
            addCriterion("FS_ACCOMNO <", value, "fsAccomno");
            return (Criteria) this;
        }

        public Criteria andFsAccomnoLessThanOrEqualTo(String value) {
            addCriterion("FS_ACCOMNO <=", value, "fsAccomno");
            return (Criteria) this;
        }

        public Criteria andFsAccomnoLike(String value) {
            addCriterion("FS_ACCOMNO like", value, "fsAccomno");
            return (Criteria) this;
        }

        public Criteria andFsAccomnoNotLike(String value) {
            addCriterion("FS_ACCOMNO not like", value, "fsAccomno");
            return (Criteria) this;
        }

        public Criteria andFsAccomnoIn(List<String> values) {
            addCriterion("FS_ACCOMNO in", values, "fsAccomno");
            return (Criteria) this;
        }

        public Criteria andFsAccomnoNotIn(List<String> values) {
            addCriterion("FS_ACCOMNO not in", values, "fsAccomno");
            return (Criteria) this;
        }

        public Criteria andFsAccomnoBetween(String value1, String value2) {
            addCriterion("FS_ACCOMNO between", value1, value2, "fsAccomno");
            return (Criteria) this;
        }

        public Criteria andFsAccomnoNotBetween(String value1, String value2) {
            addCriterion("FS_ACCOMNO not between", value1, value2, "fsAccomno");
            return (Criteria) this;
        }

        public Criteria andFsTypeIsNull() {
            addCriterion("FS_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andFsTypeIsNotNull() {
            addCriterion("FS_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andFsTypeEqualTo(String value) {
            addCriterion("FS_TYPE =", value, "fsType");
            return (Criteria) this;
        }

        public Criteria andFsTypeNotEqualTo(String value) {
            addCriterion("FS_TYPE <>", value, "fsType");
            return (Criteria) this;
        }

        public Criteria andFsTypeGreaterThan(String value) {
            addCriterion("FS_TYPE >", value, "fsType");
            return (Criteria) this;
        }

        public Criteria andFsTypeGreaterThanOrEqualTo(String value) {
            addCriterion("FS_TYPE >=", value, "fsType");
            return (Criteria) this;
        }

        public Criteria andFsTypeLessThan(String value) {
            addCriterion("FS_TYPE <", value, "fsType");
            return (Criteria) this;
        }

        public Criteria andFsTypeLessThanOrEqualTo(String value) {
            addCriterion("FS_TYPE <=", value, "fsType");
            return (Criteria) this;
        }

        public Criteria andFsTypeLike(String value) {
            addCriterion("FS_TYPE like", value, "fsType");
            return (Criteria) this;
        }

        public Criteria andFsTypeNotLike(String value) {
            addCriterion("FS_TYPE not like", value, "fsType");
            return (Criteria) this;
        }

        public Criteria andFsTypeIn(List<String> values) {
            addCriterion("FS_TYPE in", values, "fsType");
            return (Criteria) this;
        }

        public Criteria andFsTypeNotIn(List<String> values) {
            addCriterion("FS_TYPE not in", values, "fsType");
            return (Criteria) this;
        }

        public Criteria andFsTypeBetween(String value1, String value2) {
            addCriterion("FS_TYPE between", value1, value2, "fsType");
            return (Criteria) this;
        }

        public Criteria andFsTypeNotBetween(String value1, String value2) {
            addCriterion("FS_TYPE not between", value1, value2, "fsType");
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

        public Criteria andFsMealIsNull() {
            addCriterion("FS_MEAL is null");
            return (Criteria) this;
        }

        public Criteria andFsMealIsNotNull() {
            addCriterion("FS_MEAL is not null");
            return (Criteria) this;
        }

        public Criteria andFsMealEqualTo(String value) {
            addCriterion("FS_MEAL =", value, "fsMeal");
            return (Criteria) this;
        }

        public Criteria andFsMealNotEqualTo(String value) {
            addCriterion("FS_MEAL <>", value, "fsMeal");
            return (Criteria) this;
        }

        public Criteria andFsMealGreaterThan(String value) {
            addCriterion("FS_MEAL >", value, "fsMeal");
            return (Criteria) this;
        }

        public Criteria andFsMealGreaterThanOrEqualTo(String value) {
            addCriterion("FS_MEAL >=", value, "fsMeal");
            return (Criteria) this;
        }

        public Criteria andFsMealLessThan(String value) {
            addCriterion("FS_MEAL <", value, "fsMeal");
            return (Criteria) this;
        }

        public Criteria andFsMealLessThanOrEqualTo(String value) {
            addCriterion("FS_MEAL <=", value, "fsMeal");
            return (Criteria) this;
        }

        public Criteria andFsMealLike(String value) {
            addCriterion("FS_MEAL like", value, "fsMeal");
            return (Criteria) this;
        }

        public Criteria andFsMealNotLike(String value) {
            addCriterion("FS_MEAL not like", value, "fsMeal");
            return (Criteria) this;
        }

        public Criteria andFsMealIn(List<String> values) {
            addCriterion("FS_MEAL in", values, "fsMeal");
            return (Criteria) this;
        }

        public Criteria andFsMealNotIn(List<String> values) {
            addCriterion("FS_MEAL not in", values, "fsMeal");
            return (Criteria) this;
        }

        public Criteria andFsMealBetween(String value1, String value2) {
            addCriterion("FS_MEAL between", value1, value2, "fsMeal");
            return (Criteria) this;
        }

        public Criteria andFsMealNotBetween(String value1, String value2) {
            addCriterion("FS_MEAL not between", value1, value2, "fsMeal");
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