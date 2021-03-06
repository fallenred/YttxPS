package com.yttx.yttxps.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TOrderCustomExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table TORDERCUSTOM
	 * @mbggenerated  Sun Feb 21 14:08:05 CST 2016
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table TORDERCUSTOM
	 * @mbggenerated  Sun Feb 21 14:08:05 CST 2016
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table TORDERCUSTOM
	 * @mbggenerated  Sun Feb 21 14:08:05 CST 2016
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TORDERCUSTOM
	 * @mbggenerated  Sun Feb 21 14:08:05 CST 2016
	 */
	public TOrderCustomExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TORDERCUSTOM
	 * @mbggenerated  Sun Feb 21 14:08:05 CST 2016
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TORDERCUSTOM
	 * @mbggenerated  Sun Feb 21 14:08:05 CST 2016
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TORDERCUSTOM
	 * @mbggenerated  Sun Feb 21 14:08:05 CST 2016
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TORDERCUSTOM
	 * @mbggenerated  Sun Feb 21 14:08:05 CST 2016
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TORDERCUSTOM
	 * @mbggenerated  Sun Feb 21 14:08:05 CST 2016
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TORDERCUSTOM
	 * @mbggenerated  Sun Feb 21 14:08:05 CST 2016
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TORDERCUSTOM
	 * @mbggenerated  Sun Feb 21 14:08:05 CST 2016
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TORDERCUSTOM
	 * @mbggenerated  Sun Feb 21 14:08:05 CST 2016
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TORDERCUSTOM
	 * @mbggenerated  Sun Feb 21 14:08:05 CST 2016
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TORDERCUSTOM
	 * @mbggenerated  Sun Feb 21 14:08:05 CST 2016
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table TORDERCUSTOM
	 * @mbggenerated  Sun Feb 21 14:08:05 CST 2016
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

		protected void addCriterion(String condition, Object value,
				String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1,
				Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		protected void addCriterionForJDBCDate(String condition, Date value,
				String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property
						+ " cannot be null");
			}
			addCriterion(condition, new java.sql.Date(value.getTime()),
					property);
		}

		protected void addCriterionForJDBCDate(String condition,
				List<Date> values, String property) {
			if (values == null || values.size() == 0) {
				throw new RuntimeException("Value list for " + property
						+ " cannot be null or empty");
			}
			List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
			Iterator<Date> iter = values.iterator();
			while (iter.hasNext()) {
				dateList.add(new java.sql.Date(iter.next().getTime()));
			}
			addCriterion(condition, dateList, property);
		}

		protected void addCriterionForJDBCDate(String condition, Date value1,
				Date value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property
						+ " cannot be null");
			}
			addCriterion(condition, new java.sql.Date(value1.getTime()),
					new java.sql.Date(value2.getTime()), property);
		}

		public Criteria andFiIdIsNull() {
			addCriterion("FI_ID is null");
			return (Criteria) this;
		}

		public Criteria andFiIdIsNotNull() {
			addCriterion("FI_ID is not null");
			return (Criteria) this;
		}

		public Criteria andFiIdEqualTo(BigDecimal value) {
			addCriterion("FI_ID =", value, "fiId");
			return (Criteria) this;
		}

		public Criteria andFiIdNotEqualTo(BigDecimal value) {
			addCriterion("FI_ID <>", value, "fiId");
			return (Criteria) this;
		}

		public Criteria andFiIdGreaterThan(BigDecimal value) {
			addCriterion("FI_ID >", value, "fiId");
			return (Criteria) this;
		}

		public Criteria andFiIdGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("FI_ID >=", value, "fiId");
			return (Criteria) this;
		}

		public Criteria andFiIdLessThan(BigDecimal value) {
			addCriterion("FI_ID <", value, "fiId");
			return (Criteria) this;
		}

		public Criteria andFiIdLessThanOrEqualTo(BigDecimal value) {
			addCriterion("FI_ID <=", value, "fiId");
			return (Criteria) this;
		}

		public Criteria andFiIdIn(List<BigDecimal> values) {
			addCriterion("FI_ID in", values, "fiId");
			return (Criteria) this;
		}

		public Criteria andFiIdNotIn(List<BigDecimal> values) {
			addCriterion("FI_ID not in", values, "fiId");
			return (Criteria) this;
		}

		public Criteria andFiIdBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("FI_ID between", value1, value2, "fiId");
			return (Criteria) this;
		}

		public Criteria andFiIdNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("FI_ID not between", value1, value2, "fiId");
			return (Criteria) this;
		}

		public Criteria andFsOrderIdIsNull() {
			addCriterion("FS_ORDER_ID is null");
			return (Criteria) this;
		}

		public Criteria andFsOrderIdIsNotNull() {
			addCriterion("FS_ORDER_ID is not null");
			return (Criteria) this;
		}

		public Criteria andFsOrderIdEqualTo(String value) {
			addCriterion("FS_ORDER_ID =", value, "fsOrderId");
			return (Criteria) this;
		}

		public Criteria andFsOrderIdNotEqualTo(String value) {
			addCriterion("FS_ORDER_ID <>", value, "fsOrderId");
			return (Criteria) this;
		}

		public Criteria andFsOrderIdGreaterThan(String value) {
			addCriterion("FS_ORDER_ID >", value, "fsOrderId");
			return (Criteria) this;
		}

		public Criteria andFsOrderIdGreaterThanOrEqualTo(String value) {
			addCriterion("FS_ORDER_ID >=", value, "fsOrderId");
			return (Criteria) this;
		}

		public Criteria andFsOrderIdLessThan(String value) {
			addCriterion("FS_ORDER_ID <", value, "fsOrderId");
			return (Criteria) this;
		}

		public Criteria andFsOrderIdLessThanOrEqualTo(String value) {
			addCriterion("FS_ORDER_ID <=", value, "fsOrderId");
			return (Criteria) this;
		}

		public Criteria andFsOrderIdLike(String value) {
			addCriterion("FS_ORDER_ID like", value, "fsOrderId");
			return (Criteria) this;
		}

		public Criteria andFsOrderIdNotLike(String value) {
			addCriterion("FS_ORDER_ID not like", value, "fsOrderId");
			return (Criteria) this;
		}

		public Criteria andFsOrderIdIn(List<String> values) {
			addCriterion("FS_ORDER_ID in", values, "fsOrderId");
			return (Criteria) this;
		}

		public Criteria andFsOrderIdNotIn(List<String> values) {
			addCriterion("FS_ORDER_ID not in", values, "fsOrderId");
			return (Criteria) this;
		}

		public Criteria andFsOrderIdBetween(String value1, String value2) {
			addCriterion("FS_ORDER_ID between", value1, value2, "fsOrderId");
			return (Criteria) this;
		}

		public Criteria andFsOrderIdNotBetween(String value1, String value2) {
			addCriterion("FS_ORDER_ID not between", value1, value2, "fsOrderId");
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

		public Criteria andFtCreatdateIsNull() {
			addCriterion("FT_CREATDATE is null");
			return (Criteria) this;
		}

		public Criteria andFtCreatdateIsNotNull() {
			addCriterion("FT_CREATDATE is not null");
			return (Criteria) this;
		}

		public Criteria andFtCreatdateEqualTo(Date value) {
			addCriterionForJDBCDate("FT_CREATDATE =", value, "ftCreatdate");
			return (Criteria) this;
		}

		public Criteria andFtCreatdateNotEqualTo(Date value) {
			addCriterionForJDBCDate("FT_CREATDATE <>", value, "ftCreatdate");
			return (Criteria) this;
		}

		public Criteria andFtCreatdateGreaterThan(Date value) {
			addCriterionForJDBCDate("FT_CREATDATE >", value, "ftCreatdate");
			return (Criteria) this;
		}

		public Criteria andFtCreatdateGreaterThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("FT_CREATDATE >=", value, "ftCreatdate");
			return (Criteria) this;
		}

		public Criteria andFtCreatdateLessThan(Date value) {
			addCriterionForJDBCDate("FT_CREATDATE <", value, "ftCreatdate");
			return (Criteria) this;
		}

		public Criteria andFtCreatdateLessThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("FT_CREATDATE <=", value, "ftCreatdate");
			return (Criteria) this;
		}

		public Criteria andFtCreatdateIn(List<Date> values) {
			addCriterionForJDBCDate("FT_CREATDATE in", values, "ftCreatdate");
			return (Criteria) this;
		}

		public Criteria andFtCreatdateNotIn(List<Date> values) {
			addCriterionForJDBCDate("FT_CREATDATE not in", values,
					"ftCreatdate");
			return (Criteria) this;
		}

		public Criteria andFtCreatdateBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("FT_CREATDATE between", value1, value2,
					"ftCreatdate");
			return (Criteria) this;
		}

		public Criteria andFtCreatdateNotBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("FT_CREATDATE not between", value1, value2,
					"ftCreatdate");
			return (Criteria) this;
		}

		public Criteria andFiTypeIsNull() {
			addCriterion("FI_TYPE is null");
			return (Criteria) this;
		}

		public Criteria andFiTypeIsNotNull() {
			addCriterion("FI_TYPE is not null");
			return (Criteria) this;
		}

		public Criteria andFiTypeEqualTo(BigDecimal value) {
			addCriterion("FI_TYPE =", value, "fiType");
			return (Criteria) this;
		}

		public Criteria andFiTypeNotEqualTo(BigDecimal value) {
			addCriterion("FI_TYPE <>", value, "fiType");
			return (Criteria) this;
		}

		public Criteria andFiTypeGreaterThan(BigDecimal value) {
			addCriterion("FI_TYPE >", value, "fiType");
			return (Criteria) this;
		}

		public Criteria andFiTypeGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("FI_TYPE >=", value, "fiType");
			return (Criteria) this;
		}

		public Criteria andFiTypeLessThan(BigDecimal value) {
			addCriterion("FI_TYPE <", value, "fiType");
			return (Criteria) this;
		}

		public Criteria andFiTypeLessThanOrEqualTo(BigDecimal value) {
			addCriterion("FI_TYPE <=", value, "fiType");
			return (Criteria) this;
		}

		public Criteria andFiTypeIn(List<BigDecimal> values) {
			addCriterion("FI_TYPE in", values, "fiType");
			return (Criteria) this;
		}

		public Criteria andFiTypeNotIn(List<BigDecimal> values) {
			addCriterion("FI_TYPE not in", values, "fiType");
			return (Criteria) this;
		}

		public Criteria andFiTypeBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("FI_TYPE between", value1, value2, "fiType");
			return (Criteria) this;
		}

		public Criteria andFiTypeNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("FI_TYPE not between", value1, value2, "fiType");
			return (Criteria) this;
		}

		public Criteria andFsContactnameIsNull() {
			addCriterion("FS_CONTACTNAME is null");
			return (Criteria) this;
		}

		public Criteria andFsContactnameIsNotNull() {
			addCriterion("FS_CONTACTNAME is not null");
			return (Criteria) this;
		}

		public Criteria andFsContactnameEqualTo(String value) {
			addCriterion("FS_CONTACTNAME =", value, "fsContactname");
			return (Criteria) this;
		}

		public Criteria andFsContactnameNotEqualTo(String value) {
			addCriterion("FS_CONTACTNAME <>", value, "fsContactname");
			return (Criteria) this;
		}

		public Criteria andFsContactnameGreaterThan(String value) {
			addCriterion("FS_CONTACTNAME >", value, "fsContactname");
			return (Criteria) this;
		}

		public Criteria andFsContactnameGreaterThanOrEqualTo(String value) {
			addCriterion("FS_CONTACTNAME >=", value, "fsContactname");
			return (Criteria) this;
		}

		public Criteria andFsContactnameLessThan(String value) {
			addCriterion("FS_CONTACTNAME <", value, "fsContactname");
			return (Criteria) this;
		}

		public Criteria andFsContactnameLessThanOrEqualTo(String value) {
			addCriterion("FS_CONTACTNAME <=", value, "fsContactname");
			return (Criteria) this;
		}

		public Criteria andFsContactnameLike(String value) {
			addCriterion("FS_CONTACTNAME like", value, "fsContactname");
			return (Criteria) this;
		}

		public Criteria andFsContactnameNotLike(String value) {
			addCriterion("FS_CONTACTNAME not like", value, "fsContactname");
			return (Criteria) this;
		}

		public Criteria andFsContactnameIn(List<String> values) {
			addCriterion("FS_CONTACTNAME in", values, "fsContactname");
			return (Criteria) this;
		}

		public Criteria andFsContactnameNotIn(List<String> values) {
			addCriterion("FS_CONTACTNAME not in", values, "fsContactname");
			return (Criteria) this;
		}

		public Criteria andFsContactnameBetween(String value1, String value2) {
			addCriterion("FS_CONTACTNAME between", value1, value2,
					"fsContactname");
			return (Criteria) this;
		}

		public Criteria andFsContactnameNotBetween(String value1, String value2) {
			addCriterion("FS_CONTACTNAME not between", value1, value2,
					"fsContactname");
			return (Criteria) this;
		}

		public Criteria andFsContacttelIsNull() {
			addCriterion("FS_CONTACTTEL is null");
			return (Criteria) this;
		}

		public Criteria andFsContacttelIsNotNull() {
			addCriterion("FS_CONTACTTEL is not null");
			return (Criteria) this;
		}

		public Criteria andFsContacttelEqualTo(String value) {
			addCriterion("FS_CONTACTTEL =", value, "fsContacttel");
			return (Criteria) this;
		}

		public Criteria andFsContacttelNotEqualTo(String value) {
			addCriterion("FS_CONTACTTEL <>", value, "fsContacttel");
			return (Criteria) this;
		}

		public Criteria andFsContacttelGreaterThan(String value) {
			addCriterion("FS_CONTACTTEL >", value, "fsContacttel");
			return (Criteria) this;
		}

		public Criteria andFsContacttelGreaterThanOrEqualTo(String value) {
			addCriterion("FS_CONTACTTEL >=", value, "fsContacttel");
			return (Criteria) this;
		}

		public Criteria andFsContacttelLessThan(String value) {
			addCriterion("FS_CONTACTTEL <", value, "fsContacttel");
			return (Criteria) this;
		}

		public Criteria andFsContacttelLessThanOrEqualTo(String value) {
			addCriterion("FS_CONTACTTEL <=", value, "fsContacttel");
			return (Criteria) this;
		}

		public Criteria andFsContacttelLike(String value) {
			addCriterion("FS_CONTACTTEL like", value, "fsContacttel");
			return (Criteria) this;
		}

		public Criteria andFsContacttelNotLike(String value) {
			addCriterion("FS_CONTACTTEL not like", value, "fsContacttel");
			return (Criteria) this;
		}

		public Criteria andFsContacttelIn(List<String> values) {
			addCriterion("FS_CONTACTTEL in", values, "fsContacttel");
			return (Criteria) this;
		}

		public Criteria andFsContacttelNotIn(List<String> values) {
			addCriterion("FS_CONTACTTEL not in", values, "fsContacttel");
			return (Criteria) this;
		}

		public Criteria andFsContacttelBetween(String value1, String value2) {
			addCriterion("FS_CONTACTTEL between", value1, value2,
					"fsContacttel");
			return (Criteria) this;
		}

		public Criteria andFsContacttelNotBetween(String value1, String value2) {
			addCriterion("FS_CONTACTTEL not between", value1, value2,
					"fsContacttel");
			return (Criteria) this;
		}

		public Criteria andFiTotalIsNull() {
			addCriterion("FI_TOTAL is null");
			return (Criteria) this;
		}

		public Criteria andFiTotalIsNotNull() {
			addCriterion("FI_TOTAL is not null");
			return (Criteria) this;
		}

		public Criteria andFiTotalEqualTo(BigDecimal value) {
			addCriterion("FI_TOTAL =", value, "fiTotal");
			return (Criteria) this;
		}

		public Criteria andFiTotalNotEqualTo(BigDecimal value) {
			addCriterion("FI_TOTAL <>", value, "fiTotal");
			return (Criteria) this;
		}

		public Criteria andFiTotalGreaterThan(BigDecimal value) {
			addCriterion("FI_TOTAL >", value, "fiTotal");
			return (Criteria) this;
		}

		public Criteria andFiTotalGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("FI_TOTAL >=", value, "fiTotal");
			return (Criteria) this;
		}

		public Criteria andFiTotalLessThan(BigDecimal value) {
			addCriterion("FI_TOTAL <", value, "fiTotal");
			return (Criteria) this;
		}

		public Criteria andFiTotalLessThanOrEqualTo(BigDecimal value) {
			addCriterion("FI_TOTAL <=", value, "fiTotal");
			return (Criteria) this;
		}

		public Criteria andFiTotalIn(List<BigDecimal> values) {
			addCriterion("FI_TOTAL in", values, "fiTotal");
			return (Criteria) this;
		}

		public Criteria andFiTotalNotIn(List<BigDecimal> values) {
			addCriterion("FI_TOTAL not in", values, "fiTotal");
			return (Criteria) this;
		}

		public Criteria andFiTotalBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("FI_TOTAL between", value1, value2, "fiTotal");
			return (Criteria) this;
		}

		public Criteria andFiTotalNotBetween(BigDecimal value1,
				BigDecimal value2) {
			addCriterion("FI_TOTAL not between", value1, value2, "fiTotal");
			return (Criteria) this;
		}

		public Criteria andFiOlderIsNull() {
			addCriterion("FI_OLDER is null");
			return (Criteria) this;
		}

		public Criteria andFiOlderIsNotNull() {
			addCriterion("FI_OLDER is not null");
			return (Criteria) this;
		}

		public Criteria andFiOlderEqualTo(BigDecimal value) {
			addCriterion("FI_OLDER =", value, "fiOlder");
			return (Criteria) this;
		}

		public Criteria andFiOlderNotEqualTo(BigDecimal value) {
			addCriterion("FI_OLDER <>", value, "fiOlder");
			return (Criteria) this;
		}

		public Criteria andFiOlderGreaterThan(BigDecimal value) {
			addCriterion("FI_OLDER >", value, "fiOlder");
			return (Criteria) this;
		}

		public Criteria andFiOlderGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("FI_OLDER >=", value, "fiOlder");
			return (Criteria) this;
		}

		public Criteria andFiOlderLessThan(BigDecimal value) {
			addCriterion("FI_OLDER <", value, "fiOlder");
			return (Criteria) this;
		}

		public Criteria andFiOlderLessThanOrEqualTo(BigDecimal value) {
			addCriterion("FI_OLDER <=", value, "fiOlder");
			return (Criteria) this;
		}

		public Criteria andFiOlderIn(List<BigDecimal> values) {
			addCriterion("FI_OLDER in", values, "fiOlder");
			return (Criteria) this;
		}

		public Criteria andFiOlderNotIn(List<BigDecimal> values) {
			addCriterion("FI_OLDER not in", values, "fiOlder");
			return (Criteria) this;
		}

		public Criteria andFiOlderBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("FI_OLDER between", value1, value2, "fiOlder");
			return (Criteria) this;
		}

		public Criteria andFiOlderNotBetween(BigDecimal value1,
				BigDecimal value2) {
			addCriterion("FI_OLDER not between", value1, value2, "fiOlder");
			return (Criteria) this;
		}

		public Criteria andFiAdultIsNull() {
			addCriterion("FI_ADULT is null");
			return (Criteria) this;
		}

		public Criteria andFiAdultIsNotNull() {
			addCriterion("FI_ADULT is not null");
			return (Criteria) this;
		}

		public Criteria andFiAdultEqualTo(BigDecimal value) {
			addCriterion("FI_ADULT =", value, "fiAdult");
			return (Criteria) this;
		}

		public Criteria andFiAdultNotEqualTo(BigDecimal value) {
			addCriterion("FI_ADULT <>", value, "fiAdult");
			return (Criteria) this;
		}

		public Criteria andFiAdultGreaterThan(BigDecimal value) {
			addCriterion("FI_ADULT >", value, "fiAdult");
			return (Criteria) this;
		}

		public Criteria andFiAdultGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("FI_ADULT >=", value, "fiAdult");
			return (Criteria) this;
		}

		public Criteria andFiAdultLessThan(BigDecimal value) {
			addCriterion("FI_ADULT <", value, "fiAdult");
			return (Criteria) this;
		}

		public Criteria andFiAdultLessThanOrEqualTo(BigDecimal value) {
			addCriterion("FI_ADULT <=", value, "fiAdult");
			return (Criteria) this;
		}

		public Criteria andFiAdultIn(List<BigDecimal> values) {
			addCriterion("FI_ADULT in", values, "fiAdult");
			return (Criteria) this;
		}

		public Criteria andFiAdultNotIn(List<BigDecimal> values) {
			addCriterion("FI_ADULT not in", values, "fiAdult");
			return (Criteria) this;
		}

		public Criteria andFiAdultBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("FI_ADULT between", value1, value2, "fiAdult");
			return (Criteria) this;
		}

		public Criteria andFiAdultNotBetween(BigDecimal value1,
				BigDecimal value2) {
			addCriterion("FI_ADULT not between", value1, value2, "fiAdult");
			return (Criteria) this;
		}

		public Criteria andFiChildrenIsNull() {
			addCriterion("FI_CHILDREN is null");
			return (Criteria) this;
		}

		public Criteria andFiChildrenIsNotNull() {
			addCriterion("FI_CHILDREN is not null");
			return (Criteria) this;
		}

		public Criteria andFiChildrenEqualTo(BigDecimal value) {
			addCriterion("FI_CHILDREN =", value, "fiChildren");
			return (Criteria) this;
		}

		public Criteria andFiChildrenNotEqualTo(BigDecimal value) {
			addCriterion("FI_CHILDREN <>", value, "fiChildren");
			return (Criteria) this;
		}

		public Criteria andFiChildrenGreaterThan(BigDecimal value) {
			addCriterion("FI_CHILDREN >", value, "fiChildren");
			return (Criteria) this;
		}

		public Criteria andFiChildrenGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("FI_CHILDREN >=", value, "fiChildren");
			return (Criteria) this;
		}

		public Criteria andFiChildrenLessThan(BigDecimal value) {
			addCriterion("FI_CHILDREN <", value, "fiChildren");
			return (Criteria) this;
		}

		public Criteria andFiChildrenLessThanOrEqualTo(BigDecimal value) {
			addCriterion("FI_CHILDREN <=", value, "fiChildren");
			return (Criteria) this;
		}

		public Criteria andFiChildrenIn(List<BigDecimal> values) {
			addCriterion("FI_CHILDREN in", values, "fiChildren");
			return (Criteria) this;
		}

		public Criteria andFiChildrenNotIn(List<BigDecimal> values) {
			addCriterion("FI_CHILDREN not in", values, "fiChildren");
			return (Criteria) this;
		}

		public Criteria andFiChildrenBetween(BigDecimal value1,
				BigDecimal value2) {
			addCriterion("FI_CHILDREN between", value1, value2, "fiChildren");
			return (Criteria) this;
		}

		public Criteria andFiChildrenNotBetween(BigDecimal value1,
				BigDecimal value2) {
			addCriterion("FI_CHILDREN not between", value1, value2,
					"fiChildren");
			return (Criteria) this;
		}

		public Criteria andFsPostscriptIsNull() {
			addCriterion("FS_POSTSCRIPT is null");
			return (Criteria) this;
		}

		public Criteria andFsPostscriptIsNotNull() {
			addCriterion("FS_POSTSCRIPT is not null");
			return (Criteria) this;
		}

		public Criteria andFsPostscriptEqualTo(String value) {
			addCriterion("FS_POSTSCRIPT =", value, "fsPostscript");
			return (Criteria) this;
		}

		public Criteria andFsPostscriptNotEqualTo(String value) {
			addCriterion("FS_POSTSCRIPT <>", value, "fsPostscript");
			return (Criteria) this;
		}

		public Criteria andFsPostscriptGreaterThan(String value) {
			addCriterion("FS_POSTSCRIPT >", value, "fsPostscript");
			return (Criteria) this;
		}

		public Criteria andFsPostscriptGreaterThanOrEqualTo(String value) {
			addCriterion("FS_POSTSCRIPT >=", value, "fsPostscript");
			return (Criteria) this;
		}

		public Criteria andFsPostscriptLessThan(String value) {
			addCriterion("FS_POSTSCRIPT <", value, "fsPostscript");
			return (Criteria) this;
		}

		public Criteria andFsPostscriptLessThanOrEqualTo(String value) {
			addCriterion("FS_POSTSCRIPT <=", value, "fsPostscript");
			return (Criteria) this;
		}

		public Criteria andFsPostscriptLike(String value) {
			addCriterion("FS_POSTSCRIPT like", value, "fsPostscript");
			return (Criteria) this;
		}

		public Criteria andFsPostscriptNotLike(String value) {
			addCriterion("FS_POSTSCRIPT not like", value, "fsPostscript");
			return (Criteria) this;
		}

		public Criteria andFsPostscriptIn(List<String> values) {
			addCriterion("FS_POSTSCRIPT in", values, "fsPostscript");
			return (Criteria) this;
		}

		public Criteria andFsPostscriptNotIn(List<String> values) {
			addCriterion("FS_POSTSCRIPT not in", values, "fsPostscript");
			return (Criteria) this;
		}

		public Criteria andFsPostscriptBetween(String value1, String value2) {
			addCriterion("FS_POSTSCRIPT between", value1, value2,
					"fsPostscript");
			return (Criteria) this;
		}

		public Criteria andFsPostscriptNotBetween(String value1, String value2) {
			addCriterion("FS_POSTSCRIPT not between", value1, value2,
					"fsPostscript");
			return (Criteria) this;
		}

		public Criteria andFdAmtIsNull() {
			addCriterion("FD_AMT is null");
			return (Criteria) this;
		}

		public Criteria andFdAmtIsNotNull() {
			addCriterion("FD_AMT is not null");
			return (Criteria) this;
		}

		public Criteria andFdAmtEqualTo(BigDecimal value) {
			addCriterion("FD_AMT =", value, "fdAmt");
			return (Criteria) this;
		}

		public Criteria andFdAmtNotEqualTo(BigDecimal value) {
			addCriterion("FD_AMT <>", value, "fdAmt");
			return (Criteria) this;
		}

		public Criteria andFdAmtGreaterThan(BigDecimal value) {
			addCriterion("FD_AMT >", value, "fdAmt");
			return (Criteria) this;
		}

		public Criteria andFdAmtGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("FD_AMT >=", value, "fdAmt");
			return (Criteria) this;
		}

		public Criteria andFdAmtLessThan(BigDecimal value) {
			addCriterion("FD_AMT <", value, "fdAmt");
			return (Criteria) this;
		}

		public Criteria andFdAmtLessThanOrEqualTo(BigDecimal value) {
			addCriterion("FD_AMT <=", value, "fdAmt");
			return (Criteria) this;
		}

		public Criteria andFdAmtIn(List<BigDecimal> values) {
			addCriterion("FD_AMT in", values, "fdAmt");
			return (Criteria) this;
		}

		public Criteria andFdAmtNotIn(List<BigDecimal> values) {
			addCriterion("FD_AMT not in", values, "fdAmt");
			return (Criteria) this;
		}

		public Criteria andFdAmtBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("FD_AMT between", value1, value2, "fdAmt");
			return (Criteria) this;
		}

		public Criteria andFdAmtNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("FD_AMT not between", value1, value2, "fdAmt");
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
	 * This class was generated by MyBatis Generator. This class corresponds to the database table TORDERCUSTOM
	 * @mbggenerated  Sun Feb 21 14:08:05 CST 2016
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

		protected Criterion(String condition, Object value, Object secondValue,
				String typeHandler) {
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
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table TORDERCUSTOM
     *
     * @mbggenerated do_not_delete_during_merge Thu Feb 18 23:16:28 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}