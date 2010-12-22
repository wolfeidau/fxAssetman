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

package au.id.wolfe.fxassetman.server.domain.fixtures;

import au.id.wolfe.fxassetman.server.domain.asset.Asset;
import au.id.wolfe.fxassetman.server.domain.asset.AssetType;
import au.id.wolfe.fxassetman.server.domain.company.Client;
import au.id.wolfe.fxassetman.server.domain.company.Supplier;

/**
 * Creates assets for use in unit tests.
 */
public final class AssetFixture {

    public static Asset createValidAsset(){
        
        Asset asset = new Asset();
        asset.setAssetTag("12121212121212");
        asset.setSerialNo("121212");
        asset.setSupplier(new Supplier("Sun Microsystems"));
        asset.setAssetType(new AssetType("Desktop Computer", "Desktop Computer"));
        asset.setClient(new Client("DEEWR"));
        asset.setModelName("Sun V100");
        
        return asset;
    }
}
