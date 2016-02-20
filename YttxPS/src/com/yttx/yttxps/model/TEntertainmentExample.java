package com.yttx.yttxps.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TEntertainmentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TEntertainmentExample() {
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

        public Criteria andFsNoIsNull() {
            addCriterion("FS_NO is null");
            return (Criteria) this;
        }

        public Criteria andFsNoIsNotNull() {
            addCriterion("FS_NO is not null");
            return (Criteria) this;
        }

        public Criteria andFsNoEqualTo(String value) {
            addCriterion("FS_NO =", value, "fsNo");
            return (Criteria) this;
        }

        public Criteria andFsNoNotEqualTo(String value) {
            addCriterion("FS_NO <>", value, "fsNo");
            return (Criteria) this;
        }

        public Criteria andFsNoGreaterThan(String value) {
            addCriterion("FS_NO >", value, "fsNo");
            return (Criteria) this;
        }

        public Criteria andFsNoGreaterThanOrEqualTo(String value) {
            addCriterion("FS_NO >=", value, "fsNo");
            return (Criteria) this;
        }

        public Criteria andFsNoLessThan(String value) {
            addCriterion("FS_NO <", value, "fsNo");
            return (Criteria) this;
        }

        public Criteria andFsNoLessThanOrEqualTo(String value) {
            addCriterion("FS_NO <=", value, "fsNo");
            return (Criteria) this;
        }

        public Criteria andFsNoLike(String value) {
            addCriterion("FS_NO like", value, "fsNo");
            return (Criteria) this;
        }

        public Criteria andFsNoNotLike(String value) {
            addCriterion("FS_NO not like", value, "fsNo");
            return (Criteria) this;
        }

        public Criteria andFsNoIn(List<String> values) {
            addCriterion("FS_NO in", values, "fsNo");
            return (Criteria) this;
        }

        public Criteria andFsNoNotIn(List<String> values) {
            addCriterion("FS_NO not in", values, "fsNo");
            return (Criteria) this;
        }

        public Criteria andFsNoBetween(String value1, String value2) {
            addCriterion("FS_NO between", value1, value2, "fsNo");
            return (Criteria) this;
        }

        public Criteria andFsNoNotBetween(String value1, String value2) {
            addCriterion("FS_NO not between", value1, value2, "fsNo");
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

        public Criteria andFsRegionnoIsNull() {
            addCriterion("FS_REGIONNO is null");
            return (Criteria) this;
        }

        public Criteria andFsRegionnoIsNotNull() {
            addCriterion("FS_REGIONNO is not null");
            return (Criteria) this;
        }

        public Criteria andFsRegionnoEqualTo(String value) {
            addCriterion("FS_REGIONNO =", value, "fsRegionno");
            return (Criteria) this;
        }

        public Criteria andFsRegionnoNotEqualTo(String value) {
            addCriterion("FS_REGIONNO <>", value, "fsRegionno");
            return (Criteria) this;
        }

        public Criteria andFsRegionnoGreaterThan(String value) {
            addCriterion("FS_REGIONNO >", value, "fsRegionno");
            return (Criteria) this;
        }

        public Criteria andFsRegionnoGreaterThanOrEqualTo(String value) {
            addCriterion("FS_REGIONNO >=", value, "fsRegionno");
            return (Criteria) this;
        }

        public Criteria andFsRegionnoLessThan(String value) {
            addCriterion("FS_REGIONNO <", value, "fsRegionno");
            return (Criteria) this;
        }

        public Criteria andFsRegionnoLessThanOrEqualTo(String value) {
            addCriterion("FS_REGIONNO <=", value, "fsRegionno");
            return (Criteria) this;
        }

        public Criteria andFsRegionnoLike(String value) {
            addCriterion("FS_REGIONNO like", value, "fsRegionno");
            return (Criteria) this;
        }

        public Criteria andFsRegionnoNotLike(String value) {
            addCriterion("FS_REGIONNO not like", value, "fsRegionno");
            return (Criteria) this;
        }

        public Criteria andFsRegionnoIn(List<String> values) {
            addCriterion("FS_REGIONNO in", values, "fsRegionno");
            return (Criteria) this;
        }

        public Criteria andFsRegionnoNotIn(List<String> values) {
            addCriterion("FS_REGIONNO not in", values, "fsRegionno");
            return (Criteria) this;
        }

        public Criteria andFsRegionnoBetween(String value1, String value2) {
            addCriterion("FS_REGIONNO between", value1, value2, "fsRegionno");
            return (Criteria) this;
        }

        public Criteria andFsRegionnoNotBetween(String value1, String value2) {
            addCriterion("FS_REGIONNO not between", value1, value2, "fsRegionno");
            return (Criteria) this;
        }

        public Criteria andFsAddrIsNull() {
            addCriterion("FS_ADDR is null");
            return (Criteria) this;
        }

        public Criteria andFsAddrIsNotNull() {
            addCriterion("FS_ADDR is not null");
            return (Criteria) this;
        }

        public Criteria andFsAddrEqualTo(String value) {
            addCriterion("FS_ADDR =", value, "fsAddr");
            return (Criteria) this;
        }

        public Criteria andFsAddrNotEqualTo(String value) {
            addCriterion("FS_ADDR <>", value, "fsAddr");
            return (Criteria) this;
        }

        public Criteria andFsAddrGreaterThan(String value) {
            addCriterion("FS_ADDR >", value, "fsAddr");
            return (Criteria) this;
        }

        public Criteria andFsAddrGreaterThanOrEqualTo(String value) {
            addCriterion("FS_ADDR >=", value, "fsAddr");
            return (Criteria) this;
        }

        public Criteria andFsAddrLessThan(String value) {
            addCriterion("FS_ADDR <", value, "fsAddr");
            return (Criteria) this;
        }

        public Criteria andFsAddrLessThanOrEqualTo(String value) {
            addCriterion("FS_ADDR <=", value, "fsAddr");
            return (Criteria) this;
        }

        public Criteria andFsAddrLike(String value) {
            addCriterion("FS_ADDR like", value, "fsAddr");
            return (Criteria) this;
        }

        public Criteria andFsAddrNotLike(String value) {
            addCriterion("FS_ADDR not like", value, "fsAddr");
            return (Criteria) this;
        }

        public Criteria andFsAddrIn(List<String> values) {
            addCriterion("FS_ADDR in", values, "fsAddr");
            return (Criteria) this;
        }

        public Criteria andFsAddrNotIn(List<String> values) {
            addCriterion("FS_ADDR not in", values, "fsAddr");
            return (Criteria) this;
        }

        public Criteria andFsAddrBetween(String value1, String value2) {
            addCriterion("FS_ADDR between", value1, value2, "fsAddr");
            return (Criteria) this;
        }

        public Criteria andFsAddrNotBetween(String value1, String value2) {
            addCriterion("FS_ADDR not between", value1, value2, "fsAddr");
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

        public Criteria andFsDescIsNull() {
            addCriterion("FS_DESC is null");
            return (Criteria) this;
        }

        public Criteria andFsDescIsNotNull() {
            addCriterion("FS_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andFsDescEqualTo(String value) {
            addCriterion("FS_DESC =", value, "fsDesc");
            return (Criteria) this;
        }

        public Criteria andFsDescNotEqualTo(String value) {
            addCriterion("FS_DESC <>", value, "fsDesc");
            return (Criteria) this;
        }

        public Criteria andFsDescGreaterThan(String value) {
            addCriterion("FS_DESC >", value, "fsDesc");
            return (Criteria) this;
        }

        public Criteria andFsDescGreaterThanOrEqualTo(String value) {
            addCriterion("FS_DESC >=", value, "fsDesc");
            return (Criteria) this;
        }

        public Criteria andFsDescLessThan(String value) {
            addCriterion("FS_DESC <", value, "fsDesc");
            return (Criteria) this;
        }

        public Criteria andFsDescLessThanOrEqualTo(String value) {
            addCriterion("FS_DESC <=", value, "fsDesc");
            return (Criteria) this;
        }

        public Criteria andFsDescLike(String value) {
            addCriterion("FS_DESC like", value, "fsDesc");
            return (Criteria) this;
        }

        public Criteria andFsDescNotLike(String value) {
            addCriterion("FS_DESC not like", value, "fsDesc");
            return (Criteria) this;
        }

        public Criteria andFsDescIn(List<String> values) {
            addCriterion("FS_DESC in", values, "fsDesc");
            return (Criteria) this;
        }

        public Criteria andFsDescNotIn(List<String> values) {
            addCriterion("FS_DESC not in", values, "fsDesc");
            return (Criteria) this;
        }

        public Criteria andFsDescBetween(String value1, String value2) {
            addCriterion("FS_DESC between", value1, value2, "fsDesc");
            return (Criteria) this;
        }

        public Criteria andFsDescNotBetween(String value1, String value2) {
            addCriterion("FS_DESC not between", value1, value2, "fsDesc");
            return (Criteria) this;
        }

        public Criteria andFsLvlIsNull() {
            addCriterion("FS_LVL is null");
            return (Criteria) this;
        }

        public Criteria andFsLvlIsNotNull() {
            addCriterion("FS_LVL is not null");
            return (Criteria) this;
        }

        public Criteria andFsLvlEqualTo(String value) {
            addCriterion("FS_LVL =", value, "fsLvl");
            return (Criteria) this;
        }

        public Criteria andFsLvlNotEqualTo(String value) {
            addCriterion("FS_LVL <>", value, "fsLvl");
            return (Criteria) this;
        }

        public Criteria andFsLvlGreaterThan(String value) {
            addCriterion("FS_LVL >", value, "fsLvl");
            return (Criteria) this;
        }

        public Criteria andFsLvlGreaterThanOrEqualTo(String value) {
            addCriterion("FS_LVL >=", value, "fsLvl");
            return (Criteria) this;
        }

        public Criteria andFsLvlLessThan(String value) {
            addCriterion("FS_LVL <", value, "fsLvl");
            return (Criteria) this;
        }

        public Criteria andFsLvlLessThanOrEqualTo(String value) {
            addCriterion("FS_LVL <=", value, "fsLvl");
            return (Criteria) this;
        }

        public Criteria andFsLvlLike(String value) {
            addCriterion("FS_LVL like", value, "fsLvl");
            return (Criteria) this;
        }

        public Criteria andFsLvlNotLike(String value) {
            addCriterion("FS_LVL not like", value, "fsLvl");
            return (Criteria) this;
        }

        public Criteria andFsLvlIn(List<String> values) {
            addCriterion("FS_LVL in", values, "fsLvl");
            return (Criteria) this;
        }

        public Criteria andFsLvlNotIn(List<String> values) {
            addCriterion("FS_LVL not in", values, "fsLvl");
            return (Criteria) this;
        }

        public Criteria andFsLvlBetween(String value1, String value2) {
            addCriterion("FS_LVL between", value1, value2, "fsLvl");
            return (Criteria) this;
        }

        public Criteria andFsLvlNotBetween(String value1, String value2) {
            addCriterion("FS_LVL not between", value1, value2, "fsLvl");
            return (Criteria) this;
        }

        public Criteria andFsOpentimeIsNull() {
            addCriterion("FS_OPENTIME is null");
            return (Criteria) this;
        }

        public Criteria andFsOpentimeIsNotNull() {
            addCriterion("FS_OPENTIME is not null");
            return (Criteria) this;
        }

        public Criteria andFsOpentimeEqualTo(String value) {
            addCriterion("FS_OPENTIME =", value, "fsOpentime");
            return (Criteria) this;
        }

        public Criteria andFsOpentimeNotEqualTo(String value) {
            addCriterion("FS_OPENTIME <>", value, "fsOpentime");
            return (Criteria) this;
        }

        public Criteria andFsOpentimeGreaterThan(String value) {
            addCriterion("FS_OPENTIME >", value, "fsOpentime");
            return (Criteria) this;
        }

        public Criteria andFsOpentimeGreaterThanOrEqualTo(String value) {
            addCriterion("FS_OPENTIME >=", value, "fsOpentime");
            return (Criteria) this;
        }

        public Criteria andFsOpentimeLessThan(String value) {
            addCriterion("FS_OPENTIME <", value, "fsOpentime");
            return (Criteria) this;
        }

        public Criteria andFsOpentimeLessThanOrEqualTo(String value) {
            addCriterion("FS_OPENTIME <=", value, "fsOpentime");
            return (Criteria) this;
        }

        public Criteria andFsOpentimeLike(String value) {
            addCriterion("FS_OPENTIME like", value, "fsOpentime");
            return (Criteria) this;
        }

        public Criteria andFsOpentimeNotLike(String value) {
            addCriterion("FS_OPENTIME not like", value, "fsOpentime");
            return (Criteria) this;
        }

        public Criteria andFsOpentimeIn(List<String> values) {
            addCriterion("FS_OPENTIME in", values, "fsOpentime");
            return (Criteria) this;
        }

        public Criteria andFsOpentimeNotIn(List<String> values) {
            addCriterion("FS_OPENTIME not in", values, "fsOpentime");
            return (Criteria) this;
        }

        public Criteria andFsOpentimeBetween(String value1, String value2) {
            addCriterion("FS_OPENTIME between", value1, value2, "fsOpentime");
            return (Criteria) this;
        }

        public Criteria andFsOpentimeNotBetween(String value1, String value2) {
            addCriterion("FS_OPENTIME not between", value1, value2, "fsOpentime");
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