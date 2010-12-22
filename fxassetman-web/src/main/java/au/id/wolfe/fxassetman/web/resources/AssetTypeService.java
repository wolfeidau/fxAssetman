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

import java.util.Map;
import java.util.Set;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import au.id.wolfe.fxassetman.server.dao.AssetTypeDao;
import au.id.wolfe.fxassetman.server.domain.asset.AssetType;
import au.id.wolfe.fxassetman.server.util.PaginatedResult;
import au.id.wolfe.fxassetman.web.assettype.AssetTypeDTO;
import au.id.wolfe.fxassetman.web.response.PostResponse;

import com.google.common.collect.Sets;

@Path("/assetType")
@Produces("application/json")
@Transactional
public class AssetTypeService {

	private static final Logger log = LoggerFactory
			.getLogger(AssetTypeService.class);

	private static final String ENTITY_TAG = "data";

	@Autowired
	private AssetTypeDao assetTypeDao;

	@Autowired
	private Mapper mapper;

	public void setAssetTypeDao(AssetTypeDao assetTypeDao) {
		this.assetTypeDao = assetTypeDao;
	}

	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

	@GET
	public Response getAssetTypes(@QueryParam("start") int start,
			@QueryParam("limit") int limit) {

		log.info(String.format("Get List start = %d, limit = %d", start, limit));

		limit = limit != 0 ? limit : 10;

		Set<AssetTypeDTO> assetTypeDTOs = Sets.newLinkedHashSet();

		PaginatedResult<AssetType> paginatedResult = assetTypeDao.findAll(
				start, limit);

		for (AssetType assetType : paginatedResult.getResultSet()) {
			assetTypeDTOs.add(mapper.map(assetType, AssetTypeDTO.class));
		}

		// log.info(assetTypes.toString());

		PostResponse postResponse = new PostResponse();

		postResponse.setMessage("Listed asset types.");
		postResponse.setSuccess(true);
		postResponse.setResults(paginatedResult.getTotalRecords());
		postResponse.setData(assetTypeDTOs);

		return Response.ok(postResponse).build();
	}

	@PUT
	@Path("/{assetTypeId}")
	public Response updateAssetType(@PathParam("assetTypeId") Long assetTypeId,
			Map<String, AssetTypeDTO> assetType) {

		log.info("update AssetTypeId = " + assetTypeId);

		Assert.notNull(assetType.get(ENTITY_TAG));

		AssetType currentAssetType = assetTypeDao.findById(assetTypeId);

		mapper.map(assetType.get(ENTITY_TAG), currentAssetType);

		currentAssetType = assetTypeDao.update(currentAssetType);

		AssetTypeDTO updatedAssetType = mapper.map(currentAssetType,
				AssetTypeDTO.class);

		PostResponse postResponse = new PostResponse();
		postResponse.setMessage("Updated assetType with id = "
				+ currentAssetType.getId());
		postResponse.setSuccess(true);
		postResponse.setData(updatedAssetType);

		return Response.ok(postResponse).build();
	}

	@POST
	public Response setAssetType(Map<String, AssetTypeDTO> assetTypeData) {

		Assert.notNull(assetTypeData.get(ENTITY_TAG));
		Assert.hasText(assetTypeData.get(ENTITY_TAG).getName());

		log.info("Saving asset name = "
				+ assetTypeData.get(ENTITY_TAG).getName());

		AssetType newAssetType = mapper.map(assetTypeData.get(ENTITY_TAG),
				AssetType.class);

		assetTypeDao.save(newAssetType);

		AssetTypeDTO createdAssetType = mapper.map(newAssetType,
				AssetTypeDTO.class);

		PostResponse postResponse = new PostResponse();

		postResponse.setData(createdAssetType);
		postResponse.setMessage("Added assetType with id = "
				+ createdAssetType.getId());
		postResponse.setSuccess(true);

		return Response.ok(postResponse).build();
	}

	@DELETE
	@Path("/{assetTypeId}")
	public Response deleteAssetType(@PathParam("assetTypeId") Long assetTypeId) {

		assetTypeDao.removeById(assetTypeId);

		PostResponse postResponse = new PostResponse();
		postResponse.setMessage("Added assetType with id = " + assetTypeId);
		postResponse.setSuccess(true);

		return Response.ok(postResponse).build();
	}

}
