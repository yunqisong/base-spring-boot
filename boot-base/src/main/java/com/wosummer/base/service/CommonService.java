package com.wosummer.base.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.wosummer.base.model.exceptions.LogicException;
import com.wosummer.base.model.query.PageQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * CommonService created by yunqisong 2018/3/17
 * FOR : 公共Service实现
 *
 * @author yunqisong
 */
@Service
@SuppressWarnings("ALL")
public class CommonService {


  @FunctionalInterface
  public static interface QueryInterFace<T> {

    /**
     * 做查询操作
     *
     * @param page      分页
     * @param pageQuery 查询类
     * @return
     */
    List<T> doQuery(Page<T> page, PageQuery pageQuery);

  }

  /**
   * 通用分页查询
   *
   * @param query
   * @param queryInterFace
   * @param <T>
   * @return
   */
  public <T> Page<T> find(PageQuery query, QueryInterFace queryInterFace) {

    return (Page<T>) Optional
      .ofNullable(query)
      .map(q -> q.toPage())
      .map(ret -> ret.setRecords(queryInterFace.doQuery(ret, query)))
      .get();
  }


  /**
   * 判断数据库操作是否成功
   * 注意！！ 该方法为 Integer 判断，不可传入 int 基本类型
   *
   * @param result 数据库操作返回影响条数
   * @return boolean
   */
  private static boolean retBool(Integer result) {
    return SqlHelper.retBool(result);
  }

  /**
   * 通用插入数据库按照Mapper实现
   *
   * @param entity
   * @param baseMapper
   * @param <M>
   * @param <T>
   * @return
   */
  @Transactional(rollbackFor = Exception.class)
  public <M extends BaseMapper<T>, T> boolean insert(T entity, M baseMapper) {
    return retBool(baseMapper.insert(entity));
  }

  /**
   * TableId 注解存在更新记录，否插入一条记录
   *
   * @param entity 实体对象
   * @return boolean
   */
  @Transactional(rollbackFor = Exception.class)
  public <M extends BaseMapper<T>, T> boolean insertOrUpdate(T entity, M baseMapper) {
    if (null != entity) {
      Class<?> cls = entity.getClass();
      TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
      if (null != tableInfo && StringUtils.isNotEmpty(tableInfo.getKeyProperty())) {
        Object idVal = ReflectionKit.getMethodValue(cls, entity, tableInfo.getKeyProperty());
        if (StringUtils.checkValNull(idVal)) {
          return insert(entity, baseMapper);
        } else {
          /*
           * 更新成功直接返回，失败执行插入逻辑
           */
          return updateById(entity, baseMapper) || insert(entity, baseMapper);
        }
      } else {
        throw new LogicException("保存失败!");
      }
    }
    return false;
  }

  @Transactional(rollbackFor = Exception.class)
  public <M extends BaseMapper<T>, T> boolean updateById(T entity, M baseMapper) {
    return retBool(baseMapper.updateById(entity));
  }


  @Transactional(rollbackFor = Exception.class)
  public <M extends BaseMapper<T>, T> boolean update(T entity, Wrapper<T> wrapper, M baseMapper) {
    return retBool(baseMapper.update(entity, wrapper));
  }

}
