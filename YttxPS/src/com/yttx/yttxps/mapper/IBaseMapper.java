package com.yttx.yttxps.mapper;

import java.util.List;
import java.util.Map;

public interface IBaseMapper<T> {
    int selectCountSelective(Map<String, Object> map);
    List<T> selectSelectivePage(Map<String, Object> map);
}
