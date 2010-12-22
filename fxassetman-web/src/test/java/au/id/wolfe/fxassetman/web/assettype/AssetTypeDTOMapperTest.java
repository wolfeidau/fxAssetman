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

package au.id.wolfe.fxassetman.web.assettype;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.Date;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.id.wolfe.fxassetman.server.domain.asset.AssetType;

/**
 * Tests mapping from AssetType to AssetTypeDTO and back again.
 */
public class AssetTypeDTOMapperTest {

	Logger log = LoggerFactory.getLogger(AssetTypeDTOMapperTest.class);
	
	Mapper mapper;
	
	@Before
	public void setup(){
		mapper = new DozerBeanMapper(Arrays.asList("fxassetman-mappings.xml"));
	}
	

	@Test
	public void testMappingfromAssetTypeToAssetTypeDTO(){

		AssetType assetTypeOne = createValidAssetType();
		
		assetTypeOne.setDateUpdated(new Date());
		assetTypeOne.setDateAdded(new Date());
		
		log.info(assetTypeOne.toString());

		AssetTypeDTO assetTypeDTO = mapper.map(assetTypeOne, AssetTypeDTO.class);
		
		log.info(assetTypeDTO.toString());
		
		assertEquals("Desktop PC", assetTypeDTO.getName());
		assertEquals("Personal computer used for desktop applications", assetTypeDTO.getDescription());
		assertNotNull(assetTypeDTO.getDateUpdated());
		assertNotNull(assetTypeDTO.getDateAdded());
	}		

	@Test
	public void testMappingfromAssetTypeDTOToAssetType(){
		
		AssetTypeDTO assetTypeDTO = new AssetTypeDTO();
		
		assetTypeDTO.setName("Desktop PC");
		assetTypeDTO.setDescription("Personal computer used for desktop applications");
		assetTypeDTO.setDateUpdated("2010");
		assetTypeDTO.setDateAdded("2010");
		
		AssetType assetTypeTwo = mapper.map(assetTypeDTO, AssetType.class);
		
		assertNull(assetTypeTwo.getDateUpdated());
		assertNull(assetTypeTwo.getDateAdded());
		
	}
	
	public static AssetType createValidAssetType(){
		AssetType assetType = new AssetType();
		
		assetType.setName("Desktop PC");
		assetType.setDescription("Personal computer used for desktop applications");
		
		return assetType;
	}
	
}
