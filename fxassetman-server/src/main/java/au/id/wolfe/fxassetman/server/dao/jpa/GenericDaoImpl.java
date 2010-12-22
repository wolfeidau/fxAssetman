/**
 *
 * Copyright (C) 2010 markw <mark@wolfe.id.au>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package au.id.wolfe.fxassetman.server.dao.jpa;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import au.id.wolfe.fxassetman.server.dao.GenericDao;
import au.id.wolfe.fxassetman.server.util.PaginatedResult;

import com.google.common.collect.Sets;

public abstract class GenericDaoImpl<T, PK extends Serializable> implements
		GenericDao<T, PK> {

	private Class<T> type;

	public GenericDaoImpl(Class<T> type) {
		this.type = type;
	}

	@PersistenceContext
	private EntityManager entityManager;

	
	/**
	 * Get the entity manager associated with this DAO.
	 * @return Entity manager instance.
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * @param entityManager
	 *            the entityManager to set
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public T update(T entity){
		return entityManager.merge(entity);
	}

	@Override
	public void save(T entity) {
		entityManager.persist(entity);

	}

	@Override
	public void removeById(PK entityId) {
		entityManager
				.createQuery(
						"DELETE FROM " + type.getName()
								+ " e WHERE e.id = :entityId")
				.setParameter("entityId", entityId).executeUpdate();

	}

	@Override
	@SuppressWarnings("unchecked")
	public T findById(PK entityId) {
		return (T) entityManager
				.createQuery(
						"SELECT e FROM " + type.getName()
								+ " e WHERE e.id = :entityId")
				.setParameter("entityId", entityId).getSingleResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public PaginatedResult<T> findAll(int start, int limit) {
		Set<T> resultSet = Sets.newLinkedHashSet(entityManager.createQuery(
				"SELECT e FROM " + type.getName() + " e")
				.setFirstResult(start)
				.setMaxResults(limit)
				.getResultList());
		
		PaginatedResult<T> paginatedResult = new PaginatedResult<T>(type);
		
		Long totalCount = (Long)entityManager.createQuery("select count(o) from " + type.getName() + " as o").getSingleResult();

		paginatedResult.setTotalRecords(totalCount);
		paginatedResult.setResultSet(resultSet);
		
		return paginatedResult;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Set<T> findAll() {
		return Sets.newLinkedHashSet(entityManager.createQuery(
				"SELECT e FROM " + type.getName() + " e").getResultList());
	}

}
