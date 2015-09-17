function loadURL(url, frm) {
   if (frm) {

       if (frm.showProgress) {
           frm.showProgress();
       }

       frm.location.href = url;
   } else {

       showProgress();

       window.location.href = url;
   }
}
function showProgress(text, title) {
   if (!text) {
       text = "Processing, Please wait ...";
   }
   if (!title) {
       title = "Trimble Identity";
   }
   $.messager.progress({
       title:title,
       msg:text
   });
}
function hideProgress() {
   $.messager.progress('close');
}
function showWarning(msg, fn, title) {
   if (!title) {
       title = "Trimble Identity";
   }
   if (!msg) {
       msg = "{{Please enter message}}";
   }
   $.messager.alert(title, msg, 'warning', fn);
}
function showAlert(msg, fn, title) {
   if (!title) {
       title = "Trimble Identity";
   }
   if (!msg) {
       msg = "{{Please enter message}}";
   }
   $.messager.alert(title, msg, "info", fn);
}
function showConfirmDialog(msg, fn, title) {
   if (!title) {
       title = "Trimble Identity";
   }
   if (!msg) {
       msg = "{{Please enter message}}";
   }

   $.messager.confirm(title, msg, fn);
}

/**
* Returns the save errors an an Array
* @param saveResponse
*/
function getSaveErrors(saveResponse) {
   var dataJSON = eval('(' + saveResponse + ')');
   var errors = [];
   if (!dataJSON.hasErrors) {
       return errors;
   }
   $.each(dataJSON.errorList, function (index, err) {
           errors[index] = err;
       }
   );

   return errors;
}
/**
* Returns a formatted save error message to display in a Alert.
* Returns null if no errors exist
*
* @param saveResponse
* @param header
* @param footer
*/
function getSaveErrorMessage(saveResponse, header, footer) {

   var errors = getSaveErrors(saveResponse);
   if (errors.length == 0) {
       return null;
   }
   var message = "<div class='save-errors'>" + header + "<ul>";
   $.each(errors, function (index, err) {
           message += "<li>" + err + "</li>";
       }
   );
   message += "</ul>" + footer + "</div>";

   return message;
}

function showMessageDialogBoxInSignup(response){
	var dataJSON = $.parseJSON(response);

	if (typeof dataJSON.hasErrors === "undefined") {
	   	dataJSON = $.parseJSON(dataJSON);
	}

	if (dataJSON.hasErrors == true) {
	    var msg = "";
        for (var i = 0; i < dataJSON.errorList.length; i++) {
            if (i != 0) {
                msg += '<br>';
            }
            msg += dataJSON.errorList[i];
        }

        BootstrapDialog.show({
            type: BootstrapDialog.TYPE_DANGER,
            title: 'Error',
            message: msg,
            buttons: [{
                id: 'btn-ok',
                icon: 'glyphicon glyphicon-check',
                label: 'OK',
                cssClass: 'btn-danger',
                autospin: false,
                action: function(dialogRef){
                    dialogRef.close();
                }
            }]
        });
    } else {
        var msg = 'Successfully performed the Action';
        if (dataJSON.successMsg != "") {
            msg = dataJSON.successMsg;
        }

        BootstrapDialog.show({
            type: BootstrapDialog.TYPE_PRIMARY,
            title: 'Success',
            message: msg,
            buttons: [{
                id: 'btn-ok',
                icon: 'glyphicon glyphicon-check',
                label: 'OK',
                cssClass: 'btn-primary',
                autospin: false,
                action: function (dialogRef) {
                    window.location.href = "signupaccountredirect";
                    dialogRef.close();
                }
            }]
        });
    }
}

function showMessageDialogBox(response){
    var dataJSON = $.parseJSON(response);
	
    if (typeof dataJSON.hasErrors === "undefined") {
        dataJSON = $.parseJSON(dataJSON);
    }

    if (dataJSON.hasErrors == true) {
        var msg = "";
        for (var i = 0; i < dataJSON.errorList.length; i++) {
            if (i != 0) {
                msg += '<br>';
            }
            msg += dataJSON.errorList[i];
        }

        BootstrapDialog.show({
            type: BootstrapDialog.TYPE_DANGER,
            title: 'Error',
            message: msg,
            buttons: [{
                id: 'btn-ok',
                icon: 'glyphicon glyphicon-check',
                label: 'OK',
                cssClass: 'btn-danger',
                autospin: false,
                action: function(dialogRef){
                    dialogRef.close();
                }
            }]
        });
    } else {
        var msg = 'Successfully performed the Action';
        if (dataJSON.successMsg != "") {
            msg = dataJSON.successMsg;
        }

        BootstrapDialog.show({
            type: BootstrapDialog.TYPE_PRIMARY,
            title: 'Success',
            message: msg,
            buttons: [{
                id: 'btn-ok',
                icon: 'glyphicon glyphicon-check',
                label: 'OK',
                cssClass: 'btn-primary',
                autospin: false,
                action: function (dialogRef) {
                    dialogRef.close();
                }
            }]
        });
    }
}

function showMessageDialogBoxWithRefresh(response){
	var dataJSON = $.parseJSON(response);

    if (typeof dataJSON.hasErrors === "undefined") {
        dataJSON = $.parseJSON(dataJSON);
    }

    if (dataJSON.hasErrors == true) {
        var msg = "";
        for (var i = 0; i < dataJSON.errorList.length; i++) {
            if (i != 0) {
                msg += '<br>';
            }
            msg += dataJSON.errorList[i];
        }

        BootstrapDialog.show({
            type: BootstrapDialog.TYPE_DANGER,
            title: 'Error',
            message: msg,
            buttons: [{
                id: 'btn-ok',
                icon: 'glyphicon glyphicon-check',
                label: 'OK',
                cssClass: 'btn-danger',
                autospin: false,
                action: function(dialogRef){
                    dialogRef.close();
                }
            }]
        });
    } else {
        var msg = 'Successfully performed the Action';

        if (dataJSON.successMsg != "") {
            msg = dataJSON.successMsg;
        }

        BootstrapDialog.show({
            type: BootstrapDialog.TYPE_PRIMARY,
            title: 'Success',
            message: msg,
            buttons: [{
                id: 'btn-ok',
                icon: 'glyphicon glyphicon-check',
                label: 'OK',
                cssClass: 'btn-primary',
                autospin: false,
                action: function (dialogRef){
                    dialogRef.close();
                    location.reload();
                }
            }]
        });
    }
}

function showContextMenuAppTypes( ids, names){

     // var ids = new Array (1, 2);
      //var names = new Array ("Applet", "Desfire");

       var kcmenu = $('<div id="menuapptypelist" style="width:120px;"></div>').appendTo('body');

       if (ids.length != 0) {
           for (var i = 0; i < ids.length ; i++) {
               $('<div id="' +ids[i] + '" iconCls="icon-add"/>').html(names[i]).appendTo(kcmenu);
           }
       }

       $('<div iconCls="" class="menu-sep"/>').html('').appendTo(kcmenu);

       kcmenu.menu({
           menu:'#menuapptypelist'
       });

       kcmenu.menu({
           onClick:function (item) {
                   loadURL("add" + item.id);
          }
       });

}
/*
* jQuery File Upload Plugin JS Example 6.7
* https://github.com/blueimp/jQuery-File-Upload
*
* Copyright 2010, Sebastian Tschan
* https://blueimp.net
*
* Licensed under the MIT license:
* http://www.opensource.org/licenses/MIT
*/

/*jslint nomen: true, unparam: true, regexp: true */
/*global $, window, document */
