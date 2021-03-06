package com.yttx.yttxps.model;

import java.util.ArrayList;
import java.util.List;

public class TResCCExample {
	protected String orderByClause;
	protected boolean distinct;
	protected List<Criteria> oredCriteria;

	public TResCCExample() {
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

		public Criteria andFsCcnoIsNull() {
			addCriterion("FS_CCNO is null");
			return (Criteria) this;
		}

		public Criteria andFsCcnoIsNotNull() {
			addCriterion("FS_CCNO is not null");
			return (Criteria) this;
		}

		public Criteria andFsCcnoEqualTo(String value) {
			addCriterion("FS_CCNO =", value, "fsCcno");
			return (Criteria) this;
		}

		public Criteria andFsCcnoNotEqualTo(String value) {
			addCriterion("FS_CCNO <>", value, "fsCcno");
			return (Criteria) this;
		}

		public Criteria andFsCcnoGreaterThan(String value) {
			addCriterion("FS_CCNO >", value, "fsCcno");
			return (Criteria) this;
		}

		public Criteria andFsCcnoGreaterThanOrEqualTo(String value) {
			addCriterion("FS_CCNO >=", value, "fsCcno");
			return (Criteria) this;
		}

		public Criteria andFsCcnoLessThan(String value) {
			addCriterion("FS_CCNO <", value, "fsCcno");
			return (Criteria) this;
		}

		public Criteria andFsCcnoLessThanOrEqualTo(String value) {
			addCriterion("FS_CCNO <=", value, "fsCcno");
			return (Criteria) this;
		}

		public Criteria andFsCcnoLike(String value) {
			addCriterion("FS_CCNO like", value, "fsCcno");
			return (Criteria) this;
		}

		public Criteria andFsCcnoNotLike(String value) {
			addCriterion("FS_CCNO not like", value, "fsCcno");
			return (Criteria) this;
		}

		public Criteria andFsCcnoIn(List<String> values) {
			addCriterion("FS_CCNO in", values, "fsCcno");
			return (Criteria) this;
		}

		public Criteria andFsCcnoNotIn(List<String> values) {
			addCriterion("FS_CCNO not in", values, "fsCcno");
			return (Criteria) this;
		}

		public Criteria andFsCcnoBetween(String value1, String value2) {
			addCriterion("FS_CCNO between", value1, value2, "fsCcno");
			return (Criteria) this;
		}

		public Criteria andFsCcnoNotBetween(String value1, String value2) {
			addCriterion("FS_CCNO not between", value1, value2, "fsCcno");
			return (Criteria) this;
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

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to
	 * the database table TRESCC
	 *
	 * @mbggenerated do_not_delete_during_merge Tue Jan 19 23:27:22 CST 2016
	 */
	public static class Criteria extends GeneratedCriteria {

		protected Criteria() {
			super();
		}
	}
}