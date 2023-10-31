package com.edusys.dao;

import java.util.List;

public abstract class EduSysDAO<EntityType, KeyType> {
	abstract public void insert(EntityType entity);
	abstract public void update(EntityType entity);
	abstract public void delete(KeyType id);
	abstract public EntityType selectById(KeyType id);
	abstract public List<EntityType> selectAll();
	abstract protected List<EntityType> selectBySQL(String sql, Object... args);
}
