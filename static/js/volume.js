(function($, eucalyptus) {
  $.widget('eucalyptus.volume', $.eucalyptus.eucawidget, {
    options : { },
    tableWrapper : null,
    delDialog : null,
    // TODO: is _init() the right method to instantiate everything? 
    _init : function() {
      var thisObj = this;
      var $tmpl = $('html body').find('.templates #volumeTblTmpl').clone();
      var $wrapper = $($tmpl.render($.i18n.map));
      this.element.add($wrapper);
      var $base_table = $wrapper.find('table');
      tableWrapper = $wrapper.eucatable({
        id : 'volumes', // user of this widget should customize these options,
        base_table : $base_table,
        dt_arg : {
          "bProcessing": true,
          "sAjaxSource": "../ec2?Action=DescribeVolumes",
          "sAjaxDataProp": "results",
          "bAutoWidth" : false,
          "sPaginationType": "full_numbers",
          "sDom": '<"table_volumes_header"><"table-volume-filter">f<"clear"><"table_volumes_top">rt<"table-volumes-legend">p<"clear">',
          "aoColumns": [
            {
              "bSortable": false,
              "fnRender": function(oObj) { return '<input type="checkbox"/>' },
              "sWidth": "20px",
            },
            { "mDataProp": "id" },
            {
              "fnRender": function(oObj) { s = (oObj.aData.status == 'in-use') ? oObj.aData.attach_data.status : oObj.aData.status; return '<div class="volume-status-' + s + '">&nbsp;</div>'; },
              "sWidth": "20px",
              "bSearchable": false,
              "iDataSort": 8, // sort on hiden status column
            },
            { "mDataProp": "size" },
            { "mDataProp": "attach_data.instance_id" },
            { "mDataProp": "snapshot_id" },
            { "mDataProp": "zone" },
            // output creation time in browser format and timezone
            { "fnRender": function(oObj) { d = new Date(oObj.aData.create_time); return d.toLocaleString(); } },
            {
              "bVisible": false,
              "fnRender": function(oObj) { s = (oObj.aData.status == 'in-use') ? oObj.aData.attach_data.status : oObj.aData.status; return s; }
            }
          ]
        },
        header_title : volume_h_title,
        search_refresh : search_refresh,
        txt_create : volume_create,
        txt_found : volume_found,
        menu_text : table_menu_main_action,
        menu_actions : { delete: [table_menu_delete_action, function (args) { thisObj.deleteAction(args) } ] },
        row_click : function (args) { thisObj.handleRowClick(args); },
        context_menu : { value_column_inx: 8, build_callback: function (state) { return thisObj.buildContextMenu(state) } }
      //  td_hover_actions : { instance: [4, function (args) { thisObj.handleInstanceHover(args); }], snapshot: [5, function (args) { thisObj.handleSnapshotHover(args); }] }
      });
      tableWrapper.appendTo(this.element);

      //add filter to the table
      $tableFilter = $('div.table-volume-filter');
      $tableFilter.addClass('euca-table-filter');
      $tableFilter.append(
        $('<span>').addClass("filter-label").html(volume_filter_label),
        $('<select>').attr('id', 'volumes-selector'));

      //TODO: add more states
      attachedStates = { 'attached':1, 'attaching':1, 'detaching':1 };
      detachedStates = { 'available':1 };
      otherStates = ['creating', 'deleting', 'deleted', 'error'];

      filterOptions = ['all', 'attached', 'detached'];
      $sel = $tableFilter.find("#volumes-selector");
      for (o in filterOptions)
        $sel.append($('<option>').val(filterOptions[o]).text($.i18n.map['volume_selecter_' + filterOptions[o]]));

      $.fn.dataTableExt.afnFiltering.push(
	function( oSettings, aData, iDataIndex ) {
          // first check if this is called on a volumes table
          if (oSettings.sInstance != 'volumes')
            return true;
          selectorValue = $("#volumes-selector").val();
          switch (selectorValue) {
            case 'attached':
              return attachedStates[aData[8]] == 1;
              break;
            case 'detached':
              return detachedStates[aData[8]] == 1;
              break;
          }
          return true;
        }
      );

      // attach action
      $("#volumes-selector").change( function() { thisObj.reDrawTable() } );

      //add leged to the volumes table
      $tableLegend = $("div.table-volumes-legend");
      $tableLegend.append($('<span>').addClass('volume-legend').html(volume_legend));
      //TODO: this might not work in all browsers
      statuses = [].concat(Object.keys(attachedStates),Object.keys(detachedStates), otherStates);
      for (s in statuses)
        $tableLegend.append($('<span>').addClass('volume-status-legend').addClass('volume-status-' + statuses[s]).html($.i18n.map['volume_state_' + statuses[s]]));

      $tmpl = $('html body').find('.templates #volumeDelDlgTmpl').clone();
      $del_dialog = $($tmpl.render($.i18n.map));

      this.delDialog = $del_dialog.eucadialog({
         id: 'volumes-delete',
         title: volume_dialog_del_title,
         buttons: {
           'delete': {text: volume_dialog_del_btn, click: function() { thisObj._deleteSelectedVolumes(); $del_dialog.dialog("close");}},
           'cancel': {text: volume_dialog_cancel_btn, focus:true, click: function() { $del_dialog.dialog("close");}} 
         }
       });

      var createButtonId = 'volume-add-btn';
      $tmpl = $('html body').find('.templates #volumeAddDlgTmpl').clone();
      $add_dialog = $($tmpl.render($.i18n.map));
/*
      // add custom event handler to dialog elements
      // when calling eucadialog, the buttons should have domid to attach the specific domid that's used by event handler written here 
      $add_dialog.find('#key-name').keypress( function(e){
        var $createButton = $('#'+createButtonId);
        if( e.which === RETURN_KEY_CODE || e.which === RETURN_MAC_KEY_CODE ) 
           $createButton.trigger('click');
        else if ( e.which === 0 ) {
        } else if ( e.which === BACKSPACE_KEY_CODE && $(this).val().length == 1 ) 
           $createButton.prop("disabled", true).addClass("ui-state-disabled");
        else if ( $(this).val().length == 0 )
           $createButton.prop("disabled", true).addClass("ui-state-disabled");
        else 
           $createButton.prop("disabled", false).removeClass("ui-state-disabled");
      });
*/
      $add_dialog.eucadialog({
        id: 'volumes-add',
        title: volume_dialog_add_title,
        buttons: { 
        // e.g., add : { domid: keys-add-btn, text: "Add new key", disabled: true, focus: true, click : function() { }, keypress : function() { }, ...} 
        'create': { domid: createButtonId, text: volume_dialog_create_btn, disabled: true,  click: function() { $add_dialog.dialog("close"); }},
        'cancel': {text: volume_dialog_cancel_btn, focus:true, click: function() { $add_dialog.dialog("close");}}
      }});
    },

    _create : function() { 
    },

    _destroy : function() {
    },

    buildContextMenu : function(state) {
      //TODO: update it with more states
      switch (state) {
        case 'available':
          return {
            "attach": { "name": volume_con_menu_attach },
            "create_snapshot": { "name": volume_con_menu_create_snapshot },
            "delete": { "name": volume_con_menu_delete }
          }
        case 'attached':
          return {
            "detach": { "name": volume_con_menu_detach },
            "force_detach": { "name": volume_con_menu_force_detach },
            "create_snapshot": { "name": volume_con_menu_create_snapshot },
            "delete": { "name": "Delete" }
          }
        case 'attaching':
          return {
            "attach": { "name": volume_con_menu_attach },
            "detach": { "name": volume_con_menu_detach },
            "force_detach": { "name": volume_con_menu_force_detach },
            "create_snapshot": { "name": volume_con_menu_create_snapshot },
            "delete": { "name": volume_con_menu_delete }
          }
        default:
          return {
            "delete": { "name": volume_con_menu_delete }
          }
      }
    },
/*
    handleInstanceHover : function(e) {
      switch(e.type) {
        case 'mouseleave':
          $(e.currentTarget).removeClass("hoverCell");
          break;
        case 'mouseenter':
          $(e.currentTarget).addClass("hoverCell");
          break;
      }
    },

    handleSnapshotHover : function(e) {
      switch(e.type) {
        case 'mouseleave':
          $(e.currentTarget).removeClass("hoverCell");
          $(e.currentTarget).off('click');
          break;
        case 'mouseenter':
          $(e.currentTarget).addClass("hoverCell");
          break;
      }
    },
*/
    reDrawTable : function() {
      tableWrapper.eucatable('reDrawTable');
    },

    handleRowClick : function(args) {
      count = tableWrapper.eucatable('countSelectedRows');
      if ( count == 0 )
        // disable menu
        tableWrapper.eucatable('deactivateMenu');
      else
        // enable delete menu
        tableWrapper.eucatable('activateMenu');
    },

/*
    _addKeyPair : function(keyName) {
      $.ajax({
        type:"GET",
        url:"/ec2?type=key&Action=CreateKeyPair",
        data:"_xsrf="+$.cookie('_xsrf') + "&KeyName=" + keyName,
        dataType:"json",
        async:"false",
        success:
        function(data, textStatus, jqXHR){
          if (data.results && data.results.material) {
            $.generateFile({
              filename    : keyName,
              content     : data.results.material,
              script      : '/support?Action=DownloadFile&_xsrf=' + $.cookie('_xsrf')
            });
            successNotification(keypair_create_success + ' ' + keyName);
            tableWrapper.eucatable('refreshTable');
          } else {
            errorNotification(keypair_create_error + ' ' + keyName);
          }
        },
        error:
        function(jqXHR, textStatus, errorThrown){
          errorNotification(keypair_delete_error + ' ' + keyName);
        }
      });
    },
*/
    _deleteSelectedVolumes : function () {
      var rowsToDelete = tableWrapper.eucatable('getAllSelectedRows');
      for ( i = 0; i<rowsToDelete.length; i++ ) {
        var volumeId = rowsToDelete[i];
        $.ajax({
          type:"GET",
          url:"/ec2?type=key&Action=DeleteVolume&VolumeId=" + volumeId,
          data:"_xsrf="+$.cookie('_xsrf'),
          dataType:"json",
          async:"true",
          success:
          (function(volumeId) {
            return function(data, textStatus, jqXHR){
              if ( data.results && data.results == true ) {
                successNotification(volume_delete_success + ' ' + volumeId);
                tableWrapper.eucatable('refreshTable');
              } else {
                errorNotification(volume_delete_error + ' ' + volumeId);
              }
           }
          })(volumeId),
          error:
          (function(volumeId) {
            return function(jqXHR, textStatus, errorThrown){
              errorNotification(volume_delete_error + ' ' + volumeId);
            }
          })(volumeId)
        });
      }
    },

    close: function() {
      this._super('close');
    },

    deleteAction : function(rowsToDelete) {
      //TODO: add hide menu

      if ( rowsToDelete.length > 0 ) {
        // show delete dialog box
        $deleteNames = this.delDialog.find("span.delete-names")
        $deleteNames.html('');
        for ( i = 0; i<rowsToDelete.length; i++ ) {
          t = escapeHTML(rowsToDelete[i]);
          $deleteNames.append(t).append("<br/>");
        }
        this.delDialog.dialog('open');
      }
    }

  });
})(jQuery,
   window.eucalyptus ? window.eucalyptus : window.eucalyptus = {});
