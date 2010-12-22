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

package au.id.wolfe.fxassetman.server.dao;

import java.io.Serializable;
import java.util.Set;

import au.id.wolfe.fxassetman.server.util.PaginatedResult;

/**
 * Generic data access object for Entities.
 */
public interface GenericDao <T, PK extends Serializable> {

	/**
	 * Update an existing entity.
	 * @param entity Existing Entity to update.
	 */
	T update(T entity);
	
    /**
     * Save a newly created entity.
     * 
     * @param entity New Entity 
     */
    void save(T entity);
    
    /**
     * Remove an Entity by it's id.
     * 
     * @param entityId The id of the entity to remove.
     */
    void removeById(PK entityId);
    
    /**
     * Locate an Entity by it's id field.
     * 
     * @param entityId The id of the entity to search for.
     * @return The entity found.
     */
    T findById(PK entityId);
    
    /**
     * Find all Entities used for pagination.
     * 
     * @param start The start index.
     * @param limit The maximum results to return from the supplied index.
     * @return A set of Entities.
     */
    PaginatedResult<T> findAll(int start, int limit);
    
    /**
     * Find all Entities.
     * 
     * @return A set containing all Entities.
     */
    Set<T> findAll();
	
	
}
