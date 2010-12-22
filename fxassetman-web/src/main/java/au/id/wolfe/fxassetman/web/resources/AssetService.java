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

package au.id.wolfe.fxassetman.web.resources;

import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import au.id.wolfe.fxassetman.server.dao.AssetDao;
import au.id.wolfe.fxassetman.server.domain.asset.Asset;
import au.id.wolfe.fxassetman.web.asset.AssetDTO;

import com.google.common.collect.Sets;

@Path("/asset")
@Produces("application/xml")
public class AssetService {

	@Autowired
	private AssetDao assetDao;
	
	@Autowired
	private Mapper mapper;

	public void setAssetDao(AssetDao assetDao) {
		this.assetDao = assetDao;
	}
	
	public void setMapper(Mapper mapper){
		this.mapper = mapper;
	}

	public AssetService() {
	}

	@GET
	public Set<AssetDTO> getAssets() {

		Set<AssetDTO> assetsDTO = Sets.newLinkedHashSet();
		
		for (Asset asset : assetDao.findAll()) {
			assetsDTO.add(mapper.map(asset, AssetDTO.class));
		}
		
		return assetsDTO;
	}

	@GET
	@Path("/{assetId}")
	public AssetDTO getAsset(@PathParam("assetId") Long assetId) {
		return mapper.map(assetDao.findById(assetId), AssetDTO.class);
	}
}
