<%@ include file="../Home.jsp" %>
 <script type="text/javascript">
$(function () {
    $("#dvSource img").draggable({
        revert: "invalid",
        refreshPositions: true,
        drag: function (event, ui) {
            ui.helper.addClass("draggable");
        },
        stop: function (event, ui) {
            ui.helper.removeClass("draggable");
            var image = this.src.split("/")[this.src.split("/").length - 1];
            if ($.ui.ddmanager.drop(ui.helper.data("draggable"), event)) {
                alert(image + " dropped1.");
            $('#myModal').on('hide.bs.modal', function (e) {
                    alert('event fired')
                });

                alert("test");
            }
            else {
                alert(image + " not dropped.");
            }
        }
    });
    $("#dvDest").droppable({
        drop: function (event, ui) {
            if ($("#dvDest img").length == 0) {
                $("#dvDest").html("");
            }
            ui.draggable.addClass("dropped");
            $("#dvDest").append(ui.draggable);
        }
    });

        $("#dvDest img").draggable({
            revert: "invalid",
            refreshPositions: true,
            drag: function (event, ui) {
                ui.helper.addClass("draggable");
            },
            stop: function (event, ui) {
                ui.helper.removeClass("draggable");
                var image = this.src.split("/")[this.src.split("/").length - 1];
                if ($.ui.ddmanager.drop(ui.helper.data("draggable"), event)) {
                    alert(image + " dropped.");
                    $('#myModal').modal('show');
                }
                else {
                    alert(image + " not dropped.");
                }
            }
        });

        $("#dvSource").droppable({
            drop: function (event, ui) {
                if ($("#dvSource img").length == 0) {
                    $("#dvSource").html("");
                }
                ui.draggable.addClass("dropped");
                $("#dvSource").append(ui.draggable);
            }
        });
});
</script>
   <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
   <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
   <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
   <script> $(document).ready(function() { $("#datepicker").datepicker({ dateFormat: 'yy-mm-dd' }).val();; }); </script>
 <div class="container">
 <div class="col-sm-12">
 <br/>
<div class="jumbotron" id="table1">


<style type="text/css">

img
{
    height: 60px;
    width: 60px;
    margin: 2px;
}
.draggable
{
    filter: alpha(opacity=60);
    opacity: 0.6;
}
.dropped
{
    position: static !important;
}
#dvSource, #dvDest
{
    border: 5px solid #ccc;
    padding: 5px;
    min-height:200px;
    width: 430px;
}
</style>
<h5>Reservation For
<input id="datepicker" readonly="true" value="${dateselect}"/>
<select id="sel" onchange="" >
<option value="0" selected>Morning</option>
<option value="1">Day</option>
<option value="2">Evening</option>
<option value="3">Night</option>
</select> </h5>
<h4>Available Table</h4>
<div id="dvSource">
    <img alt="" src="resources/image/table1.jpg" />
    <img alt="" src="resources/image/table2.jpg" />
    <img alt="" src="resources/image/table3.jpg" />
    <img alt="" src="resources/image/table5.jpg" />
    <img alt="" src="resources/image/table6.jpg" />
    <img alt="" src="resources/image/table7.jpg" />
    <img alt="" src="resources/image/table.jpg" />

</div>
<hr />
<h4>Reserve Table</h4>
<div id="dvDest">
    Drop here
</div>


</div>
</div>
</div>
</div>
<%@ include file="../Login.jsp" %>
  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-md">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Add Table</h4>
        </div>
        <div class="modal-body">


        </div>
        <div class="modal-footer">
         <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>
</body>
</html>