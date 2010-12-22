/*
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

// Application instance.
var App = new Ext.App({});

// Create a standard HttpProxy instance.
var proxy = new Ext.data.HttpProxy({
	url : '/fxassetman-web/services/assetType'
});

// Typical JsonReader. Notice additional meta-data parameters for defining the
// core
// attributes of your JSON response.
var reader = new Ext.data.JsonReader({
	totalProperty : 'results',
	successProperty : 'success',
	idProperty : 'id',
	root : 'data',
	messageProperty: 'message'
},[ {
	name : 'id'
},{
	name : 'name',
	allowBlank : false
}, {
	name : 'description',
	allowBlank : true
}, {
	name : 'dateUpdated'
}
, {
	name : 'dateAdded'
}
]);

// The new DataWriter component.
var writer = new Ext.data.JsonWriter({
	encode : false
});

// Typical Store collecting the Proxy, Reader and Writer together.
var store = new Ext.data.Store({
	id : 'assetType',
	restful : true, 	// <-- This Store is RESTful
	proxy : proxy,
	reader : reader,
	writer : writer 	// <-- plug a DataWriter into the store just as you
});

// //
// ***New*** centralized listening of DataProxy events "beforewrite", "write"
// and "writeexception"
// upon Ext.data.DataProxy class. This is handy for centralizing user-feedback
// messaging into one place rather than
// attaching listeners to EACH Store.
//
// Listen to all DataProxy beforewrite events
//
Ext.data.DataProxy.addListener('beforewrite', function(proxy, action) {
	App.setAlert(App.STATUS_NOTICE, "Before " + action);
});

// //
// all write events
//
Ext.data.DataProxy.addListener('write', function(proxy, action, result, res, rs) {
	App.setAlert(true, action + ':' + res.message);
});

// //
// all exception events
//
Ext.data.DataProxy.addListener('exception', function(proxy, type, action, options, res) {
 App.setAlert(false, "Something bad happend while executing " + action);
});

// Grid columns
var assetTypeColumns = [ {
	header : "ID",
	sortable : true,
	dataIndex : 'id',
},{
	header : "Name",
	// width : 100,
	sortable : true,
	dataIndex : 'name',
	editor : new Ext.form.TextField({})
}, {
	header : "Description",
	// width : 150,
	sortable : true,
	dataIndex : 'description',
	editor : new Ext.form.TextField({})
}, {
	header : "Date Updated",
	sortable : true,
	dataIndex : 'dateUpdated',
}, {
	header : "Date Added",
	sortable : true,
	dataIndex : 'dateAdded',
} 
];

// Load the store immediately
store.load();

Ext.onReady(function() {
	Ext.QuickTips.init();

	// We'll use the new RowEditor.
	var editor = new Ext.ux.grid.RowEditor({
		saveText : 'Update'
	});

	// Create a typical GridPanel with RowEditor plug-in
	var userGrid = new Ext.grid.GridPanel({
		renderTo : 'assettype-grid',
		iconCls : 'icon-grid',
		frame : true,
		border : true,
		title : 'Asset Types',
		height : 330,
		width : 800, 
		store : store, 
		loadMask : true,
		stripeRows : true,
		plugins : [ editor ],
		columns : assetTypeColumns,
		tbar : [ {
			text : 'Add',
			iconCls : 'silk-add',
			handler : onAdd
		}, '-', {
			text : 'Delete',
			iconCls : 'silk-delete',
			handler : onDelete
		}, '-' ],
		bbar: new Ext.PagingToolbar ({
			pageSize: 10,
			store: store,
			displayInfo: true
		}),
		viewConfig : {
			forceFit : true
		}
	});

	/**
	 * onAdd factory method
	 */
	function onAdd(btn, ev) {
		var at = new userGrid.store.recordType({
			name : '',
			description : ''
		});
		editor.stopEditing();
		userGrid.store.insert(0, at);
		editor.startEditing(0);
	}
	/**
	 * onDelete
	 */
	function onDelete() {
		var rec = userGrid.getSelectionModel().getSelected();
		if (!rec) {
			return false;
		}
		userGrid.store.remove(rec);
	}

});
