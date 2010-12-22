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

import java.util.Set;

import au.id.wolfe.fxassetman.server.domain.asset.Asset;

/**
 * Data access object for Assets.
 */
public interface AssetDao {

    /**
     * Save a newly created asset.
     * 
     * @param asset New Asset 
     */
    void save(Asset asset);
    
    /**
     * Remove an Asset by it's id.
     * 
     * @param assetId The id of the asset to remove.
     */
    void removeById(Long assetId);
    
    /**
     * Locate an Asset by it's id field.
     * 
     * @param assetId The id of the asset to search for.
     * @return The asset found.
     */
    Asset findById(Long assetId);
    
    /**
     * Find all Assets.
     * 
     * @return A set containing all Assets.
     */
    Set<Asset> findAll();
}
