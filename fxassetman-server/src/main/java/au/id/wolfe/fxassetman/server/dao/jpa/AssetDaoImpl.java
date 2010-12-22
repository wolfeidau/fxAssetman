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

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import au.id.wolfe.fxassetman.server.dao.AssetDao;
import au.id.wolfe.fxassetman.server.domain.asset.Asset;

import com.google.common.collect.Sets;

/**
 * The implementation of AssetDao using JPA.
 */
@Repository
public class AssetDaoImpl implements AssetDao {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * @param entityManager
     *            the entityManager to set
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * @return the entityManager
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public void save(Asset asset) {
        entityManager.persist(asset);

    }

    @Override
    public void removeById(Long assetId) {
        entityManager.createQuery("DELETE FROM Asset a WHERE a.id = :assetId")
                .setParameter("assetId", assetId).executeUpdate();

    }

    @Override
    public Asset findById(Long assetId) {
        return (Asset) entityManager
                .createQuery("SELECT a FROM Asset a WHERE a.id = :assetId")
                .setParameter("assetId", assetId).getSingleResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<Asset> findAll() {
        return Sets.newLinkedHashSet(entityManager.createQuery(
                "SELECT a FROM Asset a").getResultList());
    }

}
